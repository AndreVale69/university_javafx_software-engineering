package com.univr.graphics.components.custom;

import com.univr.anagrafica.*;
import com.univr.graphics.GridPaneCustom;
import com.univr.graphics.Popup;
import com.univr.graphics.components.window.Window;
import com.univr.graphics.components.window.WindowFactory;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.function.Predicate;

/**
 * Classe che contiene le fasi di creazione e il comportamento di tutti i pulsanti
 */
public class ButtonCustom{
    private final Button button = new Button();

    /**
     * Metodo utilizzato per inserire pulsante in un GridPane
     * @param stringa testo del pulsante
     * @param gridPane
     * @param colonna
     * @param riga
     */
    public ButtonCustom (String stringa, GridPaneCustom gridPane, int colonna, int riga) {
        button.setText(stringa);
        button.setMaxHeight(Double.MAX_VALUE);
        button.setMaxWidth(Double.MAX_VALUE);
        gridPane.getGridPane().add(button, colonna, riga);
    }

    /**
     * Metodo utilizzato per inserire pulsante in un GridPane, imposta il numero di colonne/righe occupate dal pulsante
     * @param stringa
     * @param gridPane
     * @param colonna
     * @param riga
     * @param extendColonne
     * @param extendRighe
     */
    public ButtonCustom (String stringa, GridPane gridPane, int colonna, int riga, int extendColonne, int extendRighe) {
        button.setText(stringa);
        button.setMaxHeight(Double.MAX_VALUE);
        button.setMaxWidth(Double.MAX_VALUE);
        gridPane.add(button, colonna, riga, extendColonne, extendRighe);
    }

    /**
     * Metodo utilizzato per impostare lo stile del pulsante
     * @param stile
     */
    public void settingStyle (String stile) {
        button.setStyle(stile);
    }

    /**
     * Metodo per la definizione del comportamento del pulsante login
     * @param emailField
     * @param passwordField
     * @param lblErroreLogin
     * @param primaryStage
     * @return
     */
    public void loginEvent (TextFieldCustom emailField, PasswordFieldCustom passwordField, LabelErrorCustom lblErroreLogin, Stage primaryStage) {

        // Definizione di una shortcut (INVIO) da tastiera per premere il tasto LOGIN
        primaryStage.getScene().getRoot().setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                button.fire();
            }
        });

        button.setOnAction(actionEvent -> {
            // Verifica delle credenziali di accesso
            if (Agenzia.accesso(emailField.getText(), passwordField.getText())) {
                WindowFactory.getWindow("SELECT").createWindow(primaryStage, null);
            }
            else {
                // In caso di errore d'inserimento, messaggio di errore
                lblErroreLogin.activatingError();
                Popup.display(lblErroreLogin.getText());
            }
        });
    }

    /**
     * Metodo per la definizione del comportamento del pulsante inserimento
     * @param primaryStage
     */
    public void inserimentoEvent (Stage primaryStage) {
        button.setOnAction(actionEvent -> WindowFactory.getWindow("INSERT").createWindow(primaryStage, null));
    }

    /**
     * Metodo per la definizione del comportamento del pulsante ricerca anagrafiche
     * @param primaryStage
     */
    public void anagraficaEvent (Stage primaryStage) {
        button.setOnAction(actionEvent -> WindowFactory.getWindow("SEARCH").createWindow(primaryStage, null));
    }

    /**
     * Metodo per effettuare logout
     * @param primaryStage
     */
    public void logoutEvent (Stage primaryStage) {
        button.setOnAction(actionEvent -> WindowFactory.getWindow("LOGIN").createWindow(primaryStage, null));
    }

    /**
     * Metodo per la definizione del comportamento del pulsante indietro per il ritorno alla home
     * @param primaryStage
     */
    public void homeEvent(Stage primaryStage) {
        button.setOnAction(actionEvent -> WindowFactory.getWindow("SELECT").createWindow(primaryStage, null));
    }

    /**
     * Metodo per la definizione del comportamento del pulsante indietro per il ritorno alla schermata di inserimento
     * @param primaryStage
     */
    public void indietroEvent (Stage primaryStage, Lavoratore lavoratore) {
        button.setOnAction(actionEvent -> {

            Window window = WindowFactory.getWindow("INSERT");
            window.createWindow(primaryStage, null);
            Object[] objects = window.getObjects();

            int index = 0;

            ((TextFieldCustom) objects[++index]).setText(lavoratore.getNome());                     // Nome (1)

            ((TextFieldCustom) objects[++index]).setText(lavoratore.getCognome());                  // Cognome (2)

            ((TextFieldCustom) objects[++index]).setText(lavoratore.getLuogoDiNascita());           // Luogo di nascita (3)

            ((DatePickerCustom) objects[++index]).setDatePicker(
                    LocalDate.parse(lavoratore.getNascita().toString(),
                            DateTimeFormatter.ofPattern("dd/MM/yyyy")));                            // Data di nascita (4)

            selectRadio((RadioMenuItem[]) objects[++index], lavoratore.getNazionalita());           // Nazionalità (5)

            ((TextFieldCustom) objects[++index]).setText(lavoratore.getIndirizzo());                // Indirizzo (6)

            ((TextFieldCustom) objects[++index]).setText(lavoratore.getTelefono());                 // Telefono: opzionale (7)

            ((TextFieldCustom) objects[++index]).setText(lavoratore.getEmail());                    // Email (8)

            selectCheck((CheckMenuItem[]) objects[++index], lavoratore.getEsperienzeLavorative());  // Esperienze: opzionale (9)

            selectCheck((CheckMenuItem[]) objects[++index], lavoratore.getLingue());                // Lingue (10)

            selectCheck((CheckMenuItem[]) objects[++index], lavoratore.getTipoPatente());           // Patenti (11)

            selectRadio(
                    (RadioMenuItem[]) objects[++index],
                    (lavoratore.getAutomunito() ? "Sì" : "No"));                                    // Automunito (12)

            // Periodi
            index++; // = 13
            for (int flag = 0, counter = 0; index <= 20 && flag == 0; index += 2, counter++) {
                if (lavoratore.getDates()[counter][0] == null)
                    flag = 1;
                else {
                    // Data inizio periodo
                    ((DatePickerCustom) objects[index]).setDatePicker(LocalDate.parse(
                            lavoratore.getDates()[counter][0].toString(),
                            DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    // Data fine periodo
                    ((DatePickerCustom) objects[index + 1]).setDatePicker(LocalDate.parse(
                            lavoratore.getDates()[counter][1].toString(),
                            DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                }
            }

            index = 20;

            selectCheck((CheckMenuItem[]) objects[++index], lavoratore.getZone());          // Zona/e (21)

            // Array di contatti d'emergenza inseriti
            Object[] contattiEmergenza = (new Object[12]);
            System.arraycopy(objects, 23, contattiEmergenza, 0, 12);
            // Inserimento
            selectContattoEmergenza(contattiEmergenza, lavoratore.getContattiEmergenza());
        });
    }

    /**
     * Definizione del comportamento del pulsante avanti
     * @param primaryStage
     * @param objects
     * @param lblErroreAvanti
     *
     */
    public void avantiEvent(Stage primaryStage, Object[] objects, LabelErrorCustom lblErroreAvanti) {
        button.setOnAction(actionEvent -> {
            try {
                Lavoratore lavoratore = new Lavoratore(
                        ((TextFieldCustom) objects[1]).getText(),   //Nome
                        ((TextFieldCustom) objects[2]).getText(),   //Cognome
                        ((TextFieldCustom) objects[3]).getText(),   //Luogo di nascita
                        ((DatePickerCustom) objects[4]).getData(),  //Data di nascita
                        getSelected((RadioMenuItem[]) objects[5]),  //Nazionalità
                        ((TextFieldCustom) objects[6]).getText(),   //Indirizzo
                        ((TextFieldCustom) objects[8]).getText()    //Email
                );

                // Inserimento telefono (facoltativo)
                lavoratore.setTelefono(((TextFieldCustom) objects[7]).getText());

                // Controllo esperienze (facoltative)
                lavoratore.resetEsperienzeLavorative(getSelected((CheckMenuItem[]) objects[9]));

                // Controllo lingue
                lavoratore.resetLingue(getSelected((CheckMenuItem[]) objects[10]));

                // Controllo patenti
                lavoratore.resetTipoPatente(getSelected((CheckMenuItem[]) objects[11]));

                // Controllo automunito
                lavoratore.setAutomunito(getSelected((RadioMenuItem[]) objects[12]));

                // Controllo periodi di disponibilità
                for(int i = 13; i <= 20 ; i++)
                    if(i == 13 || i == 14)
                        lavoratore.addDisponibilita(((DatePickerCustom)objects[i]).getData(), ((DatePickerCustom)objects[++i]).getData());
                    else if(((DatePickerCustom)objects[i]).getValue() != null)
                        lavoratore.addDisponibilita(((DatePickerCustom)objects[i]).getData(), ((DatePickerCustom)objects[++i]).getData());

                // Controllo zone
                lavoratore.resetZone(getSelected((CheckMenuItem[]) objects[21]));

                // Controllo contatti d'emergenza
                for(int i = 23; i <= 34 ; i++){
                    ContattoDiEmergenza contattoDiEmergenza;

                    String tmpNome = ((TextFieldCustom) objects[i]).getText();          // 23
                    String tmpCognome = ((TextFieldCustom) objects[++i]).getText();     // 24
                    String tmpEmail = ((TextFieldCustom) objects[++i]).getText();       // 25
                    String tmpTelefono = ((TextFieldCustom) objects[++i]).getText();    // 26

                    // Verifica che tutti i campi del contattosono stati compilati
                    if (!tmpNome.equals("") && !tmpCognome.equals("") && !tmpEmail.equals("")) {
                        lavoratore.addContattoEmergenza(new ContattoDiEmergenza(tmpNome, tmpCognome, tmpEmail, tmpTelefono));
                    } else if (!tmpNome.equals("") || !tmpCognome.equals("") || !tmpEmail.equals("") || !tmpTelefono.equals(""))
                        throw new IllegalArgumentException("Contatto d'emergenza inserito parzialmente");

                    // Verifica che sia stato inserito almeno un contatto
                    lavoratore.existContattoEmergenza();
                }

                Agenzia agenzia = Agenzia.getInstance();
                int prevSize = agenzia.getLavoratori().size();
                agenzia.addLavoratore(lavoratore);
                if (prevSize == agenzia.getLavoratori().size())
                    throw new IllegalArgumentException("Il lavoratore inserito è già presente");

                agenzia.delLavoratore(lavoratore);

                WindowFactory.getWindow("WORK").createWindow(primaryStage, lavoratore);

            } catch (Exception e) {
                lblErroreAvanti.activatingError(e.getMessage());
                Popup.display(lblErroreAvanti.getText());
                System.out.println(e);
            }
        });
    }

    // TODO DA RIMUOVERE; SOLO DEBUG
    public void testEvent (Stage primaryStage, Object[] objects) {
        button.setOnAction(actionEvent -> {
            ((TextFieldCustom) objects[1]).setText("Gianmaria");        // Nome
            ((TextFieldCustom) objects[2]).setText("Renso");            // Cognome
            ((TextFieldCustom) objects[3]).setText("Batignano");        // Luogo di nascita

            // Data di nascita
            ((DatePickerCustom) objects[4]).setDatePicker(LocalDate.parse("01/01/2000", DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            ((RadioMenuItem[]) objects[5])[0].setSelected(true);        // Nazionalità (Afghanistan)
            ((TextFieldCustom) objects[6]).setText("Via lambrate, 12"); // Indirizzo
            // Telefono: opzionale
            ((TextFieldCustom) objects[8]).setText("prova@gmail.com");  // Email
            // Esperienze: opzionale
            ((CheckMenuItem[]) objects[10])[0].setSelected(true);       // Lingue (Afghana)
            ((CheckMenuItem[]) objects[10])[20].setSelected(true);      // Lingue (Bosniaca)
            ((CheckMenuItem[]) objects[11])[2].setSelected(true);      // Patenti (A1)
            ((CheckMenuItem[]) objects[11])[5].setSelected(true);      // Patenti (B1)
            ((RadioMenuItem[]) objects[12])[0].setSelected(true);      // Automunito (Sì)

            // Periodi
            ((DatePickerCustom) objects[13]).setDatePicker(LocalDate.parse("01/08/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            ((DatePickerCustom) objects[14]).setDatePicker(LocalDate.parse("15/10/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            ((DatePickerCustom) objects[15]).setDatePicker(LocalDate.parse("07/06/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            ((DatePickerCustom) objects[16]).setDatePicker(LocalDate.parse("10/09/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            ((CheckMenuItem[]) objects[21])[0].setSelected(true);      // Zona/e (Affi)

            ((TextFieldCustom) objects[23]).setText("Beppe"); // Nome Emergenza
            ((TextFieldCustom) objects[24]).setText("Lorito");  // Cognome Emergenza
            ((TextFieldCustom) objects[25]).setText("asparagi@gmail.com");  // Email Emergenza
        });
    }

    // TODO DA RIMUOVERE; SOLO DEBUG
    public void testLavoroEvent (Stage primaryStage, Object[] objects) {
        button.setOnAction(actionEvent -> {
            ((DatePickerCustom) objects[0]).setDatePicker(LocalDate.parse("12/01/2019", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            ((DatePickerCustom) objects[1]).setDatePicker(LocalDate.parse("15/06/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            ((TextFieldCustom) objects[2]).setText("Microsoft S.p.A.");    // Nome Azienda
            ((TextFieldCustom) objects[3]).setText("Magazziniere");        // Mansione svolta
            ((TextFieldCustom) objects[4]).setText("Gavorrano");           // Luogo di lavoro
            ((TextFieldCustom) objects[5]).setText("1657");                // Retribuzione
            ((ButtonCustom) objects[6]).activateButton((ButtonCustom) objects[6]);  // Bottone aggiungi
        });
    }

    // TODO DA RIMUOVERE; SOLO DEBUG
    public void testLoginEvent (TextFieldCustom textFieldCustom, PasswordFieldCustom passwordFieldCustom, ButtonCustom btnLogin) {
        button.setOnAction(actionEvent -> {
            textFieldCustom.setText("t@t.t");
            passwordFieldCustom.setText("1");
            activateButton(btnLogin);
        });
    }

    // TODO DA RIMUOVERE; SOLO DEBUG
    public void testResetEvent (Agenzia agenzia) {
        button.setOnAction(actionEvent -> {
            agenzia.reset();
        });
    }

    /**
     * Metodo per la definizione del comportamento del pulsante ricerca
     * @param primaryStage
     * @param objects
     */
    public void ricercaEvent (Stage primaryStage, Object[] objects, TableViewCustom tableViewCustom) {
        button.setOnAction(actionEvent -> {
            BorderPane rootAnagrafica = (BorderPane) primaryStage.getScene().getRoot();

            GridPaneCustom gridPaneRicerca = new GridPaneCustom((GridPane) rootAnagrafica.getChildren().get(0));

            // TODO ricercona tatticona

            Agenzia agenzia = Agenzia.getInstance();
            HashSet<Lavoratore> work = Agenzia.getLavoratoriBackup();
            String tmp;
            Data tmp1;
            Data tmp2;
            boolean flag = false;

            // Ricerca per nome
            tmp = ((TextFieldCustom)objects[22]).getText();
            if (tmp != "") {
                flag = true;
                String finalTmp1 = tmp;
                work = agenzia.andSearch(work, (s -> s.getNome().equals(finalTmp1)));
            }

            // Ricerca per cognome
            tmp = ((TextFieldCustom)objects[23]).getText();
            if (tmp != "")
                if (!((RadioButtonCustom)objects[0]).isSelected()) {
                    String finalTmp = tmp;
                    if (flag) {
                        if (((RadioButtonCustom)objects[1]).isSelected())
                            work = agenzia.andSearch(work, (s -> s.getCognome().equals(finalTmp)));
                        else
                            work = agenzia.orSearch(work, (s -> s.getCognome().equals(finalTmp)));
                    }
                    else {
                        flag = true;
                        work = agenzia.andSearch(work, (s -> s.getCognome().equals(finalTmp)));
                    }
                }

            // Ricerca per lingua
            tmp = getSelected((RadioMenuItem[])objects[24]);
            if (tmp != null)
                if (!((RadioButtonCustom)objects[3]).isSelected()) {
                    String finalTmp = tmp;
                    if (flag) {
                        if (((RadioButtonCustom)objects[4]).isSelected())
                            work = agenzia.andSearch(work, (s -> s.getLingue().contains(finalTmp)));
                        else
                            work = agenzia.orSearch(work, (s -> s.getLingue().contains(finalTmp)));
                    }
                    else {
                        flag = true;
                        work = agenzia.andSearch(work, (s -> s.getLingue().contains(finalTmp)));
                    }
                }

            // TODO LE DATE TORNANO SEMPRE NULL ANCHE QUANDO SONO NULL E "NONE"
            // Ricerca per periodo
            if (!((RadioButtonCustom)objects[6]).isSelected()) {
                tmp1 = ((DatePickerCustom)objects[25]).getData();
                tmp2 = ((DatePickerCustom)objects[26]).getData();
                // Condizione di ricerca (Spoto sii fiero di me)
                Predicate<Lavoratore> condizione = (s -> {
                    Data[][] date = s.getDates();
                    boolean res = false;
                    for (int index = 0 ; date[index][0] != null ; index++)
                        if (Data.compareTo(date[index][0], tmp1) <= 0 && Data.compareTo(tmp2, date[index][1]) <= 0)
                            res = true;

                    return res;
                });

                if (Data.compareTo(tmp1, tmp2) > 0)
                    throw new IllegalArgumentException("La data di inizio del periodo è maggiore della data in cui termina");

                if (flag) {
                    if (((RadioButtonCustom)objects[7]).isSelected())
                        work = agenzia.andSearch(work, condizione);
                    else
                        work = agenzia.orSearch(work, condizione);
                }
                else {
                    flag = true;
                    work = agenzia.andSearch(work, condizione);
                }
            }

            // TODO Ricerca per mansione, manca getMansione!!!
            /*
            tmp = ((TextFieldCustom)objects[27]).getText();
            if (tmp != "")
                if (!((RadioButtonCustom)objects[9]).isSelected()) {
                    String finalTmp = tmp;
                    if (flag) {
                        if (((RadioButtonCustom)objects[10]).isSelected())
                            work = agenzia.andSearch(work, (s -> s.getMansione().equals(finalTmp)));
                        else
                            work = agenzia.orSearch(work, (s -> s.getCognome().equals(finalTmp)));
                    }
                    else {
                        flag = true;
                        work = agenzia.andSearch(work, (s -> s.getCognome().equals(finalTmp)));
                    }
                }
            */

            // TODO Ricerca per comune di residenza, manca getComune!!!
            /*
            tmp = getSelected((RadioMenuItem[])objects[28]);
            if (tmp != null)
                if (!((RadioButtonCustom)objects[12]).isSelected()) {
                    String finalTmp = tmp;
                    if (flag) {
                        if (((RadioButtonCustom)objects[13]).isSelected())
                            work = agenzia.andSearch(work, (s -> s.getComune().contains(finalTmp)));
                        else
                            work = agenzia.orSearch(work, (s -> s.getComune().contains(finalTmp)));
                    }
                    else {
                        flag = true;
                        work = agenzia.andSearch(work, (s -> s.getComune().contains(finalTmp)));
                    }
                }
             */

            // Ricerca per patente
            tmp = getSelected((RadioMenuItem[])objects[29]);
            if (tmp != null)
                if (!((RadioButtonCustom)objects[15]).isSelected()) {
                    String finalTmp = tmp;
                    if (flag) {
                        if (((RadioButtonCustom)objects[16]).isSelected())
                            work = agenzia.andSearch(work, (s -> s.getTipoPatente().contains(finalTmp)));
                        else
                            work = agenzia.orSearch(work, (s -> s.getTipoPatente().contains(finalTmp)));
                    }
                    else {
                        flag = true;
                        work = agenzia.andSearch(work, (s -> s.getTipoPatente().contains(finalTmp)));
                    }
                }

            // Ricerca per automunito
            tmp = getSelected((RadioMenuItem[])objects[30]);
            if (tmp != null)
                if (!((RadioButtonCustom)objects[18]).isSelected()) {
                    boolean finalTmp;
                    finalTmp = tmp.equals("Sì");
                    if (flag) {
                        if (((RadioButtonCustom)objects[19]).isSelected())
                            work = agenzia.andSearch(work, (s -> s.getAutomunito() == finalTmp));
                        else
                            work = agenzia.orSearch(work, (s -> s.getAutomunito() == finalTmp));
                    }
                    else {
                        flag = true;
                        work = agenzia.andSearch(work, (s -> s.getAutomunito() == finalTmp));
                    }
                }


            // TODO Da rimuovere, serve solo per verificare la ricerca ottenuta
            if (work != null)
                for (Lavoratore lavoratore : work) {
                    System.out.println(lavoratore);
                }

            // TODO Aggiungere chiamata a visualizzazione con parametro hashset
            Agenzia.setLavoratoriBackup(work);

            gridPaneRicerca.addLabelsRisultatiRicerca(primaryStage, tableViewCustom);


        });
    }

    /**
     * Metodo per la definizione del comportamento del pulsante fine
     * @param primaryStage
     * @param objects
     * @param lavoratore
     */
    public void fineEvent (Stage primaryStage, Object[] objects, Lavoratore lavoratore) {
        button.setOnAction(actionEvent -> {
            Agenzia.getInstance().addLavoratore(lavoratore);

            Agenzia.getInstance().toFile();

            Lavoro.getLavoriBackup().clear();

            WindowFactory.getWindow("SELECT").createWindow(primaryStage, null);
        });
    }

    /**
     * Metodo per la definizione del comportamento del pulsante aggiungi
     * @param primaryStage
     * @param objects
     * @param lavoratore
     */
    public void aggiungiEvent (Stage primaryStage, Object[] objects, Lavoratore lavoratore, LabelErrorCustom lblErroreAggiungi, TableViewCustom tableViewCustom) {
        button.setOnAction(actionEvent -> {
            try {
                Lavoro lavoro = new Lavoro(((DatePickerCustom) objects[0]).getData(),   // Data inizio
                        ((DatePickerCustom) objects[1]).getData(),                      // Data fine
                        ((TextFieldCustom) objects[2]).getText(),                       // Nome azienda
                        ((TextFieldCustom) objects[3]).getText(),                       // Mansione
                        ((TextFieldCustom) objects[4]).getText(),                       // Luogo di lavoro
                        ((TextFieldCustom) objects[5]).getText()                        // Retribuzione lorda
                        );

                // Pulizia campi
                ((DatePickerCustom) objects[0]).setDatePicker(null);
                ((DatePickerCustom) objects[1]).setDatePicker(null);
                ((TextFieldCustom) objects[2]).setText("");
                ((TextFieldCustom) objects[3]).setText("");
                ((TextFieldCustom) objects[4]).setText("");
                ((TextFieldCustom) objects[5]).setText("");

                int prevSize = lavoratore.getLavori().size();
                lavoratore.addLavoro(lavoro);
                if (prevSize == lavoratore.getLavori().size())
                    throw new IllegalArgumentException("Il lavoro inserito è già presente");

                lblErroreAggiungi.deactivatingError();

                Lavoro.getLavoriBackup().add(lavoro);

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

            } catch (Exception e) {
                lblErroreAggiungi.activatingError(e.getMessage());
                Popup.display(lblErroreAggiungi.getText());
                System.out.println(e);
            }
        });
    }

    /**
     * Metodo per eliminare una riga di risultato
     * @param primaryStage
     */
    public void eliminaLavoroEvent (Stage primaryStage, GridPane gridPane, int riga, Lavoro lavoro, Lavoratore lavoratore) {
        button.setOnAction(actionEvent -> {
            int primoRisultato = 22;
            int inizioRange = primoRisultato + ((riga - 9) * 7);
            int fineRange = primoRisultato + 6 + ((riga - 9) * 7);

            gridPane.getChildren().remove(inizioRange, fineRange);
            lavoratore.delLavoro(lavoro);
        });
    }

    /**
     * Metodo per ottenere un unico valore selezionato
     * @param items menù a tendina
     * @return
     */
    private static String getSelected (RadioMenuItem[] items) {
        for (RadioMenuItem item : items) {
            if (item.isSelected()) {
                return item.getText();
            }
        }
        return null;
    }

    /**
     * Metodo per ottenere più valori selezionati
     * @param items menù a tendina
     * @return
     */
    private static String[] getSelected (CheckMenuItem[] items) {
        String[] strings = new String[items.length];
        int index = 0;

        for (CheckMenuItem item : items) {
            if (item.isSelected()) {
                strings[index] = item.getText();
                index++;
            }
        }
        return strings;
    }

    /**
     * Si ottiene il bottone.
     * @return bottone.
     */
    private Button getButton() {
        return this.button;
    }

    /**
     * Clicka il bottone.
     * @param buttonCustom bottone da clickare.
     */
    private void activateButton (ButtonCustom buttonCustom) {
        buttonCustom.getButton().fire();
    }

    /**
     * Seleziona solamente l'unica voce (radioMenu) appartenente al lavoratore.
     * Il metodo effettua una ricerca su tutto il menù, si ferma soltanto appena
     * trova la voce corrispondente.
     * @param objects array di oggetti, ogni oggetto rappresenta una voce nel menù.
     * @param check   voce del lavoratore da cercare.
     */
    public void selectRadio(RadioMenuItem[] objects, String check) {
        int i = 0;
        for (boolean flag = false; !flag; i++) {
            if (objects[i].getText().equals(check)) {
                flag = true;
                objects[i].setSelected(true);
            }
        }
    }

    /**
     * Seleziona le voci (checkMenu) appartenenti al lavoratore.
     * Il metodo acquisisce una voce dal menù, la cerca all'interno della lista delle voci
     * selezionate dal lavoratore ed eventualmente la seleziona. Altrimenti va avanti.
     * Il metodo si ferma solamente all'ultima voce del menù.
     * @param objects array di oggetti, ogni oggetto rappresenta una voce nel menù.
     * @param check   voce del lavoratore da cercare.
     */
    public void selectCheck(CheckMenuItem[] objects, ArrayList<String> check) {
        for (CheckMenuItem object : objects)
            for (int j = 0, flag = 0; j < check.size() && flag == 0; j++)
                if (object.getText().equals(check.get(j))) {
                    object.setSelected(true);
                    flag = 1;
                }
    }

    /**
     * Imposta eventuali contatti d'emergenza.
     * @param objects oggetti della videata che si riferiscono al contatto d'emergenza.
     * @param check   lista di contatti d'emergenza del dipendente.
     */
    public void selectContattoEmergenza(Object[] objects, HashSet<ContattoDiEmergenza> check) {
        int i = 0;
        for (ContattoDiEmergenza contact : check) {
            // Inserimento dati precedenti nella schermata
            ((TextFieldCustom) objects[i]).setText(contact.getNome());        // Nome
            ((TextFieldCustom) objects[++i]).setText(contact.getCognome());   // Cognome
            ((TextFieldCustom) objects[++i]).setText(contact.getEmail());     // Email
            ((TextFieldCustom) objects[++i]).setText(contact.getTelefono());  // Telefono
            i++;
        }
    }
}
