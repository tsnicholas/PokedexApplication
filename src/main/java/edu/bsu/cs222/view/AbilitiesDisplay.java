package edu.bsu.cs222.view;

import edu.bsu.cs222.model.Ability;
import edu.bsu.cs222.model.Pokemon;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class AbilitiesDisplay implements MenuDisplay {

    public Parent getInitialDisplay() {
        VBox abilitiesRows = new VBox(SMALL_SPACING);
        abilitiesRows.getChildren().addAll(
                createHeaderText("Abilities"),
                createHeaderText("Hidden Abilities")
        );
        return abilitiesRows;
    }

    public Parent display(Pokemon pokemon) {
        ScrollPane scrollPane = new ScrollPane();
        VBox abilitiesRows = new VBox(SMALL_SPACING);
        abilitiesRows.getChildren().addAll(
                createHeaderText("Abilities"),
                convertAbilitiesIntoText(pokemon.getAbilities()),
                createHeaderText("Hidden Abilities"),
                convertAbilitiesIntoText(pokemon.getHiddenAbilities())
        );
        scrollPane.setContent(abilitiesRows);
        return scrollPane;
    }

    private Text createHeaderText(String name) {
        Text text = new Text(name);
        text.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 20));
        return text;
    }

    private Parent convertAbilitiesIntoText(List<Ability> abilities) {
        VBox abilityText = new VBox();
        for(Ability ability: abilities) {
            abilityText.getChildren().addAll(
                    createNameText(ability.getAbilityName()),
                    createEffectText(ability.getEffect())
            );
        }
        return abilityText;
    }

    private Text createNameText(String abilityName) {
        String editedName = abilityName.replace("-", " ");
        Text text = new Text(editedName);
        text.setFont(HEADER_TEXT);
        return text;
    }

    private Text createEffectText(String effect) {
        String properEffectString = encodeStringInUTF8(effect);
        Text text = new Text(properEffectString);
        text.setFont(SIMPLE_TEXT);
        text.setWrappingWidth(750);
        return text;
    }

    private String encodeStringInUTF8(String effect) {
        // Some of the effect's characters won't show up correctly unless we re-encode into the UTF_8 charset
        // For example: Pokémon will show up as PokÃ©mon
        byte[] stringBytes = effect.getBytes();
        return new String(stringBytes, StandardCharsets.UTF_8);
    }

    public String toString() {
        return "Abilities";
    }
}
