package View;

import br.com.a3.hotel.controller.*;

import javax.swing.*;
import java.sql.SQLException;

    /**
     * Exibe o menu inicial para o usuário.
     *
     * @return A opção escolhida pelo usuário.
     */

public class AppView {
    public static int menuInicial(){
        int i = Integer.parseInt(JOptionPane.showInputDialog(null, "Escolha uma opcão: \n" +
                "[1] Fazer Login\n" +
                "[2] Cadastrar novo usuário\n" +
                "[3] Sair"));
        return i;
    }

    /**
     * Exibe o menu para um usuário logado.
     *
     * @return A opção escolhida pelo usuário.
     * @throws SQLException            Exceção relacionada ao acesso ao banco de dados.
     * @throws ClassNotFoundException Exceção para a classe não encontrada.
     */
    
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
