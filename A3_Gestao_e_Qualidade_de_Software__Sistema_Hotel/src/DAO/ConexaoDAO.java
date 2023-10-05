package DAO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {

//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        conectaBD();
//    }

    public static Connection conectaBD() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/hotel?user=root&password=admin";
        Connection conn = DriverManager.getConnection(url);

        try {
            conn = DriverManager.getConnection(url);
            JOptionPane.showMessageDialog(null, "Conexão com o BD bem sucedida");
            return conn;
        }
        catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "DAO.ConexaoDAO: " + erro.getMessage());
            System.out.println("Erro");
            return conn;
        }
    }
}
