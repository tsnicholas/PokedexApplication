package edu.bsu.cs222.view;

import javafx.scene.control.Alert;

public class ErrorWindow {

    private final Alert error;

    public ErrorWindow(String errorReason) {
        error = new Alert(Alert.AlertType.ERROR, errorReason);
    }

    public void display() {
        error.showAndWait();
    }
}
