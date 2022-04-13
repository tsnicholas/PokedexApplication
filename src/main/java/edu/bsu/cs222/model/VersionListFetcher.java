package edu.bsu.cs222.model;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs222.model.parsers.GenerationParser;
import edu.bsu.cs222.model.parsers.VersionGroupParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VersionListFetcher {
    private final URLProcessor urlProcessor;
    private final GenerationParser generationParser = new GenerationParser();

    private final HashMap<String, Integer> versionGroupMap = new HashMap<>();

    public VersionListFetcher() {
        this.urlProcessor = new ProductionURLProcessor();
    }

    public VersionListFetcher(URLProcessor urlProcessor) {
        this.urlProcessor = urlProcessor;
    }

    public List<Version> getListOfAllVersions() {
        GenerationMapFactory generationMapFactory = new GenerationMapFactory();
        List<Version> allVersions = new ArrayList<>();
        List<Generation> generations = getListOfAllGenerations();
        GenerationMap generationMap = generationMapFactory.createGenerationMap(generations);
        for (Generation generation : generations) {
            for (VersionGroup versionGroup : generation.getVersionGroups()) {
                for (String versionName : versionGroup.getVersionNames()) {
                    allVersions.add(Version.withName(versionName).andVersionGroup(versionGroup)
                            .andGeneration(generation).andGenerationMap(generationMap)
                            .andVersionGroupMap(versionGroupMap));
                }
            }
        }
        return allVersions;
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

    private List<Generation> makeListOfGenerations(Object allGenerationsJsonDocument) {
        List<Generation> generations = new ArrayList<>();
        List<String> generationURLs = generationParser.parseForGenerationURL(allGenerationsJsonDocument);
        for (String url : generationURLs) {
            Object generationJsonDocument = urlProcessor.convertStringToObject(url);
            String name = generationParser.parseForName(generationJsonDocument);
            Integer id = generationParser.parseForID(generationJsonDocument);
            List<VersionGroup> versionGroups = makeListOfVersionGroups(generationJsonDocument);
            generations.add(Generation.withName(name).andID(id).andVersionGroups(versionGroups));
        }
        return generations;
    }

    private List<VersionGroup> makeListOfVersionGroups(Object generationJsonDocument) {
        VersionGroupParser versionGroupParser = new VersionGroupParser();
        List<VersionGroup> versionGroups = new ArrayList<>();
        List<String> versionGroupURLs = JsonPath.read(generationJsonDocument, "$.version_groups..url");
        for (String url : versionGroupURLs) {
            Object versionGroupJsonDocument = urlProcessor.convertStringToObject(url);
            String name = versionGroupParser.parseForName(versionGroupJsonDocument);
            int id = versionGroupParser.parseForID(versionGroupJsonDocument);
            List<String> versions = versionGroupParser.parseForVersionNames(versionGroupJsonDocument);
            VersionGroup versionGroup = VersionGroup.withName(name).andID(id).andVersionNames(versions);
            versionGroups.add(versionGroup);
            versionGroupMap.put(versionGroup.getVersionGroupName(), versionGroup.getID());
        }
        return versionGroups;
    }
}
