package edu.bsu.cs222.view;

import edu.bsu.cs222.model.Version;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.util.List;

public class SearchBar {
    private final TextField searchInput = new TextField();
    private final ChoiceBox<Version> gameSelection = new ChoiceBox<>();
    private final Button searchButton = new Button("Search");

    public SearchBar() {
        searchInput.setPrefWidth(400);
    }

    public void setUpGameSelection(List<Version> versions) {
        for (Version version : versions) {
            gameSelection.getItems().add(version);
        }
        gameSelection.getSelectionModel().selectFirst();
    }

    public TextField getTextField() {
        return searchInput;
    }

    public Button getButton() {
        return searchButton;
    }

    public Parent getDisplay() {
        HBox searchBar = new HBox();
        searchBar.setAlignment(Pos.CENTER);
        searchBar.getChildren().addAll(
                searchInput,
                gameSelection,
                searchButton
        );
        return searchBar;
    }

    public Version getSelectedVersion() {
        return gameSelection.getSelectionModel().getSelectedItem();
    }

    public String getInput() {
        return searchInput.getText();
    }

    public void setDisable(boolean isProcessingSearch) {
        searchInput.setDisable(isProcessingSearch);
        gameSelection.setDisable(isProcessingSearch);
        searchButton.setDisable(isProcessingSearch);
    }
}
