package edu.bsu.cs222.model;

public record Move(String name, String type, String pp, String power, String accuracy, String obtained) {
    public String toString() {
        return name + " " + type + " " + pp + " " + power + " " + accuracy + " " + obtained;
    }
}
