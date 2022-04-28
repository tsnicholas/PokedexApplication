package edu.bsu.cs222.model;

public class Location {
    public static Builder withName(String speciesName) {
        return new Builder(speciesName);
    }

    public static final class Builder {
        private final String speciesName;
        private String generationName;

        public Builder(String speciesName) {
            this.speciesName = speciesName;
        }

        public Location andGenerationName(String generationName) {
            this.generationName = generationName;
            return new Location(this);
        }
    }

    private final String speciesName;
    private final String generationName;

    public Location(Builder builder) {
        speciesName = builder.speciesName;
        generationName = builder.generationName;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public String getGenerationName() {
        return generationName;
    }
}
