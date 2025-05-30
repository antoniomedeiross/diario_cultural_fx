package com.antonio.diarioculturalfx.controller;


import com.antonio.diarioculturalfx.model.Media;
import com.antonio.diarioculturalfx.model.Season;
import com.antonio.diarioculturalfx.model.Serie;
import com.antonio.diarioculturalfx.services.EvaluationService;

/**
 * Controlador de avaliação
 */
public class EvaluationController {
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
}
