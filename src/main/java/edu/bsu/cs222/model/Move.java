package edu.bsu.cs222.model;

import java.util.List;

public record Move(String name, String type, int PP, int power, int accuracy, List<String> learnMethods) {
    public String toString() {
        StringBuilder output = new StringBuilder();
        for(String learnMethod: learnMethods) {
            output.append(name);
            output.append(" ");
            output.append(type);
            output.append(" ");
            output.append(PP);
            output.append(" ");
            output.append(power);
            output.append(" ");
            output.append(accuracy);
            output.append(" ");
            output.append(learnMethod);
            output.append("\n");
        }

        return output.toString();
    }
}
