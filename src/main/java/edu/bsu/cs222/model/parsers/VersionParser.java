package edu.bsu.cs222.model.parsers;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs222.model.Version;

public class VersionParser {

    public Version parseForVersion(Object versionJsonDocument) {
        String versionName = parseForVersionName(versionJsonDocument);
        String verionGroupName = parseForVersionGroupName(versionJsonDocument);
        return new Version(versionName, verionGroupName);
    }

    private String parseForVersionGroupName(Object versionJsonDocument) {
        return JsonPath.read(versionJsonDocument, "$.version_group.name");
    }

    private String parseForVersionName(Object versionJsonDocument) {
        return JsonPath.read(versionJsonDocument, "$.name");
    }
}
