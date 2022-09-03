package com.univr.graphics.components.window;

import com.univr.anagrafica.Agenzia;
import com.univr.anagrafica.Lavoratore;
import com.univr.anagrafica.Lavoro;
import com.univr.graphics.GridPaneCustom;
import com.univr.graphics.components.custom.ButtonCustom;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SelectWindow extends Window {
    @Override
    public void createWindow(Stage primaryStage, Lavoratore lavoratore) {
        // Creazione finestra di selezionamento del servizio
        BorderPane rootSelezionamento = setWindow(primaryStage, "Selezione del servizio", 500, 500);

        final GridPaneCustom gridPaneSelezionamento = new GridPaneCustom(25, 25);

        final ButtonCustom btnInserimento = new ButtonCustom("INSERIMENTO DATI LAVORATORI", gridPaneSelezionamento, 0, 0);
        btnInserimento.inserimentoEvent(primaryStage);

        final ButtonCustom btnAnagrafica = new ButtonCustom("RICERCA E AGGIORNAMENTO ANAGRAFICHE", gridPaneSelezionamento, 0, 1);
        btnAnagrafica.anagraficaEvent(primaryStage);

        final ButtonCustom btnReset = new ButtonCustom("RESET", gridPaneSelezionamento, 0, 2);
        btnReset.testResetEvent(Agenzia.getInstance());

        final ButtonCustom btnLogOut = new ButtonCustom("LOGOUT", gridPaneSelezionamento, 0, 3);
        btnLogOut.logoutEvent(primaryStage);

        // Creazione dello stile dei bottoni
        setStyle(btnInserimento, btnAnagrafica, btnReset, btnLogOut);

        Lavoro.getLavoriBackup().clear();

        rootSelezionamento.setCenter(gridPaneSelezionamento.getGridPane());
    }

    @Override
    public Object[] getObjects() {
        return null; // TODO to fix
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
