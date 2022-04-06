package edu.bsu.cs222.model;


import edu.bsu.cs222.model.parsers.NationalPokedexParser;

import java.util.List;

public class NationalPokedex {

    public static Factory createNationalPokedex() {
        return new Factory();
    }

    public static Factory createNationalPokedex(URLProcessor urlProcessor) {
        return new Factory(urlProcessor);
    }

    public static final class Factory {

        private static final NationalPokedexParser nationalPokedexParser = new NationalPokedexParser();
        private final URLProcessor urlProcessor;
        private final Object nationalPokedexJsonDocument;
        private List<String> nationalDexPokemonNames;

        public Factory() {
            this.urlProcessor = new ProductionURLProcessor();
            this.nationalPokedexJsonDocument = urlProcessor.getNationalPokedex();
        }

        public Factory(URLProcessor urlProcessor) {
            this.urlProcessor = urlProcessor;
            this.nationalPokedexJsonDocument = urlProcessor.getNationalPokedex();
        }

        public NationalPokedex loadNationalPokedexNames() {
            this.nationalDexPokemonNames = nationalPokedexParser.parseForPokemonNames(nationalPokedexJsonDocument);
            return new NationalPokedex(this);
        }
    }

    private final List<String> pokemonNames;

    public NationalPokedex(Factory factory) {
        this.pokemonNames = factory.nationalDexPokemonNames;
    }

    public boolean containsPokemon(String pokemonName) {
        return this.pokemonNames.contains(pokemonName);
    }
}