package edu.bsu.cs222.view;

import edu.bsu.cs222.model.EvolutionChain;
import edu.bsu.cs222.model.EvolutionDetailsValues;
import edu.bsu.cs222.model.Pokemon;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.LinkedHashMap;


public class EvolutionDisplay extends DisplayCreator implements MenuDisplay{
    private final VBox evolutionRows = new VBox(SMALL_SPACING);

    public Parent getInitialDisplay() {
        return evolutionRows;
    }

    public Parent display(Pokemon pokemon) {
        evolutionRows.getChildren().remove(0, evolutionRows.getChildren().size());
        EvolutionChain evolutionChain = pokemon.getEvolutionChain();
        HBox firstEvolutionRow = new HBox(
                SMALL_SPACING,
                createText(evolutionChain.getSpeciesNames().get(0) + " ", DEFAULT_FONT),
                createText("LV 1", DEFAULT_FONT)
        );
        evolutionRows.getChildren().add(firstEvolutionRow);
        createEvolutionRows(evolutionChain);
        return wrapAroundScrollPane(evolutionRows);
    }

    private void createEvolutionRows(EvolutionChain evolutionChain) {
        for (int i = 1; i < evolutionChain.getSpeciesNames().size(); i++) {
            HBox evolutionRow = new HBox(SMALL_SPACING);
            Text speciesName = createText(evolutionChain.getSpeciesNames().get(i), DEFAULT_FONT);
            Text evolutionDetails = createText(convertEvolutionDetails(evolutionChain.getEvolutionDetails().get(i - 1)),
                    DEFAULT_FONT);
            evolutionRow.getChildren().addAll(speciesName, evolutionDetails);
            evolutionRows.getChildren().add(evolutionRow);
        }
    }

    private String convertEvolutionDetails(LinkedHashMap<String, EvolutionDetailsValues> evolutionDetails) {
        StringBuilder evolutionStringBuilder = new StringBuilder();
        for (String key : evolutionDetails.keySet()) {
            String value = evolutionDetails.get(key).toString();
            evolutionStringBuilder.append(convertToEnglish(key)).append(": ").append(value).append("\n");
        }
        return evolutionStringBuilder.toString();
    }

    private String convertToEnglish(String key) {
        return key.replace("min_level", "LV")
                .replace("held_item", "by holding")
                .replace("item", "by using")
                .replace("location", "at")
                .replace("min_affection", "at affection level");
    }

    public String toString() {
        return "EVOLUTIONS";
    }

}
