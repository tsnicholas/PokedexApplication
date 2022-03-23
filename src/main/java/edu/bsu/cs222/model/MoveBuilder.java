package edu.bsu.cs222.model;

import edu.bsu.cs222.parsers.MoveParser;

import java.util.LinkedHashMap;
import java.util.List;

public class MoveBuilder {
    private final MoveParser moveParser = new MoveParser();

    public Move createMove(String name, Object moveJsonDocument, List<String> learnMethods) {
        LinkedHashMap<String, String> moveData = new LinkedHashMap<>();
        moveData.put("Name", name);
        moveData.put("Type", moveParser.parseType(moveJsonDocument));
        moveData.put("PP", moveParser.parsePP(moveJsonDocument));
        moveData.put("Power", moveParser.parsePower(moveJsonDocument));
        moveData.put("Accuracy", moveParser.parseAccuracy(moveJsonDocument));
        return new Move(moveData, learnMethods);
    }
}
