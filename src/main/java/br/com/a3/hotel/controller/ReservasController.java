package br.com.a3.hotel.controller;

import br.com.a3.hotel.view.ReservasView;

import java.sql.SQLException;

public class ReservasController {

    public static void cadastrarReserva() throws SQLException, ClassNotFoundException {
        ReservasView reservasView = new ReservasView();
        ReservasView.cadastrarNovaReserva();
    }
}
