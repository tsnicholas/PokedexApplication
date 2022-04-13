package edu.bsu.cs222.model;

import java.util.HashMap;

public class Version {
    public static Builder withName(String name) {
        return new Builder(name);
    }

    public static final class Builder {
        private final String versionName;
        private VersionGroup versionGroup;
        private Generation generation;
        private GenerationMap generationMap;
        private HashMap<String, Integer> versionGroupMap;

        public Builder(String versionName) {
            this.versionName = versionName;
        }

        public Builder andVersionGroup(VersionGroup versionGroup) {
            this.versionGroup = versionGroup;
            return this;
        }

        public Builder andGeneration(Generation generation) {
            this.generation = generation;
            return this;
        }

        public Builder andGenerationMap(GenerationMap generationMap) {
            this.generationMap = generationMap;
            return this;
        }

        public Version andVersionGroupMap(HashMap<String, Integer> versionGroupMap) {
            this.versionGroupMap = versionGroupMap;
            return new Version(this);
        }
    }

    private final String versionName;
    private final VersionGroup versionGroup;
    private final Generation generation;
    private final GenerationMap generationMap;
    private final HashMap<String, Integer> versionGroupMap;

    private Version(Builder builder) {
        this.versionName = builder.versionName;
        this.versionGroup = builder.versionGroup;
        this.generation = builder.generation;
        this.generationMap = builder.generationMap;
        this.versionGroupMap = builder.versionGroupMap;
    }

    public VersionGroup getVersionGroup() {
        return versionGroup;
    }

    public Generation getGeneration() {
        return generation;
    }

    public GenerationMap getGenerationMap() {
        return generationMap;
    }

    public HashMap<String, Integer> getVersionGroupMap() {
        return versionGroupMap;
    }

    public String getVersionName() {
        return versionName;
    }

    // Without this, names on the ChoiceBox will be meaningless
    @Override
    public String toString() {
        return versionName;
    }
}
