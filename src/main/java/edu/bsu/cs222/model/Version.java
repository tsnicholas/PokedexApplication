package edu.bsu.cs222.model;

public class Version {

    public static Builder withName(String name) {
        return new Builder(name);
    }

    public static final class Builder {
        private final String versionName;
        private VersionGroup versionGroup;
        private Generation generation;
        private GenerationMap generationMap;

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

        public Version andGenerationMap(GenerationMap generationMap) {
            this.generationMap = generationMap;
            return new Version(this);
        }
    }

    private final String versionName;
    private final VersionGroup versionGroup;
    private final Generation generation;
    private final GenerationMap generationMap;

    private Version(Builder builder) {
        this.versionName = builder.versionName;
        this.versionGroup = builder.versionGroup;
        this.generation = builder.generation;
        this.generationMap = builder.generationMap;
    }

    public String getVersionName() {
        return versionName;
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
}
