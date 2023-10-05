package Controller;

import DAO.HospedesDAO;
import DAO.UsuarioDAO;
import Model.HospedesModel;
import Model.UsuarioModel;
import View.AppView;
import View.HospedesView;
import View.UsuarioView;

import javax.swing.*;

import java.sql.SQLException;
import java.util.List;

public class AppController {

    public void iniciar() throws SQLException, ClassNotFoundException {
        int resposta;

        resposta = AppView.menuInicial();
        while (resposta < 1 || resposta > 3) {
            resposta = AppView.menuInicial();
        }
        switch (resposta) {
            case 1: // Em caso de opção 1 no menuInicial, para fazer login
                UsuarioController usuarioController = new UsuarioController();
                int respostaLogin = usuarioController.fazerLogin();
                while (respostaLogin < 1 || respostaLogin > 4){
                    respostaLogin = usuarioController.fazerLogin();
                    if (respostaLogin == 4){
                        break;
                    }
                }
/*
                colocar aqui um while para retornar ao menu logado sempre que finalizar um case,
                inserir uma condição de uma opção para encerrar o sistema.
 */
                switch (respostaLogin){
                    case 1: // Em caso de opção 1 no menuLogado, para abrir o menuHospede
                        int retorno = 1;
                        while (retorno == 1){ // enquanto retorno = 1, voltar para menu hospede

                            int rsp_menuHospede = HospedesView.menuHospede();

                            while (rsp_menuHospede < 1 || rsp_menuHospede > 5){ // permitindo selecionar apenas entre 1 e 5 no menu hospede
                                rsp_menuHospede = HospedesView.menuHospede();
                            }

                            switch (rsp_menuHospede){ // switch case do menu hospede
                                case 1: // Em caso de opção 1 no menuHospede, para cadastrar um novo hospede.
                                    HospedesController hospedesController= new HospedesController();
                                    retorno = hospedesController.cadastrarHospede();
                                    break;

                                case 2: // Em caso de opção 2 no menuHospede, para deletar um hospede.
                                    HospedesController hospedesController1 = new HospedesController();
                                    retorno = hospedesController1.deletarHospede();
                                    break;

                                case 3: // Em caso de opção 3 no menuHospede, para editar hospede.
                                    JOptionPane.showMessageDialog(null, "Tela de editar hospede.");
                                    break;

                                case 4: // Em caso de opção 4 no menuHospede, para listar hospedes.
                                    HospedesView hospedesView = new HospedesView();
                                    List<HospedesModel> listaHospedes = HospedesDAO.listarHospedes();
                                    hospedesView.mostrarListaHospedes(listaHospedes);
                                    retorno = 1;
                                    break;

                                case 5: // Em caso de opção 5 no menuHospede, para voltar à tela anterior.
                                    retorno = 0;
                                    break;
                            }
                        }

                    case 2: // Em caso de opção 2 no menuLogado, para abrir o menuQuartos
                }
                break;
            case 2: // Em caso de opção 2 no menuInicial, para cadastrar novo usuário.
                UsuarioView usuario_vw = new UsuarioView();
                UsuarioModel usuario = usuario_vw.cadastrarNovoUsuario();
                UsuarioDAO usuario_dao = new UsuarioDAO();
                usuario_dao.cadastrarUsuario(usuario);
                break;
            case 3: // Em caso de opção 3 no menuInicial, para encerrar o sistema.
                JOptionPane.showMessageDialog(null, "Encerrando Sistema");
                break;
        }
    }
}