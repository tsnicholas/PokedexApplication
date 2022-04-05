package edu.bsu.cs222.view;

import edu.bsu.cs222.model.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
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

import java.util.List;

public class MainWindow extends Application {
    private final int WIDTH_OF_WINDOW = 700;
    private final int HEIGHT_OF_WINDOW = 800;
    private final Pos DEFAULT_POSITION = Pos.CENTER;

    private final Text instruction = new Text("Enter a name of a Pokemon");
    private final SearchBar searchBar = new SearchBar();
    private final Text types = new Text();
    private final Text stats = new Text();
    private final ImageView pokemonImage = new ImageView();
    private final ChoiceBox<MenuDisplay> dropDownMenu = new ChoiceBox<>();
    private final ScrollPane lowerPortion = new ScrollPane();
    private final PokedexProcessor pokedexProcessor = new PokedexProcessor();
    private MoveDisplay moveDisplay;
    private DamageRelationsDisplay damageRelationsDisplay;
    private Pokemon currentPokemon;

    @Override
    public void init() throws Exception {
        super.init();
        VersionListFetcher versionsListFetcher = new VersionListFetcher();
        List<Version> versions = versionsListFetcher.getListOfAllVersions();
        searchBar.setUpGameSelection(versions);
    }

    @Override
    public void start(Stage primaryStage) {
        setUpWindowBasics(primaryStage);
        setUpSizesAndFonts();
        setUpEventTriggers();
        startUpDisplay(true);
        primaryStage.show();
    }

    private void setUpWindowBasics(Stage primaryStage) {
        primaryStage.setTitle("Pokedex");
        primaryStage.getIcons().add(new Image("pokeball.png"));
        primaryStage.setScene(new Scene(createMainWindow()));
        primaryStage.setHeight(HEIGHT_OF_WINDOW);
        primaryStage.setWidth(WIDTH_OF_WINDOW);
        primaryStage.setOnCloseRequest(X -> Platform.exit());
    }

    private void setUpSizesAndFonts() {
        lowerPortion.setPrefViewportHeight(HEIGHT_OF_WINDOW);
        lowerPortion.setPrefViewportWidth(WIDTH_OF_WINDOW);
        pokemonImage.setFitHeight(300);
        pokemonImage.setFitWidth(300);
        instruction.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        types.setFont(getPokeFactsFont());
        stats.setFont(getPokeFactsFont());
        dropDownMenu.setPrefWidth(WIDTH_OF_WINDOW);
    }

    private void setUpEventTriggers() {
        searchBar.getTextField().setOnKeyPressed(keyPressed -> {
            if (keyPressed.getCode() == KeyCode.ENTER) {
                beginProcessing();
            }
        });
        searchBar.getButton().setOnAction(clicked -> beginProcessing());
    }

    private Font getPokeFactsFont() {
        return Font.font("Verdana", 25);
    }

    private void startUpDisplay(boolean firstStartedUp) {
        pokemonImage.setVisible(!firstStartedUp);
        types.setVisible(!firstStartedUp);
        stats.setVisible(!firstStartedUp);
        dropDownMenu.setVisible(!firstStartedUp);
        lowerPortion.setVisible(!firstStartedUp);
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
                lowerPortion
        );
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

    public void beginProcessing() {
        if (pokedexProcessor.pokemonExistsInNationalPokedex(searchBar.getInput())) {
            searchBar.setDisable(true);
            dropDownMenu.setDisable(true);
            search();
            Platform.runLater(() -> {
                searchBar.setDisable(false);
                dropDownMenu.setDisable(false);
            });
        } else {
            ErrorWindow doesNotExist = new ErrorWindow(searchBar.getInput() + " does not exist!");
            doesNotExist.display();
        }
    }

    private void search() {
        try {
            currentPokemon = pokedexProcessor.process(searchBar.getInput().toLowerCase(), searchBar.getSelectedVersion());
            update();
            startUpDisplay(false);
        }
        catch(NullPointerException nullPointerException) {
            ErrorWindow genericError = new ErrorWindow("An error has occurred!");
            genericError.display();
            System.err.println("Error: " + nullPointerException);
        }
        catch (RuntimeException doesNotExist) {
            ErrorWindow noExistence = new ErrorWindow(searchBar.getInput() + " doesn't exist in Pokemon " + searchBar.getSelectedVersion());
            noExistence.display();
        }
    }

    private void update() {
        types.setText(pokedexProcessor.convertTypesToString(currentPokemon));
        stats.setText(pokedexProcessor.convertStatsToString(currentPokemon));
        pokemonImage.setImage(new Image(currentPokemon.getImageURL()));
        moveDisplay = new MoveDisplay(currentPokemon);
        damageRelationsDisplay = new DamageRelationsDisplay(currentPokemon);
        updateDropDownMenu();
        setUpLowerContent();
    }

    private void updateDropDownMenu() {
        dropDownMenu.setItems(FXCollections.observableArrayList(moveDisplay, damageRelationsDisplay));
        dropDownMenu.getSelectionModel().selectFirst();
        dropDownMenu.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> setUpLowerContent());
    }

    private Parent createPokeFacts() {
        VBox pokeFacts = new VBox();
        pokeFacts.getChildren().addAll(
                types,
                stats
        );

        return pokeFacts;
    }

    private void setUpLowerContent() {
        MenuDisplay currentLayout = dropDownMenu.getSelectionModel().getSelectedItem();
        lowerPortion.setContent(currentLayout.display());
    }
}
