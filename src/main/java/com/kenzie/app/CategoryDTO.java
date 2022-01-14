package com.kenzie.app;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryDTO {

    @JsonProperty("canon")
    private boolean canon;
    @JsonProperty("title")
    private String title;
    @JsonProperty("id")
    private int id;


    CategoryDTO(){};

    CategoryDTO(boolean canon, String title, int id){
        this.canon = canon;
        this.title = title;
        this.id = id;
    }

    public boolean getCanon() {
        return canon;
    }

    public void setCanon(boolean canon) {
        this.canon = canon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{" +
                "canon=" + canon +
                ", title='" + title + '\'' +
                ", id=" + id +
                '}';
    }
}
