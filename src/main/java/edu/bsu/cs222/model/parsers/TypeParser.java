package edu.bsu.cs222.model.parsers;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.util.List;

// Dragons behave differently, blame the PokeAPI for not having past damage relations for dragons
public class TypeParser {
    private final JsonParser jsonParser = new JsonParser();

    public List<String> parseWeakTo(Object damageRelationsJsonDocument) {
        JSONArray weakToArray = JsonPath.read(damageRelationsJsonDocument, "$.double_damage_from..name");
        return jsonParser.jsonArrayToStringList(weakToArray);
    }

    public List<String> parseResistantTo(Object damageRelationsJsonDocument) {
        JSONArray resistantToArray = JsonPath.read(damageRelationsJsonDocument, "$.half_damage_from..name");
        return jsonParser.jsonArrayToStringList(resistantToArray);
    }

    public List<String> parseImmuneTo(Object damageRelationsJsonDocument) {
        JSONArray immuneToArray = JsonPath.read(damageRelationsJsonDocument, "$.no_damage_from..name");
        return jsonParser.jsonArrayToStringList(immuneToArray);
    }
}
