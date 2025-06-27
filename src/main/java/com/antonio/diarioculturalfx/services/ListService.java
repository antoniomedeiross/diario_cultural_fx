package com.antonio.diarioculturalfx.services;

import com.antonio.diarioculturalfx.model.Book;
import com.antonio.diarioculturalfx.model.Film;
import com.antonio.diarioculturalfx.model.Media;
import com.antonio.diarioculturalfx.model.Serie;
import com.antonio.diarioculturalfx.repository.MemoryManagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

/**
 * Classe que representa o serviço de listagem
 */
public class ListService {
    private final MemoryManagement memoryManagement;

    /**
     * Construtor da classe
     * @param memoryManagement
     */
    public ListService(MemoryManagement memoryManagement){
        this.memoryManagement = Objects.requireNonNull(memoryManagement, "memoryManagement cannot be null");
    }

    /**
     * Obtém a lista de livros
     * @return lista de livros
     */
    public ArrayList<Book> listBooks(){
        ordenarPorNota(memoryManagement.getBooks());
        return memoryManagement.getBooks();
    }

    /**
     * Obtém a lista de filmes
     * @return lista de filmes
     */
    public ArrayList<Film> listFilms(){
        ordenarPorNota(memoryManagement.getFilms());
        return memoryManagement.getFilms();
    }

    /**
     * Obtém  lista de séries
     * @return lista de series
     */
    public ArrayList<Serie> listSeries(){
        ordenarPorNota(memoryManagement.getSeries());
        return memoryManagement.getSeries();
    }

    public void ordenarPorNota(ArrayList<? extends Media> midias) {

        if(!midias.isEmpty()) {
            if (midias.get(0) instanceof Book || midias.get(0) instanceof Film) {
                midias.sort((m1, m2) -> Integer.compare(m2.getReview().getNote(), m1.getReview().getNote()));
            } else if ((midias.get(0) instanceof Serie)) {
                midias.sort((m1, m2) -> Integer.compare(((Serie) m2).getNote(), ((Serie) m1).getNote()));
            }
        }
    }
}
