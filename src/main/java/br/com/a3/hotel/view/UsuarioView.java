package br.com.a3.hotel.view;

import br.com.a3.hotel.model.*;

import javax.swing.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * A classe UsuarioView contém métodos para interações relacionadas aos usuários na interface de usuário.
 */

public class UsuarioView {

    /**
     * Realiza o cadastro de um novo usuário.
     *
     * @return Um objeto UsuarioModel contendo os detalhes do novo usuário.
     */
    
    public UsuarioModel cadastrarNovoUsuario() {

        String usuario = JOptionPane.showInputDialog(null, "Nome Completo:");
        String funcional = JOptionPane.showInputDialog(null, "Funcional (9 dígitos):");
        // Exibir a caixa de diálogo de senha de forma segura
        char[] senhaChars = mostrarCampoSenhaSeguro();
        String senha = null;
        if (senhaChars != null) {
            senha = new String(senhaChars);
            while (senha.contains(" ") || senha.length() < 5){
                JOptionPane.showMessageDialog(null, "- A senha deve conter no mínimo 5 dígitos\n" +
                        "- A senha não pode conter caracteres em branco.");
                senhaChars = mostrarCampoSenhaSeguro();
                if (senhaChars != null) {
                    senha = new String(senhaChars);}
            }

            senha = hashSenha(senha);
        }
        return new UsuarioModel(usuario, funcional, senha);
    }

        /**
     * Autentica um usuário existente.
     *
     * @return Um objeto UsuarioModel contendo as credenciais do usuário para autenticação.
     */

    public UsuarioModel autenticarUsuario() {
        String funcional = JOptionPane.showInputDialog(null, "Funcional (9 dígitos):");
        // Exibir a caixa de diálogo de senha de forma segura
        char[] senhaChars = mostrarCampoSenhaSeguro();
        String senha = null;
        if (senhaChars != null) {
            senha = new String(senhaChars);

            senha = hashSenha(senha);
        }
        return new UsuarioModel(null,funcional, senha);
    }

        /**
     * Mostra um diálogo de senha de forma segura para o usuário.
     *
     * @return Um array de caracteres representando a senha inserida pelo usuário.
     */
    private char[] mostrarCampoSenhaSeguro() {
        JPasswordField cxSenha = new JPasswordField();
        int resultado = JOptionPane.showConfirmDialog(null, cxSenha, "Senha:",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (resultado == JOptionPane.OK_OPTION) {
//            String senha = new String(cxSenha.getPassword());

//            if(senha.contains(" ") || senha.length() < 5){
//                JOptionPane.showMessageDialog(null, "- A senha deve conter no mínimo 5 dígitos\n" +
//                        "- A senha não pode conter caracteres em branco.");
//                return mostrarCampoSenhaSeguro();
//            } else {
            return cxSenha.getPassword();
//            }
        } else {
            return null; // O usuário cancelou
        }
    }


    /**
     * Criptografa a senha usando o algoritmo SHA-256.
     *
     * @param senha A senha a ser criptografada.
     * @return A representação hexadecimal do hash da senha.
     */
    public static String hashSenha(String senha) {
        try {
            // Cria uma instância de MessageDigest com o algoritmo SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Converte a senha em bytes
            byte[] bytes = senha.getBytes();

            // Atualiza o MessageDigest com os bytes da senha
            md.update(bytes);

            // Obtem o hash da senha
            byte[] senhaHashBytes = md.digest();

            // Converte o hash em uma representação hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : senhaHashBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
