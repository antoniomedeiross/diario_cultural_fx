package com.antonio.diarioculturalfx.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

import static com.antonio.diarioculturalfx.DiarioCultural.trocarScene;
import static com.antonio.diarioculturalfx.util.Util.setupHoverEffect;

public class MenuPrincipalController implements  Initializable {

    @FXML
    private VBox div_titulo;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Carrega efeitos dos botões
        if (div_titulo != null) {
            for (Node node : div_titulo.getChildren()) {
                if (node instanceof Button button) {
                    setupHoverEffect(button);
                }
            }
        }
    }

    @FXML
    protected void onCadastroButtonClick() { trocarScene("Cadastro"); }
    @FXML
    protected void onAvaliarButtonClick() { trocarScene("Avaliar"); }
    @FXML
    protected void onListarButtonClick() { trocarScene("Listar"); }
    @FXML
    protected void onBuscarButtonClick() { trocarScene("Buscar"); }

    // Sai da aplicação
    @FXML
    protected void onSairButtonClick() {
        trocarScene("Sair");
    }


}
