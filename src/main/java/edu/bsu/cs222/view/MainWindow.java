package edu.bsu.cs222.view;

import edu.bsu.cs222.model.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.UncheckedIOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainWindow extends Application {
    private final int WIDTH_OF_WINDOW = 800;
    private final int HEIGHT_OF_WINDOW = 800;
    private final Pos DEFAULT_POSITION = Pos.CENTER;
    private final Font UPPER_FONT = Font.font("Verdana", 25);
    private final String INSTRUCTION_STRING = "Enter a name of a Pokemon";

    private final Text instruction = new Text(INSTRUCTION_STRING);
    private final SearchBar searchBar = new SearchBar();
    private final Text types = new Text();
    private final Text abilities = new Text();
    private final Text hiddenAbilities = new Text();
    private final Text egg_groups = new Text();
    private final ImageView pokemonImage = new ImageView();
    private final ChoiceBox<MenuDisplay> dropDownMenu = new ChoiceBox<>();
    private final ScrollPane selectedItemDisplay = new ScrollPane();
    private final PokedexProcessor pokedexProcessor = new PokedexProcessor();
    private Pokemon currentPokemon;

    private final ExecutorService executor = Executors.newCachedThreadPool();

    @Override
    public void init() throws Exception {
        super.init();
        collectPokemonNames();
    }

    private void collectPokemonNames() {
        try {
            VersionListGenerator versionsListGenerator = new VersionListGenerator();
            List<Version> versions = versionsListGenerator.getListOfAllVersions();
            searchBar.setUpGameSelection(versions);
        }
        catch(UncheckedIOException initialNetworkError) {
            ErrorWindow startUpNetworkError = new ErrorWindow("A network error has occurred. Shutting down.");
            startUpNetworkError.display();
            System.err.println("Error: \n" + initialNetworkError.getMessage());
            Platform.exit();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        setUpWindowBasics(primaryStage);
        setUpSizesAndFonts();
        searchBar.addListener(this::search);
        startUpDisplay(true);
        primaryStage.show();
    }

    private void setUpWindowBasics(Stage primaryStage) {
        primaryStage.setTitle("Pokedex");
        primaryStage.getIcons().add(new Image("pokeball.png"));
        primaryStage.setScene(new Scene(createMainWindow()));
        primaryStage.setHeight(HEIGHT_OF_WINDOW);
        primaryStage.setWidth(WIDTH_OF_WINDOW);
        primaryStage.setOnCloseRequest(X -> {
            executor.shutdown();
            primaryStage.close();
            Platform.exit();
        });
    }

    private void setUpSizesAndFonts() {
        instruction.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        pokemonImage.setFitHeight(300);
        pokemonImage.setFitWidth(300);
        types.setFont(UPPER_FONT);
        abilities.setFont(UPPER_FONT);
        hiddenAbilities.setFont(UPPER_FONT);
        egg_groups.setFont(UPPER_FONT);
        dropDownMenu.setPrefWidth(WIDTH_OF_WINDOW - 250);
        selectedItemDisplay.setPrefHeight(HEIGHT_OF_WINDOW);
        selectedItemDisplay.setPrefWidth(WIDTH_OF_WINDOW);
    }

    private void startUpDisplay(boolean firstStartedUp) {
        dropDownMenu.setVisible(!firstStartedUp);
        selectedItemDisplay.setVisible(!firstStartedUp);
    }

    private Parent createMainWindow() {
        VBox mainWindow = new VBox();
        mainWindow.setAlignment(DEFAULT_POSITION);
        mainWindow.setSpacing(5);
        mainWindow.getChildren().addAll(
                instruction,
                searchBar.getDisplay(),
                createUpperPortion(),
                dropDownMenu,
                selectedItemDisplay
        );
        setUpDropDownMenu();
        return mainWindow;
    }

    private Parent createUpperPortion() {
        HBox upperPortion = new HBox();
        upperPortion.setAlignment(DEFAULT_POSITION);
        upperPortion.setSpacing(20);
        upperPortion.getChildren().addAll(
                pokemonImage,
                createPokeFacts()
        );

        return upperPortion;
    }

    private void setUpDropDownMenu() {
        MoveDisplay moveDisplay = new MoveDisplay();
        DamageRelationsDisplay damageRelationsDisplay = new DamageRelationsDisplay();
        StatsDisplay statsDisplay = new StatsDisplay();
        dropDownMenu.setItems(FXCollections.observableArrayList(statsDisplay, moveDisplay, damageRelationsDisplay));
        dropDownMenu.getSelectionModel().selectFirst();
        dropDownMenu.getSelectionModel().selectedItemProperty().addListener(
                (v, oldValue, newValue) -> setUpLowerContent());
    }

    public void search() {
        if (pokedexProcessor.pokemonExistsInNationalPokedex(searchBar.getInput())) {
            executor.execute(() -> {
                searchBar.setDisable(true);
                dropDownMenu.setDisable(true);
                instruction.setText("The pokedex is searching. Please wait...");
                beginProcessingPokemon();
                Platform.runLater(() -> {
                    update();
                    instruction.setText(INSTRUCTION_STRING);
                    searchBar.setDisable(false);
                    dropDownMenu.setDisable(false);
                });
            });
        } else {
            ErrorWindow doesNotExist = new ErrorWindow(searchBar.getInput() + " does not exist!");
            doesNotExist.display();
        }
    }

    private void beginProcessingPokemon() {
        try {
            currentPokemon = pokedexProcessor.process(searchBar.getInput(), searchBar.getSelectedVersion());
        }
        catch(PokemonDoesNotExistInVersionException notInGame) {
            Platform.runLater(() -> {
                ErrorWindow doesNotExistWindow = new ErrorWindow(searchBar.getInput() + " does not exist in " + searchBar.getSelectedVersion());
                doesNotExistWindow.display();
            });
        }
        catch(UncheckedIOException networkError) {
            Platform.runLater(() -> {
                ErrorWindow networkErrorWindow = new ErrorWindow("A network error has occurred!");
                networkErrorWindow.display();
                System.err.println("Error: \n" + networkError.getMessage());
            });
        }
    }

    private void update() {
        if(currentPokemon != null) {
            types.setText(pokedexProcessor.convertTypesToString(currentPokemon.getTypes()));
            abilities.setText("Abilities: " + pokedexProcessor.convertAbilitiesToString(currentPokemon.getAbilities()));
            hiddenAbilities.setText("Hidden Abilities: " + pokedexProcessor.convertHiddenAbilitiesToString(currentPokemon.getAbilities()));
            egg_groups.setText("Egg Groups: " + pokedexProcessor.convertEggGroupsToString(currentPokemon.getEggGroups()));
            pokemonImage.setImage(new Image(currentPokemon.getImageURL()));
            setUpLowerContent();
            startUpDisplay(false);
        }
    }

    private Parent createPokeFacts() {
        VBox pokeFacts = new VBox();
        pokeFacts.getChildren().addAll(
                types,
                abilities,
                hiddenAbilities,
                egg_groups
        );
        return pokeFacts;
    }

    private void setUpLowerContent() {
        MenuDisplay currentLayout = dropDownMenu.getSelectionModel().getSelectedItem();
        selectedItemDisplay.setContent(currentLayout.display(currentPokemon));
    }
}
