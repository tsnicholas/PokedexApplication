package edu.bsu.cs222.model;

import edu.bsu.cs222.model.parsers.PokemonParser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CurrentPokemonBuilder implements PokemonBuilder {
    private final PokemonParser pokemonParser = new PokemonParser();
    private final Object pokemonJsonObject;
    private final Pokemon pokemon;

    public CurrentPokemonBuilder(Object pokemonJsonObject) throws RuntimeException {
        this.pokemonJsonObject = pokemonJsonObject;
        this.pokemon = new Pokemon();
    }

    public void buildTypeList() {
        pokemon.setTypeList(pokemonParser.parseForTypes(pokemonJsonObject));
    }

    public void buildStatsMap() {
        pokemon.setStatsMap(pokemonParser.parseForStats(pokemonJsonObject));
    }

    public void buildMoveList() {
        pokemon.setMoveList(pokemonParser.parseForMoves(pokemonJsonObject));
    }

    public void buildDamageRelations() {
        List<Type> typeList = pokemon.getTypeList();
        if (typeList.size() == 1) {
            pokemon.setImmunities(typeList.get(0).getImmuneTo());
            pokemon.setWeaknesses(typeList.get(0).getWeakTo());
            pokemon.setResistances(typeList.get(0).getResistantTo());
            return;
        }
        List<String> immuneTo = new ArrayList<>();
        List<String> weakTo = new ArrayList<>();
        List<String> resistantTo = new ArrayList<>();
        for (Type type : typeList) {
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

        pokemon.setImmunities(immuneTo);
        pokemon.setWeaknesses(weakTo);
        pokemon.setResistances(resistantTo);
    }

    private List<String> eliminateDuplicates(List<String> stringList) {
        Set<String> stringSet = new HashSet<>(stringList);
        return new ArrayList<>(stringSet);
    }

    public void buildImageURL() {
        pokemon.setImageURL(pokemonParser.parseForImage(pokemonJsonObject));
    }

    public Pokemon getPokemon() {
        return pokemon;
    }
}
