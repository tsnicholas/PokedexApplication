package edu.bsu.cs222.model.parsers;

import com.jayway.jsonpath.JsonPath;

public class MoveParser {
    public String parseName(Object moveJsonDocument) {
        return JsonPath.read(moveJsonDocument, "$.name");
    }

    public String parseType(Object moveJsonDocument) {
        Object typeObject = JsonPath.read(moveJsonDocument, "$.type.name");
        if(nullCheck(typeObject)) {
            return "--";
        }
        return String.valueOf(typeObject);
    }

    public String parsePP(Object moveJsonDocument) {
        Object ppObject = JsonPath.read(moveJsonDocument, "$.pp");
        if(nullCheck(ppObject)) {
            return "--";
        }
        return String.valueOf(ppObject);
    }

    public String parsePower(Object moveJsonDocument) {
        Object powerObject = JsonPath.read(moveJsonDocument, "$.power");
        if(nullCheck(powerObject)) {
            return "--";
        }
        return String.valueOf(powerObject);
    }

    public String parseAccuracy(Object moveJsonDocument) {
        Object accuracyObject = JsonPath.read(moveJsonDocument, "$.accuracy");
        if(nullCheck(accuracyObject)) {
            return "--";
        }
        return String.valueOf(accuracyObject);
    }

    private boolean nullCheck(Object data) {
        return data == null;
    }
}
