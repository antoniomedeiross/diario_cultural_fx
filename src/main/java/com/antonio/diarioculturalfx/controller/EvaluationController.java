package com.antonio.diarioculturalfx.controller;


import com.antonio.diarioculturalfx.model.Media;
import com.antonio.diarioculturalfx.model.Season;
import com.antonio.diarioculturalfx.model.Serie;
import com.antonio.diarioculturalfx.services.EvaluationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import static com.antonio.diarioculturalfx.DiarioCultural.trocarScene;
import static com.antonio.diarioculturalfx.util.Util.addImgOnButton;
import static com.antonio.diarioculturalfx.util.Util.setupHoverEffect;

/**
 * Controlador de avaliação
 */
public class EvaluationController implements Initializable {
    /**
     * Avalia uma Media
     * @param media
     * @param note
     * @param comment
     * @param readWatch
     * @param whenReadWatch
     * @return
     */
    public String evaluate(Media media, int note, String comment, boolean readWatch, String whenReadWatch) {
        try {
            EvaluationService evaluationService = new EvaluationService();
            evaluationService.evaluate(media, note, comment, readWatch, whenReadWatch);
            return "Avaliação BEM SUCEDIDA";
        } catch(IllegalArgumentException e) {
            return "Avaliação MAL SUCEDIDA";
        }
    }

    /**
     * Avalia temporadas
     * @param season
     * @param note
     * @param comment
     * @param readWatch
     * @param whenReadWatch
     * @return
     */
    public String evaluate(Season season, int note, String comment, boolean readWatch, String whenReadWatch, Serie serie) {
        try {
            EvaluationService evaluationService = new EvaluationService();
            evaluationService.evaluate(season, note, comment, readWatch, whenReadWatch, serie);

            return "Avaliação de temporada BEM SUCEDIDA";
        } catch(IllegalArgumentException e) {
            return "Avaliação temporada MAL SUCEDIDA";
        }
    }



    // Controller dos elementos FX

    @FXML
    private Button bt_livro;
    @FXML
    private Button bt_filme;
    @FXML
    private Button bt_serie;
    @FXML
    private Button voltarButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addImgOnButton("/com/antonio/diarioculturalfx/icons/voltar.png" ,voltarButton);
        if(bt_filme != null && bt_livro != null && bt_serie != null) {
            setupHoverEffect(bt_livro);
            setupHoverEffect(bt_filme);
            setupHoverEffect(bt_serie);
        }
    }


    @FXML
    private void handleVoltarAvaliacao(){
        trocarScene("Avaliar");
    }
    // Voltar menu
    @FXML
    private void handleVoltarMenu() {
        trocarScene("Entrar");
    }


    @FXML
    public void mudarSceneAvaliacao(ActionEvent actionEvent) {
        if(actionEvent.getSource() == bt_livro) {
            trocarScene("Avaliar-livro");
        } else if(actionEvent.getSource() == bt_filme) {
            trocarScene("Avaliar-filme");
        } else if(actionEvent.getSource() == bt_serie) {
            trocarScene("Avaliar-serie");
        }
    }




}
