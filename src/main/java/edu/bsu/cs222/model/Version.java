package edu.bsu.cs222.model;

public class Version {

    public static Builder withName(String name) {
        return new Builder(name);
    }

    public static final class Builder {
        private final String versionName;
        private VersionGroup versionGroup;
        private Generation generation;

        public Builder(String versionName) {
            this.versionName = versionName;
        }

        public Builder andVersionGroup(VersionGroup versionGroup) {
            this.versionGroup = versionGroup;
            return this;
        }

        public Version andGeneration(Generation generation) {
            this.generation = generation;
            return new Version(this);
        }
    }

    private final String versionName;
    private final VersionGroup versionGroup;
    private final Generation generation;

    public Version(Builder builder) {
        this.versionName = builder.versionName;
        this.versionGroup = builder.versionGroup;
        this.generation = builder.generation;
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
}
