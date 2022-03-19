package edu.bsu.cs222.model;

import java.util.List;

public record Move(String name, String type, String PP, String power, String accuracy, String learnMethod) {
    public String toString() {
        return name + " " + type + " " + PP + " " + power + " " + accuracy + " " + learnMethod;
    }
}
