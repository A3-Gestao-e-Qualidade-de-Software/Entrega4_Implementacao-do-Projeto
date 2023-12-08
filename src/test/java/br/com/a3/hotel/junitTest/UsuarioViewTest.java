package br.com.a3.hotel.junitTest;

import br.com.a3.hotel.view.UsuarioView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UsuarioViewTest {

    @Test
    public void verificaSeOHashDaSenhaEDiferenteDaSenhaOriginal() {
        String senha = "senha123";
        String senhaHashed = UsuarioView.hashSenha(senha);

        Assertions.assertNotEquals(senha, senhaHashed);
    }

    @Test
    public void verificaSeOHashDaSenhaNaoENulo() {
        String senha = "minhaSenha123";
        String senhaHashed = UsuarioView.hashSenha(senha);

        Assertions.assertNotNull(senhaHashed);
    }
}