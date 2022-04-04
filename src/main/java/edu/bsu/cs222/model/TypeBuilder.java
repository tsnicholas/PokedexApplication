package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs222.model.parsers.TypeParser;

import java.util.HashMap;
import java.util.List;

public class TypeBuilder {

    //TODO: convert into damage relations parser, typeBuilder to be replaced by Type.Builder

    public Type createType(String name, Object typeJsonObject) {
        Object damageRelationsJsonDocument = makeDamageRelationsJsonDocument(typeJsonObject);
        HashMap<String, List<String>> damageRelationsMap = makeDamageRelationsMap(damageRelationsJsonDocument);
        return Type.withName(name).andDamageRelations(damageRelationsMap);
    }

    private Object makeDamageRelationsJsonDocument(Object typeJsonDocument) {
        int pastDamageRelationsArraySize = JsonPath.read(typeJsonDocument, "$.past_damage_relations.length()");
        if (pastDamageRelationsArraySize != 0) {
            typeJsonDocument = JsonPath.read(typeJsonDocument, "$.past_damage_relations[0]");
        }
        return JsonPath.read(typeJsonDocument, "$.damage_relations");
    }

    private HashMap<String, List<String>> makeDamageRelationsMap(Object damageRelationsJsonDocument) {
        TypeParser typeParser = new TypeParser();
        HashMap<String, List<String>> damageRelationsMap = new HashMap<>();

        damageRelationsMap.put("Weaknesses", typeParser.parseWeakTo(damageRelationsJsonDocument));
        damageRelationsMap.put("Resistances", typeParser.parseResistantTo(damageRelationsJsonDocument));
        damageRelationsMap.put("Immunities", typeParser.parseImmuneTo(damageRelationsJsonDocument));

        return damageRelationsMap;
    }
}
