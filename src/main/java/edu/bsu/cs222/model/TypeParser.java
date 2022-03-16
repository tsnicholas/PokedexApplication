package edu.bsu.cs222.model;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class TypeParser {
    public List<String> parseForTypes(Object pokemonJsonDocument) {
        JSONArray genOneTypeArray = JsonPath.read(pokemonJsonDocument, "$.past_types[0].types..name");
        if (genOneTypeArray.size() == 0) {
            genOneTypeArray = JsonPath.read(pokemonJsonDocument, "$.types..name");
        }
        return jsonArrayToStringList(genOneTypeArray);
    }

    private List<String> jsonArrayToStringList(JSONArray array) {
        List<String> stringList = new ArrayList<>();
        for (Object o : array) {
            stringList.add(o.toString());
        }
        return stringList;
    }

    public List<String> parseWeakTo(Object typeJsonDocument, String jsonPathRoot) {
        JSONArray weakToArray = JsonPath.read(typeJsonDocument, jsonPathRoot + ".double_damage_from..name");
        return jsonArrayToStringList(weakToArray);
    }

    public List<String> parseResistantTo(Object typeJsonDocument, String jsonPathRoot) {
        JSONArray resistantToArray = JsonPath.read(typeJsonDocument, jsonPathRoot + ".half_damage_from..name");
        return jsonArrayToStringList(resistantToArray);
    }

    public List<String> parseImmuneTo(Object typeJsonDocument, String jsonPathRoot) {
        JSONArray immuneToArray = JsonPath.read(typeJsonDocument, jsonPathRoot + ".no_damage_from..name");
        return jsonArrayToStringList(immuneToArray);
    }

    public String getRootOfDamageRelations(Object typeJsonDocument) {
        JSONArray genOneDamageRelationsArray = JsonPath.read(typeJsonDocument,"$.past_damage_relations");
        String jsonPathRoot = "$.damage_relations";
        if (genOneDamageRelationsArray.size() != 0) {
            jsonPathRoot = "$.past_damage_relations[0].damage_relations";
        }
        return jsonPathRoot;
    }

    public Object parseJson(String json) {
        return Configuration.defaultConfiguration().jsonProvider().parse(json);
    }

}
