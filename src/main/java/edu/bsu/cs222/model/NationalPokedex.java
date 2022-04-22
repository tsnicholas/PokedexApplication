package edu.bsu.cs222.model;


import java.util.List;

public class NationalPokedex {
    private final List<String> pokemonNames;

    public NationalPokedex(List<String> pokemonNames) {
        this.pokemonNames = pokemonNames;
    }

    public List<String> getPokemonNames() {
        return pokemonNames;
    }

    public boolean containsPokemon(String pokemonName) {
        return this.pokemonNames.contains(pokemonName);
    }
}