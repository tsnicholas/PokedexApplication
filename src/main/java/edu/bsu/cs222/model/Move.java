package edu.bsu.cs222.model;

import java.util.ArrayList;

public record Move(String name, String type, int PP, int power, int accuracy, ArrayList<String> learnMethods) {
    public String name() {
        return name;
    }

    public String type() {
        return type;
    }

    public int PP() {
        return PP;
    }

    public int power() {
        return power;
    }

    public int accuracy() {
        return accuracy;
    }

    public ArrayList<String> learnMethods() {
        return learnMethods;
    }
}
