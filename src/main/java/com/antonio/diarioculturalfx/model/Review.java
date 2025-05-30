package com.antonio.diarioculturalfx.model;

/**
 * Classe que representa um review no Diário
 */
public class Review {
    private int note;
    private String comment;
    private boolean readWatch;
    private String whenReadWatch;

    /**
     * Cria um novo review com as informações básicas
     * @param note
     * @param comment
     * @param readWatch
     * @param whenReadWatch
     */
    public Review(int note, String comment, boolean readWatch, String whenReadWatch) {
        this.note = note;
        this.comment = comment;
        this.readWatch = readWatch;
        this.whenReadWatch = whenReadWatch;
    }

    // Getters e Setters
    public int getNote() { return note; }
    public void setNote(int note) { this.note = note; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public boolean isReadWatch() { return readWatch; }
    public void setReadWatch(boolean readWatch) { this.readWatch = readWatch; }
    public String getWhenReadWatch() { return whenReadWatch; }
    public void setWhenReadWatch(String whenReadWatch) { this.whenReadWatch = whenReadWatch; }
}
