package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.util.List;

public class TypeParser {
    private final JsonParser jsonParser = new JsonParser();

    public List<String> parseWeakTo(Object typeJsonDocument, String jsonPathRoot) {
        JSONArray weakToArray = JsonPath.read(typeJsonDocument, jsonPathRoot + ".double_damage_from..name");
        return jsonParser.jsonArrayToStringList(weakToArray);
    }

    public List<String> parseResistantTo(Object typeJsonDocument, String jsonPathRoot) {
        JSONArray resistantToArray = JsonPath.read(typeJsonDocument, jsonPathRoot + ".half_damage_from..name");
        return jsonParser.jsonArrayToStringList(resistantToArray);
    }

    public List<String> parseImmuneTo(Object typeJsonDocument, String jsonPathRoot) {
        JSONArray immuneToArray = JsonPath.read(typeJsonDocument, jsonPathRoot + ".no_damage_from..name");
        return jsonParser.jsonArrayToStringList(immuneToArray);
    }

}
