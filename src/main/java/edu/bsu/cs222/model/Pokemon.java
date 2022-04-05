package edu.bsu.cs222.model;

import java.util.*;

public class Pokemon {
    public static final class Builder {
        private List<Type> typeList;
        private List<Move> moveList;
        private Map<String, Integer> statsMap;
        private String imageURL;

        public void setTypeList(List<Type> typeList) {
            this.typeList = typeList;
        }

        public void setStatsMap(Map<String, Integer> statsMap) {
            this.statsMap = statsMap;
        }

        public void setMoveList(List<Move> moveList) {
            this.moveList = moveList;
        }

        public void setImageURL(String imageURL) {
            this.imageURL = imageURL;
        }

        public Pokemon build() {
            return new Pokemon(this);
        }
    }

    private final List<Type> typeList;
    private final List<Move> moveList;
    private final Map<String, Integer> statsMap;
    private final String imageURL;
    private List<String> weaknesses;
    private List<String> resistances;
    private List<String> immunities;

    public Pokemon(Builder builder) {
        typeList = builder.typeList;
        moveList = builder.moveList;
        statsMap = builder.statsMap;
        imageURL = builder.imageURL;
        setDamageRelations();
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

    public List<Type> getTypeList() {
        return typeList;
    }

    public Map<String, Integer> getStats() {
        return statsMap;
    }

    public List<Move> getMoveList() {
        return moveList;
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
}
