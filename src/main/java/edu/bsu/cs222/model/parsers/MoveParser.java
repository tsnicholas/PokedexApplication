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
                moveType = parseType(pastValueDocument, moveType);
                pp = parsePP(pastValueDocument, pp);
                power = parsePower(pastValueDocument, power);
                accuracy = parseAccuracy(pastValueDocument, accuracy);
            }
        }
        moveType = parseType(moveJsonDocument, moveType);
        pp = parsePP(moveJsonDocument, pp);
        power = parsePower(moveJsonDocument, power);
        accuracy = parseAccuracy(moveJsonDocument, accuracy);
        return Move.withName(moveName).andType(moveType).andPP(pp).andPower(power).andAccuracy(accuracy)
                .andLearnMethods(learnMethods);
    }

    private String parseName(Object moveJsonDocument) {
        return JsonPath.read(moveJsonDocument, "$.name");
    }

    private String parseType(Object moveJsonDocument, String moveType) {
        Object typeObject = JsonPath.read(moveJsonDocument, "$.type");
        if (!nullCheck(typeObject) && nullCheck(moveType)) {
            return JsonPath.read(moveJsonDocument, "$.type.name");
        }
        return moveType;
    }

    private String parsePP(Object moveJsonDocument, String pp) {
        Object ppObject = JsonPath.read(moveJsonDocument, "$.pp");
        if (!nullCheck(ppObject) && nullCheck(pp)) {
            return String.valueOf(ppObject);
        }
        return pp;
    }

    private String parsePower(Object moveJsonDocument, String power) {
        Object powerObject = JsonPath.read(moveJsonDocument, "$.power");
        if (!nullCheck(powerObject) && nullCheck(power)) {
            return String.valueOf(powerObject);
        }
        return power;
    }

    private String parseAccuracy(Object moveJsonDocument, String accuracy) {
        Object accuracyObject = JsonPath.read(moveJsonDocument, "$.accuracy");
        if (!nullCheck(accuracyObject) && nullCheck(accuracy)) {
            return String.valueOf(accuracyObject);
        }
        return accuracy;
    }

    private boolean nullCheck(Object data) {
        return data == null;
    }
}
