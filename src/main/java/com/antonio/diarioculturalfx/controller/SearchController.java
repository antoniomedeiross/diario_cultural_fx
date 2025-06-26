package com.antonio.diarioculturalfx.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import static com.antonio.diarioculturalfx.DiarioCultural.trocarScene;
import static com.antonio.diarioculturalfx.util.Util.addImgOnButton;
import static com.antonio.diarioculturalfx.util.Util.setupHoverEffect;

public class SearchController implements Initializable {
    @FXML
    public Button bt_busca;
    @FXML
    public Button voltarButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addImgOnButton("/com/antonio/diarioculturalfx/icons/voltar.png" ,voltarButton);
        if(bt_busca != null ) {
            setupHoverEffect(bt_busca);
        }
    }


    // Voltar menu
    @FXML
    private void handleVoltarMenu() {
        trocarScene("Entrar");
    }
    
    @FXML
    private void buscar() {
        
    }
}
