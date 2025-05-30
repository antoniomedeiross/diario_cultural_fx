package com.antonio.diarioculturalfx.services;

import com.antonio.diarioculturalfx.model.Book;
import com.antonio.diarioculturalfx.model.Film;
import com.antonio.diarioculturalfx.model.Serie;
import com.antonio.diarioculturalfx.repository.MemoryManagement;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Classse que representa os serviços de busca
 */
public class SearchService {

    private final MemoryManagement memoryManagement;

    /**
     * Construtor
     * @param memoryManagement
     */
    public SearchService(MemoryManagement memoryManagement){
        this.memoryManagement = Objects.requireNonNull(memoryManagement, "memoryManagement cannot be null");
    }

    // busca de livros

    /**
     * Retorna uma lista de livros que contém o parametro titulo informado
     * @param title
     * @return lista de livros
     */
    public ArrayList<Book> searchBookTitle(String title){
        validateRequiredFields(title);
        // isa collections para iterar no arraylist e buscar os livros
        return this.memoryManagement.getBooks().stream().filter(p -> p.getTitle().toLowerCase()
                .contains(title.toLowerCase())).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Retorna uma lista de livros que contém o parametro autor informado
     * @param author
     * @return lista de livros
     */
    public ArrayList<Book> searchBookAuthor(String author){
        validateRequiredFields(author);
        return this.memoryManagement.getBooks().stream().filter(p -> p.getAuthor().toLowerCase()
                .contains(author.toLowerCase())).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Retorna uma lista de livros que contém o parametro genero informado
     * @param gender
     * @return lista de livros
     */
    public ArrayList<Book> searchBookGender(String gender){
        validateRequiredFields(gender);
        return this.memoryManagement.getBooks().stream().filter(p -> p.getGender().toLowerCase()
                .contains(gender.toLowerCase())).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Retorna uma lista de livros que contém o parametro ano de lancamento informado
     * @param yearReleased
     * @return lista de livros
     */
    public ArrayList<Book> searchBookYearReleased(int yearReleased){
        return this.memoryManagement.getBooks().stream().filter(p -> p.getYearReleased() == yearReleased)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Retorna uma lista de livros que contém o parametro genero informado
     * @param isbn
     * @return lista de livros
     */
    public ArrayList<Book> searchBookIsbn(String isbn){
        validateRequiredFields(isbn);
        return this.memoryManagement.getBooks().stream().filter(p -> p.getIsbn().toLowerCase()
                .contains(isbn.toLowerCase())).collect(Collectors.toCollection(ArrayList::new));
    }

    // busca de filmes

    /**
     * Retorna uma lista de filmes que contém o parametro titulo informado
     * @param title
     * @return list de filmes
     */
    public ArrayList<Film> searchFilmTitle(String title){
        validateRequiredFields(title);
        return this.memoryManagement.getFilms().stream().filter(p -> p.getTitle().toLowerCase()
                .contains(title.toLowerCase())).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Retorna uma lista de filmes que contém o parametro genero informado
     * @param gender
     * @return list de filmes
     */
    public ArrayList<Film> searchFilmGender(String gender){
        validateRequiredFields(gender);
        return this.memoryManagement.getFilms().stream().filter(p -> p.getGender().toLowerCase()
                .contains(gender.toLowerCase())).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Retorna uma lista de filmes que contém o parametro diretor informado
     * @param director
     * @return list de filmes
     */
    public ArrayList<Film> searchFilmDirector(String director){
        validateRequiredFields(director);
        return this.memoryManagement.getFilms().stream().filter(p -> p.getDirector().toLowerCase()
                .contains(director.toLowerCase())).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Retorna uma lista de filmes que contém o parametro ator informado
     * @param actor
     * @return list de filmes
     */
    public ArrayList<Film> searchFilmActor(String actor){
        validateRequiredFields(actor);
        return this.memoryManagement.getFilms().stream().filter(p -> p.getCast().stream().anyMatch(m -> m.toLowerCase().contains(actor.toLowerCase())))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Retorna uma lista de filmes que contém o parametro ano de lancamento informado
     * @param yearReleased
     * @return list de filmes
     */
    public ArrayList<Film> searchFilmYearReleased(int yearReleased){
        return this.memoryManagement.getFilms().stream().filter(p -> p.getYearReleased() == yearReleased)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    //busca de séries
    /**
     * Retorna uma lista de séries que contém o parametro titulo informado
     * @param title
     * @return list de filmes
     */
    public ArrayList<Serie> searchSerieTitle(String title){
        validateRequiredFields(title);
        return this.memoryManagement.getSeries().stream().filter(p -> p.getTitle().toLowerCase()
                .contains(title.toLowerCase())).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Retorna uma lista de séries que contém o parametro genero informado
     * @param gender
     * @return list de filmes
     */
    public ArrayList<Serie> searchSerieGender(String gender){
        validateRequiredFields(gender);
        return this.memoryManagement.getSeries().stream().filter(p -> p.getGender().toLowerCase()
                .contains(gender.toLowerCase())).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Retorna uma lista de séries que contém o parametro ator informado
     * @param actor
     * @return list de filmes
     */
    public ArrayList<Serie> searchSerieActor(String actor){
        validateRequiredFields(actor);
        return this.memoryManagement.getSeries().stream().filter(p -> p.getCast().stream().anyMatch(m -> m.toLowerCase().contains(actor.toLowerCase())))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Retorna uma lista de séries que contém o parametro ano de lancamento informado
     * @param yearReleased
     * @return list de filmes
     */
    public ArrayList<Serie> searchSerieYearReleased(int yearReleased){
        return this.memoryManagement.getSeries().stream().filter(p -> p.getYearReleased() == yearReleased)
                .collect(Collectors.toCollection(ArrayList::new));
    }


    private void validateRequiredFields(String ... fields) {
        for (String field : fields) {
            if (field == null || field.trim().isEmpty()) {
                throw new IllegalArgumentException("Campos obrigatórios não podem ser nulos ou vazios");
            }
        }
    }
}
