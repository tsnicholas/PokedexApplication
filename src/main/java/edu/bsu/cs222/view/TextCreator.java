package edu.bsu.cs222.view;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class TextCreator {
    public Text createText(String input, Font font) {
        String outputString = processStringIntoFormat(input);
        Text text = new Text(outputString);
        text.setFont(font);
        return text;
    }

    private String processStringIntoFormat(String input) {
        if (input == null) {
            return "--";
        }
        return input.replace("-", " ");
    }
}