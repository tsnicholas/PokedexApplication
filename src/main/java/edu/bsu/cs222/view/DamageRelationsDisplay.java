package edu.bsu.cs222.view;

import edu.bsu.cs222.model.Pokemon;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

public class DamageRelationsDisplay extends TextCreator implements MenuDisplay {
    private final static String IMMUNITIES = "Immunities: ";
    private final static String RESISTANCES = "Resistances: ";
    private final static String WEAKNESSES = "Weaknesses: ";
    private final VBox rows = new VBox(SMALL_SPACING);

    public DamageRelationsDisplay() {
        rows.setPadding(DEFAULT_INSETS);
    }

    public Parent getInitialDisplay() {
        rows.getChildren().addAll(
                createText(IMMUNITIES, HEADER_FONT),
                createText(RESISTANCES, HEADER_FONT),
                createText(WEAKNESSES, HEADER_FONT)
        );
        return rows;
    }

    public Parent display(Pokemon pokemon) {
        rows.getChildren().remove(0, rows.getChildren().size());
        rows.getChildren().addAll(
                createDamageRelationRow(IMMUNITIES, pokemon.getImmunities()),
                createDamageRelationRow(RESISTANCES, pokemon.getResistances()),
                createDamageRelationRow(WEAKNESSES, pokemon.getWeaknesses())
        );
        return rows;
    }

    private Parent createDamageRelationRow(String damageRelation, List<String> damageRelationList) {
        HBox damageRelationRow = new HBox(SMALL_SPACING);
        damageRelationRow.getChildren().addAll(
                createText(damageRelation, HEADER_FONT),
                createDamageRelationData(damageRelationList)
        );
        return damageRelationRow;
    }

    private Text createDamageRelationData(List<String> damageRelationList) {
        StringBuilder output = new StringBuilder();
        for (String listValue : damageRelationList) {
            output.append(listValue);
            output.append(" ");
        }
        return createText(output.toString(), DEFAULT_FONT);
    }

    public String toString() {
        return "Damage Relations";
    }
}
