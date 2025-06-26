package com.antonio.diarioculturalfx.util;

import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.InputStream;

public class Util {

    public static void addImgOnButton(String caminho, Button button) {
        InputStream imgStream = Util.class.getResourceAsStream(caminho);
        if (imgStream != null && button != null) {
            ImageView imgView = new ImageView(new Image(imgStream));
            imgView.setFitWidth(40);
            imgView.setFitHeight(40);
            button.setGraphic(imgView);
        } else {
            System.out.println("Imagem não encontrada!");
        }
    }

    public static void deixaTamanhoDaSceneIgual(Stage stage) {
        double largura = stage.getWidth();
        double altura = stage.getHeight();
        boolean isMaximized = stage.isMaximized();
        boolean isFullScreen = stage.isFullScreen();

        // Restabelece exatamente o estado anterior:
        stage.setWidth(largura);
        stage.setHeight(altura);
        stage.setMaximized(isMaximized);
        stage.setFullScreen(isFullScreen);
    }

    public static void setupHoverEffect(Button botao) { // Copiar para outros controllers
        // efeito quando o mouse entra
        botao.setOnMouseEntered(e -> {
            ScaleTransition scaleIn = new ScaleTransition(Duration.millis(150), botao);
            botao.setStyle("-fx-background-color: linear-gradient(to left, #1d8147, #2ECC71); -fx-background-radius: 25; -fx-padding: 15 40; -fx-cursor: hand; -fx-font-weight: bold; -fx-text-fill: white;");
            scaleIn.setToX(1.03);
            scaleIn.setToY(1.03);
            scaleIn.play();

        });
        // quando mouse sai do botao retira efeito
        botao.setOnMouseExited(e -> {
            ScaleTransition scaleOut = new ScaleTransition(Duration.millis(150), botao);
            botao.setStyle("-fx-background-color: linear-gradient(to right, #4A90E2, #60b4ec); -fx-background-radius: 25;-fx-padding: 15 40; -fx-cursor: hand; -fx-font-size: 20");
            scaleOut.setToX(1.0);
            scaleOut.setToY(1.0);
            scaleOut.play();
        });
    }

}
