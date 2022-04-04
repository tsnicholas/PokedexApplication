package edu.bsu.cs222.model.parsers;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs222.model.URLProcessor;
import edu.bsu.cs222.model.Version;
import net.minidev.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


public class VersionGroupParser {
    public String parseForName(Object versionGroupJsonDocument) {
        return JsonPath.read(versionGroupJsonDocument, "$.name");
    }

    public List<Version> parseForVersions(Object versionGroupJsonDocument) {
        URLProcessor urlProcessor = new URLProcessor();
        VersionParser versionParser = new VersionParser();
        List<Version> versions = new ArrayList<>();

        JSONArray versionURLs = JsonPath.read(versionGroupJsonDocument, "$.versions..url");
        for (Object url : versionURLs) {
            Object versionJsonDocument = urlProcessor.stringToObject(url.toString());
            Version version = versionParser.parseForVersion(versionJsonDocument);
            versions.add(version);
        }
        return versions;
    }
}
