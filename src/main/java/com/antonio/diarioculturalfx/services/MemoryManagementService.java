package com.antonio.diarioculturalfx.services;

import com.antonio.diarioculturalfx.model.Book;
import com.antonio.diarioculturalfx.model.Film;
import com.antonio.diarioculturalfx.model.Season;
import com.antonio.diarioculturalfx.model.Serie;
import com.antonio.diarioculturalfx.repository.MemoryManagement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe que representa os servições de memória
 */
public class MemoryManagementService {
    private final MemoryManagement memoryManagement;
    private final Pattern pattern = Pattern.compile("^[1-9]\\d{3}$");
    private final LocalDate now = LocalDate.now();

    /**
     * Construtor
     * @param memoryManagement
     */
    public MemoryManagementService(MemoryManagement memoryManagement) {
        this.memoryManagement = Objects.requireNonNull(memoryManagement, "memoryManagement cannot be null");
    }

    // Registra BOOK

    /**
     * Cria um livro e adiciona-o no banco de dados
     * @param title
     * @param gender
     * @param yearReleased
     * @param author
     * @param publisher
     * @param isbn
     * @param haveBook
     */
    public void registerMedia (String title, String gender, int yearReleased, String author,
        String publisher, String isbn, boolean haveBook){

        // Valida se os campos obrigatórios sao nulos
        validateRequiredFields(title, gender, publisher, author);
        Matcher matcher = pattern.matcher(String.valueOf(yearReleased));
        int actualYear = LocalDate.now().getYear();
        // Se ano de lançamento > ano atual lança uma excessão
        if(yearReleased > actualYear){
            throw new IllegalArgumentException("Ano de lançamento maior que o ano atual.");
        }
        // Se ano de lancamento nao for no padrão 0000
        if(!matcher.matches()){
            throw new IllegalArgumentException("Ano inválido.");
        }
        // Se chegou aqui tudo esta nos trilhos
        Book book = new Book(title, gender, yearReleased, author, publisher, isbn, haveBook);
        memoryManagement.addBook(book); // Adiciona ao banco de dados
    }

    // Registra Filme

    /**
     * Cria um filme e o adiciona no banco de dados
     * @param title
     * @param gender
     * @param yearReleased
     * @param duration
     * @param director
     * @param writer
     * @param cast
     * @param originalTitle
     * @param whereWatch
     */
    public void registerMedia(String title, String gender, int yearReleased, int duration, String director,
        String writer, ArrayList<String> cast, String originalTitle, ArrayList<String> whereWatch){

        // Valida se o campos de Strign são vazios
        validateRequiredFields(title, gender, director, writer);
        int actualYear = LocalDate.now().getYear();

        if(yearReleased > actualYear){
            throw new IllegalArgumentException("Ano de lançamento maior que o ano atual.");
        }
        Matcher matcher = pattern.matcher(String.valueOf(yearReleased));


        if(!matcher.matches()){
            throw new IllegalArgumentException("Ano inválido.");
        }

        Film film = new Film(title, gender, yearReleased, duration, director, writer, cast, originalTitle, whereWatch);
        memoryManagement.addFilm(film);
    }

    // Registra Série

    /**
     * Cria uma série e a adiciona no banco de dados
     * @param title
     * @param gender
     * @param yearReleased
     * @param yearEnding
     * @param cast
     * @param originalTitle
     * @param whereWatch
     */
    public void registerMedia(String title, String gender, int yearReleased, int yearEnding,
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
        } else if(yearEnding > now.getYear()){
            throw new IllegalArgumentException("Ano de encerramento maior que o ano atual.");
        }
        Serie serie = new Serie(title, gender, yearReleased, yearEnding, cast, originalTitle,  whereWatch);
        memoryManagement.addSerie (serie);
    }

    // Registra temporada

    /**
     * Cria e registra uma temporada
     * @param serie
     * @param year
     * @param title
     * @param numberEpisodes
     */
    public void registerMedia(Serie serie, int year, String title, int numberEpisodes) {
        if(year < serie.getYearReleased()) { // Se o ano da temporada for menor que o ano da série
            throw new IllegalArgumentException("Ano da temporada menor que o ano de lançamento da série");
        }
        Season season = new Season(year, title, numberEpisodes);
        serie.setSeason(season); // Adiciona a temporada na série
    }

    // Metodo para validar os capos, se for nulo ele lança uma excessão

    /**
     * Avalia se os campos sao nulos
     * @param fields
     */
    private void validateRequiredFields(String ... fields) {
        for (String field : fields) {
            if (field == null || field.trim().isEmpty()) {
                throw new IllegalArgumentException("Campos obrigatórios não podem ser nulos ou vazios");
            }
        }
    }
}
