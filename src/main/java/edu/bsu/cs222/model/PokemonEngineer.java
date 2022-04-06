package edu.bsu.cs222.model;

import edu.bsu.cs222.model.parsers.PokemonParser;

public class PokemonEngineer {
    //TODO: this can just be in Pokemon.Builder

    private final PokemonParser pokemonParser;

    public PokemonEngineer() {
        this.pokemonParser = new PokemonParser();
    }

    public PokemonEngineer(URLProcessor urlProcessor) {
        this.pokemonParser = new PokemonParser(urlProcessor);
    }

    public Pokemon constructPokemon(Object pokemonJsonObject, Version version) {
        Pokemon.Builder pokemonBuilder = new Pokemon.Builder();
        pokemonBuilder.setTypeList(pokemonParser.parseForTypes(pokemonJsonObject, version));
        pokemonBuilder.setStatsMap(pokemonParser.parseForStats(pokemonJsonObject));
        pokemonBuilder.setMoveList(pokemonParser.parseForMoves(pokemonJsonObject, version.getVersionName()));
        pokemonBuilder.setImageURL(pokemonParser.parseForImage(pokemonJsonObject, version));
        return pokemonBuilder.build();
    }
}
