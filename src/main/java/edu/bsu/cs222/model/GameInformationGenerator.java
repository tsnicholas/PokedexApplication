package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs222.model.parsers.GenerationParser;
import edu.bsu.cs222.model.parsers.VersionGroupParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameInformationGenerator {
    private final URLProcessor urlProcessor;
    private final GenerationParser generationParser = new GenerationParser();
    private final HashMap<String, Integer> versionGroupMap = new HashMap<>();

    public GameInformationGenerator() {
        this.urlProcessor = new ProductionURLProcessor();
    }

    public GameInformationGenerator(URLProcessor urlProcessor) {
        this.urlProcessor = urlProcessor;
    }

    public List<Version> getListOfAllVersions() {
        List<Generation> generations = getListOfAllGenerations();
        HashMap<String, Integer> generationMap = createGenerationMap(generations);
        return createVersionList(generations, generationMap);
    }

    private List<Generation> getListOfAllGenerations() {
        Object allGenerationsJsonDocument = urlProcessor.getUpTo20Generations();
        if (generationParser.containsAllGenerations(allGenerationsJsonDocument)) {
            return makeListOfGenerations(allGenerationsJsonDocument);
        }
        int count = generationParser.parseForNumberOfGenerations(allGenerationsJsonDocument);
        allGenerationsJsonDocument = urlProcessor.getAllGenerations(count);
        return makeListOfGenerations(allGenerationsJsonDocument);
    }

    private HashMap<String, Integer> createGenerationMap(List<Generation> generations) {
        HashMap<String, Integer> generationMap = new HashMap<>();
        for (Generation generation : generations) {
            generationMap.put(generation.getGenerationName(), generation.getGenerationID());
        }
        return generationMap;
    }

    private List<Generation> makeListOfGenerations(Object allGenerationsJsonDocument) {
        List<Generation> generations = new ArrayList<>();
        List<String> generationURLs = generationParser.parseForGenerationURL(allGenerationsJsonDocument);
        for (String url : generationURLs) {
            generations.add(createGeneration(url));
        }
        return generations;
    }

    private Generation createGeneration(String url) {
        Object generationJsonDocument = urlProcessor.convertStringToObject(url);
        return Generation.withName(generationParser.parseForName(generationJsonDocument))
                .andID(generationParser.parseForID(generationJsonDocument))
                .andVersionGroups(makeListOfVersionGroups(generationJsonDocument));
    }

    private List<VersionGroup> makeListOfVersionGroups(Object generationJsonDocument) {
        List<VersionGroup> versionGroups = new ArrayList<>();
        List<String> versionGroupURLs = JsonPath.read(generationJsonDocument, "$.version_groups..url");
        for (String url : versionGroupURLs) {
            VersionGroup versionGroup = createVersionGroup(url);
            versionGroups.add(versionGroup);
            versionGroupMap.put(versionGroup.getVersionGroupName(), versionGroup.getID());
        }
        return versionGroups;
    }

    private VersionGroup createVersionGroup(String url) {
        VersionGroupParser versionGroupParser = new VersionGroupParser();
        Object versionGroupJsonDocument = urlProcessor.convertStringToObject(url);
        return VersionGroup.withName(versionGroupParser.parseForName(versionGroupJsonDocument))
                .andID(versionGroupParser.parseForID(versionGroupJsonDocument))
                .andVersionNames(versionGroupParser.parseForVersionNames(versionGroupJsonDocument));
    }

    private List<Version> createVersionList(List<Generation> generations, HashMap<String, Integer> generationMap) {
        List<Version> versionList = new ArrayList<>();
        for (Generation generation : generations) {
            for (VersionGroup versionGroup : generation.getVersionGroups()) {
                for (String versionName : versionGroup.getVersionNames()) {
                    versionList.add(Version.withName(versionName).andVersionGroup(versionGroup)
                            .andGeneration(generation).andGenerationMap(generationMap)
                            .andVersionGroupMap(versionGroupMap));
                }
            }
        }
        return versionList;
    }
}
