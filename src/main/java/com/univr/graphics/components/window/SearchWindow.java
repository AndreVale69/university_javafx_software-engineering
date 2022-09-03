package com.univr.graphics.components.window;

import com.univr.anagrafica.Agenzia;
import com.univr.anagrafica.Lavoratore;
import com.univr.graphics.GridPaneCustom;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SearchWindow extends Window {
    @Override
    public void createWindow(Stage primaryStage, Lavoratore lavoratore) {
        // Creazione finestra della ricerca dell'anagrafica
        BorderPane rootAnagrafica = setWindow(primaryStage, "Ricerca anagrafiche", 1300, 700);

        // Creazione gridPane ricerca anagrafica
        final GridPaneCustom gridPaneAnagrafica = new GridPaneCustom( 10, 10);
        gridPaneAnagrafica.getGridPane().setAlignment(Pos.TOP_LEFT);
        gridPaneAnagrafica.getGridPane().setPadding(new Insets(10, 0, 0, 10));

        // Aggiunta campi ricerca
        gridPaneAnagrafica.addFieldsAnagrafica(primaryStage);
        // Aggiunta label ricerca
        gridPaneAnagrafica.addLabelRicerca(primaryStage);

        rootAnagrafica.setTop(gridPaneAnagrafica.getGridPane());
    }

    @Override
    public Object[] getObjects() {
        return null; // TODO to fix
    }
}
