package edu.bsu.cs222.model;

import java.util.*;

public class PokemonBuilder {
    private final PokemonParser pokemonParser = new PokemonParser();

    public Pokemon createPokemon(String name, Object pokemonJsonObject) {
        List<Type> types = pokemonParser.parseForTypes(pokemonJsonObject);
        Map<String, Integer> stats = setStats(pokemonJsonObject);
        Pokemon pokemon = new Pokemon(name, types, stats);
        setDamageRelations(pokemon);
        return pokemon;
    }

    private void setDamageRelations(Pokemon pokemon) {
        if (pokemon.getTypeList().size() == 1) {
            pokemon.setImmuneTo(pokemon.getTypeList().get(0).getImmuneTo());
            pokemon.setWeakTo(pokemon.getTypeList().get(0).getWeakTo());
            pokemon.setResistantTo(pokemon.getTypeList().get(0).getResistantTo());
            return;
        }
        List<String> immuneTo = new ArrayList<>();
        List<String> weakTo = new ArrayList<>();
        List<String> resistantTo = new ArrayList<>();
        for (Type type : pokemon.getTypeList()) {
            immuneTo.addAll(type.getImmuneTo());
            weakTo.addAll(type.getWeakTo());
            resistantTo.addAll(type.getResistantTo());
        }
        weakTo.removeIf(resistantTo::remove);
        immuneTo = eliminateDuplicates(immuneTo);
        weakTo = eliminateDuplicates(weakTo);
        resistantTo = eliminateDuplicates(resistantTo);
        for (String immunity : immuneTo) {
            weakTo.remove(immunity);
            resistantTo.remove(immunity);
        }
        pokemon.setImmuneTo(immuneTo);
        pokemon.setWeakTo(weakTo);
        pokemon.setResistantTo(resistantTo);
    }

    private Map<String, Integer> setStats(Object pokemonJsonObject) {
        Map<String, Integer> statMap = new HashMap<>();

        statMap.put("hp", pokemonParser.parseForHP(pokemonJsonObject));
        statMap.put("speed", pokemonParser.parseForSpeed(pokemonJsonObject));
        statMap.put("attack", pokemonParser.parseForAttack(pokemonJsonObject));
        statMap.put("defense", pokemonParser.parseForDefense(pokemonJsonObject));
        statMap.put("special-attack", pokemonParser.parseForSpecialAttack(pokemonJsonObject));
        statMap.put("special-defense", pokemonParser.parseForSpecialDefense(pokemonJsonObject));

        return statMap;
    }

    private List<String> eliminateDuplicates(List<String> stringList) {
        Set<String> stringSet = new HashSet<>(stringList);
        return new ArrayList<>(stringSet);
    }
}
