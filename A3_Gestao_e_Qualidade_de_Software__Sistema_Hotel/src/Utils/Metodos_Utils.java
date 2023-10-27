package Utils;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Metodos_Utils {

    public String solicitarData() {
        Date data_nascimento;
        SimpleDateFormat dateFormat;

        try {
            // Solicita ao usuário que insira a data de nascimento como texto
            String dataNascimentoStr = JOptionPane.showInputDialog(null, "Digite a data de nascimento (dd/MM/yyyy):");

            // Se clicar em cancelar ou confirmar valor em branco, retornar null
            if (dataNascimentoStr == null){
                return null;
            }

            // Verificar se a entrada possui o formato correto (dd/MM/yyyy)
            if (isValidDateFormat(dataNascimentoStr)) {
                // Parsear a string da data de nascimento em um objeto java.util.Date
                Date dataUtil = new SimpleDateFormat("dd/MM/yyyy").parse(dataNascimentoStr);

                // Criar uma instância de java.sql.Date a partir do valor numérico do java.util.Date
                data_nascimento = new Date(dataUtil.getTime());

                // Exibir a data formatada
                dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            } else {
                JOptionPane.showMessageDialog(null, "Data de nascimento inválida.\nUse o formato dd/MM/yyyy.");
                return solicitarData();
            }
        } catch (ParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao analisar a data de nascimento.");
            JOptionPane.showMessageDialog(null, "Data de nascimento inválida.\nUse o formato dd/MM/yyyy.");
//            solicitarData();
            return solicitarData();
        }
        return dateFormat.format(data_nascimento);

    }
    // Método para verificar se a data está no formato dd/MM/yyyy
    private boolean isValidDateFormat(String dateStr) {
        return dateStr.matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$");
    }




    public String selecionarGenero() {
        String[] opcoes = {"M", "F"};
        int escolha = JOptionPane.showOptionDialog(
                null,
                "Selecione o Gênero:",
                "Escolher Gênero",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0] // Valor padrão
        );

        if (escolha >= 0 && escolha < opcoes.length) {
            return opcoes[escolha];
        } else {
            return ""; // Valor padrão
        }
    }
}
