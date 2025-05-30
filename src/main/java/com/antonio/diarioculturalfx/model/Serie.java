package com.antonio.diarioculturalfx.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * Classe que representa a série no Diário
 */
public class Serie extends Media {
    private int yearEnding;
    private int note = 0;
    private ArrayList<String> cast;
    private String originalTitle;
    private ArrayList<String> whereWatch;
    private ArrayList<Season> seasons;

    /**
     * Cria uma série com as informações básicas
     * @param title
     * @param gender
     * @param yearReleased
     * @param yearEnding
     * @param cast
     * @param originalTitle
     * @param whereWatch
     */
    public Serie(String title, String gender, int yearReleased, int yearEnding, ArrayList<String> cast, String originalTitle, ArrayList<String> whereWatch) {
        super(title, gender, yearReleased);
        this.yearEnding = yearEnding;
        this.cast = cast;
        this.originalTitle = originalTitle;
        this.whereWatch = whereWatch;
        this.seasons = new ArrayList<Season>();
    }


    /**
     * Itera sobre a lista de temporadas e calcula o valor inteiro da média, esse valor é setado como a nota da série
     */
    public void updateNote() {
        int qnt = 0;
        int media = 0;

        for (Season season : seasons) {
            media += season.getNote();
            qnt++;
        }

        if(qnt > 0) {
            media = media / qnt;
            this.setNote(media);
        } else {
            this.setNote(0);
        }
    }

    /**
     * Deleta objeto do tipo Season
     * @param season
     */
    public void deleteSeason(Season season){
        seasons.removeIf(season1 -> season1.equals(season));
    }


    public int getYearEnding() {
        return yearEnding;
    }
    public void setYearEnding(int yearEnding) {
        this.yearEnding = yearEnding;
    }
    public ArrayList<String> getCast() {
        return cast;
    }
    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }
    public ArrayList<String> getWhereWatch() {
        return whereWatch;
    }
    public void setWhereWatch(ArrayList<String> whereWatch) {
        this.whereWatch = whereWatch;
    }
    public ArrayList<Season> getSeasons() {
        return seasons;
    }
    public void setSeason(Season seasons) {
        Season seasonNãoPodeSerNulo = Objects.requireNonNull(seasons, "Seasons não pode ser nulo");
        this.seasons.add(seasons);
    }
    public String getOriginalTitle() {
        return originalTitle;
    }
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }
    public void setNote(int note) {
        this.note = note;
    }
    public int getNote() {
        return note;
    }

}
