package edu.bsu.cs222.view;

import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Objects;

public abstract class DisplayCreator {
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


    public ImageView retrieveTypeImage(String imageString) {
        if(imageString == null) {
            imageString = "unknown.png";
        }
        return new ImageView(new Image(
                Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream(imageString)))
        );
    }

    public Parent wrapScrollPaneAround(Parent input) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(input);
        return scrollPane;
    }
}