package edu.bsu.cs222.model;

public class EvolutionDetailString implements EvolutionDetailsValues {
    private final String string;

    public EvolutionDetailString(String value) {
        this.string = value;
    }

    @Override
    public String toString() {
        return string;
    }
}
