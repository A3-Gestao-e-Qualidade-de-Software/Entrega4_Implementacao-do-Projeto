package br.com.a3.hotel.acceptance.steps;

import DAO.HospedesDAO;
import br.com.a3.hotel.DAO.ConexaoDAO;
import br.com.a3.hotel.model.HospedesModel;
import br.com.a3.hotel.view.HospedesView;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ListarHospedesSteps {

    private DAO.HospedesDAO hospedesDAO;
    private List<HospedesModel> listaDeHospedesRetornada;
    static Connection conn;

    @Dado("que existem hospedes cadastrados no sistema")
    public void que_existem_hospedes_cadastrados_no_sistema() throws SQLException, ClassNotFoundException {
        hospedesDAO = new HospedesDAO();
        hospedesDAO.cadastrarHospede(new HospedesModel("João", "da Silva", "01/01/1980", "12345678900", "m", "Rua Teste", "31999999999", "joao@email"));
    }
    @Quando("o usuario acessa a opção de listar hospedes")
    public void o_usuario_acessa_a_opção_de_listar_hospedes() throws SQLException, ClassNotFoundException {
        listaDeHospedesRetornada = hospedesDAO.listarHospedes();
    }
    @Entao("uma lista de hospedes deve ser retornada")
    public void uma_lista_de_hospedes_deve_ser_retornada() {
        assertTrue(listaDeHospedesRetornada.size() > 0);
    }

    @Dado("que nao existem hospedes cadastrados no sistema")
    public void que_nao_existem_hospedes_cadastrados_no_sistema() throws SQLException, ClassNotFoundException {
        new ConexaoDAO();
        conn = ConexaoDAO.conectaBD();

        String sql = "DELETE FROM Hospedes;";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.executeUpdate();
        pstm.close();
    }
    @Quando("o usuario acessa a opcao de listar hospedes")
    public void o_usuario_acessa_a_opcao_de_listar_hospedes() throws SQLException, ClassNotFoundException {
        listaDeHospedesRetornada = hospedesDAO.listarHospedes();
    }
    @Entao("uma lista vazia deve ser retornada")
    public void uma_lista_vazia_deve_ser_retornada() {
        assertTrue(listaDeHospedesRetornada.isEmpty());
    }
}
