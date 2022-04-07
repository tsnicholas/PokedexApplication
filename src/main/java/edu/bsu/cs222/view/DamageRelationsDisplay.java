package edu.bsu.cs222.view;

import edu.bsu.cs222.model.Pokemon;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.List;

public class DamageRelationsDisplay implements MenuDisplay {
    public Parent display(Pokemon pokemon) {
        VBox rows = new VBox();
        rows.getChildren().addAll(
            createDamageRelationRow("Immunities", pokemon.getImmunities()),
            createDamageRelationRow("Resistances", pokemon.getResistances()),
            createDamageRelationRow("Weaknesses", pokemon.getWeaknesses())
        );
        return rows;
    }

    private Text createDamageRelationRow(String damageRelation, List<String> damageRelationList) {
        StringBuilder output = new StringBuilder();
        output.append(damageRelation);
        output.append(": ");
        for(String listValue: damageRelationList) {
            output.append(listValue);
            output.append(" ");
        }
        return convertStringToText(output.toString());
    }

    private Text convertStringToText(String input) {
        Text text = new Text(input);
        text.setFont(Font.font("Times New Roman", 25));
        return text;
    }

    // Without this, the name displayed in the drop-down menu is meaningless
    @Override
    public String toString() {
        return "Damage Relations";
    }
}
