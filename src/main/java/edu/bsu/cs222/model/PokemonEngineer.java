package edu.bsu.cs222.model;

public class PokemonEngineer {
    private final PokemonBuilder pokemonBuilder;

    public PokemonEngineer(PokemonBuilder pokemonBuilder) {
        this.pokemonBuilder = pokemonBuilder;
    }

    public void constructPokemon() {
        pokemonBuilder.buildTypeList();
        pokemonBuilder.buildStatsMap();
        pokemonBuilder.buildMoveList();
        pokemonBuilder.buildDamageRelations();
        pokemonBuilder.buildImageURL();
    }

    public Pokemon getPokemon() {
        return pokemonBuilder.getPokemon();
    }
}
