package edu.bsu.cs222.model.parsers;

import com.jayway.jsonpath.JsonPath;

import java.util.List;


public class VersionGroupParser {
    public String parseForName(Object versionGroupJsonDocument) {
        return JsonPath.read(versionGroupJsonDocument, "$.name");
    }

    public int parseForID(Object versionGroupJsonDocument) {
        return JsonPath.read(versionGroupJsonDocument, "$.id");
    }

    public List<String> parseForVersionNames(Object versionGroupJsonDocument) {
        return JsonPath.read(versionGroupJsonDocument, "$.versions..name");
    }
}
