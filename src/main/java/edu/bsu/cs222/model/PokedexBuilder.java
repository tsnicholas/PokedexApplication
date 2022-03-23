package edu.bsu.cs222.model;

import edu.bsu.cs222.parsers.GameParser;

import java.util.List;

public class PokedexBuilder {
    private final GameParser gameParser = new GameParser();

    public Pokedex createPokedex(Object gameJsonDocument) {
        List<String> pokedex = gameParser.parseForPokedex(gameJsonDocument);
        return new Pokedex(pokedex);
    }
}
