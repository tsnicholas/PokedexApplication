package edu.bsu.cs222.view;

import edu.bsu.cs222.model.Ability;
import edu.bsu.cs222.model.Pokemon;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class AbilitiesDisplay extends TextCreator implements MenuDisplay {
    private final VBox abilitiesRows = new VBox(SMALL_SPACING);

    public AbilitiesDisplay() {
        abilitiesRows.setPadding(DEFAULT_INSETS);
    }

    public Parent getInitialDisplay() {
        abilitiesRows.getChildren().addAll(
                createText("Abilities", BIG_HEADER_FONT),
                createText("Hidden Abilities", BIG_HEADER_FONT)
        );
        return abilitiesRows;
    }

    public Parent display(Pokemon pokemon) {
        abilitiesRows.getChildren().remove(0, abilitiesRows.getChildren().size());
        abilitiesRows.getChildren().addAll(
                createText("Abilities", BIG_HEADER_FONT),
                convertAbilitiesIntoText(pokemon.getAbilities()),
                createText("Hidden Abilities", BIG_HEADER_FONT),
                convertAbilitiesIntoText(pokemon.getHiddenAbilities())
        );
        return warpAroundScrollPane();
    }

    private Parent convertAbilitiesIntoText(List<Ability> abilities) {
        VBox abilityText = new VBox();
        for (Ability ability : abilities) {
            abilityText.getChildren().addAll(
                    createText(ability.getAbilityName(), HEADER_FONT),
                    createEffectText(ability.getEffect())
            );
        }
        return abilityText;
    }

    private Text createEffectText(String effect) {
        String properEffectString = encodeStringInUTF8(effect);
        Text text = new Text(properEffectString);
        text.setFont(DEFAULT_FONT);
        text.setWrappingWidth(750);
        return text;
    }

    private String encodeStringInUTF8(String effect) {
        // Some of the effect's characters won't show up correctly unless we re-encode into the UTF_8 charset
        // For example: Pokémon will show up as PokÃ©mon
        byte[] stringBytes = effect.getBytes();
        return new String(stringBytes, StandardCharsets.UTF_8);
    }

    private Parent warpAroundScrollPane() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(abilitiesRows);
        return scrollPane;
    }

    public String toString() {
        return "Abilities";
    }
}
