package edu.bsu.cs222.model.parsers;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs222.model.Move;
import edu.bsu.cs222.model.Version;
import net.minidev.json.JSONArray;

import java.util.List;

public class MoveParser {
    public Move parseForMove(Object moveJsonDocument, List<String> learnMethods, Version version) {
        String moveName = parseName(moveJsonDocument);

        String moveType = null;
        String pp = null;
        String power = null;
        String accuracy = null;

        JSONArray pastValuesArray = JsonPath.read(moveJsonDocument, "$.past_values");
        for (Object pastValueDocument : pastValuesArray) {
            String versionGroupName = JsonPath.read(pastValueDocument, "$.version_group.name");
            Integer versionGroupID = version.getVersionGroupMap().get(versionGroupName);
            if (version.getVersionGroup().getID() < versionGroupID) {
                moveType = parseType(pastValueDocument);
                pp = parsePP(pastValueDocument);
                power = parsePower(pastValueDocument);
                accuracy = parseAccuracy(pastValueDocument);
                break;
            }
        }
        if (moveType == null) {
            moveType = parseType(moveJsonDocument);
        }
        if (pp == null) {
            pp = parsePP(moveJsonDocument);
        }
        if (power == null) {
            power = parsePower(moveJsonDocument);
        }
        if (accuracy == null) {
            accuracy = parseAccuracy(moveJsonDocument);
        }

        return Move.withName(moveName).andType(moveType).andPP(pp).andPower(power).andAccuracy(accuracy)
                .andLearnMethods(learnMethods);
    }

    private String parseName(Object moveJsonDocument) {
        return JsonPath.read(moveJsonDocument, "$.name");
    }

    private String parseType(Object moveJsonDocument) {
        Object typeObject = JsonPath.read(moveJsonDocument, "$.type");
        if (nullCheck(typeObject)) {
            return null;
        }
        return JsonPath.read(moveJsonDocument, "$.type.name");
    }

    private String parsePP(Object moveJsonDocument) {
        Object ppObject = JsonPath.read(moveJsonDocument, "$.pp");
        if(nullCheck(ppObject)) {
            return null;
        }
        return String.valueOf(ppObject);
    }

    private String parsePower(Object moveJsonDocument) {
        Object powerObject = JsonPath.read(moveJsonDocument, "$.power");
        if(nullCheck(powerObject)) {
            return null;
        }
        return String.valueOf(powerObject);
    }

    private String parseAccuracy(Object moveJsonDocument) {
        Object accuracyObject = JsonPath.read(moveJsonDocument, "$.accuracy");
        if(nullCheck(accuracyObject)) {
            return null;
        }
        return String.valueOf(accuracyObject);
    }

    private boolean nullCheck(Object data) {
        return data == null;
    }
}
