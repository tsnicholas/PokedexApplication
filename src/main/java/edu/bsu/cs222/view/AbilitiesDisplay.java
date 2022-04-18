package edu.bsu.cs222.view;

import edu.bsu.cs222.model.Ability;
import edu.bsu.cs222.model.Pokemon;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.List;

public class AbilitiesDisplay implements MenuDisplay {
    public Parent display(Pokemon pokemon) {
        VBox abilitiesRows = new VBox();
        abilitiesRows.getChildren().addAll(
                createAbilityRow(pokemon.getAbilities()),
                createHiddenAbilityRow(pokemon.getAbilities())
        );
        return abilitiesRows;
    }

    private Text createAbilityRow(List<Ability> abilities) {
        String abilityRow = convertAbilitiesToString(abilities);
        return createAbilityText(abilityRow);
    }

    private String convertAbilitiesToString(List<Ability> abilities) {
        StringBuilder output = new StringBuilder();
        for(Ability ability: abilities) {
            if(!ability.isHidden()) {
                output.append(ability.getAbilityName().replace("-", " "));
                output.append(", ");
            }
        }
        return getRidOfEndingComma(output.toString());
    }

    private Text createAbilityText(String name) {
        Text text = new Text("Abilities: " + name);
        text.setFont(Font.font("Times New Roman", 18));
        return text;
    }

    private Text createHiddenAbilityRow(List<Ability> abilities) {
        String abilityRow = convertHiddenAbilitiesToString(abilities);
        return createHiddenAbilityText(abilityRow);
    }

    private String convertHiddenAbilitiesToString(List<Ability> abilities) {
        StringBuilder output = new StringBuilder();
        for(Ability ability: abilities) {
            if(ability.isHidden()) {
                output.append(ability.getAbilityName().replace("-", " "));
                output.append(", ");
            }
        }
        return getRidOfEndingComma(output.toString());
    }

    private Text createHiddenAbilityText(String name) {
        Text text = new Text("Hidden Abilities: " + name);
        text.setFont(Font.font("Times New Roman", 18));
        return text;
    }

    private String getRidOfEndingComma(String output) {
        if (output.length() == 0) {
            return output;
        }
        return output.substring(0, output.length() - 2);
    }
}
