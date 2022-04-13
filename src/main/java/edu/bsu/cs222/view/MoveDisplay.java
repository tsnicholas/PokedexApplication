package edu.bsu.cs222.view;

import edu.bsu.cs222.model.Move;
import edu.bsu.cs222.model.Pokemon;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.List;

public class MoveDisplay implements MenuDisplay {
    private final String FONT_NAME = "Times New Roman";
    private final int FONT_SIZE = 18;
    private final int NAME_COLUMN_INDEX = 0;
    private final int TYPE_COLUMN_INDEX = 1;
    private final int PP_COLUMN_INDEX = 2;
    private final int POWER_COLUMN_INDEX = 3;
    private final int ACCURACY_COLUMN_INDEX = 4;
    private final int LEARN_METHOD_COLUMN_INDEX = 5;

    private GridPane layout;

    public Parent display(Pokemon pokemon) {
        layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(40);
        layout.setVgap(5);
        createMoveDataHeaders();
        createMoveDataStrings(pokemon.getMoves());
        return layout;
    }

    private void createMoveDataHeaders() {
        layout.addColumn(NAME_COLUMN_INDEX, createHeaderText("Name"));
        layout.addColumn(TYPE_COLUMN_INDEX, createHeaderText("Type"));
        layout.addColumn(PP_COLUMN_INDEX, createHeaderText("PP"));
        layout.addColumn(POWER_COLUMN_INDEX, createHeaderText("Power"));
        layout.addColumn(ACCURACY_COLUMN_INDEX, createHeaderText("Accuracy"));
        layout.addColumn(LEARN_METHOD_COLUMN_INDEX, createHeaderText("Obtained By"));
    }

    private Text createHeaderText(String header) {
        Text text = new Text(header);
        text.setFont(Font.font(FONT_NAME, FontWeight.BOLD, FONT_SIZE));
        return text;
    }

    private void createMoveDataStrings(List<Move> moveList) {
        for(int i = 0; i < moveList.size(); i++) {
            Move move = moveList.get(i);
            layout.add(createText(nullCheck(move.getName())), NAME_COLUMN_INDEX, i + 1);
            layout.add(createText(nullCheck(move.getType())), TYPE_COLUMN_INDEX, i + 1);
            layout.add(createText(nullCheck(move.getPP())), PP_COLUMN_INDEX, i + 1);
            layout.add(createText(nullCheck(move.getPower())), POWER_COLUMN_INDEX, i + 1);
            layout.add(createText(nullCheck(move.getAccuracy())), ACCURACY_COLUMN_INDEX, i + 1);
            layout.add(createText(obtainLearnMethods(move.getLearnMethods())), LEARN_METHOD_COLUMN_INDEX, i + 1);
        }
    }

    private String nullCheck(String data) {
        if(data == null) {
            return "--";
        }
        return data;
    }

    private String obtainLearnMethods(List<String> learnMethods) {
        StringBuilder output = new StringBuilder();
        for(String learnMethod: learnMethods) {
            output.append(learnMethod);
            output.append(", ");
        }
        return output.substring(0, output.length() - 2);
    }

    private Text createText(String name) {
        Text text = new Text(name);
        text.setFont(Font.font(FONT_NAME, FONT_SIZE));
        return text;
    }

    // Without this, the name displayed in the drop-down menu is meaningless
    @Override
    public String toString() {
        return "Move Set";
    }
}
