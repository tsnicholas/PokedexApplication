package edu.bsu.cs222.model;

public class Ability {
    public static Builder withName(String abilityName) {
        return new Builder(abilityName);
    }

    public static final class Builder {
        private final String abilityName;
        private boolean isHidden;

        public Builder(String abilityName) {
            this.abilityName = abilityName.replace("-", " ");
        }

        public Ability andIsHidden(boolean isHidden) {
            this.isHidden = isHidden;
            return new Ability(this);
        }
    }

    private final String abilityName;
    private final boolean isHidden;

    public Ability(Builder builder) {
        abilityName = builder.abilityName;
        isHidden = builder.isHidden;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public boolean isHidden() {
        return isHidden;
    }
}
