package br.com.a3.hotel;

import br.com.a3.hotel.controller.*;
import br.com.a3.hotel.controller.*;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        AppController ac = new AppController();
        ac.iniciar();
    }
}