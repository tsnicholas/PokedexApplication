package edu.bsu.cs222.model;

import java.util.LinkedHashMap;
import java.util.List;

public class Move {
    private final LinkedHashMap<String, String> moveData;
    private final List<String> learnMethods;

    public Move(LinkedHashMap<String, String> moveData, List<String> learnMethods) {
        this.moveData = moveData;
        this.learnMethods = learnMethods;
    }

    public LinkedHashMap<String, String> getMoveData() {
        return moveData;
    }

    public String access(String dataType) {
        return moveData.get(dataType);
    }

    public List<String> getLearnMethods() {
        return learnMethods;
    }
}
