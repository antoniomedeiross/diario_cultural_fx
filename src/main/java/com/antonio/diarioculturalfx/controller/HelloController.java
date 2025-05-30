package com.antonio.diarioculturalfx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("PARABÉNS VC CLICOU NUM BUTAUM!");
    }
}