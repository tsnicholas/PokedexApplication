package edu.bsu.cs222.model;

import edu.bsu.cs222.parsers.MoveParser;

import java.util.List;

public class MoveBuilder {
    private final MoveParser moveParser = new MoveParser();

    public Move createMove(String name, Object moveJsonDocument, List<String> learnMethods) {
        return new Move(name,
                moveParser.parseType(moveJsonDocument),
                moveParser.parsePP(moveJsonDocument),
                moveParser.parsePower(moveJsonDocument),
                moveParser.parseAccuracy(moveJsonDocument),
                learnMethods
        );
    }
}
