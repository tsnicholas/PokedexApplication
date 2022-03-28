package edu.bsu.cs222.view;

import edu.bsu.cs222.model.PokedexProcessor;
import edu.bsu.cs222.model.Pokemon;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class DamageRelationsDisplay implements MenuDisplay {
    private final PokedexProcessor pokemonProcessor = new PokedexProcessor();
    private final Text results = new Text();
    private final Pokemon currentPokemon;

    public DamageRelationsDisplay(Pokemon pokemon) {
        currentPokemon = pokemon;
        setText();
    }

    private void setText() {
        results.setText(pokemonProcessor.convertDamageRelationsToString(currentPokemon));
    }

    public Parent display() {
        HBox display = new HBox();
        display.getChildren().add(results);
        return display;
    }
}
