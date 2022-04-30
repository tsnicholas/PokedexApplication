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
        return wrapScrollPaneAround(evolutionRows);
    }

    private void createEvolutionMethods(List<Evolution> evolutions) {
        for (int i = 0; i < evolutions.size(); i++) {
            Evolution evolution = evolutions.get(i);
            evolutionRows.add(createText(evolution.getSpeciesName(), DEFAULT_FONT), POKEMON_COLUMN_INDEX, i + 1);
            evolutionRows.add(createEvolutionChart(evolution), EVOLUTION_METHOD_COLUMN_INDEX, i + 1);
        }
    }

    private Text createEvolutionChart(Evolution evolution) {
        StringBuilder evolutionChart = new StringBuilder();
        evolutionChart.append("Trigger: ").append(evolution.getEvolutionTrigger()).append("\n");
        evolutionChart.append("Minimal Level: ").append(evolution.getMinimumLevel()).append("\n");
        if (!evolution.getEvolutionTrigger().equals("base pokemon") && !evolution.getTimeOfDay().equals("")) {
            evolutionChart.append("Time of Day: ").append(evolution.getTimeOfDay()).append("\n");
        }
        evolutionChart.append(getOtherMethods(evolution));
        evolutionChart.append(getConditionalCases(evolution));
        return createText(evolutionChart.toString(), DEFAULT_FONT);
    }

    private String getOtherMethods(Evolution evolution) {
        StringBuilder output = new StringBuilder();
        if(evolution.hasGender()) {
            output.append("Gender: ").append(evolution.getGender()).append("\n");
        }
        if(evolution.hasUsedItem()) {
            output.append("Used item: ").append(evolution.getUsedItem()).append("\n");
        }
        if(evolution.hasHeldItem()) {
            output.append("Held Item: ").append(evolution.getHeldItem()).append("\n");
        }
        if(evolution.hasKnownMove()) {
            output.append("Known Move: ").append(evolution.getKnownMove()).append("\n");
        }
        if(evolution.hasKnownMoveType()) {
            output.append("Known Move Type: ").append(evolution.getKnownMoveType()).append("\n");
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
        return output.toString();
    }

    public String getDisplayName() {
        return "Evolutions";
    }

}
