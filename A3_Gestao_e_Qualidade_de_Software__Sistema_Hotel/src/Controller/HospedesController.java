package Controller;

import DAO.HospedesDAO;
import Model.HospedesModel;
import Utils.Metodos_Utils;
import View.HospedesView;

import java.sql.SQLException;
import java.util.List;

public class HospedesController {

    public int cadastrarHospede() throws SQLException, ClassNotFoundException {
        HospedesView hospedesView = new HospedesView();
        HospedesModel hospede = hospedesView.cadastrarNovoHospede();
        HospedesDAO hospedesDAO = new HospedesDAO();
        hospedesDAO.cadastrarHospede(hospede);
        return 1;
    }

    public int deletarHospede() throws SQLException, ClassNotFoundException {
        HospedesView hospedesView = new HospedesView();
        int resposta_menuDeletarHospede = HospedesView.menuDeletarHospede();
        int retorno = 0;
        switch (resposta_menuDeletarHospede) {
            case 1: // Visualisar hospedes
                List<HospedesModel> listaHospedes = HospedesDAO.listarHospedes();
                HospedesView.mostrarListaHospedes(listaHospedes);
                retorno = 1;
                break;
            case 2: // Deletar Hospedes
                String cpfHospede = HospedesView.mostrarTelaDeletarHospede();
                HospedesDAO.deletarHospede(cpfHospede); // --------Debugar método na HospedesDAO (SQL Exception para hospede com reserva cadastrada).------
                retorno = 1;
                break;
            case 3:
                retorno = 1;
                break;
        }
        return retorno;
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
                        HospedesDAO.editarHospedeString(cpf, campoEditar, novoValor);
                    }
                }
            }

            if(campoEditar.equals("Genero")){
                String novoValor = new Metodos_Utils().selecionarGenero();
                if (novoValor != null && !novoValor.equalsIgnoreCase("")) {
                    HospedesDAO.editarHospedeString(cpf, campoEditar, novoValor);
                }
            }

            if (campoEditar.equals("DT_Nascimento")){
                String novoValor = new Metodos_Utils().solicitarData();
                if (novoValor != null){
                    System.out.println(novoValor);
                    HospedesDAO.editarHospedeData(cpf, campoEditar, novoValor);
                }
            }
        }
        return 1;
    }
}