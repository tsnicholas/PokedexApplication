package edu.bsu.cs222.model.parsers;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs222.model.Pokedex;
import net.minidev.json.JSONArray;

import java.util.List;

public class PokedexParser {
    private final JsonParser jsonParser = new JsonParser();

    public Pokedex parsePokemonNames(Object nationalDexJsonObject) {
        JSONArray pokemonNamesArray = JsonPath.read(nationalDexJsonObject, "$.pokemon_entries..pokemon_species.name");
        List<String> pokemons = jsonParser.jsonArrayToStringList(pokemonNamesArray);
        return new Pokedex(pokemons);
    }
}
