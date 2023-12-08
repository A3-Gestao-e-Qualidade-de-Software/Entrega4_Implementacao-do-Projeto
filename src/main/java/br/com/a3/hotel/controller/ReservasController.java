package br.com.a3.hotel.controller;

import br.com.a3.hotel.DAO.ReservasDAO;
import br.com.a3.hotel.model.InfoReservasModel;
import br.com.a3.hotel.model.ReservasModel;
import br.com.a3.hotel.view.HospedesView;
import br.com.a3.hotel.view.ReservasView;

import java.sql.SQLException;
import java.util.List;

public class ReservasController {

    public static void cadastrarReserva() throws SQLException, ClassNotFoundException {
        ReservasModel reservasModel = ReservasView.cadastrarNovaReserva();

        ReservasDAO reservasDAO = new ReservasDAO();
        reservasDAO.cadastrarReserva(reservasModel);
    }

    public static int deletarReserva() throws SQLException, ClassNotFoundException, NumberFormatException {
        try {
            int id_reserva = ReservasView.menuDeletarReserva();
            ReservasDAO.deletarReserva(id_reserva);
            return id_reserva;

        }catch (Exception erro){
            return 1;
        }
    }
}
