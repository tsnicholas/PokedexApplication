package edu.bsu.cs222.view;

import edu.bsu.cs222.model.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainWindow extends Application {
    private final TextField searchInput = new TextField();
    private final Button searchButton = new Button("Search");
    private final ComboBox<String> gameSelection = new ComboBox<>();
    private final Text type = new Text("Type: ");
    private final Text abilities = new Text("Abilities: ");
    private final Text stats = new Text("Stats: HP  Atk  Def  SpAtk  SpDef  Spd ");
    private final ScrollPane lowerPortion = new ScrollPane();
    private final PokemonProcessor pokemonProcessor = new PokemonProcessor();
    private Pokemon currentPokemon;

    @Override
    public void start(Stage primaryStage) {
        setUpWindowBasics(primaryStage);
        setUpExit(primaryStage);
        primaryStage.show();
    }

    private void setUpWindowBasics(Stage primaryStage) {
        primaryStage.setTitle("Pokedex");
        primaryStage.setScene(new Scene(createMainWindow()));
        primaryStage.getIcons().add(new Image("pokeball.png"));
    }

    private void setUpExit(Stage primaryStage) {
        primaryStage.setOnCloseRequest(X -> {
            primaryStage.close();
            Platform.exit();
            System.exit(0);
        });
    }

    private Parent createMainWindow() {
        VBox mainWindow = new VBox();
        mainWindow.getChildren().addAll(
                createSearchBar(),
                createUpperPortion(),
                createDropDownMenu(),
                lowerPortion
        );

        return mainWindow;
    }

    private Parent createSearchBar() {
        HBox searchBar = new HBox();
        setUpGameSelection();
        setUpButton();
        searchBar.getChildren().addAll(
                searchInput,
                gameSelection,
                searchButton
        );
        return searchBar;
    }

    private Parent createUpperPortion() {
        HBox upperPortion = new HBox();
        upperPortion.getChildren().addAll(
                createPokeFacts()
        );

        return upperPortion;
    }

    private Parent createDropDownMenu() {
        ComboBox<String> dropDownMenu = new ComboBox<>();
        dropDownMenu.getItems().addAll(
                "Moveset"
        );
        dropDownMenu.getSelectionModel().selectFirst();
        return dropDownMenu;
    }

    private void setUpGameSelection() {
        gameSelection.getItems().addAll(
                "yellow"
        );
        gameSelection.getSelectionModel().selectFirst();
    }

    private void setUpButton() {
        searchButton.setOnAction(e -> {
            searchInput.setDisable(true);
            searchButton.setDisable(true);
            search();
            Platform.runLater(() -> {
                searchInput.setDisable(false);
                searchButton.setDisable(false);
            });
        });
    }

    private void search() {
        try {
            currentPokemon = pokemonProcessor.process(searchInput.getText(), gameSelection.getValue());
        }
        catch(RuntimeException doesNotExist) {
            ErrorWindow noExistence = new ErrorWindow("This Pokemon doesn't exist");
            noExistence.display();
        }
    }

    private Parent createPokeFacts() {
        VBox pokeFacts = new VBox();
        pokeFacts.getChildren().addAll(
                type,
                abilities,
                stats
        );

        return pokeFacts;
    }
}
