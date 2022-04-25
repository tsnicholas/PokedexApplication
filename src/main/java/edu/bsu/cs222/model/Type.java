package edu.bsu.cs222.model;

import java.util.HashMap;
import java.util.List;

public class Type {
    public static Builder withName(String name) {
        return new Builder(name);
    }

    public static final class Builder {
        private final String name;
        private HashMap<String, List<String>> damageRelations;

        public Builder(String name) {
            this.name = name;
        }

        public Type andDamageRelations(HashMap<String, List<String>> damageRelations) {
            this.damageRelations = damageRelations;
            return new Type(this);
        }
    }

    private final String name;
    private final String imageString;
    private final HashMap<String, List<String>> damageRelations;

    public Type(Builder builder) {
        this.name = builder.name;
        this.damageRelations = builder.damageRelations;
        imageString = convertNameToImageString(name);
    }

    private String convertNameToImageString(String type) {
        return type + ".png";
    }

    public String getName() {
        return name;
    }

    public String getImageString() {
        return imageString;
    }

    public List<String> getWeakTo() {
        return damageRelations.get("Weaknesses");
    }

    public List<String> getResistantTo() {
        return damageRelations.get("Resistances");
    }

    public List<String> getImmuneTo() {
        return damageRelations.get("Immunities");
    }
}
