package com.univr.graphics.components.windows;

import com.univr.anagrafica.Worker;
import com.univr.anagrafica.Manager;
import com.univr.graphics.components.custom.Events;
import com.univr.graphics.components.custom.SceneBuilder;
import com.univr.graphics.components.custom.ButtonCustom;
import com.univr.graphics.components.custom.LabelErrorCustom;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ModifyWorkerWindow extends Window{

    @Override
    public void createWindow(Stage primaryStage, Worker worker, Worker old, Manager manager) {
        // Creazione finestra di modifica dei dati del lavoratore
        BorderPane rootInserimento = setWindow(primaryStage, "Modifica del lavoratore", 950, 700);

        // Creazione gridPane Inserimento
        final SceneBuilder gridPaneModifica = new SceneBuilder( 10, 10);
        gridPaneModifica.getGridPane().setAlignment(Pos.TOP_LEFT);
        gridPaneModifica.getGridPane().setPadding(new Insets(10, 0, 0, 10));

        this.objects = gridPaneModifica.addFieldsWorker();

        // Aggiunta bottone: INDIETRO
        ButtonCustom btnIndietro = new ButtonCustom("INDIETRO", gridPaneModifica.getGridPane(), 0, 0, 1, 1);
        btnIndietro.settingStyle("-fx-font-weight: bold;");
        Events.indietroModifyWorkerEvent(btnIndietro.getButton(), primaryStage);
        objects[0] = btnIndietro;

        final LabelErrorCustom lblErroreSalva =  new LabelErrorCustom("Dati inseriti errati o incompleti!", gridPaneModifica.getGridPane(), 3, 18, 4, 1);

        // Creazione bottone: AVANTI
        ButtonCustom btnAvanti = new ButtonCustom("AVANTI", gridPaneModifica.getGridPane(), 0, 18, 3, 1);
        btnAvanti.settingStyle("-fx-font-weight: bold;");
        Events.avantiModifyEvent(btnAvanti.getButton(), primaryStage, objects, worker, old, lblErroreSalva);
        objects[22] = btnAvanti;

        rootInserimento.setTop(gridPaneModifica.getGridPane());
    }
}
