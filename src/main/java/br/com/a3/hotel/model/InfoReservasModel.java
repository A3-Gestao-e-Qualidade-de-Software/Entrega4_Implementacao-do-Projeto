package br.com.a3.hotel.model;
/**
 * Classe que representa informações sobre reservas.
 */
     
public class InfoReservasModel {
    private static int ID_reserva;
    private static String nome_completo;
    private static int num_quarto;
    private static int andar_quarto;
    private static String tipo_quarto;
    private static String DT_checkIN;
    private static String DT_checkOUT;
    private static String DT_reserva;
    private static int dias_reservados;
    private static Double valor_diaria;
    private static Double valor_total;
   /**
     * Construtor para criar um objeto InfoReservasModel.
     * @param ID_reserva ID da reserva.
     * @param nome_completo Nome completo do hóspede.
     * @param num_quarto Número do quarto.
     * @param andar_quarto Andar do quarto.
     * @param tipo_quarto Tipo de quarto.
     * @param DT_checkIN Data de check-in.
     * @param DT_checkOUT Data de check-out.
     * @param DT_reserva Data da reserva.
     * @param dias_reservados Número de dias reservados.
     * @param valor_diaria Valor da diária.
     * @param valor_total Valor total da reserva.
     */
    public InfoReservasModel(int ID_reserva, String nome_completo, int num_quarto, int andar_quarto, String tipo_quarto, String DT_checkIN, String DT_checkOUT, String DT_reserva, int dias_reservados, Double valor_diaria, Double valor_total) {
        this.ID_reserva = ID_reserva;
        this.nome_completo = nome_completo;
        this.num_quarto = num_quarto;
        this.andar_quarto = andar_quarto;
        this.tipo_quarto = tipo_quarto;
        this.DT_checkIN = DT_checkIN;
        this.DT_checkOUT = DT_checkOUT;
        this.DT_reserva = DT_reserva;
        this.dias_reservados = dias_reservados;
        this.valor_diaria = valor_diaria;
        this.valor_total = valor_total;
    }

    public static int getID_reserva() {
        return ID_reserva;
    }

    public void setID_reserva(int ID_reserva) {
        this.ID_reserva = ID_reserva;
    }

    public static String getNome_completo() {
        return nome_completo;
    }

    public void setNome_completo(String nome_completo) {
        this.nome_completo = nome_completo;
    }

    public static int getNum_quarto() {
        return num_quarto;
    }

    public void setNum_quarto(int num_quarto) {
        this.num_quarto = num_quarto;
    }

    public static int getAndar_quarto() {
        return andar_quarto;
    }

    public void setAndar_quarto(int andar_quarto) {
        this.andar_quarto = andar_quarto;
    }

    public static String getTipo_quarto() {
        return tipo_quarto;
    }

    public void setTipo_quarto(String tipo_quarto) {
        this.tipo_quarto = tipo_quarto;
    }

    public static String getDT_checkIN() {
        return DT_checkIN;
    }

    public void setDT_checkIN(String DT_checkIN) {
        this.DT_checkIN = DT_checkIN;
    }

    public static String getDT_checkOUT() {
        return DT_checkOUT;
    }

    public void setDT_checkOUT(String DT_checkOUT) {
        this.DT_checkOUT = DT_checkOUT;
    }

    public static String getDT_reserva() {
        return DT_reserva;
    }

    public void setDT_reserva(String DT_reserva) {
        this.DT_reserva = DT_reserva;
    }

    public static int getDias_reservados() {
        return dias_reservados;
    }

    public void setDias_reservados(int dias_reservados) {
        this.dias_reservados = dias_reservados;
    }

    public static Double getValor_diaria() {
        return valor_diaria;
    }

    public void setValor_diaria(Double valor_diaria) {
        this.valor_diaria = valor_diaria;
    }

    public static Double getValor_total() {
        return valor_total;
    }

    public void setValor_total(Double valor_total) {
        this.valor_total = valor_total;
    }
}
