package edu.bsu.cs222.view;

import edu.bsu.cs222.model.Pokemon;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.List;

public class DamageRelationsDisplay implements MenuDisplay {
    private static final String FONT_NAME = "Times New Roman";
    private static final int FONT_SIZE = 25;

    public Parent display(Pokemon pokemon) {
        VBox rows = new VBox();
        rows.getChildren().addAll(
            createDamageRelationRow("Immunities", pokemon.getImmunities()),
            createDamageRelationRow("Resistances", pokemon.getResistances()),
            createDamageRelationRow("Weaknesses", pokemon.getWeaknesses())
        );
        return rows;
    }

    private Parent createDamageRelationRow(String damageRelation, List<String> damageRelationList) {
        HBox damageRelationRow = new HBox();
        damageRelationRow.setSpacing(5);
        damageRelationRow.getChildren().addAll(
                createDamageRelationName(damageRelation),
                createDamageRelationData(damageRelationList)
        );
        return damageRelationRow;
    }

    private Text createDamageRelationName(String damageRelation) {
        Text text = new Text(damageRelation + ": ");
        text.setFont(Font.font(FONT_NAME, FontWeight.BOLD, FONT_SIZE));
        return text;
    }

    private Text createDamageRelationData(List<String> damageRelationList) {
        StringBuilder output = new StringBuilder();
        for(String listValue: damageRelationList) {
            output.append(listValue);
            output.append(" ");
        }
        return createText(output.toString());
    }

    private Text createText(String type) {
        Text text = new Text(type);
        text.setFont(Font.font(FONT_NAME, FONT_SIZE));
        return text;
    }

    // Without this, the name displayed in the drop-down menu is meaningless
    @Override
    public String toString() {
        return "Damage Relations";
    }
}
