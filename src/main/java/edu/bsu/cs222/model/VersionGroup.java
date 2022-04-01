package edu.bsu.cs222.model;

public class VersionGroup {
    private final String name;
    private final Pokedex pokedex;

    public VersionGroup(String name, Pokedex pokedex) {
        this.name = name;
        this.pokedex = pokedex;
    }

    public String getName() {
        return name;
    }

    public Pokedex getPokedex() {
        return pokedex;
    }

    @Override
    public String toString() {
        return name;
    }
}
