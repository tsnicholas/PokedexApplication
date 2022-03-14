package edu.bsu.cs222.view;

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
    private final Text name = new Text("Name: Missingno");
    private final Text type = new Text("Type: Normal / Bird");
    private final Text abilities = new Text("Abilities: ???");
    private final Text stats = new Text("Stats: HP 33 Atk 136 Def 0 SpAtk 6 SpDef 6 Spd 29");

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
            Platform.exit();
            System.exit(0);
        });
    }

    private Parent createMainWindow() {
        VBox mainWindow = new VBox();
        mainWindow.getChildren().addAll(
                createSearchBar(),
                createUpperPortion(),
                createDropDownMenu()
        );

        return mainWindow;
    }

    private Parent createSearchBar() {
        HBox searchBar = new HBox();
        searchBar.getChildren().addAll(
                searchInput,
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

    private Parent createPokeFacts() {
        VBox pokeFacts = new VBox();
        pokeFacts.getChildren().addAll(
                name,
                type,
                abilities,
                stats
        );

        return pokeFacts;
    }

    private Parent createDropDownMenu() {
        ComboBox<String> dropDownMenu = new ComboBox<>();
        dropDownMenu.getItems().addAll(
                "Moveset"
        );
        dropDownMenu.getSelectionModel().selectFirst();
        setUpDropDownMenu(dropDownMenu);
        return dropDownMenu;
    }

    private void setUpDropDownMenu(ComboBox<String> dropDownMenu) {
        dropDownMenu.setOnAction(e -> {

        });
    }
}
