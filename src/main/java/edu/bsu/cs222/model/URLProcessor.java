package edu.bsu.cs222.model;

public interface URLProcessor {
    Object getNationalPokedex();

    Object convertStringToObject(String urlString);

    Object getUpTo20Generations();

    Object getAllGenerations(int count);

    Object getPokemonJsonObject(String nameOfPokemon);
}