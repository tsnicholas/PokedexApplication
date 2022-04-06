package edu.bsu.cs222.model;

public interface URLProcessor {
    Object getNationalPokedex();

    Object stringToObject(String urlString);

    Object getUpTo20Generations();

    Object getAllGenerations(int count);
}