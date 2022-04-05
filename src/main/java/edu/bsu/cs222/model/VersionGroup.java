package edu.bsu.cs222.model;

import java.util.List;

public class VersionGroup {

    public static Builder withName(String versionGroupName) {
        return new Builder(versionGroupName);
    }

    public static final class Builder {
        private final String versionGroupName;
        private List<String> versionNames;

        public Builder(String versionGroupName) {
            this.versionGroupName = versionGroupName;
        }

        public VersionGroup andVersionNames(List<String> versionNames) {
            this.versionNames = versionNames;
            return new VersionGroup(this);
        }
    }


    private final String versionGroupName;
    private final List<String> versionNames;

    public VersionGroup(Builder builder) {
        this.versionGroupName = builder.versionGroupName;
        this.versionNames = builder.versionNames;
    }

    public String getVersionGroupName() {
        return versionGroupName;
    }

    public List<String> getVersionNames() {
        return versionNames;
    }

    @Override
    public String toString() {
        return versionGroupName;
    }
}
