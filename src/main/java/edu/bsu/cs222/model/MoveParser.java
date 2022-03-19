package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class MoveParser {
    public String parseType(Object moveInputStream) {
        String type;
        if(pastValue(moveInputStream, "type")) {
            type = JsonPath.read(moveInputStream, "$.past_values[0].type.name");
        }
        else {
            type = JsonPath.read(moveInputStream, "$.type.name");
        }
        return type;
    }

    public int parsePP(Object moveInputStream) {
        int pp;
        if(pastValue(moveInputStream, "pp")) {
            pp = JsonPath.read(moveInputStream, "$.past_values[0].pp");
        }
        else {
            pp = JsonPath.read(moveInputStream, "$.pp");
        }
        return pp;
    }

    public int parsePower(Object moveInputStream) {
        int power;
        if(pastValue(moveInputStream, "power")) {
            power = JsonPath.read(moveInputStream, "$.past_values[0].power");
        }
        else {
            power = JsonPath.read(moveInputStream, "$.power");
        }
        return power;
    }

    public int parseAccuracy(Object moveInputStream) {
        int accuracy;
        if(pastValue(moveInputStream, "accuracy")) {
            accuracy = JsonPath.read(moveInputStream, "$.past_values[0].accuracy");
        }
        else {
            accuracy = JsonPath.read(moveInputStream, "$.accuracy");
        }

        return accuracy;
    }

    // Since we're doing only Gen 1 for now, past values will always be in the first array. We'll have to change this method when
    // we implement newer gens, possibly need some kind of map that tracks game order. Won't be too hard to change.
    private boolean pastValue(Object moveInputStream, String value) {
        return JsonPath.read(moveInputStream, "$.past_values[0]." + value) != null;
    }
}
