package edu.bsu.cs222.view;

import edu.bsu.cs222.model.Pokemon;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public interface MenuDisplay {
    Font BIG_HEADER_FONT = Font.font("Times New Roman", FontWeight.BOLD, 22);
    Font HEADER_FONT = Font.font("Times New Roman", FontWeight.BOLD, 18);
    Font DEFAULT_FONT = Font.font("Times New Roman", 18);
    Insets DEFAULT_INSETS = new Insets(10, 40, 0, 20);
    double SMALL_SPACING = 10;
    double LARGE_SPACING = 40;

    Parent getInitialDisplay();

    Parent display(Pokemon pokemon);

    String getDisplayName();
}
