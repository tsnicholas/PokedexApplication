package edu.bsu.cs222.model;

import java.util.ArrayList;

public class MoveBuilder {
    private final MoveParser moveParser = new MoveParser();

    public Move createMove(String name, Object moveJsonDocument, ArrayList<String> learnMethods) {
        return new Move(name,
                moveParser.parseType(moveJsonDocument),
                moveParser.parsePP(moveJsonDocument),
                moveParser.parsePower(moveJsonDocument),
                moveParser.parseAccuracy(moveJsonDocument),
                learnMethods
        );
    }
}
