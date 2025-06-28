package com.antonio.diarioculturalfx.model;


/**
 * Classe que representa uma mídia no Diário, usada como super class para filme, livro e série
 */
abstract public class Media {
    private String title;
    private Review review;
    private String gender;
    private int yearReleased;

    /**
     * Construtor de média
     * @param title
     * @param gender
     * @param yearReleased
     */
    public Media(String title, String gender, int yearReleased) {
        this.title = title;
        this.gender = gender;
        this.yearReleased = yearReleased;
        this.review = new Review(0, "Nehnhum comentário atribuído", false,"Nenhuma data atribuída");
    }

    @Override
    public String toString() {
        return title;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public int getYearReleased() {
        return yearReleased;
    }
    public void setYearReleased(int yearReleased) {
        this.yearReleased = yearReleased;
    }
    public Review getReview() {
        return this.review;
    }
    public void setReview(Review review) {
        this.review = review;
    }
}
