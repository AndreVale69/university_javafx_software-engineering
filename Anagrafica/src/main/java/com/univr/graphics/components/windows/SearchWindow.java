package com.univr.graphics.components.windows;

import com.univr.anagrafica.Worker;
import com.univr.anagrafica.Manager;
import com.univr.graphics.components.custom.SceneBuilder;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SearchWindow extends Window {

    @Override
    public void createWindow(Stage primaryStage, Worker worker, Worker old, Manager manager) {
        // Creazione finestra della ricerca dell'anagrafica
        BorderPane rootAnagrafica = setWindow(primaryStage, "Ricerca anagrafiche", 1300, 700);

        // Creazione gridPane ricerca anagrafica
        final SceneBuilder gridPaneAnagrafica = new SceneBuilder( 10, 10);
        gridPaneAnagrafica.getGridPane().setAlignment(Pos.TOP_LEFT);
        gridPaneAnagrafica.getGridPane().setPadding(new Insets(10, 0, 0, 10));

        // Aggiunta campi ricerca
        this.objects = gridPaneAnagrafica.addFieldsSearch(primaryStage);

        rootAnagrafica.setTop(gridPaneAnagrafica.getGridPane());
    }
}
