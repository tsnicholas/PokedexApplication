package edu.bsu.cs222.model.parsers;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class EvolutionChainParser {
    public List<String> parseForEvolutionNames(Object evolutionChainJsonDocument) {
        List<String> speciesNames = new ArrayList<>();
        Object chain = JsonPath.read(evolutionChainJsonDocument, "$.chain");
        speciesNames.add(parseForSpeciesName(chain));
        return parseThroughEvolvesToArray(parseForEvolvesToArray(chain), speciesNames);
    }

    private String parseForSpeciesName(Object evolvesTo) {
        return JsonPath.read(evolvesTo, "$.species.name");
    }

    private JSONArray parseForEvolvesToArray(Object chain) {
        return JsonPath.read(chain, "$.evolves_to");
    }

    private List<String> parseThroughEvolvesToArray(JSONArray evolvesToArray, List<String> speciesNames) {
        for (Object evolvesTo : evolvesToArray) {
            speciesNames.add(parseForSpeciesName(evolvesTo));
            evolvesToArray = parseForEvolvesToArray(evolvesTo);
            parseThroughEvolvesToArray(evolvesToArray, speciesNames);
        }
        return speciesNames;
    }

    public String parseForEvolutionTrigger( Object evolutionChainJson) {
        JSONArray triggerNames = JsonPath.read(evolutionChainJson,"$.chain.evolves_to[0]..trigger.name");
        return triggerNames.get(0).toString();
    }
}
