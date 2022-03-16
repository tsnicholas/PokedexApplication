package edu.bsu.cs222.view;

import edu.bsu.cs222.model.Pokemon;
import edu.bsu.cs222.model.PokemonProcessor;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainWindow extends Application {
    private final TextField searchInput = new TextField();
    private final Button searchButton = new Button("Search");
    private final ComboBox<String> gameSelection = new ComboBox<>();
    private final Text type = new Text("Type: ");
    private final Text stats = new Text("Stats:\nHP 40  Atk 45  Def 80\nSp 90   Spd 120");
    private final ScrollPane lowerPortion = new ScrollPane();
    private final PokemonProcessor pokemonProcessor = new PokemonProcessor();
    private Pokemon currentPokemon;

    @Override
    public void start(Stage primaryStage) {
        setUpWindowBasics(primaryStage);
        setUpSizesAndFonts();
        setUpHotKeys();
        primaryStage.show();
    }

    private void setUpWindowBasics(Stage primaryStage) {
        primaryStage.setTitle("Pokedex");
        primaryStage.getIcons().add(new Image("pokeball.png"));
        primaryStage.setScene(new Scene(createMainWindow()));
    }

    private void setUpSizesAndFonts() {
        lowerPortion.setPrefViewportHeight(300);
        lowerPortion.setPrefViewportWidth(700);
        searchInput.setPrefWidth(400);
        type.setFont(Font.font("Verdana", 25));
        stats.setFont(Font.font("Verdana", 25));
    }

    private void setUpHotKeys() {
        searchInput.setOnKeyPressed(keyPressed -> {
            if(keyPressed.getCode() == KeyCode.ENTER) {
                eventTriggered();
            }
        });
        searchButton.setOnAction(clicked -> eventTriggered());
    }

    private Parent createMainWindow() {
        VBox mainWindow = new VBox();
        mainWindow.setAlignment(Pos.CENTER);
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
        searchBar.setAlignment(Pos.CENTER);
        setUpGameSelection();
        searchBar.getChildren().addAll(
                searchInput,
                gameSelection,
                searchButton
        );
        return searchBar;
    }

    private Parent createUpperPortion() {
        HBox upperPortion = new HBox();
        upperPortion.setAlignment(Pos.CENTER_LEFT);
        upperPortion.getChildren().addAll(
                createImageDisplay(),
                createPokeFacts()
        );

        return upperPortion;
    }

    private Parent createDropDownMenu() {
        ComboBox<String> dropDownMenu = new ComboBox<>();
        dropDownMenu.setPrefWidth(700);
        dropDownMenu.getItems().addAll(
                "Moveset"
        );
        dropDownMenu.getSelectionModel().selectFirst();
        return dropDownMenu;
    }

    private void setUpGameSelection() {
        gameSelection.getItems().addAll(
                "red",
                "blue",
                "yellow"
        );
        gameSelection.getSelectionModel().selectFirst();
    }

    private void eventTriggered() {
        searchInput.setDisable(true);
        searchButton.setDisable(true);
        search();
        Platform.runLater(() -> {
            searchInput.setDisable(false);
            searchButton.setDisable(false);
        });
    }

    private void search() {
        try {
            currentPokemon = pokemonProcessor.process(searchInput.getText(), gameSelection.getValue());
        }
        catch(RuntimeException doesNotExist) {
            ErrorWindow noExistence = new ErrorWindow(searchInput.getText() + " doesn't exist in Pokemon " + gameSelection.getValue());
            noExistence.display();
        }
    }

    // The image used will obviously not be in the final version.
    // This is merely for testing, so I know what size it needs to be.
    private ImageView createImageDisplay() {
        ImageView pokemonImage = new ImageView();
        pokemonImage.setImage(new Image("Angry Kitty.jpg"));
        pokemonImage.setFitHeight(300);
        pokemonImage.setFitWidth(300);
        return pokemonImage;
    }

    private Parent createPokeFacts() {
        VBox pokeFacts = new VBox();
        pokeFacts.getChildren().addAll(
                type,
                stats
        );

        return pokeFacts;
    }
}
