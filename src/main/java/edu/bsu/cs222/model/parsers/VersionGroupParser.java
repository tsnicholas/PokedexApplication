package edu.bsu.cs222.model.parsers;

import com.jayway.jsonpath.JsonPath;

public class VersionGroupParser {
    public String parseForName(Object versionGroupJsonDocument) {
        return JsonPath.read(versionGroupJsonDocument, "$.name");
    }
}
