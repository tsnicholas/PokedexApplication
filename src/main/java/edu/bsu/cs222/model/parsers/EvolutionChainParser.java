package edu.bsu.cs222.model.parsers;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs222.model.EvolutionChain;
import net.minidev.json.JSONArray;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class EvolutionChainParser {
    private List<String> speciesNames;
    private List<LinkedHashMap<String, Object>> evolutionDetails;

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

    private LinkedHashMap<String, Object> parseEvolutionDetails(Object evolvesToJson) {
        int size = JsonPath.read(evolvesToJson, "$.evolution_details.size()");
        size--;
        LinkedHashMap<String, Object> test = JsonPath.read(evolvesToJson, "$.evolution_details[" + size + "]");
        test.entrySet().removeIf(item -> item.getValue() == null);
        return test;
    }
}
