package com.antonio.diarioculturalfx.controller;

import com.antonio.diarioculturalfx.model.Book;
import com.antonio.diarioculturalfx.model.Film;
import com.antonio.diarioculturalfx.model.Serie;
import com.antonio.diarioculturalfx.repository.MemoryManagement;
import com.antonio.diarioculturalfx.services.MemoryManagementService;
import com.antonio.diarioculturalfx.services.SearchService;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Controlador de busca
 */
public class SearchController {
    MemoryManagement memoryManagement;

    /**
     * Construtor
     * @param memoryManagementService
     */
    public SearchController(MemoryManagement memoryManagementService) {this.memoryManagement = memoryManagementService;}

    /**
     * Busca livro por titulo
     * @param title
     * @return lista de livros
     */
    public ArrayList<Book> searchBookTitle(String title){
        try{
            SearchService searchService = new SearchService(memoryManagement);
            return searchService.searchBookTitle(title);

        }catch (IllegalArgumentException e){
            return new ArrayList<>();
        }
    }

    /**
     * Busca livro por titulo
     * @param title
     * @return lista de livros
     */
    public ArrayList<Book> searchBookAuthor(String title){
        try{
            SearchService searchService = new SearchService(memoryManagement);
            return searchService.searchBookAuthor(title);

        }catch (IllegalArgumentException e){
            return new ArrayList<>();
        }
    }

    /**
     * Busca livro por genero
     * @param gender
     * @return lista de livros
     */
    public ArrayList<Book> searchBookGender(String gender){
        try{
            SearchService searchService = new SearchService(memoryManagement);
            return searchService.searchBookGender(gender);

        }catch (IllegalArgumentException e){
            return new ArrayList<>();
        }
    }

    /**
     * Busca livro por Ano de lancamento
     * @param yearReleased
     * @return lista de livros
     */
    public ArrayList<Book> searchBookYearReleased(int yearReleased){
        try{
            SearchService searchService = new SearchService(memoryManagement);
            return searchService.searchBookYearReleased(yearReleased);

        }catch (IllegalArgumentException e){
            return new ArrayList<>();
        }
    }

    /**
     * Busca livro por isbn
     * @param isbn
     * @return lista de livros
     */
    public ArrayList<Book> searchBookIsbn(String isbn){
        try{
            SearchService searchService = new SearchService(memoryManagement);
            return searchService.searchBookIsbn(isbn);

        }catch (IllegalArgumentException e){
            return new ArrayList<>();
        }
    }

    /**
     * Busca filme por titulo
     * @param title
     * @return lista de filmes
     */
    public ArrayList<Film> searchFilmTitle(String title){
        try{
            SearchService searchService = new SearchService(memoryManagement);
            return searchService.searchFilmTitle(title);

        }catch (IllegalArgumentException e){
            return new ArrayList<>();
        }
    }

    /**
     * Busca filme por genero
     * @param gender
     * @return lista de filmes
     */
    public ArrayList<Film> searchFilmGender(String gender){
        try{
            SearchService searchService = new SearchService(memoryManagement);
            return searchService.searchFilmGender(gender);

        }catch (IllegalArgumentException e){
            return new ArrayList<>();
        }
    }

    /**
     * Busca filme por diretor
     * @param director
     * @return lista de filmes
     */
    public ArrayList<Film> searchFilmDirector(String director){
        try{
            SearchService searchService = new SearchService(memoryManagement);
            return searchService.searchFilmDirector(director);

        }catch (IllegalArgumentException e){
            return new ArrayList<>();
        }
    }

    /**
     * Busca filme por ator
     * @param actor
     * @return lista de filmes
     */
    public ArrayList<Film> searchFilmActor(String actor){
        try{
            SearchService searchService = new SearchService(memoryManagement);
            return searchService.searchFilmActor(actor);

        }catch (IllegalArgumentException e){
            return new ArrayList<>();
        }
    }

    /**
     * Busca filme por ano de lancamento
     * @param yearReleased
     * @return lista de filmes
     */
    public ArrayList<Film> searchFilmYearReleased(int yearReleased){
        try{
            SearchService searchService = new SearchService(memoryManagement);
            return searchService.searchFilmYearReleased(yearReleased);

        }catch (IllegalArgumentException e){
            return new ArrayList<>();
        }
    }

    /**
     * Busca série por titulo
     * @param title
     * @return lista de séries
     */
    public ArrayList<Serie> searchSerieTitle(String title){
        try{
            SearchService searchService = new SearchService(memoryManagement);
            return searchService.searchSerieTitle(title);

        }catch (IllegalArgumentException e){
            return new ArrayList<>();
        }
    }

    /**
     * Busca série por genero
     * @param gender
     * @return lista de séries
     */
    public ArrayList<Serie> searchSerieGender(String gender){
        try{
            SearchService searchService = new SearchService(memoryManagement);
            return searchService.searchSerieGender(gender);

        }catch (IllegalArgumentException e){
            return new ArrayList<>();
        }
    }

    /**
     * Busca série por ator
     * @param actor
     * @return lista de séries
     */
    public ArrayList<Serie> searchSerieActor(String actor){
        try{
            SearchService searchService = new SearchService(memoryManagement);
            return searchService.searchSerieActor(actor);

        }catch (IllegalArgumentException e){
            return new ArrayList<>();
        }
    }

    /**
     * Busca série por ano de lancamento
     * @param yearReleased
     * @return lista de séries
     */
    public ArrayList<Serie> searchSerieYearReleased(int yearReleased){
        try{
            SearchService searchService = new SearchService(memoryManagement);
            return searchService.searchSerieYearReleased(yearReleased);

        }catch (IllegalArgumentException e){
            return new ArrayList<>();
        }
    }

}
