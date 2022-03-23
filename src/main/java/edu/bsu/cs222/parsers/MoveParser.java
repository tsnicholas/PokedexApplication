package edu.bsu.cs222.parsers;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

public class MoveParser {
    public String parseType(Object moveInputStream) {
        String type;
        if(pastValue(moveInputStream, "type")) {
            type = JsonPath.read(moveInputStream, "$.past_values[0].type.name");
        }
        else {
            type = JsonPath.read(moveInputStream, "$.type.name");
        }
        return nullCheck(type);
    }

    public int parsePP(Object moveInputStream) {
        Integer pp;
        if(pastValue(moveInputStream, "pp")) {
            pp = JsonPath.read(moveInputStream, "$.past_values[0].pp");
        }
        else {
            pp = JsonPath.read(moveInputStream, "$.pp");
        }
        return nullCheck(pp);
    }

    public int parsePower(Object moveInputStream) {
        Integer power;
        if(pastValue(moveInputStream, "power")) {
            power = JsonPath.read(moveInputStream, "$.past_values[0].power");
        }
        else {
            power = JsonPath.read(moveInputStream, "$.power");
        }
        return nullCheck(power);
    }

    public int parseAccuracy(Object moveInputStream) {
        Integer accuracy;
        if(pastValue(moveInputStream, "accuracy")) {
            accuracy = JsonPath.read(moveInputStream, "$.past_values[0].accuracy");
        }
        else {
            accuracy = JsonPath.read(moveInputStream, "$.accuracy");
        }

        return nullCheck(accuracy);
    }

    private boolean pastValue(Object moveInputStream, String value) {
        JSONArray pastValues = JsonPath.read(moveInputStream, "$.past_values");
        if(pastValues.size() > 0) {
            return JsonPath.read(moveInputStream, "$.past_values[0]." + value) != null;
        }
        return false;
    }

    private int nullCheck(Integer stat) {
        if(stat == null) {
            return 0;
        }

        return stat;
    }

    private String nullCheck(String type) {
        if(type == null) {
            return "--";
        }

        return type;
    }
}
