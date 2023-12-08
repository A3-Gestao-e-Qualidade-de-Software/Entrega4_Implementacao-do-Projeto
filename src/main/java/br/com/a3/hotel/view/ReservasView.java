package br.com.a3.hotel.view;
import br.com.a3.hotel.DAO.QuartoDAO;
import br.com.a3.hotel.DAO.ReservasDAO;
import br.com.a3.hotel.controller.ReservasController;
import br.com.a3.hotel.model.HospedesModel;
import br.com.a3.hotel.model.InfoReservasModel;
import br.com.a3.hotel.model.QuartoModel;
import br.com.a3.hotel.model.ReservasModel;
import br.com.a3.hotel.utils.Metodos_Utils;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * A classe ReservasView contém métodos para exibição de menus e interações relacionadas às reservas na interface de usuário.
 */

public class ReservasView {

        /**
     * Exibe o menu de reservas para seleção de operações.
     *
     * @return O número correspondente à operação selecionada no menu.
     */
    // Exibindo Menu de Reservas:
    public static int mostrarMenuReservas(){
        try{
            int resposta = Integer.parseInt(JOptionPane.showInputDialog(null, "Menu Reservas: \n" +
                    "[1] Nova Reserva\n" +
                    "[2] Deletar Reserva\n" +
                    "[3] Voltar"));
            return resposta;
        } catch (Exception e){
            return 1;
        }
    }
 /**
     * Realiza o cadastro de uma nova reserva.
     *
     * @return Um objeto ReservasModel contendo os detalhes da nova reserva.
     * @throws SQLException            Em caso de erro de SQL.
     * @throws ClassNotFoundException Em caso de classe não encontrada.
     */
    public static ReservasModel cadastrarNovaReserva() throws SQLException, ClassNotFoundException {
        int ID = 0;
        // Listando Hospedes e solicitando CPF do Hospede a efetuar reserva:
        List<HospedesModel> listaHospedes = DAO.HospedesDAO.listarHospedes();
        JOptionPane.showMessageDialog(null, "A seguir copie o N° de CPF do hóspede a efetuar reserva.");
        HospedesView.mostrarListaHospedes(listaHospedes);
        String CPF_Hospede = JOptionPane.showInputDialog(null, "CPF (Apenas dígitos):");


        String data_checkIn = null;
        String data_checkOut = null;
        try {
            // Solicitando datas de check-in e check-out desejadas:
            String sol_data_checkIn = new Metodos_Utils().solicitarDataCheckIn();
            data_checkIn = padronizarDataString(sol_data_checkIn);
            String sol_data_checkOut = new Metodos_Utils().solicitarDataCheckOut();
            data_checkOut = padronizarDataString(sol_data_checkOut);
        } catch (Exception ignored) {
            return null;
        }


        // Listando apenas quartos disponíveis para o período desejado e solicitando ID do quarto:
        List<QuartoModel> listaQuartosDisponiveis;
        try {
            listaQuartosDisponiveis = QuartoDAO.listarQuartosDisponiveis(data_checkIn, data_checkOut);
        } catch (Exception e) {
            return null;
        }
        JOptionPane.showMessageDialog(null, "A seguir copie o N° ID do quarto desejado para hospedagem.");
        QuartoView.mostrarListaQuartos(listaQuartosDisponiveis);
        int ID_Quarto;
        try {
            ID_Quarto = Integer.parseInt((JOptionPane.showInputDialog(null, "Nº ID do Quarto desejado:")));
        } catch (Exception e) {
            return null;
        }

        // Obtendo a data de hoje e formatando para String:
        LocalDate hoje = LocalDate.now();

        // Formatando para yyyy-MM-dd
        DateTimeFormatter formatador1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String data_Hoje = hoje.format(formatador1);

        // Formatando para dd/MM/yyyy
        DateTimeFormatter formatador2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String data_HojeFormatada = hoje.format(formatador2);
        JOptionPane.showMessageDialog(null, "Data da reserva: " + data_HojeFormatada);


        int id_Status_Reserva = 1;
        int id_Status_Ativa = 1;


        return new ReservasModel(ID, CPF_Hospede, ID_Quarto, data_checkIn, data_checkOut, data_Hoje, id_Status_Reserva, id_Status_Ativa);
    }


    // Retorna a data no padrão yyyy-mm-dd para tornar possível a comparação
    // dos quartos disponíveis na Query SQL em QuartosDAO:

        /**
     * Formata a data para o padrão "yyyy-MM-dd" para comparações na base de dados.
     *
     * @param data A data a ser formatada (dd/MM/yyyy).
     * @return A data formatada no padrão "yyyy-MM-dd".
     */
    public static String padronizarDataString(String data){
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = inputFormat.parse(data);
            String dataConvertida = outputFormat.format(date);

            return dataConvertida;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

        /**
     * Exibe o menu para deletar uma reserva.
     *
     * @return O ID da reserva a ser deletada.
     * @throws SQLException            Em caso de erro de SQL.
     * @throws ClassNotFoundException Em caso de classe não encontrada.
     */

    public static int menuDeletarReserva() throws SQLException, ClassNotFoundException {
        int id_Reserva;

        try {
            id_Reserva = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Digite o ID da reserva a ser deletada:"));


        }catch (Exception erro){
            id_Reserva = 0;
        }
        return id_Reserva;
    }
}
