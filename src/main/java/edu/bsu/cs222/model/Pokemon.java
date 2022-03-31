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

    public Pokemon() {}

    public void setTypeList(List<Type> typeList) {
        this.typeList = typeList;
    }

    public List<Type> getTypeList() {
        return typeList;
    }

    public void setStatsMap(Map<String, Integer> statsMap) {
        this.statsMap = statsMap;
    }

    public Map<String, Integer> getStats() {
        return statsMap;
    }

    public void setMoveList(List<Move> moveList) {
        this.moveList = moveList;
    }

    public List<Move> getMoveList() {
        return moveList;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setWeaknesses(List<String> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public List<String> getWeaknesses() {
        return weaknesses;
    }

    public void setResistances(List<String> resistances) {
        this.resistances = resistances;
    }

    public List<String> getResistances() {
        return resistances;
    }

    public void setImmunities(List<String> immunities) {
        this.immunities = immunities;
    }

    public List<String> getImmunities() {
        return immunities;
    }
}
