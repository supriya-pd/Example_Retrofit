package com.example.pokemon;

import com.google.gson.annotations.SerializedName;

public class Poke_Info {
    private int albumId;
    private int id;
    private String title;
    @SerializedName("url")
    private String text;
    private String thumbnailUrl;

    public int getAlbumId() {
        return albumId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getThumbnailUrl(){
        return thumbnailUrl;
    }
}
