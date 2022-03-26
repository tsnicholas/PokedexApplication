package edu.bsu.cs222.model;

import edu.bsu.cs222.parsers.PokedexParser;
import java.util.List;

public class PokedexFactory {
    private final PokedexParser gameParser = new PokedexParser();

    public Pokedex createPokedex(Object gameJsonDocument) {
        List<String> pokedex = gameParser.parseForPokedex(gameJsonDocument);
        return new Pokedex(pokedex);
    }
}
