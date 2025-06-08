package com.antonio.diarioculturalfx.controller;

import com.antonio.diarioculturalfx.HelloApplication;
import com.antonio.diarioculturalfx.repository.MemoryManagement;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import static com.antonio.diarioculturalfx.HelloApplication.memoryManagement;
import static com.antonio.diarioculturalfx.HelloApplication.trocarScene;

public class MenuPrincipalController {
    MemoryManagementController memoryManagementController = new MemoryManagementController(memoryManagement);
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



    // Tela cadastro
    @FXML
    private TextField nomeField;
    @FXML
    private TextField generoField;
    @FXML
    private DatePicker publicacaoField;
    @FXML
    private TextField autorField;
    @FXML
    private TextField editoraField;
    @FXML
    private CheckBox possuiField;
    @FXML
    private TextField isbnField;


    @FXML
    protected void onEnviarClick() {
        if (validarCampos()) {
            // prosseguir com o envio
            System.out.println("Chamando o cadastro...");
            System.out.println(memoryManagementController.registerMedia(nomeField.getText(), generoField.getText(), publicacaoField.getValue().getYear(), autorField.getText(),editoraField.getText(),
                    isbnField.getText(), possuiField.isSelected()));
        }
    }

    private boolean validarCampos() {
        if (nomeField.getText().trim().isEmpty() || generoField.getText().trim().isEmpty() || publicacaoField.getValue() == null || autorField.getText().trim().isEmpty()
                || autorField.getText().trim().isEmpty() || editoraField.getText().trim().isEmpty() || isbnField.getText().trim().isEmpty()) {
            mostrarAlerta();
            return false;
        }

        return true;
    }


    private void mostrarAlerta() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro de Validação");
        alert.setHeaderText(null);
        alert.setContentText("Preencha todos os campos!.");
        alert.showAndWait();
    }
}
