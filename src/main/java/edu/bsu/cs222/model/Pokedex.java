package edu.bsu.cs222.model;

import java.util.List;

public class Pokedex {
    private final List<String> pokemonNames;

    public Pokedex(List<String> pokemonList) {
        this.pokemonNames = pokemonList;
    }

    public boolean containsPokemon(String pokemonName) {
        return this.pokemonNames.contains(pokemonName);
    }
}
