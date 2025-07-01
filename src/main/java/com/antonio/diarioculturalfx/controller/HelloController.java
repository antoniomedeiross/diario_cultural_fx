package com.antonio.diarioculturalfx.controller;

import com.antonio.diarioculturalfx.util.Util;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;

import static com.antonio.diarioculturalfx.DiarioCultural.trocarScene;
import static com.antonio.diarioculturalfx.util.Util.setupHoverEffect;
import static com.antonio.diarioculturalfx.util.Util.showAlert;

import javafx.animation.ScaleTransition;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controlador da tela de início
 */
public class HelloController implements Initializable{

    public ImageView imagemLivro;
    public ImageView imagemBusca;
    public ImageView imagemCamera;
    @FXML
    private VBox card1;

    @FXML
    private VBox card2;

    @FXML
    private VBox card3;

    @FXML
    private Button enterButton;

    /**
     * Método de inicialização
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image imgLivro = new Image(Objects.requireNonNull(getClass()
                .getResourceAsStream("/com/antonio/diarioculturalfx/icons/livro.png")));

        Image imgbusca = new Image(Objects.requireNonNull(getClass()
                .getResourceAsStream("/com/antonio/diarioculturalfx/icons/busca-tela-inicial.png")));

        Image imgCamera = new Image(Objects.requireNonNull(getClass()
                .getResourceAsStream("/com/antonio/diarioculturalfx/icons/camera.png")));

        imagemLivro.setImage(imgLivro);
        imagemBusca.setImage(imgbusca);
        imagemCamera.setImage(imgCamera);

        // Configurar efeitos de hover para os cards
        setupCardHoverEffects();

        // Configurar efeito de hover para o botão
        setupHoverEffect(enterButton);
    }

    /**
     * Adiciona efeito de hover aos cards
     */
    private void setupCardHoverEffects() {
        setupCardHover(card1);
        setupCardHover(card2);
        setupCardHover(card3);
    }

    /**
     * Condigura efeito de hover para uma vBox
     * @param card
     */
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
     * Exibe mensagem ao clicar no card
     * @param event
     */
    @FXML
    private void handleMemoriesClick(MouseEvent event) {
        animateCardClick(card1);
        showAlert("Preserve Memórias", "Preserve suas memórias mais especiais! 📚", Alert.AlertType.INFORMATION);
    }

    /**
     * Exibe mensagem ao clicar no card
     * @param event
     */
    @FXML
    private void handleExperiencesClick(MouseEvent event) {
        animateCardClick(card2);
        showAlert("Registre Experiências", "Registre experiências únicas! 📷", Alert.AlertType.INFORMATION);
    }

    /**
     * Exibe mensagem ao clicar no card
     * @param event
     */
    @FXML
    private void handleExploreClick(MouseEvent event) {
        animateCardClick(card3);
        showAlert("Explore Horizontes", "Explore novos horizontes! 🔍", Alert.AlertType.INFORMATION);
    }

    /**
     * Leva para o menu principal
     */
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


    /**
     * Adiciona animação a uma vBox
     * @param card
     */
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


}