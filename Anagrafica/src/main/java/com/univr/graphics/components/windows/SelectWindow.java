package com.univr.graphics.components.windows;

import com.univr.anagrafica.Business;
import com.univr.anagrafica.Worker;
import com.univr.anagrafica.Manager;
import com.univr.graphics.components.custom.Events;
import com.univr.graphics.components.custom.SceneBuilder;
import com.univr.graphics.components.custom.ButtonCustom;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SelectWindow extends Window {
    @Override
    public void createWindow(Stage primaryStage, Worker worker, Worker old, Manager manager) {
        // Creazione finestra di selezionamento del servizio
        BorderPane rootSelezionamento = setWindow(primaryStage, "Selezione del servizio", 500, 500);

        final SceneBuilder gridPaneSelezionamento = new SceneBuilder(25, 25);

        final ButtonCustom btnInserimento = new ButtonCustom("INSERIMENTO DATI LAVORATORI", gridPaneSelezionamento, 0, 0);
        Events.inserimentoEvent(btnInserimento.getButton(), primaryStage);

        final ButtonCustom btnAnagrafica = new ButtonCustom("RICERCA E AGGIORNAMENTO ANAGRAFICHE", gridPaneSelezionamento, 0, 1);
        Events.anagraficaEvent(btnAnagrafica.getButton(), primaryStage);

        final ButtonCustom btnReset = new ButtonCustom("RESET", gridPaneSelezionamento, 0, 2);
        Events.resetEvent(btnReset.getButton(), Business.getInstance());

        final ButtonCustom btnLogOut = new ButtonCustom("LOGOUT", gridPaneSelezionamento, 0, 3);
        Events.logoutEvent(btnLogOut.getButton(), primaryStage);

        // Creazione dello stile dei bottoni
        setStyle(btnInserimento, btnAnagrafica, btnReset, btnLogOut);

        Business.getWorksBackup().clear();

        rootSelezionamento.setCenter(gridPaneSelezionamento.getGridPane());
    }

    /**
     * Imposta lo stesso stile in tutti e tre i bottoni
     * @param inserimento bottone che apre la pagina d'inserimento
     * @param anagrafica  bottone che apre la pagina di ricerca
     * @param logout      bottone che riporta alla pagina di login
     */
    private void setStyle(ButtonCustom inserimento, ButtonCustom anagrafica, ButtonCustom reset, ButtonCustom logout) {
        inserimento.settingStyle("-fx-font-size: 13pt;");
        anagrafica.settingStyle("-fx-font-size: 13pt;");
        reset.settingStyle("-fx-font-size: 13pt;");
        logout.settingStyle("-fx-font-size: 13pt;");
    }
}
