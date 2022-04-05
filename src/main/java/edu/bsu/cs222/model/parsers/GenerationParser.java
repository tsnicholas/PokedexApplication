package edu.bsu.cs222.model.parsers;

import com.jayway.jsonpath.JsonPath;

import java.util.List;

public class GenerationParser {

    public boolean containsAllGenerations(Object allGenerationsJsonDocument) {
        Object next = JsonPath.read(allGenerationsJsonDocument, "$.next");
        return next == null;
    }

    public int parseForCountOfGenerations(Object allGenerationsJsonDocument) {
        return JsonPath.read(allGenerationsJsonDocument, "$.count");
    }

    public List<String> parseForGenerationURL(Object allGenerationsJsonDocument) {
        return JsonPath.read(allGenerationsJsonDocument, "$.results..url");
    }

    public int parseForID(Object generationJsonDocument) {
        return JsonPath.read(generationJsonDocument, "$.id");
    }

    public String parseForName(Object generationJsonDocument) {
        return JsonPath.read(generationJsonDocument, "$.name");
    }
}
