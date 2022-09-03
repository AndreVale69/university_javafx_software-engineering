package com.univr.graphics.components.window;

import com.univr.anagrafica.Lavoratore;
import com.univr.graphics.GridPaneCustom;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class InsertWindow extends Window {
    private Object[] objects = new Object[23 + 4 + 4 + 4];

    @Override
    public void createWindow(Stage primaryStage, Lavoratore lavoratore) {
        // Creazione finestra dell'inserimento dei dati del lavoratore
        BorderPane rootInserimento = setWindow(primaryStage, "Inserimento dati lavoratore", 950, 700);

        // Creazione gridPane Inserimento
        final GridPaneCustom gridPaneInserimento = new GridPaneCustom( 10, 10);
        gridPaneInserimento.getGridPane().setAlignment(Pos.TOP_LEFT);
        gridPaneInserimento.getGridPane().setPadding(new Insets(10, 0, 0, 10));

        this.objects = gridPaneInserimento.addFieldsInserimento(primaryStage);

        rootInserimento.setTop(gridPaneInserimento.getGridPane());
    }

    public Object[] getObjects() {
        return objects;
    }
}
