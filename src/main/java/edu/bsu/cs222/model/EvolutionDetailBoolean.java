package edu.bsu.cs222.model;

public class EvolutionDetailBoolean implements EvolutionDetailsValues {
    private final Boolean evolutionDetailBoolean;

    public EvolutionDetailBoolean(Boolean value) {
        this.evolutionDetailBoolean = value;
    }

    @Override
    public String toString() {
        return evolutionDetailBoolean.toString();
    }
}
