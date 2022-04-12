package edu.bsu.cs222.model;

import java.util.List;

public class Move {
    public static Builder withName(String name) {
        return new Builder(name);
    }

    public static final class Builder {
        private final String name;
        private String type;
        private String pp;
        private String power;
        private String accuracy;
        private List<String> learnMethods;

        public Builder(String name) {
            this.name = name;
        }

        public Builder andType(String type) {
            this.type = type;
            return this;
        }

        public Builder andPP(String PP) {
            this.pp = PP;
            return this;
        }

        public Builder andPower(String power) {
            this.power = power;
            return this;
        }

        public Builder andAccuracy(String accuracy) {
            this.accuracy = accuracy;
            return this;
        }

        public Move andLearnMethods(List<String> learnMethods) {
            this.learnMethods = learnMethods;
            return new Move(this);
        }
    }


    private final String name;
    private final String type;
    private final String PP;
    private final String power;
    private final String accuracy;
    private final List<String> learnMethods;

    public Move(Move.Builder builder) {
        this.name = builder.name;
        this.type = builder.type;
        this.PP = builder.pp;
        this.power = builder.power;
        this.accuracy = builder.accuracy;
        this.learnMethods = builder.learnMethods;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getPP() {
        return PP;
    }

    public String getPower() {
        return power;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public List<String> getLearnMethods() {
        return learnMethods;
    }
}
