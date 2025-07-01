package com.antonio.diarioculturalfx.services;

import com.antonio.diarioculturalfx.controller.MemoryManagementController;
import com.antonio.diarioculturalfx.model.*;

import java.time.LocalDate;
import java.util.regex.Matcher;

import static com.antonio.diarioculturalfx.util.Util.pattern;
import static com.antonio.diarioculturalfx.util.Util.validateRequiredFields;

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

        validateRequiredFields(String.valueOf(note), comment, whenReadWatch);

        if (note < 1 || note > 5 || media instanceof Serie) { // Se a nota não estiver no intervalo lança uma excessao
            throw new IllegalArgumentException("Avaliação inválida.");
        }
        validaData(whenReadWatch);
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
            throw new IllegalArgumentException("Avaliação inválida.");
        }
        validaData(whenReadWatch);
        serie.setNote(note);
        Review review = new Review(note, comment, readWatch, whenReadWatch);
        season.setReview(review);
        serie.updateNote(); // atualiza a nota da Série
    }

    private void validaData(String whenReadWatch) {
        Matcher matcher = pattern.matcher(String.valueOf(whenReadWatch));
        int actualYear = LocalDate.now().getYear();
        // Se ano de lancamento nao for no padrão 0000
        if(!matcher.matches()){
            throw new IllegalArgumentException("Ano inválido.");
        }
        // Se ano de lançamento > ano atual lança uma excessão
        if(Integer.parseInt(whenReadWatch) > actualYear){
            throw new IllegalArgumentException("Ano de lançamento maior que o ano atual.");
        }
    }
}
