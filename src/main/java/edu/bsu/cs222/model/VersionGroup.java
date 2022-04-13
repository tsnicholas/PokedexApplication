package edu.bsu.cs222.model;

import java.util.List;

public class VersionGroup {
    public static Builder withName(String versionGroupName) {
        return new Builder(versionGroupName);
    }

    public static final class Builder {
        private final String versionGroupName;
        private int id;
        private List<String> versionNames;

        public Builder(String versionGroupName) {
            this.versionGroupName = versionGroupName;
        }

        public Builder andID(int id) {
            this.id = id;
            return this;
        }

        public VersionGroup andVersionNames(List<String> versionNames) {
            this.versionNames = versionNames;
            return new VersionGroup(this);
        }
    }


    private final String versionGroupName;
    private final int id;
    private final List<String> versionNames;

    public VersionGroup(Builder builder) {
        this.versionGroupName = builder.versionGroupName;
        this.id = builder.id;
        this.versionNames = builder.versionNames;
    }

    public String getVersionGroupName() {
        return versionGroupName;
    }

    public int getID() {
        return id;
    }

    public List<String> getVersionNames() {
        return versionNames;
    }

    @Override
    public String toString() {
        return versionGroupName;
    }
}
