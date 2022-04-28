package edu.bsu.cs222.model;

import java.util.LinkedHashMap;
import java.util.List;

public class EvolutionChain {
    public static Builder withNames(List<String> speciesNames) {
        return new Builder(speciesNames);
    }

    public static final class Builder {
        private final List<String> speciesNames;
        private List<LinkedHashMap<String, EvolutionDetailsValues>> evolutionDetails;

        public Builder(List<String> speciesNames) {
            this.speciesNames = speciesNames;
        }

        public EvolutionChain andEvolutionDetails(List<LinkedHashMap<String, EvolutionDetailsValues>> evolutionDetails) {
            this.evolutionDetails = evolutionDetails;
            return new EvolutionChain(this);
        }
    }

    private final List<String> speciesNames;
    private final List<LinkedHashMap<String, EvolutionDetailsValues>> evolutionDetails;

    public EvolutionChain(Builder builder) {
        this.speciesNames = builder.speciesNames;
        this.evolutionDetails = builder.evolutionDetails;
    }

    public List<String> getSpeciesNames() {
        return speciesNames;
    }

    public List<LinkedHashMap<String, EvolutionDetailsValues>> getEvolutionDetails() {
        return evolutionDetails;
    }
}
