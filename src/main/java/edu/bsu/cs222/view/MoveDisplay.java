package edu.bsu.cs222.view;

import edu.bsu.cs222.model.Move;
import edu.bsu.cs222.model.Pokemon;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

import java.util.List;

public class MoveDisplay extends DisplayCreator implements MenuDisplay {
    private final int NAME_COLUMN_INDEX = 1;
    private final int TYPE_COLUMN_INDEX = 2;
    private final int PP_COLUMN_INDEX = 3;
    private final int POWER_COLUMN_INDEX = 4;
    private final int ACCURACY_COLUMN_INDEX = 5;
    private final int LEARN_METHOD_COLUMN_INDEX = 6;

    private GridPane moveLayout;

    public Parent getInitialDisplay() {
        moveLayout = makeNewGridPane();
        createMoveDataHeaders();
        return moveLayout;
    }

    private GridPane makeNewGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(LARGE_SPACING);
        gridPane.setVgap(SMALL_SPACING);
        return gridPane;
    }

    private void createMoveDataHeaders() {
        moveLayout.add(createText("Name", HEADER_FONT), NAME_COLUMN_INDEX, 1);
        moveLayout.add(createText("Type", HEADER_FONT), TYPE_COLUMN_INDEX, 1);
        moveLayout.add(createText("PP", HEADER_FONT), PP_COLUMN_INDEX, 1);
        moveLayout.add(createText("Power", HEADER_FONT), POWER_COLUMN_INDEX, 1);
        moveLayout.add(createText("Accuracy", HEADER_FONT), ACCURACY_COLUMN_INDEX, 1);
        moveLayout.add(createText("Obtained By", HEADER_FONT), LEARN_METHOD_COLUMN_INDEX, 1);
    }

    public Parent display(Pokemon pokemon) {
        moveLayout = makeNewGridPane();
        createMoveDataHeaders();
        createMoveDataStrings(pokemon.getMoves());
        return wrapAroundScrollPane(moveLayout);
    }

    private void createMoveDataStrings(List<Move> moveList) {
        for (int i = 1; i < moveList.size(); i++) {
            Move move = moveList.get(i);
            moveLayout.add(createText(move.getName(), DEFAULT_FONT), NAME_COLUMN_INDEX, i + 1);
            moveLayout.add(createImage(move.getType() + ".png"), TYPE_COLUMN_INDEX, i + 1);
            moveLayout.add(createText(move.getPP(), DEFAULT_FONT), PP_COLUMN_INDEX, i + 1);
            moveLayout.add(createText(move.getPower(), DEFAULT_FONT), POWER_COLUMN_INDEX, i + 1);
            moveLayout.add(createText(move.getAccuracy(), DEFAULT_FONT), ACCURACY_COLUMN_INDEX, i + 1);
            moveLayout.add(createText(obtainLearnMethods(move.getLearnMethods()), DEFAULT_FONT),
                    LEARN_METHOD_COLUMN_INDEX, i + 1);
        }
    }

    private String obtainLearnMethods(List<String> learnMethods) {
        StringBuilder output = new StringBuilder();
        for (String learnMethod : learnMethods) {
            output.append(learnMethod);
            output.append(", ");
        }
        return removeLastComma(output.toString());
    }

    private String removeLastComma(String output) {
        if (output.length() == 0) {
            return output;
        }
        return output.substring(0, output.length() - 2);
    }

    public String toString() {
        return "Move Set";
    }
}
