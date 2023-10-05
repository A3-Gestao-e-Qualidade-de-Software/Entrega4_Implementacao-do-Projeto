package DAO;

import Controller.AppController;
import Model.UsuarioModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    private Connection conn;

    public UsuarioDAO() {
        this.conn = conn;
    }

    public boolean cadastrarUsuario(UsuarioModel objUsuarioModel) throws SQLException, ClassNotFoundException {
        new ConexaoDAO();
        conn = ConexaoDAO.conectaBD();

        try {
            String sql = "INSERT INTO usuario (NomeUsuario, Funcional, Senha) VALUES (?, ?, ?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, objUsuarioModel.getNomeUsuario());
            pstm.setString(2, objUsuarioModel.getFuncional());
            pstm.setString(3, objUsuarioModel.getSenhaUsuario());

            int rowsaffected = pstm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
            AppController ac = new AppController();
            ac.iniciar();
            return rowsaffected > 0;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO: " + erro.getMessage());
            AppController ac = new AppController();
            ac.iniciar();
            return false;
        }
    }


    public ResultSet autenticarUsuario(UsuarioModel objUsuarioModel) throws SQLException, ClassNotFoundException {
        new ConexaoDAO();
        conn = ConexaoDAO.conectaBD();

        try {
            String sql = "SELECT * FROM usuario WHERE funcional = ? AND senha = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, objUsuarioModel.getFuncional());
            pstm.setString(2, objUsuarioModel.getSenhaUsuario());

            ResultSet rs = pstm.executeQuery();
            return rs;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO: " + erro.getMessage());
            return null;
        }
    }
}
