package edu.bsu.cs222.model;

public class PokemonProcessor {
    private URLProcessor Url = new URLProcessor();
    private PokemonBuilder pokemonBuilder = new PokemonProcessor();
    private PokemonVerifier pokemonVerifier = new PokemonVerifier();
    private InputStreamConverter inputStreamConverter = new InputSteamConverter();

    public PokemonProcessor() {
        

    }

    // TODO: Starting with this method, use the URLProcessor class, the parsers, and the data stored in Pokemon
    //  To return information on a given Pokemon to the main window. For now, it will simply throw a RuntimeException.
    public Pokemon process(String nameOfPokemon, String nameOfGame) throws RuntimeException {
        String inputStreamString = Url.process("https://pokeapi.co/api/v2/pokemon/" + nameOfPokemon);

        throw new RuntimeException();
    }
}
