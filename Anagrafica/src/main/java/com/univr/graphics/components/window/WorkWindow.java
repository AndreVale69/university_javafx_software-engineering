package com.univr.graphics.components.window;

import com.univr.anagrafica.Lavoratore;
import com.univr.graphics.GridPaneCustom;
import com.univr.graphics.components.custom.ButtonCustom;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class WorkWindow extends Window {
    @Override
    public void createWindow(Stage primaryStage, Lavoratore lavoratore) {
        // Creazione finestra d'inserimento del lavoro stagionale
        BorderPane rootSelezionamento = setWindow(primaryStage, "Inserimento lavoro stagionale", 1200, 750);

        // Creazione gridPane Selezionamento
        final GridPaneCustom gridPaneInsLavoro = new GridPaneCustom( 25, 25);
        gridPaneInsLavoro.getGridPane().setAlignment(Pos.TOP_LEFT);
        gridPaneInsLavoro.getGridPane().setPadding(new Insets(10, 0, 0, 10));

        Object[] objectsLavoro = gridPaneInsLavoro.addFieldsInserimentoLavoro(primaryStage, lavoratore);

        ((ButtonCustom) objectsLavoro[objectsLavoro.length - 1]).indietroEvent(primaryStage, lavoratore);

        rootSelezionamento.setTop(gridPaneInsLavoro.getGridPane());
    }

    @Override
    public Object[] getObjects() {
        return null; // TODO to fix
    }
}
