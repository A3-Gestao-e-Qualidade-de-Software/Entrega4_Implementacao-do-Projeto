import Controller.AppController;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        AppController ac = new AppController();
        ac.iniciar();
    }
}