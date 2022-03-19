package edu.bsu.cs222.model;

public record Move(String name, String type, String PP, String power, String accuracy, String learnMethod) {
    public String toString() {
        return name + " " + type + " " + PP + " " + power + " " + accuracy + " " + learnMethod;
    }
}
