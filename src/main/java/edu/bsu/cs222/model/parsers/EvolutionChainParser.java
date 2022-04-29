package edu.bsu.cs222.model.parsers;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs222.model.Evolution;
import edu.bsu.cs222.model.ProductionURLProcessor;
import edu.bsu.cs222.model.URLProcessor;
import edu.bsu.cs222.model.Version;
import net.minidev.json.JSONArray;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class EvolutionChainParser {
    List<Evolution> evolutionChain;
    URLProcessor urlProcessor;

    public EvolutionChainParser() {
        this.urlProcessor = new ProductionURLProcessor();
    }

    public EvolutionChainParser(URLProcessor urlProcessor) {
        this.urlProcessor = urlProcessor;
    }

    public List<Evolution> parseForEvolutions(Object evolutionChainJsonDocument, Version version) {
        Object chain = JsonPath.read(evolutionChainJsonDocument, "$.chain");
        evolutionChain = new LinkedList<>();
        if(assertSpeciesIsWithinGeneration(chain, version)) {
            evolutionChain.add(parseFirstPokemon(chain));
        }
        parseNextEvolutionTier(parseForEvolvesToArray(chain), version);
        return evolutionChain;
    }

    private boolean assertSpeciesIsWithinGeneration(Object evolutionDocument, Version version) {
        Object speciesJsonDocument = processSpeciesUrl(evolutionDocument);
        PokemonSpeciesParser pokemonSpeciesParser = new PokemonSpeciesParser();
        return pokemonSpeciesParser.pokemonSpeciesExistsInVersion(speciesJsonDocument, version);
    }

    private Object processSpeciesUrl(Object evolvesTo) {
        String speciesURL = JsonPath.read(evolvesTo, "$.species.url");
        return urlProcessor.convertStringToObject(speciesURL);
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

    private void parseNextEvolutionTier(JSONArray evolvesToArray, Version version) {
        for (Object evolvesTo : evolvesToArray) {
            if(assertSpeciesIsWithinGeneration(evolvesTo, version)) {
                Evolution evolution = Evolution.withName(parseForSpeciesName(evolvesTo))
                        .andEvolutionTrigger(parseEvolutionTrigger(evolvesTo));
                evolutionChain.add(gainEvolutionMethods(evolution, evolvesTo));
            }
            evolvesToArray = parseForEvolvesToArray(evolvesTo);
            parseNextEvolutionTier(evolvesToArray, version);
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
                    // Gender doesn't need to be an integer value.
                    // Not entirely sure why it's stored as an integer in the first place.
                    switch (genderValue) {
                        case 1 -> "female";
                        case 2 -> "male";
                        default -> "";
                    });
        }
    }

    // The specific affection, happiness, and beauty values mean nothing for normal players, it's also the same value almost every time.
    // However, it's still important to know if high affection or happiness is needed to evolve a pokemon.
    // Thus, why we're checking if it's null or not rather than getting the integer values since they'd go unused.
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
