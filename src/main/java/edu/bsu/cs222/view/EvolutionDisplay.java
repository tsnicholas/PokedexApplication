package edu.bsu.cs222.view;

import edu.bsu.cs222.model.Evolution;
import edu.bsu.cs222.model.Pokemon;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.List;

public class EvolutionDisplay extends DisplayCreator implements MenuDisplay {
    private static final int POKEMON_COLUMN_INDEX = 1;
    private static final int EVOLUTION_METHOD_COLUMN_INDEX = 2;

    private GridPane evolutionRows;

    public Parent getInitialDisplay() {
        evolutionRows = createNewGridPane();
        createHeaders();
        return evolutionRows;
    }

    private GridPane createNewGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(SMALL_SPACING);
        gridPane.setHgap(LARGE_SPACING);
        gridPane.setPadding(DEFAULT_INSETS);
        return gridPane;
    }

    private void createHeaders() {
        evolutionRows.addColumn(POKEMON_COLUMN_INDEX, createText("Pokemon", HEADER_FONT));
        evolutionRows.addColumn(EVOLUTION_METHOD_COLUMN_INDEX, createText("Evolution Method", HEADER_FONT));
    }

    public Parent display(Pokemon pokemon) {
        evolutionRows = createNewGridPane();
        createHeaders();
        createEvolutionMethods(pokemon.getEvolutionChain());
        return wrapAroundScrollPane(evolutionRows);
    }

    private void createEvolutionMethods(List<Evolution> evolutions) {
        for (int i = 0; i < evolutions.size(); i++) {
            Evolution evolution = evolutions.get(i);
            evolutionRows.add(createText(evolution.getSpeciesName(), DEFAULT_FONT), POKEMON_COLUMN_INDEX, i + 1);
            evolutionRows.add(createEvolutionChart(evolution), EVOLUTION_METHOD_COLUMN_INDEX, i + 1);
        }
    }

    private Text createEvolutionChart(Evolution evolution) {
        String evolutionMethodChart = "Trigger: " + evolution.getEvolutionTrigger() + "\n" +
                "Minimum Level: " + evolution.getMinimumLevel() + "\n" +
                "Used Item: " + evolution.getUsedItem() + "\n" +
                "Held Item: " + evolution.getHeldItem() + "\n" +
                "Time of Day: " + evolution.getTimeOfDay() + "\n" +
                "Location: " + evolution.getLocation() + "\n" +
                "Gender: " + evolution.getGender() + "\n" +
                "Known Move: " + evolution.getKnownMove() + "\n" +
                "Known Move Type: " + evolution.getKnownMoveType() + "\n" +
                getOutlierCases(evolution) +
                getConditionalCases(evolution);
        return createText(evolutionMethodChart, DEFAULT_FONT);
    }

    // More cases will be added later
    private String getOutlierCases(Evolution evolution) {
        StringBuilder output = new StringBuilder();
        if(evolution.getEvolutionTrigger().equals("shed")) {
            output.append("This pokemon will be included in your party automatically when the base pokemon has evolved.").append("\n");
        }
        return output.toString();
    }

    private String getConditionalCases(Evolution evolution) {
        StringBuilder output = new StringBuilder();
        if (evolution.isHappyEvolution()) {
            output.append("Requires High Happiness!").append("\n");
        }
        if (evolution.isAffectionEvolution()) {
            output.append("Requires High Affection!").append("\n");
        }
        if (evolution.isBeautyEvolution()) {
            output.append("Requires High Beauty!").append("\n");
        }
        if (evolution.needsOverworldRain()) {
            output.append("Requires Overworld Rain!").append("\n");
        }
        if (evolution.needsToBeUpsideDown()) {
            output.append("Requires the Switch to be held upside in handheld mode!").append("\n");
        }
        return output.toString();
    }

    public String toString() {
        return "Evolutions";
    }

}
