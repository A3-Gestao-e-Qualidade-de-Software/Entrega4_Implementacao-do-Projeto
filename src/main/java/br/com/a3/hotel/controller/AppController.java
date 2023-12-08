package br.com.a3.hotel.controller;

import br.com.a3.hotel.DAO.*;
import br.com.a3.hotel.model.*;
import br.com.a3.hotel.view.*;

import javax.swing.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class AppController {

    public void iniciar() throws SQLException, ClassNotFoundException {
        int resposta;

        resposta = View.AppView.menuInicial();
        while (resposta < 1 || resposta > 3) {
            resposta = View.AppView.menuInicial();
        }
/*
Verificar se há possibilidade de criar função para rodar o sistema,
recebendo como parâmetro a variável resposta --> resposta = View.AppView.menuInicial();:
 */
        switch (resposta) {
            // Em caso de opção 1 no menuInicial, para fazer login
            case 1:
                UsuarioController usuarioController = new UsuarioController();
                boolean resultadoLogin = usuarioController.fazerLogin();
                int respLogin = 0;
                if (resultadoLogin) {
                    respLogin = View.AppView.menuLogado();
                    while (respLogin < 1 || respLogin > 3) {
                        respLogin = View.AppView.menuLogado();
                    }
//                    if (respLogin == 4) {
//                        break;
//                    }
                    utilizarSistemaLogado(respLogin);
                } else break;
/*
                colocar aqui um while para retornar ao menu logado sempre que finalizar um case,
                inserir uma condição de uma opção para encerrar o sistema.
 */
            // Em caso de opção 2 no menuInicial, para cadastrar novo usuário.
            case 2:
                UsuarioView usuario_vw = new UsuarioView();
                UsuarioModel usuario = usuario_vw.cadastrarNovoUsuario();
                UsuarioDAO usuario_dao = new UsuarioDAO();
                usuario_dao.cadastrarUsuario(usuario);
                break;
            // Em caso de opção 3 no menuInicial, para encerrar o sistema.
            case 3:
                JOptionPane.showMessageDialog(null, "Encerrando Sistema");
                break;
        }
    }

    public static void utilizarSistemaLogado(int respostaLogin) throws SQLException, ClassNotFoundException {

        switch (respostaLogin) {
            // Em caso de opção 1 no menuLogado, para abrir o menuHospede
            case 1:
                int retorno = 1;
                // enquanto retorno = 1, voltar para menu hospede
                while (retorno == 1) {

                    int rsp_menuHospede = HospedesView.menuHospede();

                    // permitindo selecionar apenas entre 1 e 5 no menu hospede
                    while (rsp_menuHospede < 1 || rsp_menuHospede > 5) {
                        rsp_menuHospede = HospedesView.menuHospede();
                    }
                    // switch case do menu hospede
                    switch (rsp_menuHospede) {
                        // Em caso de opção 1 no menuHospede, para cadastrar um novo hospede.
                        case 1:
                            HospedesController hospedesController = new HospedesController();
                            retorno = hospedesController.cadastrarHospede();
                            break;
                        // Em caso de opção 2 no menuHospede, para deletar um hospede.
                        case 2:
                            HospedesController hospedesController1 = new HospedesController();
                            retorno = hospedesController1.deletarHospede();
                            break;
                        // Em caso de opção 3 no menuHospede, para editar hospede.
                        case 3:
                            HospedesController hospedesController2 = new HospedesController();
                            retorno = hospedesController2.editarHospede();
                            break;
                        // Em caso de opção 4 no menuHospede, para listar hospedes.
                        case 4:
                            HospedesView hospedesView = new HospedesView();
                            List<HospedesModel> listaHospedes = DAO.HospedesDAO.listarHospedes();
                            HospedesView.mostrarListaHospedes(listaHospedes);
                            retorno = 1;
                            break;
                        // Em caso de opção 5 no menuHospede, para voltar ao menu logado.
                        case 5:
                            retorno=0;
                            respostaLogin();
                            break;
                    }
                }


                // Em caso de opção 2 no menuLogado, para abrir o menuQuartos
            case 2:
                QuartoDAO quartoDAO = new QuartoDAO();
                List<QuartoModel> listaQuartos = quartoDAO.listarQuartos();
                QuartoView.mostrarListaQuartos(listaQuartos);

                respostaLogin();
                break;


            // Em caso de opção 3 no menuLogado, para abrir o menuReservas
            case 3:
                retorno = 1;
                while (retorno == 1) {
                    ReservasView reservasView = new ReservasView();
                    int rspMenuReservas = reservasView.mostrarMenuReservas();

                    switch (rspMenuReservas) {
                        // Em caso de opção 1 no menuReservas, para fazer nova reserva:
                        case 1:
                            ReservasController.cadastrarReserva();
                            break;

                        // Em caso de opção 2 no menuReservas, para deletar uma reserva:
                        case 2:
                            ReservasController.deletarReserva();
                            break;

                        // Em caso de opção 3 no menuReservas, para voltar ao menu logado:
                        case 3:
                            respostaLogin();
                            break;
                    }
                }
        }
    }

    public static void respostaLogin() throws SQLException, ClassNotFoundException {
        int respLogin = 0;
        respLogin = View.AppView.menuLogado();
        while (respLogin < 1 || respLogin > 3) {
            respLogin = View.AppView.menuLogado();
        }
        utilizarSistemaLogado(respLogin);
    }
}