package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.util.List;

public class TypeBuilder {

    public Type createType(String name, Object typeJsonObject) {
        TypeParser typeParser = new TypeParser();
        String jsonRootOfDamageRelations = createRootOfDamageRelations(typeJsonObject);

        List<String> weakToList = typeParser.parseWeakTo(typeJsonObject, jsonRootOfDamageRelations);
        List<String> resistantToList = typeParser.parseResistantTo(typeJsonObject, jsonRootOfDamageRelations);
        List<String> immuneToList = typeParser.parseImmuneTo(typeJsonObject, jsonRootOfDamageRelations);

        return new Type(name, weakToList, resistantToList, immuneToList);
    }

    private String createRootOfDamageRelations(Object typeJsonDocument) {
        JSONArray genOneDamageRelationsArray = JsonPath.read(typeJsonDocument,"$.past_damage_relations");
        String jsonPathRoot = "$.damage_relations";
        if (genOneDamageRelationsArray.size() != 0) {
            jsonPathRoot = "$.past_damage_relations[0].damage_relations"; // This solution only works for gen 1
        }
        return jsonPathRoot;
    }
}
