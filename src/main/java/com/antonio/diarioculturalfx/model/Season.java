package com.antonio.diarioculturalfx.model;

/**
 * Classe que representa as temporadas no Diário
 */
public class Season {
    private int year;
    private String title;
    private int numberEpisodes;
    private Review review;

    /**
     * Cria uma temporada com as informações básicas
     * @param year
     * @param title
     * @param numberEpisodes
     */
    public Season(int year, String title, int numberEpisodes) {
        this.year = year;
        this.title = title;
        this.numberEpisodes = numberEpisodes;
        this.review = new Review(0, "Nehnhum comentário atribuído", false,"Nenhuma data atribuída");
    }

    // Getters e Setters
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getNote() {
        return review.getNote();
    }
    public void setNote(int note, Serie serie) {
        this.review.setNote(note);
        serie.updateNote();
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getNumberEpisodes() {
        return numberEpisodes;
    }
    public void setNumberEpisodes(int numberEpisodes) {
        this.numberEpisodes = numberEpisodes;
    }
    public Review getReview() { return review; }
    public void setReview(Review review) {this.review = review;}
}
