package Controller;

import DAO.HospedesDAO;
import Model.HospedesModel;
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
        int resposta_menuDeletarHospede = hospedesView.menuDeletarHospede();
        switch (resposta_menuDeletarHospede){
            case 1: // Visualisar hospedes
                List<HospedesModel> listaHospedes = HospedesDAO.listarHospedes();
                hospedesView.mostrarListaHospedes(listaHospedes);
                return 1;
            case 2: // Deletar Hospedes
                String cpfHospede = HospedesView.mostrarTelaDeletarHospede();
                HospedesDAO.deletarHospede(cpfHospede); // --------Debugar método na HospedesDAO (SQL Exception para hospede com reserva cadastrada).------
                return 1;
/*
---------------------------------------------- Continuar daqui ----------------------------------------------------------------------
 */
        }
        return 1;
    }
}
