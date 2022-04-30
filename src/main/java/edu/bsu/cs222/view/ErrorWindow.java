package edu.bsu.cs222.view;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class ErrorWindow {
    private final Alert error;

    public ErrorWindow(String errorReason) {
        error = new Alert(Alert.AlertType.ERROR, errorReason);
        Stage stage = (Stage) error.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(
                Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream("Error Icon.jpg"))));
    }

    public void display() {
        error.showAndWait();
    }
}
