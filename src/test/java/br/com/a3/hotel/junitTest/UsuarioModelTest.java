package br.com.a3.hotel.junitTest;

import br.com.a3.hotel.model.UsuarioModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioModelTest {

    UsuarioModel usuario;

    @Test
    public void deveCriarUmUsuarioValido(){
        usuario = new UsuarioModel(
                "Joao da Silva",
                "JoaoSilva",
                "12345");

        Assertions.assertNotNull(usuario);
        Assertions.assertEquals("Joao da Silva", usuario.getNomeUsuario());
        Assertions.assertEquals("JoaoSilva", usuario.getFuncional());
        Assertions.assertEquals("12345", usuario.getSenhaUsuario());
    }

}