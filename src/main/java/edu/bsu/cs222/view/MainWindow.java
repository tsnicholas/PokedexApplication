package edu.bsu.cs222.view;

import edu.bsu.cs222.model.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private final int SIZE_OF_WINDOW = 800;
    private final Pos DEFAULT_POSITION = Pos.TOP_CENTER;
    private final Font UPPER_FONT = Font.font("Verdana", 25);
    private final String INSTRUCTION_STRING = "Enter a name of a Pokemon";

    private final Text instruction = new Text(INSTRUCTION_STRING);
    private final SearchBar searchBar = new SearchBar();
    private final Text types = new Text();
    private final Text egg_groups = new Text();
    private final Text stats = new Text();
    private final ImageView pokemonImage = new ImageView();
    private final TabPane tabMenu = new TabPane();
    private final PokedexProcessor pokedexProcessor = new PokedexProcessor();
    private Pokemon currentPokemon;

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
        primaryStage.setHeight(SIZE_OF_WINDOW);
        primaryStage.setWidth(SIZE_OF_WINDOW);
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
        stats.setFont(UPPER_FONT);
        egg_groups.setFont(UPPER_FONT);
        tabMenu.setPrefWidth(SIZE_OF_WINDOW);
    }

    private Parent createMainWindow() {
        VBox mainWindow = new VBox();
        mainWindow.setAlignment(DEFAULT_POSITION);
        mainWindow.setSpacing(5);
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
        tabMenu.getTabs().addAll(
                new Tab("Abilities"),
                new Tab("Move Set"),
                new Tab("Damage Relations")
        );
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

    public void search() {
        if (pokedexProcessor.pokemonExistsInNationalPokedex(searchBar.getInput())) {
            executor.execute(() -> {
                searchBar.setDisable(true);
                instruction.setText("The pokedex is searching. Please wait...");
                beginProcessingPokemon();
                Platform.runLater(() -> {
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
            stats.setText(pokedexProcessor.convertStatsToString(currentPokemon.getStats()));
            egg_groups.setText("Egg Groups: " + pokedexProcessor.convertEggGroupsToString(currentPokemon.getEggGroups()));
            pokemonImage.setImage(new Image(currentPokemon.getImageURL()));
            setUpLowerContent();
        }
    }

    private Parent createPokeFacts() {
        VBox pokeFacts = new VBox();
        pokeFacts.setSpacing(10);
        pokeFacts.getChildren().addAll(
                types,
                stats,
                egg_groups
        );
        return pokeFacts;
    }

    private void setUpLowerContent() {
        List<MenuDisplay> menuDisplays = List.of(new AbilitiesDisplay(), new MoveDisplay(), new DamageRelationsDisplay());
        for(int i = 0; i < tabMenu.getTabs().size(); i++) {
            tabMenu.getTabs().get(i).setContent(menuDisplays.get(i).display(currentPokemon));
        }
    }
}
