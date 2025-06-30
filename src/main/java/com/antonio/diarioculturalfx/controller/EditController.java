package com.antonio.diarioculturalfx.controller;

import com.antonio.diarioculturalfx.model.Media;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import static com.antonio.diarioculturalfx.DiarioCultural.trocarScene;
import static com.antonio.diarioculturalfx.util.Util.addImgOnButton;

public class EditController implements Initializable {

    @FXML
    private Button voltarButton;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addImgOnButton("/com/antonio/diarioculturalfx/icons/voltar.png" ,voltarButton);
    }

    public void setMediaParaEditar(Media mediaSelecionada) {

    }

    public void handleVoltarMenu(ActionEvent actionEvent) {
        trocarScene("Entrar");
    }
}
