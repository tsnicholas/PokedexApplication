package edu.bsu.cs222.view;

import edu.bsu.cs222.model.Move;
import edu.bsu.cs222.model.Pokemon;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.List;

public class MoveDisplay implements MenuDisplay {
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
        moveLayout.addColumn(NAME_COLUMN_INDEX, createHeaderText("Name"));
        moveLayout.addColumn(TYPE_COLUMN_INDEX, createHeaderText("Type"));
        moveLayout.addColumn(PP_COLUMN_INDEX, createHeaderText("PP"));
        moveLayout.addColumn(POWER_COLUMN_INDEX, createHeaderText("Power"));
        moveLayout.addColumn(ACCURACY_COLUMN_INDEX, createHeaderText("Accuracy"));
        moveLayout.addColumn(LEARN_METHOD_COLUMN_INDEX, createHeaderText("Obtained By"));
    }

    private Text createHeaderText(String header) {
        Text text = new Text(header);
        text.setFont(HEADER_TEXT);
        return text;
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
            moveLayout.add(createText(move.getName()), NAME_COLUMN_INDEX, i + 1);
            moveLayout.add(createText(move.getType()), TYPE_COLUMN_INDEX, i + 1);
            moveLayout.add(createText(move.getPP()), PP_COLUMN_INDEX, i + 1);
            moveLayout.add(createText(move.getPower()), POWER_COLUMN_INDEX, i + 1);
            moveLayout.add(createText(move.getAccuracy()), ACCURACY_COLUMN_INDEX, i + 1);
            moveLayout.add(createText(obtainLearnMethods(move.getLearnMethods())), LEARN_METHOD_COLUMN_INDEX, i + 1);
        }
    }

    private String obtainLearnMethods(List<String> learnMethods) {
        StringBuilder output = new StringBuilder();
        for (String learnMethod : learnMethods) {
            output.append(learnMethod);
            output.append(", ");
        }
        return output.substring(0, output.length() - 2);
    }

    private Text createText(String name) {
        String outputString = nullCheck(name);
        Text text = new Text(outputString);
        text.setFont(SIMPLE_TEXT);
        return text;
    }

    private String nullCheck(String data) {
        if (data == null) {
            return "--";
        }
        return data;
    }

    public String toString() {
        return "Move Set";
    }
}
