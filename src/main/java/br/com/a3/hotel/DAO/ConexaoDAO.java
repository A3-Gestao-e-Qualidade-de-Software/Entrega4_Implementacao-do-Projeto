package br.com.a3.hotel.DAO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Esta classe gerencia a conexão com o banco de dados.
 */
public class ConexaoDAO {

    /**
     * Estabelece uma conexão com o banco de dados.
     * @return Uma instância de Connection representando a conexão estabelecida.
     * @throws SQLException se ocorrer um erro ao tentar se conectar ao banco de dados.
     * @throws ClassNotFoundException se a classe do driver JDBC não for encontrada.
     */
    public static Connection conectaBD() throws SQLException, ClassNotFoundException {

        // Registrando o driver JDBC
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/hotel?user=root&password=admin";
        Connection conn = null;

        try {
            // Estabelecendo a conexão com o banco de dados
            conn = DriverManager.getConnection(url);
            JOptionPane.showMessageDialog(null, "Conexão com o BD bem sucedida");
            return conn;
        } catch (SQLException erro) {
            // Lidando com exceções ao estabelecer a conexão
            JOptionPane.showMessageDialog(null, "DAO.ConexaoDAO: " + erro.getMessage());
            System.out.println("Erro ao conectar ao banco de dados");
            return conn;
        }
    }
}
