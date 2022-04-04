package edu.bsu.cs222.model;

import java.util.List;

public class VersionGroup {
    private final String name;
    private final List<Version> versions;

    public VersionGroup(String name, List<Version> versions) {
        this.name = name;
        this.versions = versions;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
