package edu.illinois.cs465.traveltales.ui.search.model;

public class PlacesModel {

    private final Integer iconId;
    private final String letters;

    public PlacesModel(Integer iconId, String letters) {
        this.iconId = iconId;
        this.letters = letters;
    }

    public Integer getIconId() {
        return iconId;
    }

    public String getLetters() {
        return letters;
    }
}