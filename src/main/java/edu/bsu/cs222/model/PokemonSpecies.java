package edu.bsu.cs222.model;

public class PokemonSpecies {
    public static Builder withName(String speciesName) {
        return new Builder(speciesName);
    }

    public static final class Builder {
        private final String speciesName;
        private String generationName;

        public Builder(String speciesName) {
            this.speciesName = speciesName;
        }

        public PokemonSpecies andGenerationName(String generationName) {
            this.generationName = generationName;
            return new PokemonSpecies(this);
        }
    }

    private final String speciesName;
    private final String generationName;

    public PokemonSpecies(Builder builder) {
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
