package edu.bsu.cs222.view;

import edu.bsu.cs222.model.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
    private final int HEIGHT_OF_WINDOW = 800;
    private final int WIDTH_OF_WINDOW = 800;
    private final double SMALL_SPACING = 10;
    private final Pos DEFAULT_POSITION = Pos.TOP_CENTER;
    private final Font UPPER_FONT = Font.font("Verdana", 25);
    private final String INSTRUCTION_STRING = "Enter a name of a Pokemon";

    private final Text instruction = new Text(INSTRUCTION_STRING);
    private final SearchBar searchBar = new SearchBar();
    private final Text types = new Text();
    private final Text stats = new Text();
    private final Text egg_groups = new Text();
    private final ImageView pokemonImage = new ImageView();
    private final TabPane tabMenu = new TabPane();
    private final ChoiceBox<Pokemon> pokemonForms = new ChoiceBox<>();

    private final PokedexProcessor pokedexProcessor = new PokedexProcessor();
    private final List<MenuDisplay> menuDisplayList = List.of(new MoveDisplay(), new DamageRelationsDisplay(),
            new AbilitiesDisplay());

    private final ExecutorService executor = Executors.newCachedThreadPool();

    @Override
    public void init() throws Exception {
        super.init();
        collectVersions();
    }

    private void collectVersions() {
        VersionListGenerator versionsListGenerator = new VersionListGenerator();
        List<Version> versions = versionsListGenerator.getListOfAllVersions();
        searchBar.setUpGameSelection(versions);
    }

    @Override
    public void start(Stage primaryStage) {
        setUpWindowBasics(primaryStage);
        setUpSizesAndFonts();
        searchBar.addListener(this::search);
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
        pokemonForms.setPrefWidth(300);
        types.setFont(UPPER_FONT);
        stats.setFont(UPPER_FONT);
        egg_groups.setFont(UPPER_FONT);
        egg_groups.setWrappingWidth(300);
        tabMenu.setPrefWidth(WIDTH_OF_WINDOW);
        tabMenu.setPrefHeight(HEIGHT_OF_WINDOW);
    }

    private Parent createMainWindow() {
        VBox mainWindow = new VBox(SMALL_SPACING);
        mainWindow.setAlignment(DEFAULT_POSITION);
        mainWindow.getChildren().addAll(
                instruction,
                searchBar.getDisplay(),
                createUpperPortion(),
                tabMenu
        );
        setUpTabMenu();
        return mainWindow;
    }

    private void setUpTabMenu() {
        tabMenu.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabMenu.setTabDragPolicy(TabPane.TabDragPolicy.REORDER);
        insertTabsIntoTabMenu();
    }

    private void insertTabsIntoTabMenu() {
        for (MenuDisplay menuDisplay : menuDisplayList) {
            tabMenu.getTabs().add(new Tab(menuDisplay.toString(), menuDisplay.getInitialDisplay()));
        }
    }

    private Parent createUpperPortion() {
        HBox upperPortion = new HBox(20);
        upperPortion.setAlignment(DEFAULT_POSITION);
        upperPortion.getChildren().addAll(
                createPokemonImageAndFormMenu(),
                createBasicInformation()
        );
        return upperPortion;
    }

    public Parent createPokemonImageAndFormMenu() {
        VBox image = new VBox(5);
        image.getChildren().addAll(
                pokemonImage,
                pokemonForms
        );
        setUpFormMenu();
        return image;
    }

    private void setUpFormMenu() {
        pokemonForms.setVisible(false);
        pokemonForms.getSelectionModel().selectedItemProperty().addListener(
                (v, oldValue, newValue) -> update());
    }

    private Parent createBasicInformation() {
        VBox pokeFacts = new VBox(SMALL_SPACING);
        pokeFacts.getChildren().addAll(
                types,
                stats,
                egg_groups
        );
        return pokeFacts;
    }

    public void search() {
        if (pokedexProcessor.pokemonExistsInNationalPokedex(searchBar.getInput())) {
            pokemonForms.setVisible(false);
            pokemonForms.getItems().remove(0, pokemonForms.getItems().size());
            executor.execute(() -> {
                searchBar.setDisable(true);
                instruction.setText("The pokedex is searching. Please wait...");
                beginProcessingPokemon();
                Platform.runLater(() -> {
                    pokemonForms.getSelectionModel().selectFirst();
                    update();
                    instruction.setText(INSTRUCTION_STRING);
                    searchBar.setDisable(false);
                });
            });
        } else {
            ErrorWindow doesNotExist = new ErrorWindow(searchBar.getInput() + " does not exist!");
            doesNotExist.display();
        }
    }

    private void beginProcessingPokemon() {
        try {
            pokemonForms.getItems().addAll(pokedexProcessor.process(searchBar.getInput(), searchBar.getSelectedVersion()));
        } catch (PokemonDoesNotExistInVersionException notInGame) {
            Platform.runLater(() -> {
                ErrorWindow doesNotExistWindow = new ErrorWindow(searchBar.getInput() + " does not exist in " + searchBar.getSelectedVersion());
                doesNotExistWindow.display();
            });
        } catch (UncheckedIOException networkError) {
            Platform.runLater(() -> {
                ErrorWindow networkErrorWindow = new ErrorWindow("A network error has occurred!");
                networkErrorWindow.display();
                System.err.println("Error: \n" + networkError.getMessage());
            });
        }
    }

    private void update() {
        Pokemon currentPokemon = pokemonForms.getSelectionModel().getSelectedItem();
        if (currentPokemon != null) {
            types.setText(pokedexProcessor.convertTypesToString(currentPokemon.getTypes()));
            stats.setText(pokedexProcessor.convertStatsToString(currentPokemon.getStats()));
            egg_groups.setText("Egg Groups: " + pokedexProcessor.convertEggGroupsToString(currentPokemon.getEggGroups()));
            pokemonImage.setImage(new Image(currentPokemon.getImageURL()));
            insertContentIntoTabs(currentPokemon);
            pokemonForms.setVisible(true);
        }
    }

    private void insertContentIntoTabs(Pokemon currentPokemon) {
        for (int i = 0; i < menuDisplayList.size(); i++) {
            tabMenu.getTabs().get(i).setContent(menuDisplayList.get(i).display(currentPokemon));
        }
    }
}
