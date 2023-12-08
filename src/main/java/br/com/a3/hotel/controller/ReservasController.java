package br.com.a3.hotel.controller;

import br.com.a3.hotel.DAO.ReservasDAO;
import br.com.a3.hotel.model.InfoReservasModel;
import br.com.a3.hotel.model.ReservasModel;
import br.com.a3.hotel.view.HospedesView;
import br.com.a3.hotel.view.ReservasView;

import java.sql.SQLException;
import java.util.List;

/**
 * Controlador responsável por operações relacionadas às reservas.
 */

public class ReservasController {

    /**
     * Cadastra uma nova reserva no sistema.
     *
     * @throws SQLException             se ocorrer um erro durante a execução da operação no banco de dados.
     * @throws ClassNotFoundException se a classe do driver JDBC não for encontrada.
     */
    
    public static void cadastrarReserva() throws SQLException, ClassNotFoundException {
        ReservasModel reservasModel = ReservasView.cadastrarNovaReserva();

        ReservasDAO reservasDAO = new ReservasDAO();
        reservasDAO.cadastrarReserva(reservasModel);
    }

        /**
     * Deleta uma reserva do sistema.
     *
     * @return o ID da reserva excluída.
     * @throws SQLException             se ocorrer um erro durante a execução da operação no banco de dados.
     * @throws ClassNotFoundException se a classe do driver JDBC não for encontrada.
     * @throws NumberFormatException    se houver um problema com o formato do número da reserva.
     */

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
