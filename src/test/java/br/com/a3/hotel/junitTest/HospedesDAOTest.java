package br.com.a3.hotel.junitTest;

import br.com.a3.hotel.DAO.ConexaoDAO;
import br.com.a3.hotel.model.HospedesModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HospedesDAOTest {

    private HospedesModel hospede;
    private DAO.HospedesDAO hospedesDAO;
    static Connection conn;

    @BeforeEach
    public void beforeEach() throws SQLException, ClassNotFoundException {
        new ConexaoDAO();
        conn = ConexaoDAO.conectaBD();

        String sql = "DELETE FROM Hospedes;";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.executeUpdate();
        pstm.close();

        hospedesDAO = new DAO.HospedesDAO();
    }

    @Test
    void deveCadastrarUmHospede() throws SQLException, ClassNotFoundException {

        hospede = new HospedesModel("Fulano", "Silva", "01/01/2000", "19859145067", "m", "MG, BH", "31999999999", "fulano@email");
        boolean cadastroHospede = hospedesDAO.cadastrarHospede(hospede);
        Assertions.assertEquals(true, cadastroHospede);
    }

    @Test
    void naoDeveCadastrarUmHospede() throws SQLException, ClassNotFoundException {
        hospede = new HospedesModel("", "", "", "", "", "", "", "");
        boolean cadastroHospede = hospedesDAO.cadastrarHospede(hospede);
        Assertions.assertEquals(false, cadastroHospede);
    }

    @Test
    void naoDeveCadastrarUmHospedeComCPFRepetido() throws SQLException, ClassNotFoundException {
        HospedesModel hospede1 = new HospedesModel("Fulano", "Silva", "01/01/2000", "19859145067", "m", "MG, BH", "31999999999", "fulano@email");
        hospedesDAO.cadastrarHospede(hospede1);
        HospedesModel hospede2 = new HospedesModel("Beltrano", "Santos", "01/01/1980", "19859145067", "m", "SP, SP", "11999999999", "beltrano@email");
        boolean cadastroHospede = hospedesDAO.cadastrarHospede(hospede2);
        Assertions.assertEquals(false, cadastroHospede);
    }

}
