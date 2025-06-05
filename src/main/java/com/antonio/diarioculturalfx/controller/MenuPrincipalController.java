package com.antonio.diarioculturalfx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import static com.antonio.diarioculturalfx.HelloApplication.trocarScene;

public class MenuPrincipalController {

    @FXML
    protected void onCadastroButtonClick() {
        trocarScene("Cadastro");
    }
    protected void onAvaliarButtonClick() {}
    protected void onListarButtonClick() {}
    protected void onBuscarButtonClick() {}

    @FXML
    protected void onSairButtonClick() {
        trocarScene("Sair");
    }


    @FXML
    private TextField nomeField;

    @FXML
    private TextField generoField;

    @FXML
    protected void onEnviarClick() {
        if (validarCampos()) {
            // prosseguir com o envio
            System.out.println("Formulário enviado!");
        }
    }

    private boolean validarCampos() {
        if (nomeField.getText().trim().isEmpty()) {
            mostrarAlerta("O campo Nome é obrigatório.");
            return false;
        }

        if (generoField.getText().trim().isEmpty()) {
            mostrarAlerta("O campo Gênero é obrigatório.");
            return false;
        }

        return true;
    }

    private void mostrarAlerta(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro de Validação");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
