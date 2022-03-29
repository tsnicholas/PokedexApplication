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
    private final int WIDTH_OF_WINDOW = 700;
    private final int HEIGHT_OF_WINDOW = 800;
    private final Pos DEFAULT_POSITION = Pos.CENTER;

    private final Text instruction = new Text("Enter a name of a Pokemon");
    private final TextField searchInput = new TextField();
    private final ChoiceBox<String> gameSelection = new ChoiceBox<>();
    private final Button searchButton = new Button("Search");
    private final Text types = new Text();
    private final Text stats = new Text();
    private final ImageView pokemonImage = new ImageView();
    private final ChoiceBox<String> dropDownMenu = new ChoiceBox<>();
    private final ScrollPane lowerPortion = new ScrollPane();
    private final PokedexProcessor pokemonProcessor = new PokedexProcessor();
    private MoveDisplay moveDisplay;
    private DamageRelationsDisplay damageRelationsDisplay;
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
        primaryStage.setHeight(HEIGHT_OF_WINDOW);
        primaryStage.setWidth(WIDTH_OF_WINDOW);
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(X -> Platform.exit());
    }

    private void setUpEventTriggers() {
        searchInput.setOnKeyPressed(keyPressed -> {
            if (keyPressed.getCode() == KeyCode.ENTER) {
                beginProcessing();
            }
        });
        searchButton.setOnAction(clicked -> beginProcessing());
    }

    private void setUpSizesAndFonts() {
        lowerPortion.setPrefViewportHeight(HEIGHT_OF_WINDOW);
        lowerPortion.setPrefViewportWidth(WIDTH_OF_WINDOW);
        searchInput.setPrefWidth(WIDTH_OF_WINDOW - 300);
        pokemonImage.setFitHeight(300);
        pokemonImage.setFitWidth(300);
        instruction.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        types.setFont(getPokeFactsFont());
        stats.setFont(getPokeFactsFont());
    }

    private Font getPokeFactsFont() {
        return Font.font("Verdana", 25);
    }

    private void startUpDisplay(boolean startUp) {
        pokemonImage.setVisible(!startUp);
        types.setVisible(!startUp);
        stats.setVisible(!startUp);
        dropDownMenu.setVisible(!startUp);
        lowerPortion.setVisible(!startUp);
    }

    private Parent createMainWindow() {
        VBox mainWindow = new VBox();
        mainWindow.setAlignment(DEFAULT_POSITION);
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
        searchBar.setAlignment(DEFAULT_POSITION);
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
        upperPortion.setAlignment(DEFAULT_POSITION);
        upperPortion.setSpacing(20);
        upperPortion.getChildren().addAll(
                pokemonImage,
                createPokeFacts()
        );

        return upperPortion;
    }

    private Parent createDropDownMenu() {
        dropDownMenu.setPrefWidth(WIDTH_OF_WINDOW);
        dropDownMenu.getItems().addAll(
                "Move Set",
                "Damage Relations"
        );
        dropDownMenu.getSelectionModel().selectFirst();
        dropDownMenu.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> setUpLowerContent(newValue));
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
        gameSelection.setDisable(true);
        searchButton.setDisable(true);
        dropDownMenu.setDisable(true);
        search();
        Platform.runLater(() -> {
            searchInput.setDisable(false);
            gameSelection.setDisable(false);
            searchButton.setDisable(false);
            dropDownMenu.setDisable(false);
        });
    }

    private void search() {
        try {
            currentPokemon = pokemonProcessor.process(searchInput.getText().toLowerCase(), gameSelection.getValue());
            update();
            startUpDisplay(false);
        } catch (RuntimeException doesNotExist) {
            ErrorWindow noExistence = new ErrorWindow(searchInput.getText() + " doesn't exist in Pokemon " + gameSelection.getValue());
            noExistence.display();
        }
    }

    private void update() {
        types.setText(pokemonProcessor.convertTypesToString(currentPokemon));
        stats.setText(pokemonProcessor.convertStatsToString(currentPokemon));
        pokemonImage.setImage(new Image(currentPokemon.getImageURL()));
        moveDisplay = new MoveDisplay(currentPokemon);
        damageRelationsDisplay = new DamageRelationsDisplay(currentPokemon);
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

    private void setUpLowerContent(String selectedValue) {
        if (currentPokemon != null) {
            if (selectedValue.equals("Move Set")) {
                lowerPortion.setContent(moveDisplay.display());
            } else if (selectedValue.equals("Damage Relations")) {
                lowerPortion.setContent(damageRelationsDisplay.display());
            }
        }
    }
}
