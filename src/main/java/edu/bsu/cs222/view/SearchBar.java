package edu.bsu.cs222.view;

import edu.bsu.cs222.model.Generation;
import edu.bsu.cs222.model.Version;
import edu.bsu.cs222.model.VersionGroup;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;

import java.util.List;

public class SearchBar extends MainWindow {
    private final TextField searchInput = new TextField();
    private final ChoiceBox<Version> gameSelection = new ChoiceBox<>();
    private final Button searchButton = new Button("Search");

    public SearchBar() {
        searchInput.setPrefWidth(400);
        setUpEventTriggers();
    }

    private void setUpEventTriggers() {
        searchInput.setOnKeyPressed(keyPressed -> {
            if (keyPressed.getCode() == KeyCode.ENTER) {
                beginProcessing();
            }
        });
        searchButton.setOnAction(clicked -> beginProcessing());
    }

    public void setUpGameSelection(List<Generation> generations) {
        for(Generation generation: generations) {
            getVersions(generation.getVersionGroups());
        }
        gameSelection.getSelectionModel().selectFirst();
    }

    private void getVersions(List<VersionGroup> versionGroups) {
        for(VersionGroup versionGroup: versionGroups) {
            gameSelection.getItems().addAll(versionGroup.getVersions());
        }
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
