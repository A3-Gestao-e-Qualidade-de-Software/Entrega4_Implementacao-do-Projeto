package br.com.a3.hotel.junitTest;

import br.com.a3.hotel.DAO.ConexaoDAO;
import br.com.a3.hotel.DAO.UsuarioDAO;
import br.com.a3.hotel.model.UsuarioModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class UsuarioDAOTest {

    private UsuarioModel usuario;
    private UsuarioDAO usuarioDAO;
    static Connection conn;

    @BeforeEach
    public void beforeEach() throws SQLException, ClassNotFoundException {
        new ConexaoDAO();
        conn = ConexaoDAO.conectaBD();

        String sql = "DELETE FROM usuario;";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.executeUpdate();
        pstm.close();

        usuarioDAO = new UsuarioDAO();
    }

    @Test
    public void deveriaCadastrarumUsuario() throws SQLException, ClassNotFoundException {
        usuario = new UsuarioModel(
                "Joao da Silva",
                "JoaoSilva",
                "12345");

        usuarioDAO = new UsuarioDAO();

        boolean cadastroUsuario = usuarioDAO.cadastrarUsuario(usuario);

        assertEquals(true, cadastroUsuario);
    }

    @Test
    public void naoDeveriaCadastrarumUsuarioComFuncionalRepetido() throws SQLException, ClassNotFoundException {
        UsuarioModel usuario1 = new UsuarioModel(
                "Joao da Silva",
                "JoaoSilva",
                "12345");

        UsuarioModel usuario2 = new UsuarioModel(
                "Joao da Silva Santos",
                "JoaoSilva",
                "12345");

        usuarioDAO = new UsuarioDAO();

        usuarioDAO.cadastrarUsuario(usuario1);
        boolean cadastroUsuario = usuarioDAO.cadastrarUsuario(usuario2);

        assertEquals(false, cadastroUsuario);
    }

}