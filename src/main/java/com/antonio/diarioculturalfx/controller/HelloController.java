package com.antonio.diarioculturalfx.controller;

import com.antonio.diarioculturalfx.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import static com.antonio.diarioculturalfx.HelloApplication.trocarScene;

public class HelloController {

    @FXML
    protected void onEntrarButtonClick() {
        System.out.println("Entrando no Controller");
        // carregar menu principal
        trocarScene("Entrar");

    }
}