package edu.bsu.cs222.model;

import java.util.List;

public class Pokedex {
    private final List<String> pokemonName;

    public Pokedex(List<String> pokemonList) {
        this.pokemonName = pokemonList;
    }

    public List<String> getPokemonNames() {
        return pokemonName;
    }
}
