package edu.bsu.cs222.model.parsers;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs222.model.Evolution;
import net.minidev.json.JSONArray;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class EvolutionChainParser {
    public List<Evolution> parseForEvolutions(Object evolutionChainJsonDocument) {
        Object chain = JsonPath.read(evolutionChainJsonDocument, "$.chain");
        List<Evolution> evolutionChain = new LinkedList<>();
        evolutionChain.add(parseFirstPokemon(chain));
        parseThroughEvolvesToArray(parseForEvolvesToArray(chain), evolutionChain);
        return evolutionChain;
    }

    private Evolution parseFirstPokemon(Object chain) {
        String name = parseForSpeciesName(chain);
        return Evolution.withName(name).andEvolutionTrigger("base pokemon");
    }

    private String parseForSpeciesName(Object evolvesTo) {
        return JsonPath.read(evolvesTo, "$.species.name");
    }

    private JSONArray parseForEvolvesToArray(Object chain) {
        return JsonPath.read(chain, "$.evolves_to");
    }

    private void parseThroughEvolvesToArray(JSONArray evolvesToArray, List<Evolution> evolutionChain) {
        for (Object evolvesTo : evolvesToArray) {
            Evolution evolution = Evolution.withName(parseForSpeciesName(evolvesTo))
                    .andEvolutionTrigger(parseEvolutionTrigger(evolvesTo));
            evolutionChain.add(gainEvolutionMethods(evolution, evolvesTo));
            evolvesToArray = parseForEvolvesToArray(evolvesTo);
            parseThroughEvolvesToArray(evolvesToArray, evolutionChain);
        }
    }

    private String parseEvolutionTrigger(Object evolvesToDocument) {
        JSONArray evolutionTrigger = JsonPath.read(evolvesToDocument, "$.evolution_details..trigger..name");
        return evolutionTrigger.get(0).toString();
    }

    private Evolution gainEvolutionMethods(Evolution evolution, Object evolvesTo) {
        LinkedHashMap<String, Object> evolutionDetailsMap = parseEvolutionDetailsMap(evolvesTo);
        checkMinimumLevel(evolutionDetailsMap, evolution);
        checkGender(evolutionDetailsMap, evolution);
        checkRelationshipRequirements(evolutionDetailsMap, evolution);
        evolution.setTimeOfDay(evolutionDetailsMap.get("time_of_day").toString());

        evolution.setUsedItem(getObjectName(evolutionDetailsMap, "item"));
        evolution.setHeldItem(getObjectName(evolutionDetailsMap, "held_item"));
        evolution.setKnownMove(getObjectName(evolutionDetailsMap, "known_move"));
        evolution.setKnownMoveType(getObjectName(evolutionDetailsMap, "known_move_type"));

        return evolution;
    }

    private LinkedHashMap<String, Object> parseEvolutionDetailsMap(Object evolvesTo) {
        int size = JsonPath.read(evolvesTo, "$.evolution_details.size()");
        size--;
        return JsonPath.read(evolvesTo, "$.evolution_details[" + size + "]");
    }

    private void checkMinimumLevel(LinkedHashMap<String, Object> evolutionDetailsMap, Evolution evolution) {
        Integer minLevel = (Integer) evolutionDetailsMap.get("min_level");
        evolution.setMinimumLevel(Objects.requireNonNullElse(minLevel, 1));
    }

    private void checkGender(LinkedHashMap<String, Object> evolutionDetailsMap, Evolution evolution) {
        Integer genderValue = (Integer) evolutionDetailsMap.get("gender");
        if (genderValue != null) {
            evolution.setGender(
                    switch (genderValue) {
                        case 1 -> "female";
                        case 2 -> "male";
                        default -> "";
                    });
        }
    }

    // The specific affection, happiness, and beauty values mean nothing for normal players
    // However, it's still important to know if high affection or happiness is needed to evolve a pokemon.
    // Thus, why we're checking if it's null or not rather than getting the values since they'd go unused.
    private void checkRelationshipRequirements(LinkedHashMap<String, Object> evolutionDetailsMap, Evolution evolution) {
        evolution.setAffectionEvolution(evolutionDetailsMap.get("min_affection") != null);
        evolution.setBeautyEvolution(evolutionDetailsMap.get("min_beauty") != null);
        evolution.setHappyEvolution(evolutionDetailsMap.get("min_happiness") != null);
    }

    private String getObjectName(LinkedHashMap<String, Object> evolutionDetailsMap, String key) {
        Object jsonHashMap = evolutionDetailsMap.get(key);
        if(jsonHashMap != null) {
            return JsonPath.read(jsonHashMap, "$.name");
        }
        return null;
    }

}
