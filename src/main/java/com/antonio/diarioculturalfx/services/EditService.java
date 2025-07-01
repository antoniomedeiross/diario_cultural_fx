package com.antonio.diarioculturalfx.services;

import com.antonio.diarioculturalfx.model.Book;
import com.antonio.diarioculturalfx.model.Film;
import com.antonio.diarioculturalfx.model.Serie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;

import static com.antonio.diarioculturalfx.util.Util.pattern;
import static com.antonio.diarioculturalfx.util.Util.validateRequiredFields;

public class EditService {
    int actualYear = LocalDate.now().getYear();


    public void editMedia(Book livro, String title, String gender, int yearReleased, String author,
                          String publisher, String isbn, boolean haveBook) {
        // Valida se os campos obrigatórios sao nulos
        validateRequiredFields(title, gender, publisher, author, isbn);
        Matcher matcher = pattern.matcher(String.valueOf(yearReleased));
        // Se ano de lançamento > ano atual lança uma excessão
        if(yearReleased > actualYear){
            throw new IllegalArgumentException("Ano de lançamento maior que o ano atual.");
        }
        // Se ano de lancamento nao for no padrão 0000
        if(!matcher.matches()){
            throw new IllegalArgumentException("Ano inválido.");
        }
        livro.setTitle(title);
        livro.setGender(gender);
        livro.setYearReleased(yearReleased);
        livro.setAuthor(author);
        livro.setPublisher(publisher);
        livro.setIsbn(isbn);
        livro.setHaveBook(haveBook);
    }

    public void editMedia(Film filme, String title, String gender, int yearReleased, int duration, String director,
                          String writer, ArrayList<String> cast, String originalTitle, ArrayList<String> whereWatch) {
        // Valida se o campos de Strign são vazios
        validateRequiredFields(title, gender, director, writer, originalTitle);

        if(yearReleased > actualYear){
            throw new IllegalArgumentException("Ano de lançamento maior que o ano atual.");
        }
        Matcher matcher = pattern.matcher(String.valueOf(yearReleased));

        if(!matcher.matches()){
            throw new IllegalArgumentException("Ano inválido.");
        }

        filme.setTitle(title);
        filme.setGender(gender);
        filme.setYearReleased(yearReleased);
        filme.setDuration(duration);
        filme.setDirector(director);
        filme.setWriter(writer);
        filme.setCast(cast);
        filme.setOriginalTitle(originalTitle);
        filme.setWhereWatch(whereWatch);
    }

    public void editMedia(Serie serie, String title, String gender, int yearReleased, int yearEnding,
                          ArrayList<String> cast, String originalTitle, ArrayList<String> whereWatch) {

        Matcher matcher = pattern.matcher(String.valueOf(yearReleased));
        if(!matcher.matches()){
            throw new IllegalArgumentException("Ano inválido.");
        }
        int actualYear = LocalDate.now().getYear();

        if(yearReleased > actualYear){
            throw new IllegalArgumentException("Ano de lançamento maior que o ano atual.");
        }

        validateRequiredFields(title, gender);
        if((yearEnding < yearReleased) ){
            throw new IllegalArgumentException("Ano de encerramento menor que o ano de lançamento.");
        } else if(yearEnding > actualYear){
            throw new IllegalArgumentException("Ano de encerramento maior que o ano atual.");
        }

        serie.setTitle(title);
        serie.setGender(gender);
        serie.setYearReleased(yearReleased);
        serie.setYearEnding(yearEnding);
        serie.setCast(cast);
        serie.setOriginalTitle(originalTitle);
        serie.setWhereWatch(whereWatch);
    }

    public void editSeason(Serie serie, int year, String title) {

        if (year < serie.getYearReleased()) { // Se o ano da temporada for menor que o ano da série
            throw new IllegalArgumentException("Ano da temporada menor que o ano de lançamento da série");
        }
        serie.setYearReleased(year);
        serie.setTitle(title);
    }
}
