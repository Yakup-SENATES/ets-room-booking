package com.example.roombookingapp.model;

public enum Layout {

    THEATER("Theater"),
    USHAPE("U-Shape"),
    BOARD("Boardroom");


    private String description;

    Layout(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
