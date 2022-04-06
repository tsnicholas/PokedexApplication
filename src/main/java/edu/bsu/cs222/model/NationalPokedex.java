package edu.bsu.cs222.model;

import edu.bsu.cs222.model.parsers.NationalPokedexParser;

import java.util.List;

public class NationalPokedex {

    public static Factory createNationalPokedex() {
        return new Factory();
    }

    public static final class Factory {

        private static final URLProcessor urlProcessor = new URLProcessor();
        private static final Object nationalPokedexJsonDocument = urlProcessor.getNationalPokedex();
        private static final NationalPokedexParser nationalPokedexParser = new NationalPokedexParser();
        private List<String> nationalDexPokemonNames;

        public Factory() {
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