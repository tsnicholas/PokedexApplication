package edu.bsu.cs222.model;

import java.util.List;

public class Pokedex {
    private final List<String> pokedex;

    public Pokedex(List<String> pokedex) {
        this.pokedex = pokedex;
    }

    public List<String> getPokedex() {
        return pokedex;
    }
}
