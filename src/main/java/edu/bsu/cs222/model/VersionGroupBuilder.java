package edu.bsu.cs222.model;

import edu.bsu.cs222.model.parsers.VersionGroupParser;

import java.util.List;

public class VersionGroupBuilder {
    public VersionGroup createVersionGroup(Object versionGroupJsonDocument) {
        VersionGroupParser versionGroupParser = new VersionGroupParser();
        String name = versionGroupParser.parseForName(versionGroupJsonDocument);
        List<Version> versions = versionGroupParser.parseForVersions(versionGroupJsonDocument);
        return new VersionGroup(name, versions);
    }
}
