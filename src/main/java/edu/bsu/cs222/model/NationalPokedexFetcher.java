package edu.bsu.cs222.model;

import edu.bsu.cs222.model.parsers.PokedexParser;

public class NationalPokedexFetcher {

    // Instead of PokedexProcessor, we could use this class in the main application to retrieve the national
    // pokedex, and then just reference the national pokedex in the main application when needed
    // since the pokedex data structure is able to check if it contains the name of a Pokemon

    public Pokedex getNationalPokedex() {
        PokedexParser nationalPokedexParser = new PokedexParser();
        URLProcessor urlProcessor = new URLProcessor();

        Object nationalPokedexDocument = urlProcessor.stringToObject("https://pokeapi.co/api/v2/pokedex/national");
        return new Pokedex(nationalPokedexParser.parseForPokemonNames(nationalPokedexDocument));
    }
}
