package edu.bsu.cs222.model;

public class Evolution {
    public static Builder withName(String speciesNames) {
        return new Builder(speciesNames);
    }

    public static final class Builder {
        private final String speciesNames;
        private String evolutionTrigger;

        public Builder(String speciesNames) {
            this.speciesNames = speciesNames;
        }

        public Evolution andEvolutionTrigger(String evolutionTrigger) {
            this.evolutionTrigger = evolutionTrigger;
            return new Evolution(this);
        }
    }

    private final String speciesName;
    private final String evolutionTrigger;
    private int minimumLevel = 1;
    private boolean isAffectionEvolution = false;
    private boolean isHappyEvolution = false;
    private boolean isBeautyEvolution = false;
    private String timeOfDay;
    private String gender;
    private String heldItem;
    private String usedItem;
    private String knownMove;
    private String knownMoveType;

    public Evolution(Builder builder) {
        this.speciesName = builder.speciesNames;
        this.evolutionTrigger = builder.evolutionTrigger;
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

    public boolean hasGender() {
        return gender != null;
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

    public void setKnownMove(String known_move) {
        knownMove = known_move;
    }

    public String getKnownMoveType() {
        return knownMoveType;
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
}
