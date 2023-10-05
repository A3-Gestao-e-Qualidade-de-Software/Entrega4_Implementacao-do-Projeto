package DAO;

import Controller.HospedesController;
import Model.HospedesModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HospedesDAO {
    static Connection conn;

    public boolean cadastrarHospede(HospedesModel objHospedesModel) throws SQLException, ClassNotFoundException {
        new ConexaoDAO();
        conn = ConexaoDAO.conectaBD();

        try {
            String sql =
                    "INSERT INTO" +
                    " Hospedes (Nome, Sobrenome, DT_Nascimento, CPF, Genero, Endereco, Telefone, Email)" +
                    " VALUES (?, ?, STR_TO_DATE(? , '%d/%m/%Y'), ?, ?, ?, ?, ?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, objHospedesModel.getNome());
            pstm.setString(2, objHospedesModel.getSobrenome());
            pstm.setString(3, objHospedesModel.getDtNascimento());
            pstm.setString(4, objHospedesModel.getCpf());
            pstm.setString(5, objHospedesModel.getGenero());
            pstm.setString(6, objHospedesModel.getEndereco());
            pstm.setString(7, objHospedesModel.getTelefone());
            pstm.setString(8, objHospedesModel.getEmail());

            int rowsaffected = pstm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Hospede cadastrado com sucesso!");
            return rowsaffected > 0;
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "HospedesDAO: " + erro.getMessage());
            HospedesController h_ctrl = new HospedesController();
            h_ctrl.cadastrarHospede();
            return false;
        }
    }



    public static List<HospedesModel> listarHospedes() throws SQLException, ClassNotFoundException {
        new ConexaoDAO();
        conn = ConexaoDAO.conectaBD();

        List<HospedesModel> listaHospedes = new ArrayList<>();

        ResultSet rs = null;
        PreparedStatement pstm = null;
        try {
            String sql =
                    "SELECT " +
                            "Nome, Sobrenome, DATE_FORMAT(DT_Nascimento, '%d/%m/%Y') AS DT_Nascimento, " +
                            "CPF, Genero, Endereco, Telefone, Email " +
                            "FROM Hospedes";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String generoChar = rs.getString("Genero");
                String genero = generoChar.toUpperCase();

                HospedesModel hospedesModel = new HospedesModel(
                        rs.getString("Nome"),
                        rs.getString("Sobrenome"),
                        rs.getString("DT_Nascimento"),
                        rs.getString("CPF"),
                        genero,
                        rs.getString("Endereco"),
                        rs.getString("Telefone"),
                        rs.getString("Email")
                );
                listaHospedes.add(hospedesModel);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "HospedesDAO: " + erro.getMessage());
            // adicionar método recursivo
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listaHospedes;
    }



    public static boolean deletarHospede(String cpfHospede) throws SQLException, ClassNotFoundException {
        new ConexaoDAO();
        conn = ConexaoDAO.conectaBD();
        PreparedStatement pstm = null;
        boolean retorno = false;

        try {
            String sql = "DELETE FROM Hospedes WHERE CPF = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, cpfHospede);
            int rowsAffected = pstm.executeUpdate();

            if (retorno == rowsAffected > 0){
                JOptionPane.showMessageDialog(null, "Hospede de CPF: " +cpfHospede+ ", deletado com sucesso!");
            } else if (retorno == false) {
                JOptionPane.showMessageDialog(null, "CPF não corresponde a nenhum hospede cadastrado.\n" +
                        "Verifique e tente novamente.");
            }
            return retorno;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "HospedesDAO: " + erro.getMessage());
            retorno = false;
            return retorno;
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
