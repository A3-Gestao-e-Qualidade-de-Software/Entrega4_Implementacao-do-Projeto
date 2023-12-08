package br.com.a3.hotel.DAO;

import br.com.a3.hotel.controller.ReservasController;
import br.com.a3.hotel.model.HospedesModel;
import br.com.a3.hotel.model.InfoReservasModel;
import br.com.a3.hotel.model.ReservasModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservasDAO {

    public boolean cadastrarReserva(ReservasModel objReservasModel) throws SQLException, ClassNotFoundException {
        Connection conn = ConexaoDAO.conectaBD();

        try {
            String sql = "INSERT INTO Reservas (\n" +
                    "    ID,\n" +
                    "    ID_Hospede,\n" +
                    "    ID_Quarto,\n" +
                    "    DT_Check_IN,\n" +
                    "    DT_Check_OUT,\n" +
                    "    DT_Reserva,\n" +
                    "    ID_Status_Reserva,\n" +
                    "    Status_Ativa\n" +
                    ")\n" +
/*
*Obs: Corrigir HospedesModel e classes relacionadas para retornar o ID_Hospede
 */
                    // Subquery para consultar ID_Hospede, e valores dos campos a serem inseridos:
                    "SELECT\n" +
                    "    0,\n" + // ID_Reserva
                    "    ID_Hospede,\n" + //ID_Hospede (retornará pelo CPF da tabela Hospedes)
                    "    ?,\n" + // (1)ID_Quarto
                    "    ?,\n" + // (2)Data_CheckIN
                    "    ?,\n" + // (3)Data_CheckOUT
                    "    ?,\n" + // (4)Data_Reserva
                    "    ?,\n" + // (5)ID_Status_Reserva
                    "    ?\n" + // (6)Status_Ativa
                    "FROM Hospedes\n" +
                    "WHERE CPF = ?;"; // (7)CPF do hospede o qual retornará o ID_Hospede

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, objReservasModel.getID_Quarto());
            pstm.setString(2, objReservasModel.getData_checkIN());
            pstm.setString(3, objReservasModel.getData_checkOUT());
            pstm.setString(4, objReservasModel.getData_Reserva());
            pstm.setInt(5, objReservasModel.getID_Status_Reserva());
            pstm.setInt(6, objReservasModel.getStatus_Ativa());
            pstm.setString(7, objReservasModel.getCPF_Hospede());

            int rowsaffected = pstm.executeUpdate();
            if (rowsaffected > 0) {
                JOptionPane.showMessageDialog(null, "Reserva cadastrada com sucesso!");
                return true;
            }
            else
                JOptionPane.showMessageDialog(null, "Erro!! \n" +
                        "Revise as informações, e tente novamente ");
            ReservasController.cadastrarReserva();

                return false;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "ReservasDAO " + erro.getMessage());
            return false;
        }
    }

    public static boolean deletarReserva(int id_reserva) throws SQLException, ClassNotFoundException {
        Connection conn = ConexaoDAO.conectaBD();
        PreparedStatement pstm = null;
        boolean retorno = false;

        try {
            String sql = "DELETE FROM reservas WHERE ID = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id_reserva);
            int rowsAffected = pstm.executeUpdate();
            retorno = rowsAffected > 0;

            if (retorno == true){
                JOptionPane.showMessageDialog(null, "Reserva de ID: " +id_reserva+ ", deletada com sucesso!");
            } else if (retorno == false) {
                JOptionPane.showMessageDialog(null, "ID não corresponde a nenhuma reserva cadastrada.\n" +
                        "Verifique e tente novamente.");
            }
            return retorno;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ReservasDAO: " + erro.getMessage());
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
