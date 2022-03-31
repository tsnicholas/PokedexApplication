package edu.bsu.cs222.model;

import java.util.*;

public class Pokemon implements PokemonPlan {
    private List<Type> typeList;
    private List<Move> moveList;
    private Map<String, Integer> statsMap;
    private String imageURL;
    private List<String> weaknesses = new ArrayList<>();
    private List<String> resistances = new ArrayList<>();
    private List<String> immunities = new ArrayList<>();

    public Pokemon(List<Type> types, Map<String, Integer> stats, List<Move> moves, String pokemonImageURL) {
        setDamageRelations();
    }

    public void setTypeList() {
        this.typeList = typeList;
    }

    public List<Type> getTypeList() {
        return typeList;
    }

    public void setStatsMap() {
        this.statsMap = statsMap;
    }

    public Map<String, Integer> getStats() {
        return statsMap;
    }

    public void setMoveList() {
        this.moveList = moveList;
    }

    public List<Move> getMoveList() {
        return moveList;
    }

    public void setImageURL() {
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public List<String> getWeaknesses() {
        return weaknesses;
    }

    public List<String> getResistances() {
        return resistances;
    }

    public List<String> getImmunities() {
        return immunities;
    }

    private void setDamageRelations() {
        if (typeList.size() == 1) {
            immunities = typeList.get(0).getImmuneTo();
            weaknesses = typeList.get(0).getWeakTo();
            resistances = typeList.get(0).getResistantTo();
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

        immunities = immuneTo;
        weaknesses = weakTo;
        resistances = resistantTo;
    }

    private List<String> eliminateDuplicates(List<String> stringList) {
        Set<String> stringSet = new HashSet<>(stringList);
        return new ArrayList<>(stringSet);
    }
}
