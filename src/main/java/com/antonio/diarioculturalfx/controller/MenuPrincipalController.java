package com.antonio.diarioculturalfx.controller;

import javafx.fxml.FXML;

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
}
