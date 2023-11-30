package br.com.a3.hotel.junitTest;

import br.com.a3.hotel.model.HospedesModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.a3.hotel.view.*;

class HospedesViewTest {

    HospedesView hospedesView = new HospedesView();
    @Test
    public void deveRetornarUmHospedeValido(){
        HospedesModel hospedeValido = hospedesView.cadastrarNovoHospede();
        Assertions.assertNotNull(hospedeValido);
    }

    @Test
    public void deveMostrarTelaDeletarHospede() {

        String cpfHospede = hospedesView.mostrarTelaDeletarHospede();
        Assertions.assertNotNull(cpfHospede);
    }

}