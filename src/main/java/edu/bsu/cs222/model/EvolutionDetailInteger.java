package edu.bsu.cs222.model;

public class EvolutionDetailInteger implements EvolutionDetailsValues {
    private final Integer integer;

    public EvolutionDetailInteger(Integer value) {
        this.integer = value;
    }

    @Override
    public String toString() {
        return integer.toString();
    }
}
