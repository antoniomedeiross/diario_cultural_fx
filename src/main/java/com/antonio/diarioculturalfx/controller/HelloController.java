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
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
public class HelloController implements Initializable{

    @FXML
    private VBox card1;

    @FXML
    private VBox card2;

    @FXML
    private VBox card3;

    @FXML
    private Button enterButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurar efeitos de hover para os cards
        setupCardHoverEffects();

        // Configurar efeito de hover para o botão
        setupButtonHoverEffect();
    }

    private void setupCardHoverEffects() {
        setupCardHover(card1);
        setupCardHover(card2);
        setupCardHover(card3);
    }

    private void setupCardHover(VBox card) {
        card.setOnMouseEntered(e -> {
            ScaleTransition scaleIn = new ScaleTransition(Duration.millis(200), card);
            scaleIn.setToX(1.03);
            scaleIn.setToY(1.03);
            scaleIn.play();
        });

        card.setOnMouseExited(e -> {
            ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), card);
            scaleOut.setToX(1.0);
            scaleOut.setToY(1.0);
            scaleOut.play();
        });
    }

    /**
     * Adicona efeitos ao passar mouse pelo botao
     */
    private void setupButtonHoverEffect() { // Copiar para outros controllers
        // efeito quando o mouse entra
        enterButton.setOnMouseEntered(e -> {
            ScaleTransition scaleIn = new ScaleTransition(Duration.millis(150), enterButton);
            enterButton.setStyle("-fx-background-color: linear-gradient(to right, #1d8147, #2ECC71); -fx-background-radius: 25; -fx-padding: 15 40; -fx-cursor: hand; -fx-font-weight: bold; -fx-text-fill: white;");
            scaleIn.setToX(1.03);
            scaleIn.setToY(1.03);
            scaleIn.play();

        });
        // quando mouse sai do botao retira efeito
        enterButton.setOnMouseExited(e -> {
            ScaleTransition scaleOut = new ScaleTransition(Duration.millis(150), enterButton);
            enterButton.setStyle("-fx-background-color: linear-gradient(to right, #4A90E2, #60b4ec); -fx-background-radius: 25;-fx-padding: 15 40; -fx-cursor: hand; -fx-font-size: 20");
            scaleOut.setToX(1.0);
            scaleOut.setToY(1.0);
            scaleOut.play();
        });
    }

    @FXML
    private void handleMemoriesClick(MouseEvent event) {
        animateCardClick(card1);
        showAlert("Preserve Memórias", "Preserve suas memórias mais especiais! 📚", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void handleExperiencesClick(MouseEvent event) {
        animateCardClick(card2);
        showAlert("Registre Experiências", "Registre experiências únicas! 📷", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void handleExploreClick(MouseEvent event) {
        animateCardClick(card3);
        showAlert("Explore Horizontes", "Explore novos horizontes! 🔍", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void handleEnterButton() {
        // Animação de clique no botão
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100), enterButton);
        scaleDown.setToX(0.95);
        scaleDown.setToY(0.95);

        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(100), enterButton);
        scaleUp.setToX(1.0);
        scaleUp.setToY(1.0);

        scaleDown.play();
        trocarScene("Entrar");

    }

    private void animateCardClick(VBox card) {
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100), card);
        scaleDown.setToX(0.95);
        scaleDown.setToY(0.95);

        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(100), card);
        scaleUp.setToX(1.0);
        scaleUp.setToY(1.0);

        scaleDown.setOnFinished(e -> scaleUp.play());
        scaleDown.play();
    }

    static void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        // Personalizar o estilo do Alert
        alert.getDialogPane().setStyle(
                "-fx-background-color: linear-gradient(to bottom right, #4A90E2, #7B68EE, #DA70D6, #FF69B4); " +
                        "-fx-border-color: #dee2e6; " +
                        "-fx-border-radius: 10; " +
                        "-fx-background-radius: 10;"+
                        "-fx-text-alignment: center;"
        );

        alert.showAndWait();
    }
}