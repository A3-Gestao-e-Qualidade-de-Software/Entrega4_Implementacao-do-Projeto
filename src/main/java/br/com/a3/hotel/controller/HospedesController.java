package br.com.a3.hotel.controller;

import br.com.a3.hotel.DAO.*;
import br.com.a3.hotel.model.*;
import br.com.a3.hotel.view.*;
import br.com.a3.hotel.utils.*;

import java.sql.SQLException;
import java.util.List;

public class HospedesController {

    public int cadastrarHospede() throws SQLException, ClassNotFoundException {
        HospedesView hospedesView = new HospedesView();
        HospedesModel hospede = hospedesView.cadastrarNovoHospede();
        DAO.HospedesDAO hospedesDAO = new DAO.HospedesDAO();
        hospedesDAO.cadastrarHospede(hospede);
        return 1;
    }

    public int deletarHospede() throws SQLException, ClassNotFoundException {
        HospedesView hospedesView = new HospedesView();
        int resposta_menuDeletarHospede = HospedesView.menuDeletarHospede();

        while (resposta_menuDeletarHospede < 1 || resposta_menuDeletarHospede > 3){
            resposta_menuDeletarHospede = HospedesView.menuDeletarHospede();
        }

        switch (resposta_menuDeletarHospede) {
            case 1: // Visualisar hospedes
                List<HospedesModel> listaHospedes = DAO.HospedesDAO.listarHospedes();
                HospedesView.mostrarListaHospedes(listaHospedes);
                break;
            case 2: // Deletar Hospedes
                String cpfHospede = HospedesView.mostrarTelaDeletarHospede();
                DAO.HospedesDAO.deletarHospede(cpfHospede); // --------Debugar método na HospedesDAO (SQL Exception para hospede com reserva cadastrada).------
                break;
        }
        return 1;
    }


    public int editarHospede() throws SQLException, ClassNotFoundException {
        HospedesView hospedesView = new HospedesView();
        String cpf = hospedesView.solicitarCPFEditar();

        if (cpf != null && !cpf.equalsIgnoreCase("")){
            String campoEditar = hospedesView.mostrarOpcoesEditarHospede();
            String[] camposString = {"Nome", "Sobrenome", "Endereco", "Telefone", "Email"};

            for (String valor : camposString) {
                if (campoEditar.equals(valor)) {
                    String novoValor = hospedesView.inserirNovoValorString(campoEditar);
                    if (novoValor != null && !novoValor.equalsIgnoreCase("")){
                        DAO.HospedesDAO.editarHospedeString(cpf, campoEditar, novoValor);
                    }
                }
            }

            if(campoEditar.equals("Genero")){
                String novoValor = new Metodos_Utils().selecionarGenero();
                if (novoValor != null && !novoValor.equalsIgnoreCase("")) {
                    DAO.HospedesDAO.editarHospedeString(cpf, campoEditar, novoValor);
                }
            }

            if (campoEditar.equals("DT_Nascimento")){
                String novoValor = new Metodos_Utils().solicitarData();
                if (novoValor != null){
                    System.out.println(novoValor);
                    DAO.HospedesDAO.editarHospedeData(cpf, campoEditar, novoValor);
                }
            }
        }
        return 1;
    }
}