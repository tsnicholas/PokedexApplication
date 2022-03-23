package edu.bsu.cs222.model;

import java.util.List;

public class Pokedex {
    private final List<String> pokemonList;

    public Pokedex(List<String> pokemonList) {
        this.pokemonList = pokemonList;
    }

    public List<String> getPokemonList() {
        return pokemonList;
    }
}
