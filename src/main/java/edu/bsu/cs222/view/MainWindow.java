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
    private final ComboBox<String> gameSelection = new ComboBox<>();
    private final Button searchButton = new Button("Search");
    private final Text type = new Text("Type: ");
    private final Text stats = new Text("Stats:\nHP 40  Atk 45  Def 80\nSp 90   Spd 120");
    private final ImageView pokemonImage = new ImageView();
    private final ComboBox<String> dropDownMenu = new ComboBox<>();
    private final ScrollPane lowerPortion = new ScrollPane();
    private final PokemonProcessor pokemonProcessor = new PokemonProcessor();
    private Pokemon currentPokemon;

    @Override
    public void start(Stage primaryStage) {
        setUpWindowBasics(primaryStage);
        setUpEventTriggers();
        setUpSizesAndFonts();
        // startUpDisplay(true);
        primaryStage.show();
    }

    private void setUpWindowBasics(Stage primaryStage) {
        primaryStage.setTitle("Pokedex");
        primaryStage.getIcons().add(new Image("pokeball.png"));
        primaryStage.setScene(new Scene(createMainWindow()));
        primaryStage.setHeight(700);
        primaryStage.setWidth(800);
        primaryStage.setOnCloseRequest(X -> Platform.exit());
    }

    private void setUpEventTriggers() {
        searchInput.setOnKeyPressed(keyPressed -> {
            if(keyPressed.getCode() == KeyCode.ENTER) {
                beginProcessing();
            }
        });
        searchButton.setOnAction(clicked -> beginProcessing());
    }

    private void setUpSizesAndFonts() {
        lowerPortion.setPrefViewportHeight(300);
        lowerPortion.setPrefViewportWidth(700);
        searchInput.setPrefWidth(400);
        type.setFont(Font.font("Verdana", 25));
        stats.setFont(Font.font("Verdana", 25));
    }

    //      Will be added later
//    private void startUpDisplay(boolean status) {
//        pokemonImage.setVisible(!status);
//        type.setVisible(!status);
//        stats.setVisible(!status);
//        dropDownMenu.setVisible(!status);
//        lowerPortion.setVisible(!status);
//    }

    private Parent createMainWindow() {
        VBox mainWindow = new VBox();
        mainWindow.setAlignment(Pos.CENTER);
        mainWindow.setSpacing(5);
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
        upperPortion.setAlignment(Pos.CENTER);
        upperPortion.setSpacing(20);
        upperPortion.getChildren().addAll(
                createImageDisplay(),
                createPokeFacts()
        );

        return upperPortion;
    }

    private Parent createDropDownMenu() {
        dropDownMenu.setPrefWidth(700);
        dropDownMenu.getItems().addAll(
                "Move Set"
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

    private void beginProcessing() {
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
