// Considering making this into a .exe file, thus why we have module-info for packaging.
module Pokedex.main {
    requires javafx.controls;
    requires json.path;
    requires json.smart;
    requires org.controlsfx.controls;
    exports edu.bsu.cs222.model;
    exports edu.bsu.cs222.model.parsers;
    exports edu.bsu.cs222.view;
}