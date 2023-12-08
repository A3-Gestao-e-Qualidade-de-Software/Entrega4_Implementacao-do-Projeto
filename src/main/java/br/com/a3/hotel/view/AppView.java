package View;

import br.com.a3.hotel.controller.*;

import javax.swing.*;
import java.sql.SQLException;

public class AppView {
    public static int menuInicial(){
        int i = Integer.parseInt(JOptionPane.showInputDialog(null, "Escolha uma opcão: \n" +
                "[1] Fazer Login\n" +
                "[2] Cadastrar novo usuário\n" +
                "[3] Sair"));
        return i;
    }

    public static int menuLogado() throws SQLException, ClassNotFoundException {
        try{

        int i = Integer.parseInt(JOptionPane.showInputDialog(null, "Menu: \n" +
                "[1] Hospedes\n" +
                "[2] Quartos\n" +
                "[3] Reservas\n" +
                "[4] Sair"));
        if (i == 4){
            System.exit(0);
        }
        return i;
        }catch (NumberFormatException e){
            return 0;
        }
    }
}