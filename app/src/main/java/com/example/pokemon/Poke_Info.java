package com.example.pokemon;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Poke_Info {

    private int count;
    private String next;
    private String previous;
    List<PokeArray> results;

    public int getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public List<PokeArray> getResults() {
        return results;
    }
}

