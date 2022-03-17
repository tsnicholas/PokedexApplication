package edu.bsu.cs222.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PokemonBuilder {

    public Pokemon createPokemon(String name, Object pokemonJsonObject) {
        PokemonParser pokemonParser = new PokemonParser();
        List<Type> types = pokemonParser.parseForTypes(pokemonJsonObject);
        Pokemon pokemon = new Pokemon(name, types);
        setDamageRelations(pokemon);
        return pokemon; // Not finished, only returns w/ immunity
    }

    private void setDamageRelations(Pokemon pokemon) {
        if (pokemon.getTypeList().size() == 1) {
            pokemon.setImmuneTo(pokemon.getTypeList().get(0).getImmuneTo());
            pokemon.setWeakTo(pokemon.getTypeList().get(0).getWeakTo());
            pokemon.setResistantTo(pokemon.getTypeList().get(0).getResistantTo());
            return;
        }
        Set<String> immuneToSet = new HashSet<>();
        for (Type type : pokemon.getTypeList()) {
            immuneToSet.addAll(type.getImmuneTo());
        }
        List<String> immuneTo = new ArrayList<>(immuneToSet);
        pokemon.setImmuneTo(immuneTo);
    }
}
