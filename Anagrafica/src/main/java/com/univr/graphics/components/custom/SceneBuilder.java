package com.univr.graphics.components.custom;

import com.univr.anagrafica.*;
import com.univr.graphics.components.windows.WindowFactory;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.HashSet;

/**
 * Classe per la creazione e la gestione dei GridPane
 */
public class SceneBuilder {
    private GridPane gridPane = new GridPane();
    private static TableViewCustom tableViewStatic;
    private static final String[] stringLabel = {
            "Nome*",
            "Cognome*",
            "Luogo di nascita*",
            "Data di nascita*",
            "Nazionalità*",
            "Indirizzo*",
            "Telefono",
            "Email*",
            "Specializzazioni/Esperienze prec.",
            "Lingue parlate*",
            "Tipo patente*",
            "Automunito?*",
            "Periodo/i*",
            "Zona/e*"
    };
    private static final String[] stringLabelIns = {
            "Periodo",
            "Nome dell'azienda",
            "Mansioni svolte",
            "Luogo di lavoro",
            "Retribuzione lorda giornaliera"
    };

    private static final String[] strings = {
            "Nome",
            "Cognome",
            "Lingua/e",
            "Indirizzo",
            "Patente/i",
            "Automunito?",
            "Modifica",
            "Elimina"
    };

    private static final String[] namesValueFactoryWorkers = {
            "nome",
            "cognome",
            "lingue",
            "residenza",
            "patenti",
            "automunito",
            "btnModify",
            "btnDelete"
    };

    // Array contenente i tipi di dato delle colonne
    private static final String[] nameValueFactoryWorks = {
            "btnDelete",
            "periodoInizio",
            "periodoFine",
            "nomeAzienda",
            "mansioniSvolte",
            "luogoLavoro",
            "retribuzione"};

    // Array contenente i nomi delle colonne
    private static final String[] colNames = {
            "Elimina",
            "Periodo inizio",
            "Periodo fine",
            "Azienda",
            "Mansioni",
            "Luogo",
            "Retribuzione"
    };

    private static final String[] labelsAdmin = {
            "Nome",
            "Cognome",
            "Luogo di nascita",
            "Data di nascita",
            "Nazionalità",
            "Email",
            "Telefono",
            "Password"
    };

    /**
     * Costruttore di GridPaneCustom, definisce i margini e la spaziatura
     * @param hGap hGap
     * @param vGap vGap
     */
    public SceneBuilder(double hGap, double vGap) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(hGap);
        gridPane.setVgap(vGap);
    }

    /**
     * Costruttore di GridPaneCustom, "converte" un GridPane in GridPaneCustom
     * @param gridPane gridPane
     */
    public SceneBuilder(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    /**
     * Metodo utilizzato per ottenere il GridPane
     * @return GridPane
     */
    public GridPane getGridPane () {
        return gridPane;
    }

    /**
     * Metodo che definisce la composizione del GridPane della pagina di inserimento
     * @return Object[]
     */
    public Object[] addFieldsWorker() {
        Object[] arrays = new Object[35];

        // Aggiunta label: Obbligatorio
        LabelErrorCustom lblErroreObbl = new LabelErrorCustom("Campo obbligatorio *", gridPane, 1, 0, 1, 1);
        lblErroreObbl.activatingError();

        /*
         * CONTATTO D'EMERGENZA
         */
        for (int i = 1; i <= 3; i++) {
            // Aggiunta label : Nome, cognome, email, telefono
            String[] strings = {"Nome*", "Cognome*", "Email*", "Telefono"};
            if (i == 1) {
                // Aggiunta titolo: Contatto d'emergenza
                LabelCustom lblTitle = new LabelCustom("Contatto d'emergenza " + i + "*", gridPane, 4, 0, 2, 1);
                lblTitle.settingStyle("-fx-font-weight: bold;");
                new LabelCustom(strings, gridPane, 4, new int[]{1, 2, 3, 4});
                // Aggiunta TextField : Nome, cognome, email, telefono
                arrays[arrays.length - 12] = new TextFieldCustom("Nome*", gridPane, 5, 1);
                arrays[arrays.length - 11] = new TextFieldCustom("Cognome*", gridPane, 5, 2);
                arrays[arrays.length - 10] = new TextFieldCustom("Email*", gridPane, 5, 3);
                arrays[arrays.length - 9] = new TextFieldCustom("Telefono", gridPane, 5, 4);
            }
            if (i == 2) {
                // Aggiunta titolo: Contatto d'emergenza
                LabelCustom lblTitle = new LabelCustom("Contatto d'emergenza " + i, gridPane, 4, 5, 2, 1);
                lblTitle.settingStyle("-fx-font-weight: bold;");
                new LabelCustom(strings, gridPane, 4, new int[]{6, 7, 8, 9});
                // Aggiunta TextField : Nome, cognome, email, telefono
                arrays[arrays.length - 8] = new TextFieldCustom("Nome*", gridPane, 5, 6);
                arrays[arrays.length - 7] = new TextFieldCustom("Cognome*", gridPane, 5, 7);
                arrays[arrays.length - 6] = new TextFieldCustom("Email*", gridPane, 5, 8);
                arrays[arrays.length - 5] = new TextFieldCustom("Telefono", gridPane, 5, 9);
            }
            if (i == 3) {
                // Aggiunta titolo: Contatto d'emergenza
                LabelCustom lblTitle = new LabelCustom("Contatto d'emergenza " + i, gridPane, 4, 10, 2, 1);
                lblTitle.settingStyle("-fx-font-weight: bold;");
                new LabelCustom(strings, gridPane, 4, new int[]{11, 12, 13, 14});
                // Aggiunta TextField : Nome, cognome, email, telefono
                arrays[arrays.length - 4] = new TextFieldCustom("Nome*", gridPane, 5, 11);
                arrays[arrays.length - 3] = new TextFieldCustom("Cognome*", gridPane, 5, 12);
                arrays[arrays.length - 2] = new TextFieldCustom("Email*", gridPane, 5, 13);
                arrays[arrays.length - 1] = new TextFieldCustom("Telefono", gridPane, 5, 14);
            }
        }

        // Aggiunta testi
        int[] righe = new int[stringLabel.length];
        for (int i = 1; i < righe.length ; i++)
            righe[i - 1] = i;

        righe[stringLabel.length - 1] = 17;

        new LabelCustom(stringLabel, gridPane, 0, righe);

        int index;
        // Creazione campi: Nome, Cognome, Luogo di nascita, Data di nascita
        for (index = 1; index < 5; index++) {
            if (index == 4)
                arrays[index] = new DatePickerCustom(stringLabel[index - 1], gridPane, 1, index);
            else
                arrays[index] = new TextFieldCustom(stringLabel[index - 1], gridPane, 1, index);
        }

        // Creazione campo: Nazionalità
        MenuButtonCustom menuButtonNation = new MenuButtonCustom("Nazionalità", gridPane, 1, index);
        RadioMenuItem[] radioMenuItemsNation = menuButtonNation.radioMenuItems("nazioni.txt");
        arrays[index] = radioMenuItemsNation;

        index = index + 1; // = 6

        // Creazione campo: Indirizzo, Telefono, Email
        for (; index < 9; index++)
            arrays[index] = new TextFieldCustom(stringLabel[index - 1], gridPane, 1, index);

        // Creazione campo: Esperienze
        MenuButtonCustom menuButtonExperiences = new MenuButtonCustom("Esperienze", gridPane, 1, index);
        CheckMenuItem[] checkMenuItemsExperiences = menuButtonExperiences.checkMenuItems("lavori_precedenti.txt");
        arrays[index] = checkMenuItemsExperiences;

        index++; // = 10

        // Creazione campo: Lingue parlate
        MenuButtonCustom menuButtonLanguages = new MenuButtonCustom("Lingue", gridPane, 1, index);
        CheckMenuItem[] checkMenuItemsLanguages = menuButtonLanguages.checkMenuItems("nazionalita.txt");
        arrays[index] = checkMenuItemsLanguages;

        index++; // = 11

        // Creazione campo: Tipo patente
        MenuButtonCustom menuButtonDriveLicense = new MenuButtonCustom("Patenti", gridPane, 1, index);
        CheckMenuItem[] checkMenuItemsDriveLicense = menuButtonDriveLicense.checkMenuItems("patenti.txt");
        arrays[index] = checkMenuItemsDriveLicense;

        index++; // = 12

        // Creazione campo: Automunito
        arrays[index] = createRadioCarOwner(index, 1);

        index++; // = 13

        // Creazione campi: Date inizio e fine
        int rowIndex = index;
        for (; index < 21; index++) {
            if (index % 2 != 0) {
                if (index != 13)
                    rowIndex++;

                DatePickerCustom datePickerCustom = new DatePickerCustom(gridPane, 1, rowIndex);
                datePickerCustom.setPromptText("Data d'inizio");
                arrays[index] = datePickerCustom;
            } else {
                DatePickerCustom datePickerCustom = new DatePickerCustom(gridPane, 2, rowIndex);
                datePickerCustom.setPromptText("Data di fine");
                arrays[index] = datePickerCustom;
            }
        }
        rowIndex++; // = 17

        // Creazione campo: Zona/e
        MenuButtonCustom menuButtonZones = new MenuButtonCustom("Zone", gridPane, 1, rowIndex);
        CheckMenuItem[] checkMenuItemsZones = menuButtonZones.checkMenuItems("comuni.txt");
        arrays[index] = checkMenuItemsZones;

        //index++; // = 22
        //rowIndex++; // = 18

        // TODO DA RIMUOVERE; SOLO DEBUG
        ButtonCustom btnTest = new ButtonCustom("TEST", gridPane, 5, 15, 1, 1);
        btnTest.settingStyle("-fx-font-weight: bold;");
        Events.testWorkerEvent(btnTest.getButton(), arrays);

        return arrays;
    }

    /**
     * Metodo che definisce la composizione del GridPane della pagina di ricerca
     * @param primaryStage stage primario
     * @return Object[]
     */
    public Object[] addFieldsSearch(Stage primaryStage) {

        // Aggiunta bottone: INDIETRO
        ButtonCustom btnIndietro = new ButtonCustom("INDIETRO", gridPane, 0, 0, 3, 1);
        btnIndietro.settingStyle("-fx-font-weight: bold;");
        Events.homeEvent(btnIndietro.getButton(), primaryStage);

        Object[] arrays = new Object[37]; // 24 Radio button + 9 campi inserimento + 1 pulsante ricerca + 1 pulsante mostra
        int index = 0;

        for (int i = 1; i < 9; i++) {
            ToggleGroup toggleGroup = new ToggleGroup();
            RadioButtonCustom radioButtonCustom1 = new RadioButtonCustom("NONE", gridPane, 0, i);
            arrays[index] = radioButtonCustom1;
            index++;
            RadioButtonCustom radioButtonCustom2 = new RadioButtonCustom("AND", gridPane, 1, i);
            arrays[index] = radioButtonCustom2;
            index++;
            RadioButtonCustom radioButtonCustom3 = new RadioButtonCustom("OR", gridPane, 2, i);
            arrays[index] = radioButtonCustom3;
            index++;
            radioButtonCustom1.setMultiToggleGroup(toggleGroup, radioButtonCustom2.getRadioButton(), radioButtonCustom3.getRadioButton());
        }
        // Index = 24
        int indexRow = 1;

        // Aggiunta campo: Nome e Cognome
        index++;
        for (int i = 1; i <= 2; i++, indexRow++, index++)
            arrays[index] = new TextFieldCustom(stringLabel[i - 1], gridPane, 3, indexRow);

        // Index = 27
        // IndexRow = 2

        // Creazione campo: Lingue parlate
        MenuButtonCustom menuButtonLingueParlate = new MenuButtonCustom("Lingua", gridPane, 3, indexRow);
        RadioMenuItem[] radioMenuItemsLingueParlate = menuButtonLingueParlate.radioMenuItems("nazionalita.txt");
        arrays[index] = radioMenuItemsLingueParlate;

        index++;    // = 28
        indexRow++; // = 3

        // Creazione campo: Data inizio e data fine
        DatePickerCustom datePickerCustomInizio = new DatePickerCustom(gridPane, 3, indexRow);
        datePickerCustomInizio.setPromptText("Data d'inizio");
        arrays[index] = datePickerCustomInizio;
        index++; // = 29
        DatePickerCustom datePickerCustomFine = new DatePickerCustom(gridPane, 4, indexRow);
        datePickerCustomFine.setPromptText("Data di fine");
        arrays[index] = datePickerCustomFine;

        index++;    // = 30
        indexRow++; // = 4

        // Creazione campo: Mansione
        arrays[index] = new TextFieldCustom("Mansione", gridPane, 3, indexRow);

        index++;    // = 31
        indexRow++; // = 5

        // Creazione campo: Comune di residenza
        arrays[index] = new TextFieldCustom("Indirizzo", gridPane, 3, indexRow);

        index++;    // = 32
        indexRow++; // = 6

        // Creazione campo: Patenti
        MenuButtonCustom menuButtonTipoPatente = new MenuButtonCustom("Patente", gridPane, 3, indexRow);
        RadioMenuItem[] radioMenuItemsTipoPatente = menuButtonTipoPatente.radioMenuItems("patenti.txt");
        arrays[index] = radioMenuItemsTipoPatente;

        index++;    // = 33
        indexRow++; // = 7

        // Creazione campo: Automunito
        arrays[index] = createRadioCarOwner(indexRow, 3);

        index++;    // = 34
        indexRow++; // = 8

        // Imposta la base di ricerca
        // System.out.println(Business.getInstance().getWorkers());
        Business.setWorkersBackup(new HashSet<>(Business.getInstance().getWorkers()));

        ButtonCustom btnCerca = new ButtonCustom("CERCA", gridPane, 0, indexRow, 5, 1);
        btnCerca.settingStyle("-fx-font-weight: bold;");

        ButtonCustom btnMostraTutti = new ButtonCustom("MOSTRA TUTTI", gridPane, 0, ++indexRow, 5, 1);
        btnMostraTutti.settingStyle("-fx-font-weight: bold;");


        // Aggiunta label: Titolo tabella
        LabelCustom titolo = new LabelCustom("RISULTATI RICERCA", gridPane, 7, 0, 1, 1);
        titolo.settingStyle("-fx-font-weight: bold;");

        TableViewCustom tableViewCustom = new TableViewWorker(gridPane, strings, namesValueFactoryWorkers, 7, 1, 20, 20);
        tableViewCustom.setMaxSizeTableView(641.6, 471.2);

        tableViewStatic = tableViewCustom;

        Events.ricercaEvent(btnCerca.getButton(), primaryStage, arrays, tableViewCustom);
        arrays[index] = btnCerca;

        Events.mostraTuttiEvent(btnMostraTutti.getButton(), primaryStage, tableViewCustom);
        arrays[++index] = btnMostraTutti;

        arrays[++index] =  new LabelErrorCustom("Dati inseriti errati o incompleti!", gridPane, 0, ++indexRow, 5, 1);

        return arrays;
    }

    /**
     * Restituisce la TableView statica salvata
     * @return tableView
     */
    public static TableViewCustom getTableViewCustom(){
        return tableViewStatic;
    }

    /**
     * Metodo che definisce la composizione del GridPane della pagina di inserimneto del lavoro
     * @param primaryStage stage primario
     * @param worker       lavoratore stagionale
     * @return Object[]
     */
    public Object[] addFieldsInsertWork(Stage primaryStage, Worker worker) {
        // Pulsante INDIETRO
        ButtonCustom btnIndietro = new ButtonCustom("INDIETRO", gridPane, 0, 0, 1, 1);
        btnIndietro.settingStyle("-fx-font-weight: bold;");

        // Pulsante TEST
        ButtonCustom btnTest = new ButtonCustom("TEST", gridPane, 2, 0, 1, 1);
        btnTest.settingStyle("-fx-font-weight: bold;");

        Object[] objects2 = new Object[8];

        int index = 0;

        objects2[objects2.length - 1] = btnIndietro;

        for (int i = 1; i <= stringLabelIns.length; i++)
            new LabelCustom(stringLabelIns[i - 1], gridPane, 0, i);

        objects2[index] = new DatePickerCustom("Data d'inizio", gridPane, 1, index + 1);

        index++; // = 1
        objects2[index] = new DatePickerCustom("Data di fine", gridPane, 2, index);

        index++; // = 2
        objects2[index] = new TextFieldCustom("Nome azienda", gridPane, 1, index);

        index++; // = 3
        objects2[index] = new TextFieldCustom("Mansioni separate da \";\"", gridPane, 1, index);
        //((TextFieldCustom)objects2[index]).setTooltip("Inserire le mansioni separate da \";\"");

        index++; // = 4
        objects2[index] = new TextFieldCustom("Luogo di lavoro", gridPane, 1, index);

        index++; // = 5
        objects2[index] = new TextFieldCustom("Retribuzione lorda", gridPane, 1, index);

        index++; // = 6
        // Pulsante AGGIUNGI
        ButtonCustom btnAggiungiLavoro = new ButtonCustom("AGGIUNGI", gridPane, 0, index, 3, 1);
        btnAggiungiLavoro.settingStyle("-fx-font-weight: bold;");
        objects2[index] = btnAggiungiLavoro;

        index++; // = 7

        ButtonCustom btnFineLavoro = new ButtonCustom("FINE", gridPane, 0, index, 3, 1);
        btnFineLavoro.settingStyle("-fx-font-weight: bold;");
        Events.fineEvent(btnFineLavoro.getButton(), primaryStage, worker);

        index++; // = 8

        final LabelErrorCustom lblErroreAggiungi =  new LabelErrorCustom("Dati inseriti errati o incompleti!", gridPane, 0, index, 4, 1);

        // Aggiunta label: Titolo
        LabelCustom titolo = new LabelCustom("LAVORI STAGIONALI INSERITI", gridPane, 3, 0, 6, 1);
        titolo.settingStyle("-fx-font-weight: bold;");

        TableViewCustom tableViewCustom = new TableViewWork(gridPane, colNames, nameValueFactoryWorks, 3, 1, 13, 15);
        tableViewCustom.setMaxSizeTableView(1200, 1000);

        for (Work work : new HashSet<>(worker.getWorks())) {
            tableViewCustom.addItems(new Button("Elimina"),
                    (work.getPeriod())[0].toString(),
                    (work.getPeriod())[1].toString(),
                    work.getCompany(),
                    work.getTasks(),
                    work.getPlace(),
                    work.getPay(),
                    worker,
                    work
            );
        }

        // Definizione comportamento pulsante AGGIUNGI
        Events.aggiungiEvent(btnAggiungiLavoro.getButton(), objects2, worker, lblErroreAggiungi, tableViewCustom);

        // Tasto di debug
        Events.testWorkEvent(btnTest.getButton(), objects2);

        return objects2;
    }

    /**
     * Metodo che definisce la composizione del GridPane della pagina di inserimneto del lavoro
     * @param primaryStage stage primario
     * @param worker       lavoratore stagionale
     * @return Object[]
     */
    public Object[] addFieldsModifyWork(Stage primaryStage, Worker worker, Worker old) {
        // Pulsante INDIETRO
        ButtonCustom btnIndietro = new ButtonCustom("INDIETRO", gridPane, 0, 0, 1, 1);
        btnIndietro.settingStyle("-fx-font-weight: bold;");

        // Pulsante TEST
        ButtonCustom btnTest = new ButtonCustom("TEST", gridPane, 2, 0, 1, 1);
        btnTest.settingStyle("-fx-font-weight: bold;");

        Object[] objects2 = new Object[8];

        int index = 0;

        objects2[objects2.length - 1] = btnIndietro;

        for (int i = 1; i <= stringLabelIns.length; i++)
            new LabelCustom(stringLabelIns[i - 1], gridPane, 0, i);

        objects2[index] = new DatePickerCustom("Data d'inizio", gridPane, 1, index + 1);

        index++; // = 1
        objects2[index] = new DatePickerCustom("Data di fine", gridPane, 2, index);

        index++; // = 2
        objects2[index] = new TextFieldCustom("Nome azienda", gridPane, 1, index);

        index++; // = 3
        objects2[index] = new TextFieldCustom("Mansioni separate da \";\"", gridPane, 1, index);
        //((TextFieldCustom)objects2[index]).setTooltip("Inserire le mansioni separate da \";\"");

        index++; // = 4
        objects2[index] = new TextFieldCustom("Luogo di lavoro", gridPane, 1, index);

        index++; // = 5
        objects2[index] = new TextFieldCustom("Retribuzione lorda", gridPane, 1, index);

        index++; // = 6
        // Pulsante AGGIUNGI
        ButtonCustom btnAggiungiLavoro = new ButtonCustom("AGGIUNGI", gridPane, 0, index, 3, 1);
        btnAggiungiLavoro.settingStyle("-fx-font-weight: bold;");
        objects2[index] = btnAggiungiLavoro;

        index++; // = 7

        ButtonCustom btnFineLavoro = new ButtonCustom("AGGIORNA", gridPane, 0, index, 3, 1);
        btnFineLavoro.settingStyle("-fx-font-weight: bold;");

        index++; // = 8

        final LabelErrorCustom lblErroreAggiungi =  new LabelErrorCustom("Dati inseriti errati o incompleti!", gridPane, 0, index, 4, 1);

        // Aggiunta label: Titolo
        LabelCustom titolo = new LabelCustom("LAVORI STAGIONALI INSERITI", gridPane, 3, 0, 6, 1);
        titolo.settingStyle("-fx-font-weight: bold;");

        TableViewCustom tableViewCustom = new TableViewWork(gridPane, colNames, nameValueFactoryWorks, 3, 1, 13, 15);
        tableViewCustom.setMaxSizeTableView(1200, 1000);

        if (worker.getWorks().size() != 0) {
            for (Work work : worker.getWorks()) {
                tableViewCustom.addItems(new Button("Elimina"),
                        (work.getPeriod())[0].toString(),
                        (work.getPeriod())[1].toString(),
                        work.getCompany(),
                        work.getTasks(),
                        work.getPlace(),
                        work.getPay(),
                        worker,
                        work
                );
            }
        } else {
            for (Work work : new HashSet<>(old.getWorks())) {
                worker.addWork(work);
                tableViewCustom.addItems(new Button("Elimina"),
                        (work.getPeriod())[0].toString(),
                        (work.getPeriod())[1].toString(),
                        work.getCompany(),
                        work.getTasks(),
                        work.getPlace(),
                        work.getPay(),
                        worker,
                        work
                );
            }
        }

        // Definizione comportamento pulsante AGGIUNGI
        Events.aggiungiModifyEvent(btnAggiungiLavoro.getButton(), objects2, worker, lblErroreAggiungi, tableViewCustom);

        Events.aggiornaEvent(btnFineLavoro.getButton(), primaryStage, worker, old);

        // Tasto di debug
        Events.testWorkEvent(btnTest.getButton(), objects2);

        return objects2;
    }

    /**
     * Impostazione dei fields nella schermata di gestione presentata all'admin
     * @param primaryStage stage primario
     * @return Object[]
     */
    public Object[] addFieldsAdmin (Stage primaryStage) {
        Object[] objects = new Object[12];
        int index = 0;

        // Creazione labels
        for (int i = 1; i <= labelsAdmin.length; i++)
            new LabelCustom(labelsAdmin[i - 1], gridPane, 0, i);

        // Tasto: INDIETRO
        objects[index] = new ButtonCustom("INDIETRO", gridPane, 0, 0, 1, 1);
        // Stile del bottone
        ((ButtonCustom) objects[index]).settingStyle("-fx-font-weight: bold;");
        // Definizione comportamento tasto, torna alla pagina Login
        ((ButtonCustom) objects[index]).getButton().setOnAction(actionEvent -> WindowFactory.getWindow("LOGIN").createWindow(primaryStage, null, null, null));

        index ++; // = 1

        // Campo: NOME
        objects[index] = new TextFieldCustom("Nome", gridPane, 1, index, 2, 1);

        index ++; // = 2

        // Campo: COGNOME
        objects[index] = new TextFieldCustom("Cognome", gridPane, 1, index, 2, 1);

        index ++; // = 3

        // Campo: LUOGO DI NASCITA
        objects[index] = new TextFieldCustom("Luogo di nascita", gridPane, 1, index, 2, 1);

        index ++; // = 4

        // Campo: DATA DI NASCITA
        objects[index] = new DatePickerCustom("Data di nascita", gridPane, 1, index);

        index ++; // = 5

        // Campo: NAZIONALITÀ
        MenuButtonCustom menuButtonNazionalita = new MenuButtonCustom("Nazionalità", gridPane, 1, index, 2, 1);
        RadioMenuItem[] radioMenuItemsNazionalita = menuButtonNazionalita.radioMenuItems("nazioni.txt");
        objects[index] = radioMenuItemsNazionalita;

        index ++; // = 6

        // Campo: EMAIL
        objects[index] = new TextFieldCustom("Email", gridPane, 1, index, 2, 1);

        index ++; // = 7

        // Campo: TELEFONO
        objects[index] = new TextFieldCustom("Telefono", gridPane, 1, index, 2, 1);

        index ++; // = 8

        // Campo: PASSWORD
        objects[index] = new TextFieldCustom("Password", gridPane, 1, index, 2, 1);

        index ++; // = 9

        // Tasto errore
        objects[index] = new LabelErrorCustom("Errore", gridPane, 0, index + 2, 4, 1);

        index ++; // = 10

        final String[] labelsTableResponsabile = {
                "Nome",
                "Cognome",
                "Luogo di nascita",
                "Data di nascita",
                "Nazionalità",
                "Email",
                "Telefono",
                "Modifica",
                "Elimina"
        };

        final String[] nomiValueFactoryResponsabile = {
                "nome",
                "cognome",
                "luogoDiNascita",
                "dataDiNascita",
                "nazionalita",
                "email",
                "telefono",
                "btnModify",
                "btnDelete"
        };

        // Titolo tabella
        new LabelCustom("Responsabili", gridPane, 3, 0, 2, 1).settingStyle("-fx-font-weight: bold;");
        // Creazione tabella visiva
        TableViewCustom tableViewCustom = new TableViewManager(gridPane, labelsTableResponsabile, nomiValueFactoryResponsabile, 3, 1, 3, 20);
        // Aggiunta dei responsabili
        addLabelsManagers(primaryStage, tableViewCustom);
        tableViewCustom.setMaxSizeTableView(721.6, 445.6);

        // Tasto: SALVA
        objects[index] = new ButtonCustom("SALVA", gridPane, 0, index - 1, 3, 1);
        // Stile del bottone
        ((ButtonCustom) objects[index]).settingStyle("-fx-font-weight: bold;");

        // Definizione di una shortcut (INVIO) da tastiera per premere il tasto LOGIN
        int finalIndex = index;
        primaryStage.getScene().getRoot().setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                ((ButtonCustom) objects[finalIndex]).getButton().fire();
            }
        });

        index++; // = 11
        objects[index] = tableViewCustom;

        // Definizione comportamento tasto, salva il dipendente e lo aggiunge alla tabella
        Events.salvaManagerEvent(((ButtonCustom) objects[index - 1]).getButton(), primaryStage, objects);

        return objects;
    }

    public Object[] addFieldsModifyManager(Stage primaryStage, Manager manager) {
        Object[] objects = new Object[12];
        int index = 0;

        // Creazione labels
        for (int i = 1; i <= labelsAdmin.length; i++)
            new LabelCustom(labelsAdmin[i - 1], gridPane, 0, i);

        // Tasto: INDIETRO
        objects[index] = new ButtonCustom("INDIETRO", gridPane, 0, 0, 1, 1);
        // Stile del bottone
        ((ButtonCustom) objects[index]).settingStyle("-fx-font-weight: bold;");
        // Definizione comportamento tasto, torna alla pagina Admin
        ((ButtonCustom) objects[index]).getButton().setOnAction(actionEvent -> WindowFactory.getWindow("ADMIN").createWindow(primaryStage, null, null, null));

        index ++; // = 1

        // Campo: NOME
        objects[index] = new TextFieldCustom("Nome", gridPane, 1, index, 2, 1);

        index ++; // = 2

        // Campo: COGNOME
        objects[index] = new TextFieldCustom("Cognome", gridPane, 1, index, 2, 1);

        index ++; // = 3

        // Campo: LUOGO DI NASCITA
        objects[index] = new TextFieldCustom("Luogo di nascita", gridPane, 1, index, 2, 1);

        index ++; // = 4

        // Campo: DATA DI NASCITA
        objects[index] = new DatePickerCustom("Data di nascita", gridPane, 1, index);

        index ++; // = 5

        // Campo: NAZIONALITÀ
        MenuButtonCustom menuButtonNazionalita = new MenuButtonCustom("Nazionalità", gridPane, 1, index, 2, 1);
        RadioMenuItem[] radioMenuItemsNazionalita = menuButtonNazionalita.radioMenuItems("nazioni.txt");
        objects[index] = radioMenuItemsNazionalita;

        index ++; // = 6

        // Campo: EMAIL
        objects[index] = new TextFieldCustom("Email", gridPane, 1, index, 2, 1);

        index ++; // = 7

        // Campo: TELEFONO
        objects[index] = new TextFieldCustom("Telefono", gridPane, 1, index, 2, 1);

        index ++; // = 8

        // Campo: PASSWORD
        objects[index] = new TextFieldCustom("(Invariata)", gridPane, 1, index, 2, 1);

        index ++; // = 9

        // Label: errore
        objects[index] = new LabelErrorCustom("Errore", gridPane, 0, index + 2, 4, 1);

        index ++; // = 10
        // Tasto: SALVA
        objects[index] = new ButtonCustom("MODIFICA", gridPane, 0, index - 1, 3, 1);
        // Stile del bottone
        ((ButtonCustom) objects[index]).settingStyle("-fx-font-weight: bold;");

        // Definizione di una shortcut (INVIO) da tastiera per premere il tasto SALVA
        int finalIndex = index;
        primaryStage.getScene().getRoot().setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                ((ButtonCustom) objects[finalIndex]).getButton().fire();
            }
        });

        // Imposta il comportamento del bottone modificando il responsabile
        Events.modificaManagerEvent(((ButtonCustom) objects[index]).getButton(), primaryStage, objects, manager);

        return objects;
    }

    /**
     * Aggiunge i risultati nella tabella dei responsabili (solo l'admin può vederla!).
     *
     * @param primaryStage    stage primario
     * @param tableViewCustom tabella
     */
    public void addLabelsManagers(Stage primaryStage, TableViewCustom tableViewCustom) {

        tableViewCustom.getTableView().getItems().clear();

        int index;
        Object[] objects = new Object[11];

        for (Manager manager : Business.getInstance().getManagers()) {
            index = 0;

            objects[index] = manager.getName();                  // Nome
            objects[++index] = manager.getSurName();             // Cognome
            objects[++index] = manager.getBirthPlace();      // Luogo di nascita
            objects[++index] = manager.getBirthDay().toString();  // Data di nascita
            objects[++index] = manager.getNation();         // Nazionalita
            objects[++index] = manager.getEmail();               // Email
            objects[++index] = manager.getPhone();            // Telefono
            objects[++index] = new Button("MODIFICA");             // Bottone Modifica
            objects[++index] = new Button("ELIMINA");              // Bottone Elimina
            objects[++index] = manager;                          // Oggetto Responsabile
            objects[++index] = primaryStage;                          // Oggetto Stage

            tableViewCustom.addItems(objects);
        }

    }

    /**
     * Aggiunta dei vari risultati di ricerca
     *
     * @param primaryStage stage primario
     */
    public void addLabelsSearch(Stage primaryStage, TableViewCustom tableViewCustom) {

        tableViewCustom.getTableView().getItems().clear();

        // TODO da inserire
        int index;
        Object[] objects = new Object[10];

        for (Worker worker : Business.getWorkersBackup()) {
            //new LabelCustom(numRisultato.toString(), gridPane, index, riga);      // Numero risultato

            index = 0;

            Button btnModifica = new Button("MODIFICA");
            Button btnElimina = new Button("ELIMINA");

            objects[index] = worker.getName();                  // Nome
            objects[++index] = worker.getSurName();             // Cognome
            objects[++index] = worker.getLanguages();              // Lingua/e
            objects[++index] = worker.getAddress();           // Indirizzo
            objects[++index] = worker.getDriveLicense();         // Patente/i
            objects[++index] = worker.getCarOwner();          // Automunito
            objects[++index] = btnModifica;                         // Bottone Modifica
            objects[++index] = btnElimina;                          // Bottone Elimina
            objects[++index] = worker;                          // Oggetto Lavoratore
            objects[++index] = primaryStage;

            tableViewCustom.addItems(objects);
        }

    }

    /**
     * Metodo per creare un menù a tendina a scelta singola (Sì/No) per automunito
     * @param index indice
     * @param col   colonna
     * @return menù a tendina
     */
    private RadioMenuItem[] createRadioCarOwner(int index, int col) {
        MenuButton menuButtonAutomunito = new MenuButton("Automunito?");
        menuButtonAutomunito.setMaxHeight(Double.MAX_VALUE);
        menuButtonAutomunito.setMaxWidth(Double.MAX_VALUE);

        RadioMenuItem[] radioMenuItemAutomunito = new RadioMenuItem[2];

        ToggleGroup toggleGroupAutomunito = new ToggleGroup();

        radioMenuItemAutomunito[0] = new RadioMenuItem("Sì");
        toggleGroupAutomunito.getToggles().add(radioMenuItemAutomunito[0]);
        menuButtonAutomunito.getItems().add(radioMenuItemAutomunito[0]);

        radioMenuItemAutomunito[1] = new RadioMenuItem("No");
        toggleGroupAutomunito.getToggles().add(radioMenuItemAutomunito[1]);
        menuButtonAutomunito.getItems().add(radioMenuItemAutomunito[1]);

        gridPane.add(menuButtonAutomunito, col, index);

        return radioMenuItemAutomunito;
    }
}