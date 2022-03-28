package edu.bsu.cs222.model;

import edu.bsu.cs222.model.parsers.PokemonParser;

import java.util.*;

public class PokemonBuilder {
    private final PokemonParser pokemonParser = new PokemonParser();

    public Pokemon createPokemon(Object pokemonJsonObject) {
        List<Type> types = pokemonParser.parseForTypes(pokemonJsonObject);
        Map<String, Integer> stats = pokemonParser.parseForStats(pokemonJsonObject);
        List<Move> moves = pokemonParser.parseForMoves(pokemonJsonObject);
        String pokemonImageURL = pokemonParser.parseForImage(pokemonJsonObject);
        return new Pokemon(types, stats, moves, pokemonImageURL);
    }
}
