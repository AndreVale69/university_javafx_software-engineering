package com.univr.graphics;

import com.univr.anagrafica.*;
import com.univr.graphics.components.custom.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.HashSet;

/**
 * Classe per la creazione e la gestione dei GridPane
 */
public class GridPaneCustom {
    private GridPane gridPane = new GridPane();
    private int numRisultatoLavoriStagionali = 0;
    private TextField[] textFields;
    private final String[] stringLabel = {
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
    private final String[] stringLabelIns = {
            "Periodo",
            "Nome dell'azienda",
            "Mansioni svolte",
            "Luogo di lavoro",
            "Retribuzione lorda giornaliera"
    };

    /**
     * Costruttore di GridPaneCustom, definisce i margini e la spaziatura
     * @param hGap
     * @param vGap
     */
    public GridPaneCustom(double hGap, double vGap) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(hGap);
        gridPane.setVgap(vGap);
    }

    /**
     * Costruttore di GridPaneCustom, "converte" un GridPane in GridPaneCustom
     * @param gridPane
     */
    public GridPaneCustom(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    /**
     * Metodo utilizzato per ottenere il GridPane
     * @return
     */
    public GridPane getGridPane () {
        return gridPane;
    }

    /**
     * Metodo che definisce la composizione del GridPane della pagina di inserimento
     * @param primaryStage
     * @return
     */
    public Object[] addFieldsInserimento (Stage primaryStage) {
        Object[] arrays = new Object[23 + 4 + 4 + 4];

        // Aggiunta bottone: INDIETRO
        ButtonCustom btnIndietro = new ButtonCustom("INDIETRO", gridPane, 0, 0, 1, 1);
        btnIndietro.settingStyle("-fx-font-weight: bold;");
        btnIndietro.homeEvent(primaryStage);
        arrays[0] = btnIndietro;

        // Aggiunta label: Obbligatorio
        LabelErrorCustom lblErroreObbl = new LabelErrorCustom("Campo obbligatorio *", gridPane, 1, 0, 1, 1);
        lblErroreObbl.activatingError();

        /*
         * CONTATTO D'EMEGENZA
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
        MenuButtonCustom menuButtonNazionalita = new MenuButtonCustom("Nazionalità", gridPane, 1, index);
        RadioMenuItem[] radioMenuItemsNazionalita = menuButtonNazionalita.radioMenuItems("nazioni.txt");
        arrays[index] = radioMenuItemsNazionalita;

        index = index + 1; // = 6

        // Creazione campo: Indirizzo, Telefono, Email
        for (; index < 9; index++)
            arrays[index] = new TextFieldCustom(stringLabel[index - 1], gridPane, 1, index);

        // Creazione campo: Esperienze
        MenuButtonCustom menuButtonEsperienze = new MenuButtonCustom("Esperienze", gridPane, 1, index);
        CheckMenuItem[] checkMenuItemsEsperienze = menuButtonEsperienze.checkMenuItems("lavori_precedenti.txt");
        arrays[index] = checkMenuItemsEsperienze;

        index = index + 1; // = 10

        // Creazione campo: Lingue parlate
        MenuButtonCustom menuButtonLingueParlate = new MenuButtonCustom("Lingue", gridPane, 1, index);
        CheckMenuItem[] checkMenuItemsLingueParlate = menuButtonLingueParlate.checkMenuItems("nazionalita.txt");
        arrays[index] = checkMenuItemsLingueParlate;

        index = index + 1; // = 11

        // Creazione campo: Tipo patente
        MenuButtonCustom menuButtonTipoPatente = new MenuButtonCustom("Patenti", gridPane, 1, index);
        CheckMenuItem[] checkMenuItemsTipoPatente = menuButtonTipoPatente.checkMenuItems("patenti.txt");
        arrays[index] = checkMenuItemsTipoPatente;

        index = index + 1; // = 12

        // Creazione campo: Automunito
        arrays[index] = creaAutomunito(index, 1);

        index = index + 1; // = 13

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
        rowIndex = rowIndex + 1; // = 17

        // Creazione campo: Zona/e
        MenuButtonCustom menuButtonZona = new MenuButtonCustom("Zone", gridPane, 1, rowIndex);
        CheckMenuItem[] checkMenuItemsZona = menuButtonZona.checkMenuItems("comuni.txt");
        arrays[index] = checkMenuItemsZona;

        index = index + 1; // = 22
        rowIndex = rowIndex + 1; // = 18

        final LabelErrorCustom lblErroreSalva =  new LabelErrorCustom("Dati inseriti errati o incompleti!", gridPane, 3, 18, 4, 1);

        // Creazione bottone: SALVA
        ButtonCustom btnAvanti = new ButtonCustom("AVANTI", gridPane, 0, rowIndex, 3, 1);
        btnAvanti.settingStyle("-fx-font-weight: bold;");
        btnAvanti.avantiEvent(primaryStage, arrays, lblErroreSalva);
        arrays[index] = btnAvanti;

        // TODO DA RIMUOVERE; SOLO DEBUG
        ButtonCustom btnTest = new ButtonCustom("TEST", gridPane, 5, 15, 1, 1);
        btnTest.settingStyle("-fx-font-weight: bold;");
        btnTest.testEvent(primaryStage, arrays);

        return arrays;
    }

    /**
     * Metodo che definisce la composizione del GridPane della pagina di ricerca
     * @param primaryStage
     * @return
     */
    public Object[] addFieldsAnagrafica (Stage primaryStage) {

        // Aggiunta bottone: INDIETRO
        ButtonCustom btnIndietro = new ButtonCustom("INDIETRO", gridPane, 0, 0, 3, 1);
        btnIndietro.settingStyle("-fx-font-weight: bold;");
        btnIndietro.homeEvent(primaryStage);

        Object[] arrays = new Object[32]; // 21 Radio button + 9 campi inserimento + 1 bottone ricerca
        int index = 0;

        for (int i = 2; i < 9; i++) {
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
        // Index = 21
        int indexRow = 1;

        // Aggiunta campo: Nome e Cognome
        index++;
        for (int i = 1; i <= 2; i++, indexRow++, index++)
            arrays[index] = new TextFieldCustom(stringLabel[i - 1], gridPane, 3, indexRow);

        // Index = 24
        // IndexRow = 2

        // Creazione campo: Lingue parlate
        MenuButtonCustom menuButtonLingueParlate = new MenuButtonCustom("Lingua", gridPane, 3, indexRow);
        RadioMenuItem[] radioMenuItemsLingueParlate = menuButtonLingueParlate.radioMenuItems("nazionalita.txt");
        arrays[index] = radioMenuItemsLingueParlate;

        index = index + 1;    // = 25
        indexRow = indexRow + 1; // = 3

        // Creazione campo: Data inizio e data fine
        DatePickerCustom datePickerCustomInizio = new DatePickerCustom(gridPane, 3, indexRow);
        datePickerCustomInizio.setPromptText("Data d'inizio");
        arrays[index] = datePickerCustomInizio;
        index = index + 1; // = 26
        DatePickerCustom datePickerCustomFine = new DatePickerCustom(gridPane, 4, indexRow);
        datePickerCustomFine.setPromptText("Data di fine");
        arrays[index] = datePickerCustomFine;

        index = index + 1;    // = 27
        indexRow = indexRow + 1; // = 4

        // Creazione campo: Mansione
        arrays[index] = new TextFieldCustom("Mansione", gridPane, 3, indexRow);

        index = index + 1;    // = 28
        indexRow = indexRow + 1; // = 5

        // Creazione campo: Comune di residenza
        MenuButtonCustom menuButtonZona = new MenuButtonCustom("Comune di Residenza", gridPane, 3, indexRow);
        RadioMenuItem[] radioMenuItemsZona = menuButtonZona.radioMenuItems("comuni.txt");
        arrays[index] = radioMenuItemsZona;

        index = index + 1;    // = 29
        indexRow = indexRow + 1; // = 6

        // Creazione campo: Patenti
        MenuButtonCustom menuButtonTipoPatente = new MenuButtonCustom("Patente", gridPane, 3, indexRow);
        RadioMenuItem[] radioMenuItemsTipoPatente = menuButtonTipoPatente.radioMenuItems("patenti.txt");
        arrays[index] = radioMenuItemsTipoPatente;

        index = index + 1;    // = 30
        indexRow = indexRow + 1; // = 7

        // Creazione campo: Automunito
        arrays[index] = creaAutomunito(indexRow, 3);

        index = index + 1;    // = 31
        indexRow = indexRow + 1; // = 8

        // Imposta la base di ricerca
        System.out.println(Agenzia.getInstance().getLavoratori());
        Agenzia.setLavoratoriBackup(new HashSet<Lavoratore>(Agenzia.getInstance().getLavoratori()));

        ButtonCustom btnCerca = new ButtonCustom("CERCA", gridPane, 0, indexRow, 5, 1);
        btnCerca.settingStyle("-fx-font-weight: bold;");

        // Creazione tabella
        String[] strings = {
                "Nome",
                "Cognome",
                "Lingua/e",
                "Comune di residenza",
                "Patente/i",
                "Automunito?",
                "Modifica",
                "Elimina"
        };

        String[] nomiValueFactory = {"nome",
                "cognome",
                "lingue",
                "residenza",
                "patenti",
                "automunito",
                "btnModify",
                "btnDelete"
        };

        // Aggiunta label: Titolo tabella
        LabelCustom titolo = new LabelCustom("RISULTATI RICERCA", gridPane, 7, 0, 1, 1);
        titolo.settingStyle("-fx-font-weight: bold;");

        TableViewCustom tableViewCustom = new TableViewLavoratore(gridPane, strings, nomiValueFactory, 7, 1, 20, 20);
        tableViewCustom.setMaxSizeTableView(1366, 768);

        btnCerca.ricercaEvent(primaryStage, arrays, tableViewCustom);
        arrays[index] = btnCerca;

        return arrays;
    }

    /**
     * Metodo che definisce la composizione del GridPane della pagina di inserimneto del lavoro
     * @param primaryStage
     * @param lavoratore
     * @return
     */
    public Object[] addFieldsInserimentoLavoro (Stage primaryStage, Lavoratore lavoratore) {
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
        objects2[index] = new TextFieldCustom("Mansione svolta", gridPane, 1, index);

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
        btnFineLavoro.fineEvent(primaryStage, objects2, lavoratore);

        index++; // = 8

        final LabelErrorCustom lblErroreAggiungi =  new LabelErrorCustom("Dati inseriti errati o incompleti!", gridPane, 0, index, 4, 1);

        // Array contenente i nomi delle colonne
        String[] colNames = {
                "Elimina",
                "Periodo inizio",
                "Periodo fine",
                "Azienda",
                "Mansioni",
                "Luogo",
                "Retribuzione"
        };
        // Array contenente i tipi di dato delle colonne
        String[] nomiValueFactory = {"btnDelete",
                "periodoInizio",
                "periodoFine",
                "nomeAzienda",
                "mansioniSvolte",
                "luogoLavoro",
                "retribuzione"};

        // Aggiunta label: Titolo
        LabelCustom titolo = new LabelCustom("LAVORI STAGIONALI INSERITI", gridPane, 3, 0, 6, 1);
        titolo.settingStyle("-fx-font-weight: bold;");

        index++; // = 9

        TableViewCustom tableViewCustom = new TableViewLavoro(gridPane, colNames, nomiValueFactory, 3, 1, 13, 15);
        tableViewCustom.setMaxSizeTableView(1200, 1000);

        for (Lavoro lavoro : Lavoro.getLavoriBackup()) {
            tableViewCustom.addItems(new Button("Elimina"),
                    (lavoro.getPeriodo())[0].toString(),
                    (lavoro.getPeriodo())[1].toString(),
                    lavoro.getAzienda(),
                    lavoro.getMansione(),
                    lavoro.getLuogo(),
                    lavoro.getRetribuzione(),
                    lavoratore,
                    lavoro
            );
        }

        // Definizione comportamento pulsante AGGIUNGI
        btnAggiungiLavoro.aggiungiEvent(primaryStage, objects2, lavoratore, lblErroreAggiungi, tableViewCustom);

        // Tasto di debug
        btnTest.testLavoroEvent(primaryStage, objects2);

        return objects2;
    }

    /**
     * Aggiunta dei soli label della pagina di ricerca
     * @param primaryStage
     */
    public void addLabelRicerca(Stage primaryStage) {


        /*
        int colonna = 7;
        final int riga = 1;

        String[] strings = {
                "Nome",
                "Cognome",
                "Lingua/e",
                "Comune di residenza",
                "Patente/i",
                "Automunito?"
        };

        // Aggiunta label: Titolo
        LabelCustom titolo = new LabelCustom("RISULTATI RICERCA", gridPane, colonna, 0, 6, 1);
        titolo.settingStyle("-fx-font-weight: bold;");

        // Aggiunta label: Nome, Cognome, Lingua/e, Comune di residenza, Patente/i, Automunito?
        while (colonna != 13) {
            new LabelCustom(strings[colonna - 7], gridPane, colonna, riga);
            colonna++;
        }
        */
    }

    /**
     * Aggiunta dei vari risultati di ricerca
     * @param primaryStage
     * @return
     */
    public Object[] addLabelsRisultatiRicerca (Stage primaryStage, TableViewCustom tableViewCustom) {

        tableViewCustom.getTableView().getItems().clear();

        // TODO da inserire
        int index = 0;
        int riga = 2;
        Integer numRisultato = 0;
        String convertBool;
        Object[] objects = new Object[9];

        for (Lavoratore lavoratore : Agenzia.getLavoratoriBackup()) {
            //new LabelCustom(numRisultato.toString(), gridPane, index, riga);      // Numero risultato

            index = 0;

            Button btnModifica = new Button("MODIFICA");
            Button btnElimina = new Button("ELIMINA");

            objects[index] = lavoratore.getNome();                  // Nome
            objects[++index] = lavoratore.getCognome();             // Cognome
            objects[++index] = lavoratore.getLingue();              // Lingua/e
            objects[++index] = lavoratore.getIndirizzo();           // Indirizzo
            objects[++index] = lavoratore.getTipoPatente();         // Patente/i
            objects[++index] = lavoratore.getAutomunito();          // Automunito
            objects[++index] = btnModifica;                         // Bottone Modifica
            objects[++index] = btnElimina;                          // Bottone Elimina
            objects[++index] = lavoratore;                          // Oggetto Lavoratore

            tableViewCustom.addItems(objects);
            /*
            index++;
            new LabelCustom(lavoratore.getNome(), gridPane, index, riga);         // Nome
            index++;
            new LabelCustom(lavoratore.getCognome(), gridPane, index, riga);      // Cognome
            index++;
            new LabelCustom(lavoratore.getLingue(), gridPane, index, riga);       // Lingua/e
            index++;
            new LabelCustom(lavoratore.getIndirizzo(), gridPane, index, riga);    // Indirizzo
            index++;
            new LabelCustom(lavoratore.getTipoPatente(), gridPane, index, riga);  // Patente/i
            index++;
            convertBool = lavoratore.getAutomunito().toString().equals("Sì") ? "Sì" : "No";
            new LabelCustom(convertBool, gridPane, index, riga);                  // Automunito?

            numRisultato ++;
            index = 6;
            riga++;*/
        }

        return null;
    }

    /**
     * Metodo per creare un menù a tendina a scelta singola (Sì/No) per automunito
     * @param index
     * @param colonna
     * @return
     */
    private RadioMenuItem[] creaAutomunito(int index, int colonna) {
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

        gridPane.add(menuButtonAutomunito, colonna, index);

        return radioMenuItemAutomunito;
    }

    /*
    public TextField addTextField (int colonna, int riga) {
        final TextField textField = new TextField();
        gridPane.add(textField, colonna, riga);
        return textField;
    }

    public TextField[] addMultiTextField (int[] colonna, int[] riga) {
        textFields = new TextField[colonna.length];
        for (int i = 0; i < colonna.length; i++)
            textFields[i] = addTextField(colonna[i], riga[i]);

        return textFields;
    }

    public TextField[] addMultiTextField (int colonna, int[] riga) {
        textFields = new TextField[riga.length];
        for (int i = 0; i < riga.length; i++)
            textFields[i] = addTextField(colonna, riga[i]);

        return textFields;
    }

    public TextField[] addMultiTextField (int[] colonna, int riga) {
        textFields = new TextField[colonna.length];
        for (int i = 0; i < colonna.length; i++)
            textFields[i] = addTextField(colonna[i], riga);

        return textFields;
    }
    */
}