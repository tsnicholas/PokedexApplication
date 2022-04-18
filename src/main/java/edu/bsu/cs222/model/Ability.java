package edu.bsu.cs222.model;

public class Ability {
    public static Builder withName(String abilityName) {
        return new Builder(abilityName);
    }

    public static final class Builder {
        private final String abilityName;
        private boolean isHidden;
        private String effect;

        public Builder(String abilityName) {
            this.abilityName = abilityName;
        }

        public Builder andEffect(String effect) {
            this.effect = effect;
            return this;
        }

        public Ability andIsHidden(boolean isHidden) {
            this.isHidden = isHidden;
            return new Ability(this);
        }
    }

    private final String abilityName;
    private final String effect;
    private final boolean isHidden;

    public Ability(Builder builder) {
        abilityName = builder.abilityName;
        effect = builder.effect;
        isHidden = builder.isHidden;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public String getEffect() {
        return effect;
    }

    public boolean isHidden() {
        return isHidden;
    }
}
