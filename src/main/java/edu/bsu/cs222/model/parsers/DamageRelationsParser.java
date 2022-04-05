package edu.bsu.cs222.model.parsers;

import com.jayway.jsonpath.JsonPath;

import java.util.HashMap;
import java.util.List;

// Dragons behave differently, blame the PokeAPI for not having past damage relations for dragons
public class DamageRelationsParser {

    public HashMap<String, List<String>> parseForDamageRelations(Object typeJsonObject) {
        Object damageRelationsJsonDocument = makeDamageRelationsJsonDocument(typeJsonObject);
        return makeDamageRelationsMap(damageRelationsJsonDocument);
    }

    private Object makeDamageRelationsJsonDocument(Object typeJsonDocument) {
        int pastDamageRelationsArraySize = JsonPath.read(typeJsonDocument, "$.past_damage_relations.length()");
        if (pastDamageRelationsArraySize != 0) {
            typeJsonDocument = JsonPath.read(typeJsonDocument, "$.past_damage_relations[0]");
        }
        return JsonPath.read(typeJsonDocument, "$.damage_relations");
    }

    private HashMap<String, List<String>> makeDamageRelationsMap(Object damageRelationsJsonDocument) {
        HashMap<String, List<String>> damageRelationsMap = new HashMap<>();

        damageRelationsMap.put("Weaknesses", parseWeakTo(damageRelationsJsonDocument));
        damageRelationsMap.put("Resistances", parseResistantTo(damageRelationsJsonDocument));
        damageRelationsMap.put("Immunities", parseImmuneTo(damageRelationsJsonDocument));

        return damageRelationsMap;
    }

    private List<String> parseWeakTo(Object damageRelationsJsonDocument) {
        return JsonPath.read(damageRelationsJsonDocument, "$.double_damage_from..name");
    }

    private List<String> parseResistantTo(Object damageRelationsJsonDocument) {
        return JsonPath.read(damageRelationsJsonDocument, "$.half_damage_from..name");
    }

    private List<String> parseImmuneTo(Object damageRelationsJsonDocument) {
        return JsonPath.read(damageRelationsJsonDocument, "$.no_damage_from..name");
    }
}
