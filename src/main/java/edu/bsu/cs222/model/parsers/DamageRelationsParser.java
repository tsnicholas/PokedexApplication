package edu.bsu.cs222.model.parsers;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs222.model.Version;
import net.minidev.json.JSONArray;

import java.util.HashMap;
import java.util.List;

// PokeAPI does not have past damage relations for Dragon or Fighting type
public class DamageRelationsParser {
    public HashMap<String, List<String>> parseForDamageRelations(Object typeJsonObject, Version version) {
        Object damageRelationsJsonDocument = makeDamageRelationsJsonDocument(typeJsonObject, version);
        return makeDamageRelationsMap(damageRelationsJsonDocument);
    }

    private Object makeDamageRelationsJsonDocument(Object typeJsonDocument, Version version) {
        JSONArray pastDamageRelationsArray = JsonPath.read(typeJsonDocument, "$.past_damage_relations");
        for (Object pastDamageRelation : pastDamageRelationsArray) {
            String generationName = JsonPath.read(pastDamageRelation, "$.generation.name");
            int generationID = version.getGenerationMap().get(generationName);
            if (version.getGeneration().getGenerationID() <= generationID) {
                return JsonPath.read(pastDamageRelation, "$.damage_relations");
            }
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
