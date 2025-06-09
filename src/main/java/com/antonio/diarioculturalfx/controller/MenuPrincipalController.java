package com.antonio.diarioculturalfx.controller;

import com.antonio.diarioculturalfx.HelloApplication;
import com.antonio.diarioculturalfx.repository.MemoryManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

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



    @ FXML
    private VBox div_titulo;

    public void mudarSceneCadastro(ActionEvent actionEvent) throws IOException {
        Button botao = (Button) actionEvent.getSource();
        String destino = botao.getText();

        String caminho = switch (destino) {
            case "Livro" -> "cadastro-livro.fxml";
            case "Filme" -> "cadastro-filme.fxml";
            case "Série" -> "cadastro-serie.fxml";
            default -> null;
        };

        if (caminho != null) {
            Parent formulario = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/antonio/diarioculturalfx/view/sub_menus/" + caminho)));
            div_titulo.getChildren().setAll(formulario);
        }
    }


}
