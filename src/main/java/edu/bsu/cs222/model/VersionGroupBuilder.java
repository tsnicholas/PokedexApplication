package edu.bsu.cs222.model;

import edu.bsu.cs222.model.parsers.VersionGroupParser;

public class VersionGroupBuilder {
    private final PokedexFactory pokedexFactory = new PokedexFactory();

    public VersionGroup createVersionGroup(Object versionGroupJsonDocument) {
        VersionGroupParser versionGroupParser = new VersionGroupParser();
        String name = versionGroupParser.parseForName(versionGroupJsonDocument);
        Pokedex pokedex = pokedexFactory.createPokedex(versionGroupJsonDocument);

        return new VersionGroup(name, pokedex);
    }
}
