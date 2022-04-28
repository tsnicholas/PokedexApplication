package edu.bsu.cs222.model.parsers;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs222.model.*;
import net.minidev.json.JSONArray;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class EvolutionChainParser {
    private List<String> speciesNames;
    private List<LinkedHashMap<String, EvolutionDetailsValues>> evolutionDetails;

    public EvolutionChain parseForEvolutionChain(Object evolutionChainJsonDocument) {
        speciesNames = new LinkedList<>();
        evolutionDetails = new LinkedList<>();
        Object chain = JsonPath.read(evolutionChainJsonDocument, "$.chain");
        speciesNames.add(parseForSpeciesName(chain));
        return parseThroughEvolvesToArray(parseForEvolvesToArray(chain));
    }

    private String parseForSpeciesName(Object evolvesTo) {
        return JsonPath.read(evolvesTo, "$.species.name");
    }

    private JSONArray parseForEvolvesToArray(Object chain) {
        return JsonPath.read(chain, "$.evolves_to");
    }

    private EvolutionChain parseThroughEvolvesToArray(JSONArray evolvesToArray) {
        for (Object evolvesTo : evolvesToArray) {
            speciesNames.add(parseForSpeciesName(evolvesTo));
            evolutionDetails.add(parseEvolutionDetails(evolvesTo));
            evolvesToArray = parseForEvolvesToArray(evolvesTo);
            parseThroughEvolvesToArray(evolvesToArray);
        }
        return EvolutionChain.withNames(speciesNames)
                .andEvolutionDetails(evolutionDetails);
    }

    private LinkedHashMap<String, EvolutionDetailsValues> parseEvolutionDetails(Object evolvesToJson) {
        int size = JsonPath.read(evolvesToJson, "$.evolution_details.size()");
        size--;

        LinkedHashMap<String, ?> jsonHashMap = JsonPath.read(evolvesToJson, "$.evolution_details[" + size + "]");
        removeNullKeyAndValuePairs(jsonHashMap);

        LinkedHashMap<String, EvolutionDetailsValues> evolutionDetailMap = new LinkedHashMap<>();
        evolutionDetailMap.put("trigger", getValueType(jsonHashMap.get("trigger")));

        jsonHashMap.remove("trigger");
        jsonHashMap.forEach((key, value) -> evolutionDetailMap.put(key, getValueType(value)));

        removeNullKeyAndValuePairs(evolutionDetailMap);

        return evolutionDetailMap;
    }

    private EvolutionDetailsValues getValueType(Object value) {
        if (value instanceof Integer) {
            return new EvolutionDetailInteger((Integer) value);
        } else if (value instanceof String && !value.equals("")) {
            return new EvolutionDetailString((String) value);
        } else if (value instanceof LinkedHashMap<?, ?>) {
            return new EvolutionDetailLinkedHashMap((LinkedHashMap<?, ?>) value);
        } else if (value instanceof Boolean && (Boolean) value) {
            return new EvolutionDetailBoolean((Boolean) value);
        } else {
            return null;
        }
    }

    private void removeNullKeyAndValuePairs(LinkedHashMap<?, ?> linkedHashMap) {
        linkedHashMap.entrySet().removeIf(item -> item.getValue() == null);
    }
}
