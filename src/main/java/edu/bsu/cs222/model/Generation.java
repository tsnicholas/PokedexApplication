package edu.bsu.cs222.model;

import java.util.List;

public class Generation {
    private final String name;
    private final int id;
    private final List<VersionGroup> versionGroups;

    public Generation(String name, int id, List<VersionGroup> versionGroups) {
        this.name = name;
        this.id = id;
        this.versionGroups = versionGroups;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<VersionGroup> getVersionGroups() {
        return versionGroups;
    }
}
