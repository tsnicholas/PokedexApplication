package edu.bsu.cs222.view;

import edu.bsu.cs222.model.PokedexProcessor;
import edu.bsu.cs222.model.Version;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import org.controlsfx.control.textfield.TextFields;

import java.util.ArrayList;
import java.util.List;

public class SearchBar {
    private final TextField searchInput = new TextField();
    private final ChoiceBox<Version> gameSelection = new ChoiceBox<>();
    private final Button searchButton = new Button("Search");
    private final List<SearchBarListener> listeners = new ArrayList<>();

    public SearchBar(PokedexProcessor pokedexProcessor) {
        TextFields.bindAutoCompletion(searchInput, pokedexProcessor.getNationalPokedex().getPokemonNames());
        searchInput.setPrefWidth(400);
        setUpEventTriggers();
    }

    private void setUpEventTriggers() {
        searchInput.setOnKeyPressed(keyPressed -> {
            if (keyPressed.getCode() == KeyCode.ENTER) {
                fireSearch();
            }
        });
        searchButton.setOnAction(clicked -> fireSearch());
    }

    private void fireSearch() {
        for (SearchBarListener listener : listeners) {
            listener.onSearchRequest();
        }
    }

    public void addListener(SearchBarListener listener) {
        listeners.add(listener);
    }

    public void setUpGameSelection(List<Version> versions) {
        for (Version version : versions) {
            gameSelection.getItems().add(version);
        }
        gameSelection.getSelectionModel().selectFirst();
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
        return searchInput.getText().toLowerCase();
    }

    public void setDisable(boolean isProcessingSearch) {
        searchInput.setDisable(isProcessingSearch);
        gameSelection.setDisable(isProcessingSearch);
        searchButton.setDisable(isProcessingSearch);
    }
}
