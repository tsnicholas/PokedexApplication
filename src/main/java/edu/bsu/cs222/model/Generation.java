package edu.bsu.cs222.model;

import java.util.List;

public class Generation {

    public static Builder withName(String name) {
        return new Builder(name);
    }

    public static final class Builder {
        private final String generationName;
        private Integer generationID;
        private List<VersionGroup> versionGroups;

        public Builder(String name) {
            this.generationName = name;
        }

        public Builder andID(Integer id) {
            this.generationID = id;
            return this;
        }

        public Generation andVersionGroups(List<VersionGroup> versionGroups) {
            this.versionGroups = versionGroups;
            return new Generation(this);
        }
    }


    private final String generationName;
    private final Integer generationID;
    private final List<VersionGroup> versionGroups;

    public Generation(Builder builder) {
        this.generationName = builder.generationName;
        this.generationID = builder.generationID;
        this.versionGroups = builder.versionGroups;
    }

    public String getGenerationName() {
        return generationName;
    }

    public Integer getGenerationID() {
        return generationID;
    }

    public List<VersionGroup> getVersionGroups() {
        return versionGroups;
    }
}
