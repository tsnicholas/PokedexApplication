package edu.bsu.cs222.model;

public class EvolutionChainLink {

    public static Builder withName(String speciesName) {
        return new Builder(speciesName);
    }

    public static final class Builder {
        private final String speciesName;
        private String timeOfDay;
        private Boolean needsOverworldRain;
        private Boolean turnUpsideDown;
        private String trigger;

        public Builder(String speciesName) {
            this.speciesName = speciesName;
        }

        public Builder andTimeOfDay(String timeOfDay) {
            this.timeOfDay = timeOfDay;
            return this;
        }

        public Builder andNeedsOverworldRain(Boolean needsOverworldRain) {
            this.needsOverworldRain = needsOverworldRain;
            return this;
        }

        public Builder andTurnUpsideDown(Boolean turnUpsideDown) {
            this.turnUpsideDown = turnUpsideDown;
            return this;
        }

        public EvolutionChainLink andTrigger(String trigger) {
            this.trigger = trigger;
            return new EvolutionChainLink(this);
        }
    }

    private final String speciesName;
    private final String timeOfDay;
    private final String trigger;
    private final Boolean needsOverworldRain;
    private final Boolean turnUpsideDown;
    private Item item;
    private Item heldItem;
    private Move knownMove;
    private Type knownMoveType;
    private Location location;
    private PokemonSpecies partySpecies;
    private Type partyType;
    private PokemonSpecies tradeSpecies;
    private Integer gender;
    private Integer minLevel;
    private Integer minHappiness;
    private Integer minBeauty;
    private Integer minAffection;
    private Integer relativePhysicalStats;

    public EvolutionChainLink(Builder builder) {
        speciesName = builder.speciesName;
        timeOfDay = builder.timeOfDay;
        needsOverworldRain = builder.needsOverworldRain;
        turnUpsideDown = builder.turnUpsideDown;
        trigger = builder.trigger;
    }

    public String getSpeciesName() {
        return speciesName;
    }


    public String getTimeOfDay() {
        return timeOfDay;
    }

    public String getTrigger() {
        return trigger;
    }

    public Boolean getNeedsOverworldRain() {
        return needsOverworldRain;
    }

    public Boolean getTurnUpsideDown() {
        return turnUpsideDown;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public boolean hasItem() {
        return item != null;
    }

    public Item getHeldItem() {
        return heldItem;
    }

    public boolean hasHeldItem() {
        return heldItem != null;
    }

    public void setHeldItem(Item heldItem) {
        this.heldItem = heldItem;
    }

    public Move getKnownMove() {
        return knownMove;
    }

    public boolean hasKnownMove() {
        return knownMove != null;
    }

    public void setKnownMove(Move knownMove) {
        this.knownMove = knownMove;
    }

    public Type getKnownMoveType() {
        return knownMoveType;
    }

    public boolean hasKnownMoveType() {
        return knownMoveType != null;
    }

    public void setKnownMoveType(Type knownMoveType) {
        this.knownMoveType = knownMoveType;
    }

    public Location getLocation() {
        return location;
    }

    public boolean hasLocation() {
        return location != null;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public PokemonSpecies getPartySpecies() {
        return partySpecies;
    }

    public boolean hasPartySpecies() {
        return partySpecies != null;
    }

    public void setPartySpecies(PokemonSpecies partySpecies) {
        this.partySpecies = partySpecies;
    }

    public Type getPartyType() {
        return partyType;
    }

    public boolean hasPartyType() {
        return partyType != null;
    }

    public void setPartyType(Type partyType) {
        this.partyType = partyType;
    }

    public PokemonSpecies getTradeSpecies() {
        return tradeSpecies;
    }

    public boolean hasTradeSpecies() {
        return tradeSpecies != null;
    }

    public void setTradeSpecies(PokemonSpecies tradeSpecies) {
        this.tradeSpecies = tradeSpecies;
    }

    public Integer getGender() {
        return gender;
    }

    public boolean hasGender() {
        return gender != null;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getMinLevel() {
        return minLevel;
    }

    public boolean hasMinLevel() {
        return minLevel != null;
    }

    public void setMinLevel(Integer minLevel) {
        this.minLevel = minLevel;
    }

    public Integer getMinHappiness() {
        return minHappiness;
    }

    public boolean hasMinHappiness() {
        return minHappiness != null;
    }

    public void setMinHappiness(Integer minHappiness) {
        this.minHappiness = minHappiness;
    }

    public Integer getMinBeauty() {
        return minBeauty;
    }

    public boolean hasMinBeauty() {
        return minBeauty != null;
    }

    public void setMinBeauty(Integer minBeauty) {
        this.minBeauty = minBeauty;
    }

    public Integer getMinAffection() {
        return minAffection;
    }

    public boolean hasMinAffection() {
        return minAffection != null;
    }

    public void setMinAffection(Integer minAffection) {
        this.minAffection = minAffection;
    }

    public Integer getRelativePhysicalStats() {
        return relativePhysicalStats;
    }

    public boolean hasRelativePhysicalStats() {
        return relativePhysicalStats != null;
    }

    public void setRelativePhysicalStats(Integer relativePhysicalStats) {
        this.relativePhysicalStats = relativePhysicalStats;
    }
}
