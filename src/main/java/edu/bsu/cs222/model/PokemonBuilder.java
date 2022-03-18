package edu.bsu.cs222.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PokemonBuilder {

    public Pokemon createPokemon(String name, Object pokemonJsonObject) {
        PokemonParser pokemonParser = new PokemonParser();
        List<Type> types = pokemonParser.parseForTypes(pokemonJsonObject);
        int hp = pokemonParser.parseForHP(pokemonJsonObject);
        int speed = pokemonParser.parseForSpeed(pokemonJsonObject);
        int attack = pokemonParser.parseForAttack(pokemonJsonObject);
        int defense = pokemonParser.parseForDefense(pokemonJsonObject);
        int specialAttack = pokemonParser.parseForSpecialAttack(pokemonJsonObject);
        int specialDefense = pokemonParser.parseForSpecialDefense(pokemonJsonObject);
        Pokemon pokemon = new Pokemon(name, types, hp, speed, attack, defense, specialAttack, specialDefense);
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

    private void setStats(Pokemon pokemon) {

    }

    private List<String> eliminateDuplicates(List<String> stringList) {
        Set<String> stringSet = new HashSet<>(stringList);
        return new ArrayList<>(stringSet);
    }
}
