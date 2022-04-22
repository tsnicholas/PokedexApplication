package edu.bsu.cs222.view;

import edu.bsu.cs222.model.NationalPokedex;

import javax.swing.*;

public class AutoComplete extends JFrame {
    JTextField pokemons = new JTextField(10);
    String enteredName = null;
    private String[] NationalPokedex;
    String[] cities = NationalPokedex;
    JList list = new JList();

    public static void main(String[] args) {
        new AutoComplete();
    }
    public AutoComplete(){

    }
}
