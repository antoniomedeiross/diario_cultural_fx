package com.antonio.diarioculturalfx.model;


/**
 * Classe que representa um livro cadastrado pelo usuário no Diário
 */

public class Book extends Media {
    private String author;
    private String publisher;
    private String isbn;
    private boolean haveBook;

    /**
     * Cria um novo livro com as informações básicas
     *
     * @param title
     * @param gender
     * @param yearReleased
     * @param author
     * @param publisher
     * @param isbn
     * @param haveBook
     */
    public Book(String title, String gender, int yearReleased, String author, String publisher, String isbn, boolean haveBook) {
        super(title, gender, yearReleased);
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.haveBook = haveBook;
    }

    public String getAuthor() {
        return author;
    }
    public String getPublisher() {
        return publisher;
    }
    public String getIsbn() {
        return isbn;
    }
    public boolean isHaveBook() {
        return haveBook;
    }

    public void setAuthor(String author){this.author = author;}
    public void setPublisher(String publisher){this.publisher = publisher;}
    public void setIsbn(String isbn){this.isbn = isbn;}
    public void setHaveBook(boolean haveBook){this.haveBook = haveBook;}







}
