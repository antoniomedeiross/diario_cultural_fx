package com.antonio.diarioculturalfx.controller;


import javafx.fxml.FXML;
import com.antonio.diarioculturalfx.model.Serie;
import com.antonio.diarioculturalfx.model.Film;
import com.antonio.diarioculturalfx.model.Book;
import com.antonio.diarioculturalfx.model.Season;
//

import com.antonio.diarioculturalfx.repository.MemoryManagement;
import com.antonio.diarioculturalfx.services.MemoryManagementService;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.util.ArrayList;


/**
 * Controlador de manejo de memoria
 */
public class MemoryManagementController {
    MemoryManagement memoryManagement;

    /**
     * Construtor
     * @param memoryManagement
     */
    public MemoryManagementController(MemoryManagement memoryManagement) {
        this.memoryManagement = memoryManagement;
    }

    // Registro de livros

    /**
     * Resgistra um livros
     * @param title
     * @param gender
     * @param yearReleased
     * @param author
     * @param publisher
     * @param isbn
     * @param haveBook
     * @return
     */
    public String registerMedia(String title, String gender, int yearReleased, String author,
        String publisher, String isbn, boolean haveBook){
        MemoryManagementService memoryManagementService = new MemoryManagementService(memoryManagement);


        try {
            memoryManagementService.registerMedia(title, gender, yearReleased, author,
                publisher, isbn, haveBook);
        } catch (IllegalArgumentException e) {
            return "Tentativa de registro FALHO - " + e.getMessage();
        }

        return "Livro registrado com SUCESSO!";
    }

    // Registro de Filmes

    /**
     * Resgistra filmes
     * @param title
     * @param gender
     * @param yearReleased
     * @param duration
     * @param director
     * @param writer
     * @param cast
     * @param originalTitle
     * @param whereWatch
     * @return
     */
    public String registerMedia(String title, String gender, int yearReleased, int duration, String director,
        String writer, ArrayList<String> cast, String originalTitle, ArrayList<String> whereWatch) {
        MemoryManagementService memoryManagementService = new MemoryManagementService(memoryManagement);

        try{
            memoryManagementService.registerMedia(title, gender, yearReleased, duration, director,
                writer, cast, originalTitle, whereWatch);
        } catch (IllegalArgumentException e) {
            return "Tentativa de registro FALHO - " + e.getMessage();
        }

        return "Filme registrado com SUCESSO!";
    }

    // Registro de Series

    /**
     * Registra séries
     * @param title
     * @param gender
     * @param yearReleased
     * @param yearEnding
     * @param cast
     * @param originalTitle
     * @param whereWatch
     * @return
     */
    public String registerMedia(String title, String gender, int yearReleased, int yearEnding,
        ArrayList<String> cast, String originalTitle, ArrayList<String> whereWatch) {
        MemoryManagementService memoryManagementService = new MemoryManagementService(memoryManagement);

        try {
            memoryManagementService.registerMedia(title, gender, yearReleased, yearEnding, cast, originalTitle, whereWatch);
        } catch (IllegalArgumentException e) {
            return "Tentativa de registro FALHO - " + e.getMessage();
        }

        return "Serie registrada com SUCESSO!";
    }

    /**
     * Registra temporadas
     * @param serie
     * @param title
     * @param year
     * @param numberEpisodes
     * @return
     */
    public String registerSeason(Serie serie, String title, int year, int numberEpisodes) {
        MemoryManagementService memoryManagementService = new MemoryManagementService(memoryManagement);
        try {
            memoryManagementService.registerMedia(serie, year, title, numberEpisodes);
        } catch (IllegalArgumentException e) {
            return "Tentativa de registro FALHO - " + e.getMessage();
        }

        return "Temporada registrada com SUCESSO!";
    }

    /**
     * Salva mídias em arquivo .json
     */
    public String salvarArquivos() {
        try {
            memoryManagement.salvaArquivosBd();
            return "Arquivos salvo com SUCESSO!";
        } catch (IllegalArgumentException e) {
            return "Tentativa de salvar arquivos FALHO - " + e.getMessage();
        }
    }

    /**
     * Deleta Filme
     * @param film
     */
    public String deleteMedia(Film film){
        try{
            memoryManagement.deleteMedia(film);
        } catch(Exception e){
            return "Tentativa de deletar filme Falho - " + e.getMessage();
        }

        return "Filme deletado com SUCESSO";
    }

    /**
     * Deleta Série
     * @param serie
     */
    public String deleteMedia(Serie serie){
        try{
            memoryManagement.deleteMedia(serie);
        } catch(Exception e){
            return "Tentativa de deletar série Falho - " + e.getMessage();
        }

        return "Série deletada com SUCESSO";
    }

    /**
     * Deleta Livro
     * @param book
     */
    public String deleteMedia(Book book){
        try{
            memoryManagement.deleteMedia(book);
        } catch(Exception e){
            return "Tentativa de deletar livro Falho - " + e.getMessage();
        }

        return "Livro deletado com SUCESSO";
    }

    /**
     * Deleta temporada
     * @param season
     */
    public String deleteMedia(Season season, Serie serie) {
        try {
            serie.deleteSeason(season);
        } catch (Exception e) {
            return "Tentativa de deletar Temporada Falho - " + e.getMessage();
        }

        return "Temporada deletada com SUCESSO";
    }


}
