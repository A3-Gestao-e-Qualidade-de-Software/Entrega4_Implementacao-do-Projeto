package br.com.a3.hotel.DAO;

import br.com.a3.hotel.model.*;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que gerencia operações relacionadas aos quartos no banco de dados.
 */

public class QuartoDAO {
    static Connection conn;

   /**
     * Lista todos os quartos cadastrados no banco de dados.
     *
     * @return Uma lista de QuartoModel com os dados de todos os quartos cadastrados.
     * @throws SQLException             se ocorrer um erro durante a execução da operação no banco de dados.
     * @throws ClassNotFoundException se a classe do driver JDBC não for encontrada.
     */

    public List<QuartoModel> listarQuartos() throws SQLException, ClassNotFoundException {
        new ConexaoDAO();
        conn = ConexaoDAO.conectaBD();

        List<QuartoModel> listaQuartos = new ArrayList<>();

        ResultSet rs = null;
        PreparedStatement pstm = null;
        try {
            String sql =
                    "SELECT " +
                            "ID_Quarto, Num_Quarto, Andar_Quarto, Tipo_Quarto, Preco_Noite, Status_Ocupacao, Descricao " +
                            "FROM Quarto";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                QuartoModel quartoModel = new QuartoModel(
                        rs.getInt("ID_Quarto"),
                        rs.getInt("Num_Quarto"),
                        rs.getInt("Andar_Quarto"),
                        rs.getString("Tipo_Quarto"),
                        rs.getDouble("Preco_Noite"),
                        rs.getString("Status_Ocupacao"),
                        rs.getString("Descricao")
                );
                listaQuartos.add(quartoModel);
            }
            return listaQuartos;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "QuartoDAO: " + erro.getMessage());
            return null;
        }


    }

    /**
     * Lista os quartos disponíveis em um intervalo de datas especificado.
     *
     * @param checkIn  Data de check-in para verificar disponibilidade.
     * @param checkOut Data de check-out para verificar disponibilidade.
     * @return Uma lista de QuartoModel com os quartos disponíveis no intervalo de datas.
     * @throws SQLException             se ocorrer um erro durante a execução da operação no banco de dados.
     * @throws ClassNotFoundException se a classe do driver JDBC não for encontrada.
     */

    public static List<QuartoModel> listarQuartosDisponiveis(String checkIn, String checkOut) throws SQLException, ClassNotFoundException {
        new ConexaoDAO();
        conn = ConexaoDAO.conectaBD();

        List<QuartoModel> listaQuartos = new ArrayList<>();

        ResultSet rs = null;
        PreparedStatement pstm = null;
        try {
            String sql =
                    "SELECT " +
                            "ID_Quarto, Num_Quarto, Andar_Quarto, Tipo_Quarto, Preco_Noite, Status_Ocupacao, Descricao " +
                            "FROM Quarto " +
                            "WHERE Quarto.ID_Quarto NOT IN(" +
                            "    SELECT " +
                            "        r.ID_Quarto " +
                            "    FROM Reservas r " +
                            "    WHERE r.DT_Check_IN <= '" + checkOut + "' AND r.DT_Check_OUT >= '" + checkIn +"');";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                QuartoModel quartoModel = new QuartoModel(
                        rs.getInt("ID_Quarto"),
                        rs.getInt("Num_Quarto"),
                        rs.getInt("Andar_Quarto"),
                        rs.getString("Tipo_Quarto"),
                        rs.getDouble("Preco_Noite"),
                        rs.getString("Status_Ocupacao"),
                        rs.getString("Descricao")
                );
                listaQuartos.add(quartoModel);
            }
            return listaQuartos;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "QuartoDAO: " + erro.getMessage());
            System.out.println(erro);
            return null;
        }
    }

    /**
     * Edita um campo específico de um quarto no banco de dados.
     *
     * @param idQuarto   ID do quarto a ser editado.
     * @param campo      Campo a ser editado (por exemplo, Num_Quarto, Andar_Quarto, etc.).
     * @param novoValor  Novo valor para o campo a ser editado.
     * @throws SQLException             se ocorrer um erro durante a execução da operação no banco de dados.
     * @throws ClassNotFoundException se a classe do driver JDBC não for encontrada.
     */

    public void editarQuarto(int idQuarto, String campo, String novoValor) throws SQLException, ClassNotFoundException {
        conn = ConexaoDAO.conectaBD();
        PreparedStatement pstm = null;

        try {
            String sql = "UPDATE Quartos SET " + campo + " = ? WHERE ID_Quarto = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, novoValor);
            pstm.setInt(2, idQuarto);

            int rowsAffected = pstm.executeUpdate();
            if (rowsAffected > 0) {
                // Trate a edição bem-sucedida
            } else {
                // Trate a edição mal-sucedida
            }
        } catch (SQLException erro) {
            // Trate o erro apropriadamente
        } finally {
            // Feche recursos
        }
    }
}
