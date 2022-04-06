package edu.bsu.cs222.model;

import edu.bsu.cs222.model.parsers.MoveParser;

import java.util.List;

public class MoveEngineer {
    public Move createMove(Object moveJsonDocument, List<String> learnMethods) {
        MoveParser moveParser = new MoveParser();
        return Move.withName(moveParser.parseName(moveJsonDocument))
                .andType(moveParser.parseType(moveJsonDocument))
                .andPP(moveParser.parsePP(moveJsonDocument))
                .andPower(moveParser.parsePower(moveJsonDocument))
                .andAccuracy(moveParser.parseAccuracy(moveJsonDocument))
                .andLearnMethods(learnMethods);
    }
}
