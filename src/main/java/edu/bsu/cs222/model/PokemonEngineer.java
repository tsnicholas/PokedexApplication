package edu.bsu.cs222.model;

import edu.bsu.cs222.model.parsers.PokemonParser;

public class PokemonEngineer {
    private final PokemonParser pokemonParser = new PokemonParser();

    public PokemonEngineer() {

    }

    public Pokemon constructPokemon(Object pokemonJsonObject, Version version) {
        Pokemon.Builder pokemonBuilder = new Pokemon.Builder();
        pokemonBuilder.setTypeList(pokemonParser.parseForTypes(pokemonJsonObject));
        pokemonBuilder.setStatsMap(pokemonParser.parseForStats(pokemonJsonObject));
        pokemonBuilder.setMoveList(pokemonParser.parseForMoves(pokemonJsonObject, version.getVersionName()));
        pokemonBuilder.setImageURL(pokemonParser.parseForImage(pokemonJsonObject, version));
        return pokemonBuilder.build();
    }
}
