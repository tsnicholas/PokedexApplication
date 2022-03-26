package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs222.parsers.TypeParser;
import net.minidev.json.JSONArray;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class TypeBuilder {

    public Type createType(String name, Object typeJsonObject) {
        TypeParser typeParser = new TypeParser();
        LinkedHashMap<Class<?>, Class<?>> damageRelationsMap = createRootOfDamageRelations(typeJsonObject);
        HashMap<String, List<String>> damageRelations = new HashMap<>();
        damageRelations.put("Weaknesses", typeParser.parseWeakTo(damageRelationsMap));
        damageRelations.put("Resistances", typeParser.parseResistantTo(damageRelationsMap));
        damageRelations.put("Immunities", typeParser.parseImmuneTo(damageRelationsMap));
        return new Type(name, damageRelations);
    }

    private LinkedHashMap<Class<?>, Class<?>> createRootOfDamageRelations(Object typeJsonDocument) {
        JSONArray pastDamageRelationsArray = JsonPath.read(typeJsonDocument,"$.past_damage_relations");
        LinkedHashMap<Class<?>, Class<?>> yellowDamageRelationsMap;
        if (pastDamageRelationsArray.size() != 0) {
            yellowDamageRelationsMap = JsonPath.read(typeJsonDocument, "$.past_damage_relations[0].damage_relations");
        } else {
            yellowDamageRelationsMap = JsonPath.read(typeJsonDocument, "$.damage_relations");
        }
        return yellowDamageRelationsMap;
    }
}
