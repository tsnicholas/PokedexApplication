package edu.bsu.cs222.view;

import edu.bsu.cs222.model.Pokemon;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Map;

public class StatsDisplay implements MenuDisplay {
    public Parent display(Pokemon pokemon) {
        VBox vBox = new VBox();
        vBox.getChildren().add(createText(convertStatsToString(pokemon.getStats())));
        return vBox;
    }

    private String convertStatsToString(Map<String, Integer> statsMap) {
        StringBuilder output = new StringBuilder();
        for (Map.Entry<String, Integer> stat : statsMap.entrySet()) {
            output.append(stat.getKey());
            output.append(" ");
            output.append(stat.getValue());
            output.append("\n");
        }
        return output.toString();
    }

    private Text createText(String string) {
        Text text = new Text(string);
        text.setFont(Font.font("Times New Roman", 25));
        return text;
    }

    @Override
    public String toString() {
        return "Stats";
    }
}
