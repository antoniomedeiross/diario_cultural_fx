package com.antonio.diarioculturalfx.controller;

import com.antonio.diarioculturalfx.model.Book;
import com.antonio.diarioculturalfx.model.Film;
import com.antonio.diarioculturalfx.model.Serie;
import com.antonio.diarioculturalfx.repository.MemoryManagement;
import com.antonio.diarioculturalfx.services.ListService;
import com.antonio.diarioculturalfx.services.MemoryManagementService;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Controlador de listagem
 */
public class ListController {
    MemoryManagement memoryManagement;

    /**
     * Construtor
     * @param memoryManagement
     */
    public ListController(MemoryManagement memoryManagement) {
        this.memoryManagement = Objects.requireNonNull(memoryManagement, "memoryManagement cannot be null");
    }
    /**
     * Lista os livros em memória
     */
    public ArrayList<Book> listBooks(){
        try {
            ListService listService = new ListService(memoryManagement);
            return listService.listBooks();
        }catch (Exception e){
            return new ArrayList<>();
        }

    }

    /**
     * Lista as séries em memoria
     * @return
     */
    public ArrayList<Serie> listSeries(){
        try {
            ListService listService = new ListService(memoryManagement);
            return listService.listSeries();
        }catch (Exception e){
            return new ArrayList<>();
        }

    }

    /**
     * Lista os filmes em memoria
     * @return
     */
    public ArrayList<Film> listFilms(){
        try {
            ListService listService = new ListService(memoryManagement);
            return listService.listFilms();
        }catch (Exception e){
            return new ArrayList<>();
        }

    }
}
