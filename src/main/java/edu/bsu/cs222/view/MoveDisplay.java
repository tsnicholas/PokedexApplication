package edu.bsu.cs222.view;

import edu.bsu.cs222.model.PokedexProcessor;
import edu.bsu.cs222.model.Pokemon;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MoveDisplay implements MenuDisplay {
    private final String[] MOVE_DATA_KEYS = {"Name", "Type", "PP", "Power", "Accuracy"};

    private final PokedexProcessor pokedexProcessor = new PokedexProcessor();
    private final HBox layout = new HBox();
    private final Pokemon currentPokemon;

    public MoveDisplay(Pokemon pokemon) {
        currentPokemon = pokemon;
        createLayout();
    }

    private void createLayout() {
        layout.setSpacing(50);
        layout.setAlignment(Pos.CENTER);
        for (String moveData : MOVE_DATA_KEYS) {
            layout.getChildren().add(createDataText(moveData));
        }
        layout.getChildren().add(getLearnMethods());
    }

    private Parent createDataText(String moveData) {
        VBox moveDataText = new VBox();
        moveDataText.setSpacing(5);
        moveDataText.getChildren().addAll(
                createText(moveData)
//                createText(pokedexProcessor.convertMoveDataToString(currentPokemon, moveData))
        );
        return moveDataText;
    }

    private Parent getLearnMethods() {
        VBox learnMethods = new VBox();
        learnMethods.setSpacing(5);
        learnMethods.getChildren().addAll(
                createText("Obtained By"),
                createText(pokedexProcessor.convertLearnMethodsToString(currentPokemon))
        );
        return learnMethods;
    }

    private Text createText(String name) {
        Text text = new Text(name);
        text.setFont(Font.font("Times New Roman", 18));
        return text;
    }

    public Parent display() {
        return layout;
    }

    // Without this, the names displayed in the drop-down menu are meaningless
    @Override
    public String toString() {
        return "Move Set";
    }
}
