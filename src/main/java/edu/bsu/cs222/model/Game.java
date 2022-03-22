package edu.bsu.cs222.model;

import java.util.List;

public class Game {
    private String name;
    private final List<String> pokedex;

    public Game(String gameName, List<String> pokedex) {
        this.name = gameName;
        this.pokedex = pokedex;
    }

    public List<String> getPokedex() {
        return pokedex;
    }
}
