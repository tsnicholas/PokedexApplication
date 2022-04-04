package edu.bsu.cs222.model.parsers;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs222.model.Generation;
import edu.bsu.cs222.model.URLProcessor;
import edu.bsu.cs222.model.VersionGroup;
import edu.bsu.cs222.model.VersionGroupBuilder;
import net.minidev.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class GenerationParser {
    private final URLProcessor urlProcessor = new URLProcessor();
    private final VersionGroupBuilder versionGroupBuilder = new VersionGroupBuilder();

    public List<Generation> parseForGenerations() {
        List<Generation> generations = new ArrayList<>();

        Object allGenerationsJsonDocument = getAllGenerations();
        JSONArray generationURLs = JsonPath.read(allGenerationsJsonDocument, "$.results..url");
        for (Object url : generationURLs) {
            Object generationJsonDocument = urlProcessor.stringToObject(url.toString());
            Generation generation = parseGeneration(generationJsonDocument);
            generations.add(generation);
        }
        return generations;
    }

    private Object getAllGenerations() {
        Object generationJsonDocument = urlProcessor.stringToObject("https://pokeapi.co/api/v2/generation");
        if (containsAllGenerations(generationJsonDocument)) {
            return generationJsonDocument;
        }
        int count = JsonPath.read(generationJsonDocument, "$.count");
        return urlProcessor.stringToObject("https://pokeapi.co/api/v2/generation?limit=" + count);
    }

    private boolean containsAllGenerations(Object allGenerationsJsonDocument) {
        Object next = JsonPath.read(allGenerationsJsonDocument, "$.next");
        return next == null;
    }

    private Generation parseGeneration(Object generationJsonDocument) {
        String name = parseForName(generationJsonDocument);
        int id = parseForID(generationJsonDocument);
        List<VersionGroup> versionGroups = parseForVersionGroups(generationJsonDocument);
        return new Generation(name, id, versionGroups);
    }

    private int parseForID(Object generationJsonDocument) {
        return JsonPath.read(generationJsonDocument, "$.id");
    }

    private String parseForName(Object generationJsonDocument) {
        return JsonPath.read(generationJsonDocument, "$.name");
    }

    private List<VersionGroup> parseForVersionGroups(Object generationJsonDocument) {
        List<VersionGroup> versionGroups = new ArrayList<>();
        JSONArray versionGroupURLs = JsonPath.read(generationJsonDocument, "$.version_groups..url");
        for (Object url : versionGroupURLs) {
            Object versionGroupJsonDocument = urlProcessor.stringToObject(url.toString());
            VersionGroup versionGroup = versionGroupBuilder.createVersionGroup(versionGroupJsonDocument);
            versionGroups.add(versionGroup);
        }
        return versionGroups;
    }
}
