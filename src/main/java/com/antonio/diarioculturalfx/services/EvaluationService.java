package com.antonio.diarioculturalfx.services;

import com.antonio.diarioculturalfx.controller.MemoryManagementController;
import com.antonio.diarioculturalfx.model.*;

/**
 * Classe que representa os serviços de avaliação
 */
public class EvaluationService {
    /**
     * Avalia a Filme e Livro se os parâmetros estiverem corretos
     * @param media
     * @param note
     * @param comment
     * @param readWatch
     * @param whenReadWatch
     */
    public void evaluate(Media media, int note, String comment, boolean readWatch, String whenReadWatch) { // Avaliação de Livros e Filmes

        if (note < 1 || note > 5 || media instanceof Serie) { // Se a nota não estiver no intervalo lança uma excessao
            throw new IllegalArgumentException("Invalid Evaluation");
        }
        media.getReview().setNote(note);
        media.getReview().setComment(comment);
        media.getReview().setReadWatch(readWatch);
        media.getReview().setWhenReadWatch(whenReadWatch);

    }


    /**
     * Avalia a série se os parâmetros estiverem corretos
     * @param season
     * @param note
     * @param comment
     * @param readWatch
     * @param whenReadWatch
     */
    public void evaluate(Season season, int note, String comment, boolean readWatch, String whenReadWatch, Serie serie) { // Avaliação de temporada das Series
        if (note < 1 || note > 5) { // Se a nota não estiver no intervalo lança uma excessao
            throw new IllegalArgumentException("Invalid Evaluation");
        }
        serie.setNote(note);
        Review review = new Review(note, comment, readWatch, whenReadWatch);
        season.setReview(review);
        serie.updateNote(); // atualiza a nota da Série
    }
}
