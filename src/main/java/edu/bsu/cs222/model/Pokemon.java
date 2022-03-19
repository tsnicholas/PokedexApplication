package edu.bsu.cs222.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Pokemon {
    private final List<Type> typeList;
    private final List<Move> moveList;
    private final Map<String, Integer> statsMap;
    private final String imageURL;
    private List<String> weakTo = new ArrayList<>();
    private List<String> resistantTo = new ArrayList<>();
    private List<String> immuneTo = new ArrayList<>();

    public Pokemon(String name, List<Type> types, Map<String, Integer> stats, List<Move> moves, String pokemonImageURL) {
        typeList = types;
        statsMap = stats;
        moveList = moves;
        imageURL = pokemonImageURL;
    }

    public List<Type> getTypeList() {
        return typeList;
    }

    public Map<String, Integer> getStats() {
        return statsMap;
    }

    public List<String> getWeakTo() {
        return weakTo;
    }

    public List<String> getResistantTo() {
        return resistantTo;
    }

    public List<String> getImmuneTo() {
        return immuneTo;
    }

    public List<Move> getMoveList() {
        return moveList;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setWeakTo(List<String> weakTo) {
        this.weakTo = weakTo;
    }

    public void setResistantTo(List<String> resistantTo) {
        this.resistantTo = resistantTo;
    }

    public void setImmuneTo(List<String> immuneTo) {
        this.immuneTo = immuneTo;
    }

}
