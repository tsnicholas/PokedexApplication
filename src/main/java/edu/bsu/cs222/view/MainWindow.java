package edu.bsu.cs222.view;

import edu.bsu.cs222.model.Pokemon;
import edu.bsu.cs222.model.PokedexProcessor;
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
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainWindow extends Application {
    private final Text instruction = new Text("Enter a name of a Pokemon");
    private final TextField searchInput = new TextField();
    private final ChoiceBox<String> gameSelection = new ChoiceBox<>();
    private final Button searchButton = new Button("Search");
    private final Text types = new Text();
    private final Text stats = new Text();
    private final ImageView pokemonImage = new ImageView();
    private final ChoiceBox<String> dropDownMenu = new ChoiceBox<>();
    private final ScrollPane lowerPortion = new ScrollPane();
    private final Text moveList = new Text();
    private final Text damageRelations = new Text();
    private final PokedexProcessor pokemonProcessor = new PokedexProcessor();
    private Pokemon currentPokemon;

    @Override
    public void start(Stage primaryStage) {
        setUpWindowBasics(primaryStage);
        setUpEventTriggers();
        setUpSizesAndFonts();
        startUpDisplay(true);
        primaryStage.show();
    }

    private void setUpWindowBasics(Stage primaryStage) {
        primaryStage.setTitle("Pokedex");
        primaryStage.getIcons().add(new Image("pokeball.png"));
        primaryStage.setScene(new Scene(createMainWindow()));
        primaryStage.setHeight(700);
        primaryStage.setWidth(800);
        primaryStage.setOnCloseRequest(X -> {
            Platform.exit();
            System.exit(0);
        });
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
        pokemonImage.setFitHeight(300);
        pokemonImage.setFitWidth(300);
        instruction.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        types.setFont(Font.font("Verdana", 25));
        stats.setFont(Font.font("Verdana", 25));
        moveList.setFont(Font.font("Times New Roman", 18));
        damageRelations.setFont(Font.font("Times New Roman", 18));
    }

    private void startUpDisplay(boolean status) {
        pokemonImage.setVisible(!status);
        types.setVisible(!status);
        stats.setVisible(!status);
        dropDownMenu.setVisible(!status);
        lowerPortion.setVisible(!status);
    }

    private Parent createMainWindow() {
        VBox mainWindow = new VBox();
        mainWindow.setAlignment(Pos.CENTER);
        mainWindow.setSpacing(5);
        mainWindow.getChildren().addAll(
                instruction,
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
                pokemonImage,
                createPokeFacts()
        );

        return upperPortion;
    }

    private Parent createDropDownMenu() {
        dropDownMenu.setPrefWidth(700);
        dropDownMenu.getItems().addAll(
                "Move Set",
                "Damage Relations"
        );
        dropDownMenu.getSelectionModel().selectFirst();
        setUpMenuSwitcher();
        return dropDownMenu;
    }

    private void setUpGameSelection() {
        gameSelection.getItems().addAll(
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
            currentPokemon = pokemonProcessor.process(searchInput.getText().toLowerCase(), gameSelection.getValue());
            update();
            startUpDisplay(false);
        }
        catch(Exception doesNotExist) {
            ErrorWindow noExistence = new ErrorWindow(searchInput.getText() + " doesn't exist in Pokemon " + gameSelection.getValue());
            noExistence.display();
        }
    }

    private void update() {
        types.setText(pokemonProcessor.typesToString(currentPokemon));
        stats.setText(pokemonProcessor.statsToString(currentPokemon));
        moveList.setText(pokemonProcessor.movesToString(currentPokemon));
        damageRelations.setText(pokemonProcessor.damageRelationsToString(currentPokemon));
        pokemonImage.setImage(new Image(currentPokemon.getImageURL()));
        setUpLowerContent(dropDownMenu.getSelectionModel().getSelectedItem());
    }

    private Parent createPokeFacts() {
        VBox pokeFacts = new VBox();
        pokeFacts.getChildren().addAll(
                types,
                stats
        );

        return pokeFacts;
    }

    private void setUpMenuSwitcher() {
        dropDownMenu.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue) -> setUpLowerContent(newValue));
    }

    private void setUpLowerContent(String selectedValue) {
        if(currentPokemon != null) {
            if (selectedValue.equals("Move Set")) {
                lowerPortion.setContent(moveList);
            } else if (selectedValue.equals("Damage Relations")) {
                lowerPortion.setContent(damageRelations);
            }
        }
    }
}
