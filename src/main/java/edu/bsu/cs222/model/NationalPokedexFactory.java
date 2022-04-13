package edu.bsu.cs222.model;

import edu.bsu.cs222.model.parsers.NationalPokedexParser;

import java.util.List;

public class NationalPokedexFactory {
    private final URLProcessor urlProcessor;

    public NationalPokedexFactory() {
        this.urlProcessor = new ProductionURLProcessor();
    }

    public NationalPokedexFactory(URLProcessor urlProcessor) {
        this.urlProcessor = urlProcessor;
    }

    public NationalPokedex createNationalPokedex() {
        NationalPokedexParser nationalPokedexParser = new NationalPokedexParser();
        Object nationalPokedexJsonObject = urlProcessor.getNationalPokedex();
        List<String> pokemonNames = nationalPokedexParser.parseForPokemonNames(nationalPokedexJsonObject);
        return new NationalPokedex(pokemonNames);
    }
}
