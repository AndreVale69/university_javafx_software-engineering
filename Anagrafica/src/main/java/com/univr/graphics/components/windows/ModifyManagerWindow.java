package com.univr.graphics.components.windows;

import com.univr.anagrafica.Worker;
import com.univr.anagrafica.Manager;
import com.univr.graphics.components.custom.SceneBuilder;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ModifyManagerWindow extends Window{

    @Override
    public void createWindow(Stage primaryStage, Worker worker, Worker old, Manager manager) {
        // Creazione finestra della modifica di un responsabile
        BorderPane rootModResponsabile = setWindow(primaryStage, "Modifica responsabile", 380, 400);

        // Creazione gridPane modifica responsabile
        final SceneBuilder gridPaneModResponsabile = new SceneBuilder( 10, 10);
        gridPaneModResponsabile.getGridPane().setAlignment(Pos.TOP_LEFT);
        gridPaneModResponsabile.getGridPane().setPadding(new Insets(10, 0, 0, 10));

        this.objects = gridPaneModResponsabile.addFieldsModifyManager(primaryStage, manager);

        rootModResponsabile.setTop(gridPaneModResponsabile.getGridPane());
    }
}
