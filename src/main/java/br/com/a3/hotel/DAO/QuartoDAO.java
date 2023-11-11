package br.com.a3.hotel.DAO;

import br.com.a3.hotel.model.*;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuartoDAO {
    static Connection conn;


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
