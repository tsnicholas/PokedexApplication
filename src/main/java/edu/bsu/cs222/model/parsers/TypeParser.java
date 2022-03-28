package edu.bsu.cs222.model.parsers;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.util.LinkedHashMap;
import java.util.List;

public class TypeParser {
    private final JsonParser jsonParser = new JsonParser();

    public List<String> parseWeakTo(LinkedHashMap<Class<?>, Class<?>> damageRelationsArray) {
        JSONArray weakToArray = JsonPath.read(damageRelationsArray,"$.double_damage_from..name");
        return jsonParser.jsonArrayToStringList(weakToArray);
    }

    public List<String> parseResistantTo(LinkedHashMap<Class<?>, Class<?>> damageRelationsArray) {
        JSONArray resistantToArray = JsonPath.read(damageRelationsArray,"$.half_damage_from..name");
        return jsonParser.jsonArrayToStringList(resistantToArray);
    }

    public List<String> parseImmuneTo(LinkedHashMap<Class<?>, Class<?>> damageRelationsArray) {
        JSONArray immuneToArray = JsonPath.read(damageRelationsArray,"$.no_damage_from..name");
        return jsonParser.jsonArrayToStringList(immuneToArray);
    }

}
