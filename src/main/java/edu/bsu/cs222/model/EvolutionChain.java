package edu.bsu.cs222.model;

import java.util.LinkedHashMap;
import java.util.List;

public class EvolutionChain {
    public static Builder withNames(List<String> speciesNames) {
        return new Builder(speciesNames);
    }

    public static final class Builder {
        private final List<String> speciesNames;
        private List<LinkedHashMap<String, Object>> evolutionDetails;
        private List<String> evolutionTriggers;

        public Builder(List<String> speciesNames) {
            this.speciesNames = speciesNames;
        }

        public Builder andEvolutionDetails(List<LinkedHashMap<String, Object>> evolutionDetails) {
            this.evolutionDetails = evolutionDetails;
            return this;
        }

        public EvolutionChain andEvolutionTriggers(List<String> evolutionTriggers) {
            this.evolutionTriggers = evolutionTriggers;
            return new EvolutionChain(this);
        }
    }
    private final List<String> speciesNames;
    private final List<LinkedHashMap<String, Object>> evolutionDetails;
    private final List<String> evolutionTriggers;

    public EvolutionChain(Builder builder) {
        this.speciesNames = builder.speciesNames;
        this.evolutionDetails = builder.evolutionDetails;
        this.evolutionTriggers = builder.evolutionTriggers;
    }

    public List<String> getSpeciesNames() {
        return speciesNames;
    }

    public List<LinkedHashMap<String, Object>> getEvolutionDetails() {
        return evolutionDetails;
    }

    public List<String> getEvolutionTriggers() {
        return evolutionTriggers;
    }
}
