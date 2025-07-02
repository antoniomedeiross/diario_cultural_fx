package com.antonio.diarioculturalfx.controller;


import com.antonio.diarioculturalfx.model.*;
import com.antonio.diarioculturalfx.repository.MemoryManagement;
import com.antonio.diarioculturalfx.services.MemoryManagementService;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Controlador de manejo de memoria
 */
public class MemoryManagementController {
    MemoryManagement memoryManagement;

    /**
     * Construtor
     * @param memoryManagement ponteiro do memorymanagement
     */
    public MemoryManagementController(MemoryManagement memoryManagement) {
        this.memoryManagement = memoryManagement;
    }

    // Registro de livros

    /**
     * Resgistra um livros
     * @param title titulo
     * @param gender genero
     * @param yearReleased ano de lancamento
     * @param author autor
     * @param publisher editora
     * @param isbn isbn
     * @param haveBook possui
     * @return resultado
     */
    public String registerMedia(String title, String gender, int yearReleased, String author,
        String publisher, String isbn, boolean haveBook){
        MemoryManagementService memoryManagementService = new MemoryManagementService(memoryManagement);


        try {
            memoryManagementService.registerMedia(title, gender, yearReleased, author,
                publisher, isbn, haveBook);
        } catch (IllegalArgumentException e) {
            return "Tentativa de registro FALHO\n" + e.getMessage();
        }

        return "Registrado com SUCESSO!";
    }

    // Registro de Filmes

    /**
     * Resgistra filmes
     * @param title titulo
     * @param gender genero
     * @param yearReleased ano de lancamento
     * @param duration duração
     * @param director diretor
     * @param writer escritor
     * @param cast elenco
     * @param originalTitle titulo original
     * @param whereWatch onde assistir
     * @return resultado
     */
    public String registerMedia(String title, String gender, int yearReleased, int duration, String director,
        String writer, ArrayList<String> cast, String originalTitle, ArrayList<String> whereWatch) {
        MemoryManagementService memoryManagementService = new MemoryManagementService(memoryManagement);

        try{
            memoryManagementService.registerMedia(title, gender, yearReleased, duration, director,
                writer, cast, originalTitle, whereWatch);
        } catch (IllegalArgumentException e) {
            return "Tentativa de registro FALHO\n" + e.getMessage();
        }

        return "Registrado com SUCESSO!";
    }

    // Registro de Series

    /**
     * Registra séries
     * @param title titulo
     * @param gender genero
     * @param yearReleased ano de lançamento
     * @param yearEnding ano de encerramento
     * @param cast elenco
     * @param originalTitle titulo original
     * @param whereWatch onde assistir
     * @return resultado
     */
    public String registerMedia(String title, String gender, int yearReleased, int yearEnding,
        ArrayList<String> cast, String originalTitle, ArrayList<String> whereWatch) {
        MemoryManagementService memoryManagementService = new MemoryManagementService(memoryManagement);

        try {
            memoryManagementService.registerMedia(title, gender, yearReleased, yearEnding, cast, originalTitle, whereWatch);
        } catch (IllegalArgumentException e) {
            return "Tentativa de registro FALHO\n" + e.getMessage();
        }

        return "Registrada com SUCESSO!";
    }

    /**
     * Registra temporadas
     * @param serie serie
     * @param title titulo
     * @param year ano
     * @param numberEpisodes numero de episodios
     * @return resultado
     */
    public String registerSeason(Serie serie, String title, int year, int numberEpisodes) {
        MemoryManagementService memoryManagementService = new MemoryManagementService(memoryManagement);
        try {
            memoryManagementService.registerMedia(serie, year, title, numberEpisodes);
        } catch (IllegalArgumentException e) {
            return "Tentativa de registro FALHO\n" + e.getMessage();
        }

        return "Temporada registrada com SUCESSO!";
    }

    /**
     * Salva mídias em arquivo .json
     */
    public void salvarArquivos() {
        try {
            memoryManagement.salvaArquivosBd();

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deleta Filme
     * @param film obj filme
     */
    public String deleteMedia(Film film){
        try{
            memoryManagement.deleteMedia(film);
        } catch(Exception e){
            return "Tentativa de deletar filme Falho\n" + e.getMessage();
        }

        return "Deletado com SUCESSO";
    }

    /**
     * Deleta Série
     * @param serie obj serie
     */
    public String deleteMedia(Serie serie){
        try{
            memoryManagement.deleteMedia(serie);
        } catch(Exception e){
            return "Tentativa de deletar série Falho\n" + e.getMessage();
        }

        return "Deletada com SUCESSO";
    }

    /**
     * Deleta Livro
     * @param book obj livro
     */
    public String deleteMedia(Book book){
        try{
            memoryManagement.deleteMedia(book);
        } catch(Exception e){
            return "Tentativa de deletar livro Falho\n" + e.getMessage();
        }

        return "Deletado com SUCESSO";
    }

    /**
     * Deleta temporada
     * @param season obj temporada
     */
    public String deleteMedia(Season season, Serie serie) {
        try {
            serie.deleteSeason(season);
        } catch (Exception e) {
            return "Tentativa de deletar Temporada Falho\n" + e.getMessage();
        }

        return "Deletada com SUCESSO";
    }

    public Map<String, List<Media>> loadDataBase(File arq) {
        try{
            return  memoryManagement.jsonToClass(arq);
        } catch (IllegalStateException e){
            return null;
        }
    }

}
