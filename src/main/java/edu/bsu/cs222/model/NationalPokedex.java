package edu.bsu.cs222.model;


import java.util.List;

public class NationalPokedex {
    private final List<String> pokemonNames;

    public NationalPokedex(List<String> pokemonNames) {
        this.pokemonNames = pokemonNames;
    }

    public boolean containsPokemon(String pokemonName) {
        return this.pokemonNames.contains(pokemonName);
    }

    public List<String> getPokemonNames() {
        return pokemonNames;
    }
}