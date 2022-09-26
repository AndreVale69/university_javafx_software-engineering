package com.univr.graphics.components.windows;

import com.univr.anagrafica.Worker;
import com.univr.anagrafica.Manager;
import com.univr.graphics.components.custom.SceneBuilder;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AdminWindow extends Window{

    @Override
    public void createWindow(Stage primaryStage, Worker worker, Worker old, Manager manager) {
        // Creazione finestra della finestra admin
        BorderPane rootAdmin = setWindow(primaryStage, "Gestione dei Responsabili", 1100, 600);

        // Creazione gridPane Inserimento
        final SceneBuilder gridPaneAdmin = new SceneBuilder( 10, 10);
        gridPaneAdmin.getGridPane().setAlignment(Pos.TOP_LEFT);
        gridPaneAdmin.getGridPane().setPadding(new Insets(10, 0, 0, 10));

        this.objects = gridPaneAdmin.addFieldsAdmin(primaryStage);

        rootAdmin.setTop(gridPaneAdmin.getGridPane());
    }
}
