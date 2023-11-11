package br.com.a3.hotel.controller;

import br.com.a3.hotel.DAO.*;
import br.com.a3.hotel.model.*;
import br.com.a3.hotel.view.*;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioController {
    public int fazerLogin() throws SQLException, ClassNotFoundException {
        UsuarioView usuario_view = new UsuarioView();
        UsuarioModel Usuario = usuario_view.autenticarUsuario();
        UsuarioDAO usuario_DAO = new UsuarioDAO();
        ResultSet resultSet = usuario_DAO.autenticarUsuario(Usuario);
        if (resultSet.next()){
            JOptionPane.showMessageDialog(null, "Login feito com sucesso!!");
            int menu = View.AppView.menuLogado();
            while (menu < 1 || menu > 4){
                menu = View.AppView.menuLogado();
            }
            return menu;
        }else {
            JOptionPane.showMessageDialog(null, "Funcional e/ou Senha Inválida!");
            AppController ac = new AppController();
            ac.iniciar();
            return 4;
        }
    }
}
