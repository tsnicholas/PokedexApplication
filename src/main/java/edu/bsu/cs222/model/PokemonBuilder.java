package edu.bsu.cs222.model;

import java.util.List;

public class PokemonBuilder {

    public Pokemon createPokemon(String name, Object pokemonJsonObject) {
        PokemonParser pokemonParser = new PokemonParser();
        List<Type> types = pokemonParser.parseForTypes(pokemonJsonObject);
        Pokemon pokemon = new Pokemon(name, types);
        return null; // placeholder
    }

    private void setDamageRelations(Pokemon pokemon) {
        if (pokemon.getTypeList().size() == 1) {
            return;
        }
        for (String immunity : pokemon.getTypeList().get(0).getImmuneTo()) {
            if (!pokemon.getTypeList().get(1).getImmuneTo().contains(immunity)) {
                //TODO: finish this
            }
        }
    }
}
