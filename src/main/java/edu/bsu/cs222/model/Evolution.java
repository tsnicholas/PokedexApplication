package edu.bsu.cs222.model;

public class Evolution {
    public static Builder withName(String speciesNames) {
        return new Builder(speciesNames);
    }

    public static final class Builder {
        private final String speciesNames;
        private boolean needsOverWorldRain;
        private boolean needsToBeUpsideDown;
        private String evolutionTrigger;

        public Builder(String speciesNames) {
            this.speciesNames = speciesNames;
        }

        public Builder needsOverworldRain(boolean needsOverworldRain) {
            this.needsOverWorldRain = needsOverworldRain;
            return this;
        }

        public Builder needsToBeUpsideDown(boolean needsToBeUpsideDown) {
            this.needsToBeUpsideDown = needsToBeUpsideDown;
            return this;
        }

        public Evolution andEvolutionTrigger(String evolutionTrigger) {
            this.evolutionTrigger = evolutionTrigger;
            return new Evolution(this);
        }
    }
    private static final String NULL_STRING_VALUE = "--";

    private final String speciesName;
    private final String evolutionTrigger;
    private final boolean needsOverworldRain;

    private final boolean needsToBeUpsideDown;
    private int minimumLevel = 1;
    private boolean isAffectionEvolution = false;
    private boolean isHappyEvolution = false;
    private boolean isBeautyEvolution = false;
    private String timeOfDay = NULL_STRING_VALUE;
    private String gender = NULL_STRING_VALUE;
    private String location = NULL_STRING_VALUE;
    private String heldItem = NULL_STRING_VALUE;
    private String usedItem = NULL_STRING_VALUE;
    private String knownMove = NULL_STRING_VALUE;
    private String knownMoveType = NULL_STRING_VALUE;
    private PokemonSpecies tradeSpecies;
    private PokemonSpecies partySpecies;
    private Integer relativePhysicalStats;

    public Evolution(Builder builder) {
        this.speciesName = builder.speciesNames;
        this.evolutionTrigger = builder.evolutionTrigger;
        this.needsOverworldRain = builder.needsOverWorldRain;
        this.needsToBeUpsideDown = builder.needsToBeUpsideDown;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public String getEvolutionTrigger() {
        return evolutionTrigger;
    }

    public boolean needsOverworldRain() {
        return needsOverworldRain;
    }


    public boolean needsToBeUpsideDown() {
        return needsToBeUpsideDown;
    }

    public void setMinimumLevel(int minimumLevel) {
        this.minimumLevel = minimumLevel;
    }

    public int getMinimumLevel() {
        return minimumLevel;
    }

    public void setAffectionEvolution(boolean hasFriendshipRequirements) {
        isAffectionEvolution = hasFriendshipRequirements;
    }

    public boolean isAffectionEvolution() {
        return isAffectionEvolution;
    }

    public void setHappyEvolution(boolean hasHappinessRequirements) {
        isHappyEvolution = hasHappinessRequirements;
    }

    public boolean isHappyEvolution() {
        return isHappyEvolution;
    }

    public void setBeautyEvolution(boolean hasBeautyRequirements) {
        isBeautyEvolution = hasBeautyRequirements;
    }

    public boolean isBeautyEvolution() {
        return isBeautyEvolution;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setHeldItem(String heldItem) {
        this.heldItem = heldItem;
    }

    public String getHeldItem() {
        return heldItem;
    }

    public boolean hasHeldItem() {
        return heldItem != null;
    }

    public void setUsedItem(String usedItem) {
        this.usedItem = usedItem;
    }

    public String getUsedItem() {
        return usedItem;
    }

    public boolean hasUsedItem() {
        return usedItem != null;
    }

    public String getKnownMove() {
        return knownMove;
    }

    public String getKnownMoveType() {
        return knownMoveType;
    }

    public void setKnownMove(String known_move) {
        knownMove = known_move;
    }

    public boolean hasKnownMoveType() {
        return knownMoveType != null;
    }

    public void setKnownMoveType(String knownMoveType) {
        this.knownMoveType = knownMoveType;
    }

    public boolean hasKnownMove() {
        return knownMove != null;
    }

    public PokemonSpecies getTradeSpecies() {
        return tradeSpecies;
    }

    public void setTradeSpecies(PokemonSpecies tradeSpecies) {
        this.tradeSpecies = tradeSpecies;
    }

    public Integer getRelativePhysicalStats() {
        return relativePhysicalStats;
    }

    public void setRelativePhysicalStats(Integer relativePhysicalStats) {
        this.relativePhysicalStats = relativePhysicalStats;
    }

    public PokemonSpecies getPartySpecies() {
        return partySpecies;
    }

    public void setPartySpecies(PokemonSpecies partySpecies) {
        this.partySpecies = partySpecies;
    }
}
