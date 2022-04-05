package edu.bsu.cs222.model.parsers;

import com.jayway.jsonpath.JsonPath;

import java.util.List;

// Dragons behave differently, blame the PokeAPI for not having past damage relations for dragons
public class TypeParser {

    public List<String> parseWeakTo(Object damageRelationsJsonDocument) {
        return JsonPath.read(damageRelationsJsonDocument, "$.double_damage_from..name");
    }

    public List<String> parseResistantTo(Object damageRelationsJsonDocument) {
        return JsonPath.read(damageRelationsJsonDocument, "$.half_damage_from..name");
    }

    public List<String> parseImmuneTo(Object damageRelationsJsonDocument) {
        return JsonPath.read(damageRelationsJsonDocument, "$.no_damage_from..name");
    }
}
