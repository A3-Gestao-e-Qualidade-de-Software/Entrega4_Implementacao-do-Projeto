package br.com.a3.hotel.acceptance.steps;

import DAO.HospedesDAO;
import br.com.a3.hotel.DAO.ConexaoDAO;
import br.com.a3.hotel.controller.HospedesController;
import br.com.a3.hotel.model.HospedesModel;
import br.com.a3.hotel.view.HospedesView;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CadastroHospedesSteps {

    private HospedesModel hospede;
    private HospedesView hospedesView;
    private DAO.HospedesDAO hospedesDAO;

    @Dado("que o usuario está na tela de cadastro de hospede")
    public void que_o_usuario_está_na_tela_de_cadastro_de_hospede() {
        hospedesView = new HospedesView();
    }
    @Quando("o usuario insere os seguintes dados:")
    public void o_usuario_insere_os_seguintes_dados(DataTable dataTable) {

        List<Map<String, String>> valores = dataTable.asMaps();

        String nome = null;
        String sobrenome = null;
        String dtNascimento = null;
        String cpf = null;
        String genero = null;
        String endereco = null;
        String telefone = null;
        String email = null;

        for (Map<String, String> mapa : valores){

            nome = mapa.get("nome");
            sobrenome = mapa.get("sobrenome");
            dtNascimento = mapa.get("dtNascimento");
            cpf = mapa.get("cpf");
            genero = mapa.get("genero");
            endereco = mapa.get("endereco");
            telefone = mapa.get("telefone");
            email = mapa.get("email");
        }

        hospede = new HospedesModel(nome, sobrenome, dtNascimento, cpf, genero, endereco, telefone, email);
    }

    @Entao("o sistema deve cadastrar o hospede no banco de dados")
    public void o_sistema_deve_cadastrar_o_hospede_no_banco_de_dados() throws SQLException, ClassNotFoundException {

        hospedesDAO = new HospedesDAO();
        boolean cadastroHospede = hospedesDAO.cadastrarHospede(hospede);

        assertEquals(true, cadastroHospede);
    }
}
