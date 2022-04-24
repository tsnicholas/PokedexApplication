package edu.bsu.cs222.view;

import edu.bsu.cs222.model.Move;
import edu.bsu.cs222.model.Pokemon;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

import java.util.List;

public class MoveDisplay extends TextCreator implements MenuDisplay {
    private final int NAME_COLUMN_INDEX = 1;
    private final int TYPE_COLUMN_INDEX = 2;
    private final int PP_COLUMN_INDEX = 3;
    private final int POWER_COLUMN_INDEX = 4;
    private final int ACCURACY_COLUMN_INDEX = 5;
    private final int LEARN_METHOD_COLUMN_INDEX = 6;

    private GridPane moveLayout;

    public Parent getInitialDisplay() {
        moveLayout = new GridPane();
        moveLayout.setHgap(LARGE_SPACING);
        moveLayout.setVgap(SMALL_SPACING);
        createMoveDataHeaders();
        return moveLayout;
    }

    private void createMoveDataHeaders() {
        moveLayout.addColumn(NAME_COLUMN_INDEX, createText("Name", HEADER_FONT));
        moveLayout.addColumn(TYPE_COLUMN_INDEX, createText("Type", HEADER_FONT));
        moveLayout.addColumn(PP_COLUMN_INDEX, createText("PP", HEADER_FONT));
        moveLayout.addColumn(POWER_COLUMN_INDEX, createText("Power", HEADER_FONT));
        moveLayout.addColumn(ACCURACY_COLUMN_INDEX, createText("Accuracy", HEADER_FONT));
        moveLayout.addColumn(LEARN_METHOD_COLUMN_INDEX, createText("Obtained By", HEADER_FONT));
    }

    public Parent display(Pokemon pokemon) {
        moveLayout = new GridPane();
        moveLayout.setHgap(LARGE_SPACING);
        moveLayout.setVgap(SMALL_SPACING);
        createMoveDataHeaders();
        createMoveDataStrings(pokemon.getMoves());
        return wrapAroundScrollPane();
    }

    private Parent wrapAroundScrollPane() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(moveLayout);
        return scrollPane;
    }

    private void createMoveDataStrings(List<Move> moveList) {
        for (int i = 0; i < moveList.size(); i++) {
            Move move = moveList.get(i);
            moveLayout.add(createText(move.getName(), DEFAULT_FONT), NAME_COLUMN_INDEX, i + 1);
            moveLayout.add(createText(move.getType(), DEFAULT_FONT), TYPE_COLUMN_INDEX, i + 1);
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
