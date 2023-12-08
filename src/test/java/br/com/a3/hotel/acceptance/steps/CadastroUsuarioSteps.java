package br.com.a3.hotel.acceptance.steps;

import br.com.a3.hotel.DAO.UsuarioDAO;
import br.com.a3.hotel.model.UsuarioModel;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CadastroUsuarioSteps {

    private UsuarioModel usuario;
    private UsuarioDAO usuarioDAO;

    @Dado("que foram preenchidos os seguintes dados:")
    public void que_foram_preenchidos_os_seguintes_dados(DataTable dataTable) {

        List<Map<String, String>> valores = dataTable.asMaps();

        String nomeUsuario = null;
        String funcional = null;
        String senha = null;

        for (Map<String, String> mapa : valores){
            nomeUsuario = mapa.get("NomeUsuario");
            funcional = mapa.get("Funcional");
            senha = mapa.get("Senha");
        }
        usuario = new UsuarioModel(nomeUsuario, funcional, senha);

    }
    @Quando("o usuario acessa a opcao de cadastrar usuario")
    public void o_usuario_acessa_a_opcao_de_cadastrar_usuario() {
        usuarioDAO = new UsuarioDAO();
    }
    @Entao("o usuario deve ser criado com sucesso")
    public void o_usuario_deve_ser_criado_com_sucesso() throws SQLException, ClassNotFoundException {
        boolean cadastroUsuario = usuarioDAO.cadastrarUsuario(usuario);

        assertEquals(true, cadastroUsuario);
    }

    @Entao("o usuario nao deve ser criado")
    public void o_usuario_nao_deve_ser_criado() throws SQLException, ClassNotFoundException {
        boolean cadastroUsuario = usuarioDAO.cadastrarUsuario(usuario);

        assertEquals(false, cadastroUsuario);
    }
}
