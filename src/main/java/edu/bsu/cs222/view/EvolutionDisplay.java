package edu.bsu.cs222.view;

import edu.bsu.cs222.model.EvolutionChain;
import edu.bsu.cs222.model.EvolutionDetailsValues;
import edu.bsu.cs222.model.Pokemon;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

import java.util.LinkedHashMap;


public class EvolutionDisplay extends DisplayCreator implements MenuDisplay{
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
        EvolutionChain evolutionChain = pokemon.getEvolutionChain();
        createEvolutionRows(evolutionChain);
        return wrapAroundScrollPane(evolutionRows);
    }

    private void createEvolutionRows(EvolutionChain evolutionChain) {
        addBasePokemon(evolutionChain);
        for (int i = 1; i < evolutionChain.getSpeciesNames().size(); i++) {
            evolutionRows.add(createText(evolutionChain.getSpeciesNames().get(i), DEFAULT_FONT), POKEMON_COLUMN_INDEX, i + 1);
            evolutionRows.add(createText(convertEvolutionDetails(evolutionChain.getEvolutionDetails().get(i - 1)),
                    DEFAULT_FONT), EVOLUTION_METHOD_COLUMN_INDEX, i + 1);
        }
    }

    private void addBasePokemon(EvolutionChain evolutionChain) {
        evolutionRows.add(createText(evolutionChain.getSpeciesNames().get(0), DEFAULT_FONT), POKEMON_COLUMN_INDEX, 1);
        evolutionRows.add(createText("base pokemon", DEFAULT_FONT), EVOLUTION_METHOD_COLUMN_INDEX, 1);
    }

    private String convertEvolutionDetails(LinkedHashMap<String, EvolutionDetailsValues> evolutionDetails) {
        StringBuilder evolutionSentence = new StringBuilder();
        for (String key : evolutionDetails.keySet()) {
            String value = evolutionDetails.get(key).toString();
            evolutionSentence.append(convertKeyToEnglish(key)).append(" ").append(convertValueToEnglish(value)).append(" ");
        }
        return evolutionSentence.toString();
    }

    // We want to take the keys given by the API and use them to create a readable sentence
    private String convertKeyToEnglish(String key) {
        return key.replace("trigger", "")
                .replace("gender", "as a")
                .replace("held_item", "and holding")
                .replace("item", "")
                .replace("location", "at")
                .replace("min_affection", "at affection level")
                .replace("min_beauty", "at beauty level")
                .replace("min_happiness", "at happiness level")
                .replace("min_level", "to")
                .replace("time_of_day", "")
                .replace("needs_overworld_rain", "")
                .replace("turn_upside_down", "")
                .replace("_", " ");
    }

    // Same thing with the values
    private String convertValueToEnglish(String value) {
        return value.replace("use-item", "use a")
                .replace("night", "during night time")
                .replace("day", "during day time")
                .replace("false", "");
    }

    public String toString() {
        return "Evolutions";
    }

}
