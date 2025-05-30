package com.antonio.diarioculturalfx.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Classe que representa um filme cadastrado pelo usuário no Diário
 */
public class Film extends Media {
    private int duration;
    private String director;
    private String writer;
    private ArrayList<String> cast;
    private String originalTitle;
    private ArrayList<String> whereWatch;

    private boolean watched;
    private String whenWatch;

    /**
     * Cria um novo filme com as informações básicas
     * @param title
     * @param gender
     * @param yearReleased
     * @param duration
     * @param director
     * @param writer
     * @param cast
     * @param originalTitle
     * @param whereWatch
     */
    public Film(String title, String gender, int yearReleased, int duration, String director,
            String writer, ArrayList<String> cast, String originalTitle, ArrayList<String> whereWatch) {

        super(title, gender, yearReleased);
        this.duration = duration;
        this.director = director;
        this.writer = writer;
        this.cast = cast;
        this.originalTitle = originalTitle;
        this.whereWatch = whereWatch;
    }
    public int getDuration() {return duration;}
    public String getDirector() {return director;}
    public String getWriter() {return writer;}
    public ArrayList<String> getCast() {return cast;}
    public String getOriginalTitle() {return originalTitle;}
    public ArrayList<String> getWhereWatch() {return whereWatch;}
    public boolean getWatched() {return watched;}
    public String getWhenWatch() {return whenWatch;}

    public void setDuration(int duration){this.duration = duration;}
    public void setDirector(String director){this.director = director;}
    public void setWriter(String writer){this.writer = writer;}
    public void setCast(ArrayList<String> cast){this.cast = cast;}
    public void setOriginalTitle(String originalTitle){this.originalTitle = originalTitle;}
    public void setWhereWatch(ArrayList<String> whereWatch){this.whereWatch = whereWatch;}
    public void setWatched(boolean watched){this.watched= watched;}
    public void setWhenWatch(String whenWatch){this.whenWatch = whenWatch;}

}
