package com.univr.graphics.components.custom;

import com.univr.anagrafica.*;
import com.univr.graphics.components.popup.PopupFactory;
import com.univr.graphics.components.windows.Window;
import com.univr.graphics.components.windows.WindowFactory;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Classe che definisce tutti gli eventi dei pulsanti
 */
public class Events {
    /**
     * Definizione del comportamento del pulsante login.
     * @param emailField    campo email
     * @param passwordField campo password
     * @param lblErrorLogin label errore login
     * @param primaryStage  stage primario
     */
    public static void loginEvent (Button button, TextFieldCustom emailField, PasswordFieldCustom passwordField, LabelErrorCustom lblErrorLogin, Stage primaryStage) {
        button.setOnAction(actionEvent -> {
            // Se le credenziali sono quelle del admin
            // TODO modificare credenziali, magari non banali
            if (emailField.getText().equals("admin") && passwordField.getText().equals("admin"))
                WindowFactory.getWindow("ADMIN").createWindow(primaryStage, null, null, null);
            else
                // Verifica delle credenziali di accesso
                if (Business.access(emailField.getText(), passwordField.getText())) {
                    WindowFactory.getWindow("SELECT").createWindow(primaryStage, null, null, null);
                }
                else {
                    // In caso di errore d'inserimento, messaggio di errore
                    lblErrorLogin.activatingError();
                    PopupFactory.getPopup("ERROR").createPopup(lblErrorLogin.getText());
                }
        });
    }

    /**
     * Definizione del comportamento del pulsante inserimento.
     * @param primaryStage stage primario
     */
    public static void inserimentoEvent(Button button, Stage primaryStage) {
        button.setOnAction(actionEvent -> WindowFactory.getWindow("INSERT").createWindow(primaryStage, null, null, null));
    }

    /**
     * Definizione del comportamento del pulsante ricerca anagrafiche.
     * @param primaryStage stage primario
     */
    public static void anagraficaEvent(Button button, Stage primaryStage) {
        button.setOnAction(actionEvent -> WindowFactory.getWindow("SEARCH").createWindow(primaryStage, null, null, null));
    }

    /**
     * Definizione del comportamento del pulsante logout.
     * @param primaryStage stage primario
     */
    public static void logoutEvent (Button button, Stage primaryStage) {
        button.setOnAction(actionEvent -> WindowFactory.getWindow("LOGIN").createWindow(primaryStage, null, null, null));
    }

    /**
     * Definizione del comportamento del pulsante per il ritorno alla home.
     * @param primaryStage stage primario
     */
    public static void homeEvent(Button button, Stage primaryStage) {
        button.setOnAction(actionEvent -> WindowFactory.getWindow("SELECT").createWindow(primaryStage, null, null, null));
    }

    /**
     * Definizione del comportamento del pulsante indietro per il ritorno alla schermata d'inserimento.
     * @param primaryStage stage primario
     */
    public static void indietroInsertEvent(Button button, Stage primaryStage, Worker worker) {
        button.setOnAction(actionEvent -> {

            Window window = WindowFactory.getWindow("INSERT");
            window.createWindow(primaryStage, null, null, null);
            Object[] objects = window.getObjects();
            autoFillWorker(objects, worker);

        });
    }

    /**
     * Definizione del comportamento del pulsante indietro per il ritorno alla schermata di modifica del lavoratore.
     * @param primaryStage stage primario
     */
    public static void indietroModifyWorkEvent(Button button, Stage primaryStage, Worker worker, Worker old) {
        button.setOnAction(actionEvent -> {

            Window window = WindowFactory.getWindow("MODIFYWORKER");
            window.createWindow(primaryStage, worker, old, null);
            Object[] objects = window.getObjects();
            autoFillWorker(objects, worker);

        });
    }

    /**
     * Definizione del comportamento del pulsante indietro per tornare alla ricerca.
     * @param primaryStage stage primario
     */
    public static void indietroModifyWorkerEvent(Button button, Stage primaryStage) {
        button.setOnAction(actionEvent -> {
            HashSet<Worker> tmpLavoratori = new HashSet<>(Business.getWorkersBackup());
            Window window = WindowFactory.getWindow("SEARCH");
            window.createWindow(primaryStage, null, null, null);
            BorderPane rootAnagrafica = (BorderPane) primaryStage.getScene().getRoot();

            SceneBuilder gridPaneRicerca = new SceneBuilder((GridPane) rootAnagrafica.getChildren().get(0));

            Business.setWorkersBackup(tmpLavoratori);

            gridPaneRicerca.addLabelsSearch(primaryStage, SceneBuilder.getTableViewCustom());
        });
    }

    /**
     * Definizione del comportamento del pulsante avanti nella finestra d'inserimento.
     * @param primaryStage    stage primario
     * @param objects         array di oggetti
     * @param lblErroreAvanti label errore avanti
     *
     */
    public static void avantiInsertEvent(Button button, Stage primaryStage, Object[] objects, LabelErrorCustom lblErroreAvanti) {
        button.setOnAction(actionEvent -> {
            try {
                Worker worker = new Worker(
                        ((TextFieldCustom) objects[1]).getText(),   //Nome
                        ((TextFieldCustom) objects[2]).getText(),   //Cognome
                        ((TextFieldCustom) objects[3]).getText(),   //Luogo di nascita
                        ((DatePickerCustom) objects[4]).getDateCustom(),  //Data di nascita
                        getSelected((RadioMenuItem[]) objects[5]),  //Nazionalità
                        ((TextFieldCustom) objects[6]).getText(),   //Indirizzo
                        ((TextFieldCustom) objects[8]).getText()    //Email
                );

                // Inserimento telefono (facoltativo)
                worker.setPhone(((TextFieldCustom) objects[7]).getText());

                // Controllo esperienze (facoltative)
                worker.resetExperiences(getSelected((CheckMenuItem[]) objects[9]));

                // Controllo lingue
                worker.resetLanguages(getSelected((CheckMenuItem[]) objects[10]));

                // Controllo patenti
                worker.resetDriveLicense(getSelected((CheckMenuItem[]) objects[11]));

                // Controllo automunito
                worker.setCarOwner(getSelected((RadioMenuItem[]) objects[12]));

                // Controllo periodi di disponibilità
                for(int i = 13; i <= 20 ; i++)
                    if(i == 13 || i == 14)
                        worker.addAvailability(((DatePickerCustom)objects[i]).getDateCustom(), ((DatePickerCustom)objects[++i]).getDateCustom());
                    else if(((DatePickerCustom)objects[i]).getValue() != null)
                        worker.addAvailability(((DatePickerCustom)objects[i]).getDateCustom(), ((DatePickerCustom)objects[++i]).getDateCustom());

                // Controllo zone
                worker.resetZones(getSelected((CheckMenuItem[]) objects[21]));

                // Controllo contatti d'emergenza
                for(int i = 23; i <= 34 ; i++){

                    String tmpNome = ((TextFieldCustom) objects[i]).getText();          // 23
                    String tmpCognome = ((TextFieldCustom) objects[++i]).getText();     // 24
                    String tmpEmail = ((TextFieldCustom) objects[++i]).getText();       // 25
                    String tmpTelefono = ((TextFieldCustom) objects[++i]).getText();    // 26

                    // Verifica che tutti i campi del contatto sono stati compilati
                    if (!tmpNome.equals("") && !tmpCognome.equals("") && !tmpEmail.equals("")) {
                        worker.addEmergencyContact(new EmergencyContact(tmpNome, tmpCognome, tmpEmail, tmpTelefono));
                    } else if (!tmpNome.equals("") || !tmpCognome.equals("") || !tmpEmail.equals("") || !tmpTelefono.equals(""))
                        throw new IllegalArgumentException("Contatto d'emergenza inserito parzialmente");

                    // Verifica che sia stato inserito almeno un contatto
                    worker.existEmergencyContact();
                }

                Business business = Business.getInstance();
                int prevSize = business.getWorkers().size();
                business.addWorker(worker);
                if (prevSize == business.getWorkers().size())
                    throw new IllegalArgumentException("Il lavoratore inserito è già presente");

                business.removeWorker(worker);

                WindowFactory.getWindow("WORK").createWindow(primaryStage, worker, null, null);

            } catch (Exception e) {
                lblErroreAvanti.activatingError(e.getMessage());
                PopupFactory.getPopup("ERROR").createPopup(lblErroreAvanti.getText());
                // System.out.println(e);
            }
        });
    }

    /**
     * Definizione del comportamento del pulsante avanti nella finestra di modifica.
     * @param primaryStage    stage primario
     * @param objects         array di oggetti
     * @param lblErroreAvanti label errore avanti
     *
     */
    public static void avantiModifyEvent(Button button, Stage primaryStage, Object[] objects, Worker backup, Worker old, LabelErrorCustom lblErroreAvanti) {
        button.setOnAction(actionEvent -> {
            try {
                Worker worker = new Worker(
                        ((TextFieldCustom) objects[1]).getText(),   //Nome
                        ((TextFieldCustom) objects[2]).getText(),   //Cognome
                        ((TextFieldCustom) objects[3]).getText(),   //Luogo di nascita
                        ((DatePickerCustom) objects[4]).getDateCustom(),  //Data di nascita
                        getSelected((RadioMenuItem[]) objects[5]),  //Nazionalità
                        ((TextFieldCustom) objects[6]).getText(),   //Indirizzo
                        ((TextFieldCustom) objects[8]).getText()    //Email
                );

                // Inserimento telefono (facoltativo)
                worker.setPhone(((TextFieldCustom) objects[7]).getText());

                // Controllo esperienze (facoltative)
                worker.resetExperiences(getSelected((CheckMenuItem[]) objects[9]));

                // Controllo lingue
                worker.resetLanguages(getSelected((CheckMenuItem[]) objects[10]));

                // Controllo patenti
                worker.resetDriveLicense(getSelected((CheckMenuItem[]) objects[11]));

                // Controllo automunito
                worker.setCarOwner(getSelected((RadioMenuItem[]) objects[12]));

                // Controllo periodi di disponibilità
                for(int i = 13; i <= 20 ; i++)
                    if(i == 13 || i == 14)
                        worker.addAvailability(((DatePickerCustom)objects[i]).getDateCustom(), ((DatePickerCustom)objects[++i]).getDateCustom());
                    else if(((DatePickerCustom)objects[i]).getValue() != null)
                        worker.addAvailability(((DatePickerCustom)objects[i]).getDateCustom(), ((DatePickerCustom)objects[++i]).getDateCustom());

                // Controllo zone
                worker.resetZones(getSelected((CheckMenuItem[]) objects[21]));

                // Controllo contatti d'emergenza
                for(int i = 23; i <= 34 ; i++){

                    String tmpNome = ((TextFieldCustom) objects[i]).getText();          // 23
                    String tmpCognome = ((TextFieldCustom) objects[++i]).getText();     // 24
                    String tmpEmail = ((TextFieldCustom) objects[++i]).getText();       // 25
                    String tmpTelefono = ((TextFieldCustom) objects[++i]).getText();    // 26

                    // Verifica che tutti i campi del contatto sono stati compilati
                    if (!tmpNome.equals("") && !tmpCognome.equals("") && !tmpEmail.equals("")) {
                        worker.addEmergencyContact(new EmergencyContact(tmpNome, tmpCognome, tmpEmail, tmpTelefono));
                    } else if (!tmpNome.equals("") || !tmpCognome.equals("") || !tmpEmail.equals("") || !tmpTelefono.equals(""))
                        throw new IllegalArgumentException("Contatto d'emergenza inserito parzialmente");

                    // Verifica che sia stato inserito almeno un contatto
                    worker.existEmergencyContact();
                }

                Business business = Business.getInstance();
                business.removeWorker(old);
                int prevSize = business.getWorkers().size();
                business.addWorker(worker);
                if (prevSize == business.getWorkers().size()) {
                    business.addWorker(old);
                    throw new IllegalArgumentException("Esiste già un lavoratore con questi dati");
                }

                business.removeWorker(worker);
                business.addWorker(old);

                if (backup != null)
                    for (Work work : backup.getWorks())
                        worker.addWork(work);

                WindowFactory.getWindow("MODIFYWORK").createWindow(primaryStage, worker, old, null);

            } catch (Exception e) {
                lblErroreAvanti.activatingError(e.getMessage());
                PopupFactory.getPopup("ERROR").createPopup(lblErroreAvanti.getText());
                // System.out.println(e);
            }
        });
    }

    // TODO DA RIMUOVERE; SOLO DEBUG
    public static void testWorkerEvent(Button button, Object[] objects) {
        button.setOnAction(actionEvent -> {
            ((TextFieldCustom) objects[1]).setText("Gianmaria");        // Nome
            ((TextFieldCustom) objects[2]).setText("Renso");            // Cognome
            ((TextFieldCustom) objects[3]).setText("Batignano");        // Luogo di nascita

            // Data di nascita
            ((DatePickerCustom) objects[4]).setDatePicker(LocalDate.parse("01/01/2000", DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            ((RadioMenuItem[]) objects[5])[0].setSelected(true);        // Nazionalità (Afghanistan)
            ((TextFieldCustom) objects[6]).setText("Via lambrate 12"); // Indirizzo
            // Telefono: opzionale
            ((TextFieldCustom) objects[8]).setText("prova@gmail.com");  // Email
            // Esperienze: opzionale
            ((CheckMenuItem[]) objects[10])[0].setSelected(true);       // Lingue (Afghana)
            ((CheckMenuItem[]) objects[10])[20].setSelected(true);      // Lingue (Bosniaca)
            ((CheckMenuItem[]) objects[11])[2].setSelected(true);      // Patenti (A1)
            ((CheckMenuItem[]) objects[11])[5].setSelected(true);      // Patenti (B1)
            ((RadioMenuItem[]) objects[12])[0].setSelected(true);      // Automunito (Sì)

            // Periodi
            ((DatePickerCustom) objects[13]).setDatePicker(LocalDate.parse("01/08/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            ((DatePickerCustom) objects[14]).setDatePicker(LocalDate.parse("15/10/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            ((DatePickerCustom) objects[15]).setDatePicker(LocalDate.parse("07/06/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            ((DatePickerCustom) objects[16]).setDatePicker(LocalDate.parse("10/09/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            ((CheckMenuItem[]) objects[21])[0].setSelected(true);      // Zona/e (Affi)

            ((TextFieldCustom) objects[23]).setText("Beppe"); // Nome Emergenza
            ((TextFieldCustom) objects[24]).setText("Lorito");  // Cognome Emergenza
            ((TextFieldCustom) objects[25]).setText("asparagi@gmail.com");  // Email Emergenza
        });
    }

    // TODO DA RIMUOVERE; SOLO DEBUG
    public static void testWorkEvent(Button button, Object[] objects) {
        button.setOnAction(actionEvent -> {
            ((DatePickerCustom) objects[0]).setDatePicker(LocalDate.parse("12/01/2019", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            ((DatePickerCustom) objects[1]).setDatePicker(LocalDate.parse("15/06/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            ((TextFieldCustom) objects[2]).setText("Microsoft S.p.A.");    // Nome Azienda
            ((TextFieldCustom) objects[3]).setText("Magazziniere");        // Mansione svolta
            ((TextFieldCustom) objects[4]).setText("Gavorrano");           // Luogo di lavoro
            ((TextFieldCustom) objects[5]).setText("1657");                // Retribuzione
            //((ButtonCustom) objects[6]).activateButton((ButtonCustom) objects[6]);  // Bottone aggiungi
        });
    }

    // TODO DA RIMUOVERE; SOLO DEBUG
    public static void testLoginEvent (Button button, TextFieldCustom textFieldCustom, PasswordFieldCustom passwordFieldCustom, ButtonCustom btnLogin) {
        button.setOnAction(actionEvent -> {
            textFieldCustom.setText("t@t.t");
            passwordFieldCustom.setText("1");
            // Attiva il bottone
            btnLogin.getButton().fire();
        });
    }

    // TODO DA RIMUOVERE; SOLO DEBUG
    public static void resetEvent(Button button, Business business) {
        button.setOnAction(actionEvent -> business.reset());
    }

    /**
     * Definizione del comportamento del pulsante ricerca.
     * @param primaryStage stage primario
     * @param objects      array di oggetti
     */
    public static void ricercaEvent (Button button, Stage primaryStage, Object[] objects, TableViewCustom tableViewCustom) {
        button.setOnAction(actionEvent -> {
            try {
                BorderPane rootAnagrafica = (BorderPane) primaryStage.getScene().getRoot();
                SceneBuilder gridPaneRicerca = new SceneBuilder((GridPane) rootAnagrafica.getChildren().get(0));

                HashSet<Worker> baseLavoratori = Business.getWorkersBackup();
                String tmp;
                DateCustom tmp1;
                DateCustom tmp2;

                // Ricerca per nome
                tmp = ((TextFieldCustom) objects[25]).getText();
                if (!(tmp.isEmpty() || tmp.isBlank())) {
                    if (!((RadioButtonCustom) objects[0]).isSelected()) {
                        String finalTmp = tmp;
                        Predicate<Worker> condition = (lav -> lav.getName().equalsIgnoreCase(finalTmp));

                        baseLavoratori = generalSearch(((RadioButtonCustom) objects[1]), baseLavoratori, condition);
                    }
                }

                // Ricerca per cognome
                tmp = ((TextFieldCustom) objects[26]).getText();
                if (!(tmp.isEmpty() || tmp.isBlank()))
                    if (!((RadioButtonCustom) objects[3]).isSelected()) {
                        String finalTmp = tmp;
                        Predicate<Worker> condition = (lav -> lav.getSurName().equalsIgnoreCase(finalTmp));

                        baseLavoratori = generalSearch(((RadioButtonCustom) objects[4]), baseLavoratori, condition);
                    }

                // Ricerca per lingua
                tmp = getSelected((RadioMenuItem[]) objects[27]);
                if (tmp != null)
                    if (!((RadioButtonCustom) objects[6]).isSelected()) {
                        String finalTmp = tmp;
                        Predicate<Worker> condition = (lav -> lav.getLanguages().contains(finalTmp));
                        baseLavoratori = generalSearch(((RadioButtonCustom) objects[7]), baseLavoratori, condition);
                    }

                // Ricerca per periodo
                if (!((RadioButtonCustom) objects[9]).isSelected()) {
                    tmp1 = ((DatePickerCustom) objects[28]).getDateCustom();
                    tmp2 = ((DatePickerCustom) objects[29]).getDateCustom();
                    // Condizione di ricerca (Spoto sii fiero di me)
                    Predicate<Worker> condition = (s -> {
                        DateCustom[][] date = s.getDates();
                        for (int index = 0; date[index][0] != null; index++)
                            if (DateCustom.compareTo(date[index][0], tmp1) <= 0 && DateCustom.compareTo(tmp2, date[index][1]) <= 0)
                                return true;

                        return false;
                    });

                    if (DateCustom.compareTo(tmp1, tmp2) > 0)
                        throw new IllegalArgumentException("La data di inizio del periodo è maggiore della data in cui termina");

                    baseLavoratori = generalSearch(((RadioButtonCustom) objects[10]), baseLavoratori, condition);
                }

                // Ricerca per mansione
                tmp = ((TextFieldCustom) objects[30]).getText();
                if (!(tmp.isEmpty() || tmp.isBlank()))
                    if (!((RadioButtonCustom) objects[12]).isSelected()) {

                        // Condizione di ricerca
                        String finalTmp = tmp;
                        Predicate<Worker> condition = (lav -> {
                            for (Work lavoro : lav.getWorks())
                                for (String mansione : lavoro.getTasks().split(";"))
                                    if (finalTmp.equalsIgnoreCase(mansione.trim()))
                                        return true;

                            return false;
                        });

                        baseLavoratori = generalSearch(((RadioButtonCustom) objects[13]), baseLavoratori, condition);
                    }

                // Ricerca per indirizzo
                tmp = ((TextFieldCustom) objects[31]).getText();
                if (!(tmp.isEmpty() || tmp.isBlank()))
                    if (!((RadioButtonCustom) objects[15]).isSelected()) {
                        String finalTmp = tmp;
                        Predicate<Worker> condition = (lav -> lav.getAddress().equalsIgnoreCase(finalTmp));

                        baseLavoratori = generalSearch(((RadioButtonCustom) objects[16]), baseLavoratori, condition);
                    }

                // Ricerca per patente
                tmp = getSelected((RadioMenuItem[]) objects[32]);
                if (tmp != null)
                    if (!((RadioButtonCustom) objects[18]).isSelected()) {
                        String finalTmp = tmp;
                        Predicate<Worker> condition = (lav -> lav.getDriveLicense().contains(finalTmp));

                        baseLavoratori = generalSearch(((RadioButtonCustom) objects[19]), baseLavoratori, condition);
                    }

                // Ricerca per automunito
                tmp = getSelected((RadioMenuItem[]) objects[33]);
                if (tmp != null)
                    if (!((RadioButtonCustom) objects[21]).isSelected()) {
                        boolean finalTmp;
                        finalTmp = tmp.equals("Sì");
                        Predicate<Worker> condition = (lav -> lav.getCarOwner() == finalTmp);

                        baseLavoratori = generalSearch(((RadioButtonCustom) objects[22]), baseLavoratori, condition);
                    }

                Business.setWorkersBackup(baseLavoratori);

                gridPaneRicerca.addLabelsSearch(primaryStage, tableViewCustom);

            } catch (Exception e) {
                ((LabelErrorCustom)objects[36]).activatingError(e.getMessage());
                PopupFactory.getPopup("ERROR").createPopup(((LabelErrorCustom)objects[36]).getText());
                // System.out.println(e);
            }
        });
    }

    /**
     * Mostra tutti i lavoratori presenti nel sistema.
     * @param primaryStage    stage primario
     * @param tableViewCustom tabella
     */
    public static void mostraTuttiEvent (Button button, Stage primaryStage, TableViewCustom tableViewCustom) {
        button.setOnAction(actionEvent -> {
            BorderPane rootAnagrafica = (BorderPane) primaryStage.getScene().getRoot();

            SceneBuilder gridPaneRicerca = new SceneBuilder((GridPane) rootAnagrafica.getChildren().get(0));

            Business.setWorkersBackup(new HashSet<>(Business.getInstance().getWorkers()));

            gridPaneRicerca.addLabelsSearch(primaryStage, tableViewCustom);
        });
    }

    /**
     * Definizione del comportamento del pulsante fine.
     * @param primaryStage stage primario
     * @param worker       lavoratori stagionali
     */
    public static void fineEvent (Button button, Stage primaryStage, Worker worker) {
        button.setOnAction(actionEvent -> {
            Business.getInstance().addWorker(worker);

            Business.getInstance().toFile();

            Business.getWorksBackup().clear();

            PopupFactory.getPopup("INFORMATION").createPopup("Il lavoratore è stato salvato con successo.");

            WindowFactory.getWindow("SELECT").createWindow(primaryStage, null, null, null);
        });
    }

    /**
     * Definizione del comportamento del pulsante fine.
     * @param primaryStage stage primario
     * @param worker       lavoratori stagionali
     */
    public static void aggiornaEvent(Button button, Stage primaryStage, Worker worker, Worker old) {
        button.setOnAction(actionEvent -> {
            Business.getInstance().removeWorker(old);
            Business.getInstance().addWorker(worker);

            Business.getInstance().toFile();

            HashSet<Worker> tmpLavoratori = new HashSet<>(Business.getWorkersBackup());
            tmpLavoratori.remove(old);
            tmpLavoratori.add(worker);

            PopupFactory.getPopup("INFORMATION").createPopup("Il lavoratore è stato modificato con successo.");

            Window window = WindowFactory.getWindow("SEARCH");
            window.createWindow(primaryStage, null, null, null);
            BorderPane rootAnagrafica = (BorderPane) primaryStage.getScene().getRoot();
            SceneBuilder gridPaneRicerca = new SceneBuilder((GridPane) rootAnagrafica.getChildren().get(0));

            Business.setWorkersBackup(tmpLavoratori);

            gridPaneRicerca.addLabelsSearch(primaryStage, SceneBuilder.getTableViewCustom());
        });
    }

    /**
     * Definizione del comportamento del pulsante aggiungi.
     * @param objects          array di oggetti
     * @param worker           lavoratori stagionali
     * @param lblErrorAggiungi label errore aggiungi
     * @param tableViewCustom  tabella
     */
    public static void aggiungiEvent (Button button, Object[] objects, Worker worker, LabelErrorCustom lblErrorAggiungi, TableViewCustom tableViewCustom) {
        button.setOnAction(actionEvent -> {
            try {
                Work work = new Work(((DatePickerCustom) objects[0]).getDateCustom(),   // Data inizio
                        ((DatePickerCustom) objects[1]).getDateCustom(),                      // Data fine
                        ((TextFieldCustom) objects[2]).getText(),                       // Nome azienda
                        ((TextFieldCustom) objects[3]).getTextTasks(),               // Mansione
                        ((TextFieldCustom) objects[4]).getText(),                       // Luogo di lavoro
                        ((TextFieldCustom) objects[5]).getText()                        // Retribuzione lorda
                );

                int prevSize = worker.getWorks().size();
                worker.addWork(work);
                if (prevSize == worker.getWorks().size())
                    throw new IllegalArgumentException("Il lavoro inserito è già presente");

                lblErrorAggiungi.deactivatingError();

                Business.getWorksBackup().add(work);

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

                // Pulizia campi
                ((DatePickerCustom) objects[0]).setDatePicker(null);
                ((DatePickerCustom) objects[1]).setDatePicker(null);
                ((TextFieldCustom) objects[2]).setText("");
                ((TextFieldCustom) objects[3]).setText("");
                ((TextFieldCustom) objects[4]).setText("");
                ((TextFieldCustom) objects[5]).setText("");
            } catch (Exception e) {
                lblErrorAggiungi.activatingError(e.getMessage());
                PopupFactory.getPopup("ERROR").createPopup(lblErrorAggiungi.getText());
                // System.out.println(e);
            }
        });
    }

    /**
     * Definizione del comportamento del pulsante aggiungi.
     * @param objects          array di oggetti
     * @param worker           lavoratori stagionali
     * @param lblErrorAggiungi label errore aggiungi
     * @param tableViewCustom  tabella
     */
    public static void aggiungiModifyEvent(Button button, Object[] objects, Worker worker, LabelErrorCustom lblErrorAggiungi, TableViewCustom tableViewCustom) {
        button.setOnAction(actionEvent -> {
            try {
                Work work = new Work(((DatePickerCustom) objects[0]).getDateCustom(),   // Data inizio
                        ((DatePickerCustom) objects[1]).getDateCustom(),                      // Data fine
                        ((TextFieldCustom) objects[2]).getText(),                       // Nome azienda
                        ((TextFieldCustom) objects[3]).getTextTasks(),               // Mansione
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

                int prevSize = worker.getWorks().size();
                worker.addWork(work);
                if (prevSize == worker.getWorks().size())
                    throw new IllegalArgumentException("Il lavoro inserito è già presente");

                lblErrorAggiungi.deactivatingError();

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

            } catch (Exception e) {
                lblErrorAggiungi.activatingError(e.getMessage());
                PopupFactory.getPopup("ERROR").createPopup(lblErrorAggiungi.getText());
                // System.out.println(e);
            }
        });
    }

    /**
     * Salva un nuovo lavoratore (possibile solo dall'admin!).
     * @param primaryStage stage primario
     * @param objects      array di oggetti
     */
    public static void salvaManagerEvent(Button button, Stage primaryStage, Object[] objects){
        button.setOnAction(actionEvent -> {
            try {
                int index = 1;

                Manager manager = new Manager(
                        ((TextFieldCustom) objects[index]).getText(),           // Nome (1)
                        ((TextFieldCustom) objects[++index]).getText(),         // Cognome (2)
                        ((TextFieldCustom) objects[++index]).getText(),         // Luogo di nascita (3)
                        ((DatePickerCustom) objects[++index]).getDateCustom(),  // Data di nascita (4)
                        getSelected(((RadioMenuItem[]) objects[++index])),      // Nazionalità (5)
                        ((TextFieldCustom) objects[++index]).getText(),         // Email (6)
                        ((TextFieldCustom) objects[++index]).getText(),         // Telefono (7)
                        ((TextFieldCustom) objects[++index]).getText()          // Password (8)
                );

                // Verifica di un eventuale doppione
                int prevSize = Business.getInstance().getManagers().size();
                Business.getInstance().addManager(manager);
                if (prevSize == Business.getInstance().getManagers().size())
                    throw new IllegalArgumentException("Il responsabile inserito è già presente");


                // Aggiunta elemento alla tabella
                ((TableViewCustom) objects[11]).addItems(
                        manager.getName(),                                 // Nome
                        manager.getSurName(),                              // Cognome
                        manager.getBirthPlace(),                       // Luogo di nascita
                        manager.getBirthDay().toString(),                   // Data di nascita
                        manager.getNation(),                          // Nazionalità
                        manager.getEmail(),                                // Email
                        manager.getPhone(),                             // Telefono
                        new Button("MODIFICA"),                              // Bottone modifica
                        new Button("ELIMINA"),                               // Bottone elimina
                        manager,                                           // Oggetto responsabile
                        primaryStage
                );

                // Pulizia dei campi d'inserimento
                index = 1;
                ((TextFieldCustom) objects[index]).setText("");             // Nome (1)
                ((TextFieldCustom) objects[++index]).setText("");           // Cognome (2)
                ((TextFieldCustom) objects[++index]).setText("");           // Luogo di nascita (3)
                ((DatePickerCustom) objects[++index]).setDatePicker(null);  // Data di nascita (4)
                deselectRadio(((RadioMenuItem[]) objects[++index]));        // Nazionalità (5)
                ((TextFieldCustom) objects[++index]).setText("");           // Email (6)
                ((TextFieldCustom) objects[++index]).setText("");           // Telefono (7)
                ((TextFieldCustom) objects[++index]).setText("");           // Password (8)
            } catch (Exception e) {
                ((LabelErrorCustom) objects[9]).activatingError(e.getMessage());
                PopupFactory.getPopup("ERROR").createPopup(((LabelErrorCustom) objects[9]).getText());
                // System.out.println(e);
            }
        });
    }

    /**
     * Evento che si realizza una volta cliccato su modifica del responsabile.
     * Ci sono controlli su eventuali doppioni e le modifiche avvengono con successo.
     * @param primaryStage stage primario
     * @param objects      array di oggetti
     * @param manager      responsabile
     */
    public static void modificaManagerEvent(Button button, Stage primaryStage, Object[] objects, Manager manager) {
        button.setOnAction(actionEvent -> {
            try {
                // Indice per scorrere
                int index = 1;
                String name = ((TextFieldCustom) objects[index]).getText();
                String surName = ((TextFieldCustom) objects[++index]).getText();
                String birthPlace = ((TextFieldCustom) objects[++index]).getText();
                String birthDay = ((DatePickerCustom) objects[++index]).getDateCustom().toString();
                String nation = getSelected((RadioMenuItem[]) objects[++index]);
                String email = ((TextFieldCustom) objects[++index]).getText();
                String phone = ((TextFieldCustom) objects[++index]).getText();
                String password = ((TextFieldCustom) objects[8]).getText();

                // Controllo se qualche campo è cambiato
                if (name.equals(manager.getName()) &&
                        surName.equals(manager.getSurName()) &&
                        birthPlace.equals(manager.getBirthPlace()) &&
                        birthDay.equals(manager.getBirthDay().toString()) &&
                        Objects.equals(nation, manager.getNation()) &&
                        email.equals(manager.getEmail()) &&
                        phone.equals(manager.getPhone()))
                    if (password.isEmpty() || password.isBlank())
                        throw new IllegalArgumentException("Non è stata effettuata nessuna modifica");
                    else
                        // Se è cambiata solo la password, si modifica
                        manager.setPassword(password);
                else {
                    // Se qualche campo è cambiato, controllo se è rimasta invariata la password
                    if (password.isEmpty() || password.isBlank()) {
                        // Aggiungi vecchia password
                        Manager newManager = new Manager(
                                name,                                       // Nome (1)
                                surName,                                    // Cognome (2)
                                birthPlace,                             // Luogo di nascita (3)
                                ((DatePickerCustom) objects[4]).getDateCustom(),  // Data di nascita (4)
                                nation,                                // Nazionalità (5)
                                email,                                      // Email (6)
                                phone,                                   // Telefono (7)
                                "password"                                  // Password (8)
                        );

                        // Verifica di un eventuale doppione
                        int prevSize = Business.getInstance().getManagers().size();
                        Business.getInstance().addManager(newManager);
                        if (prevSize == Business.getInstance().getManagers().size())
                            throw new IllegalArgumentException("Il responsabile modificato è già presente");
                        else
                            Business.getInstance().removeManager(newManager);

                        // Se i dati inseriti non sono identici a qualche responsabile già esistente,
                        // Modifica del responsabile
                        manager.setName(name);
                        manager.setSurName(surName);
                        manager.setBirthPlace(birthPlace);
                        manager.setBirthDay(new DateCustom(
                                ((DatePickerCustom) objects[4]).getDateCustom().getDay(),
                                ((DatePickerCustom) objects[4]).getDateCustom().getMonth(),
                                ((DatePickerCustom) objects[4]).getDateCustom().getYear()
                        ));
                        manager.setNation(nation);
                        manager.setEmail(email);
                        manager.setPhone(phone);

                    } else {
                        // Se i campi sono cambiati e la password è stata inserita, si modifica creandone uno nuovo
                        Manager newManager = new Manager(
                                name,                                       // Nome (1)
                                surName,                                    // Cognome (2)
                                birthPlace,                             // Luogo di nascita (3)
                                ((DatePickerCustom) objects[4]).getDateCustom(),  // Data di nascita (4)
                                nation,                                // Nazionalità (5)
                                email,                                      // Email (6)
                                phone,                                   // Telefono (7)
                                password                                    // Password (8)
                        );

                        // Verifica di un eventuale doppione
                        int prevSize = Business.getInstance().getManagers().size();
                        Business.getInstance().addManager(newManager);
                        if (prevSize == Business.getInstance().getManagers().size())
                            throw new IllegalArgumentException("Il responsabile modificato è già presente");
                        else
                            // Se tutto è andato a buon fine, si rimuove il vecchio responsabile
                            Business.getInstance().removeManager(manager);
                    }
                }
                // Popup di successo
                PopupFactory.getPopup("INFORMATION").createPopup("Il responsabile è stato modificato con successo!");

                // Ritorno alla pagina visualizzazione dei responsabili
                WindowFactory.getWindow("ADMIN").createWindow(primaryStage, null, null, null);
            } catch (Exception e) {
                ((LabelErrorCustom) objects[9]).activatingError(e.getMessage());
                PopupFactory.getPopup("ERROR").createPopup(((LabelErrorCustom) objects[9]).getText());
                // System.out.println(e);
            }
        });
    }

    /**
     * Utilizzato per riempire i campi nella modifica del lavoratore.
     * @param objects array di oggetti
     * @param worker  lavoratori stagionali
     */
    public static void autoFillWorker(Object[] objects, Worker worker){
        int index = 0;

        ((TextFieldCustom) objects[++index]).setText(worker.getName());                     // Nome (1)

        ((TextFieldCustom) objects[++index]).setText(worker.getSurName());                  // Cognome (2)

        ((TextFieldCustom) objects[++index]).setText(worker.getBirthPlace());           // Luogo di nascita (3)

        ((DatePickerCustom) objects[++index]).setDatePicker(
                LocalDate.parse(worker.getBirthDay().toString(),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy")));                            // Data di nascita (4)

        selectRadio((RadioMenuItem[]) objects[++index], worker.getNation());           // Nazionalità (5)

        ((TextFieldCustom) objects[++index]).setText(worker.getAddress());                // Indirizzo (6)

        ((TextFieldCustom) objects[++index]).setText(worker.getPhone());                 // Telefono: opzionale (7)

        ((TextFieldCustom) objects[++index]).setText(worker.getEmail());                    // Email (8)

        selectCheck((CheckMenuItem[]) objects[++index], worker.getExperiences());  // Esperienze: opzionale (9)

        selectCheck((CheckMenuItem[]) objects[++index], worker.getLanguages());                // Lingue (10)

        selectCheck((CheckMenuItem[]) objects[++index], worker.getDriveLicense());           // Patenti (11)

        selectRadio(
                (RadioMenuItem[]) objects[++index],
                (worker.getCarOwner() ? "Sì" : "No"));                                    // Automunito (12)

        // Periodi
        index++; // = 13
        for (int flag = 0, counter = 0; index <= 20 && flag == 0; index += 2, counter++) {
            if (worker.getDates()[counter][0] == null)
                flag = 1;
            else {
                // Data inizio periodo
                ((DatePickerCustom) objects[index]).setDatePicker(LocalDate.parse(
                        worker.getDates()[counter][0].toString(),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                // Data fine periodo
                ((DatePickerCustom) objects[index + 1]).setDatePicker(LocalDate.parse(
                        worker.getDates()[counter][1].toString(),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }
        }

        index = 20;

        selectCheck((CheckMenuItem[]) objects[++index], worker.getZones());          // Zona/e (21)

        // Array di contatti d'emergenza inseriti
        Object[] contattiEmergenza = (new Object[12]);
        System.arraycopy(objects, 23, contattiEmergenza, 0, 12);
        // Inserimento
        selectEmergencyContact(contattiEmergenza, worker.getEmergencyContacts());
    }

    /**
     * Utilizzato per riempire i campi nella modifica del responsabile.
     * @param objects array di oggetti
     * @param manager responsabile
     */
    public static void autoFillManager(Object[] objects, Manager manager) {
        int index = 0;

        ((TextFieldCustom) objects[++index]).setText(manager.getName());               // Nome (1)

        ((TextFieldCustom) objects[++index]).setText(manager.getSurName());            // Cognome (2)

        ((TextFieldCustom) objects[++index]).setText(manager.getBirthPlace());     // Luogo di nascita (3)

        ((DatePickerCustom) objects[++index]).setDatePicker(
                LocalDate.parse(manager.getBirthDay().toString(),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy")));                        // Data di nascita (4)

        selectRadio((RadioMenuItem[]) objects[++index], manager.getNation());     // Nazionalità (5)

        ((TextFieldCustom) objects[++index]).setText(manager.getEmail());              // Email (6)

        ((TextFieldCustom) objects[++index]).setText(manager.getPhone());           // Telefono (7)

        // Messaggio di allerta riguardo la password
        PopupFactory.getPopup("WARNING").createPopup("La password non è visibile per questioni di privacy.\nPer cambiarla, basta inserirne una nuova.");
    }

    /**
     * Ottenere un unico valore selezionato dal menù a tendina.
     * @param items menù a tendina
     * @return elemento selezionato
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
     * Ottenere più valori selezionati.
     * @param items menù a tendina
     * @return elementi selezionati
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
     * Seleziona solamente l'unica voce (radioMenu) appartenente al lavoratore.
     * Il metodo effettua una ricerca su tutto il menù, si ferma soltanto appena
     * trova la voce corrispondente.
     * @param objects array di oggetti, ogni oggetto rappresenta una voce nel menù.
     * @param check   voce del lavoratore da cercare.
     */
    public static void selectRadio(RadioMenuItem[] objects, String check) {
        int i = 0;
        for (boolean flag = false; !flag && i < objects.length; i++) {
            if (objects[i].getText().equalsIgnoreCase(check)) {
                flag = true;
                objects[i].setSelected(true);
            }
        }
    }

    /**
     * Deseleziona l'unica voce che è stata selezionata.
     * @param radioMenuItems array di oggetti, ognuno rappresenta una voce nel menù.
     */
    public static void deselectRadio(RadioMenuItem[] radioMenuItems) {
        int i = 0;
        for (boolean flag = false; !flag && i < radioMenuItems.length; i++)
            if (radioMenuItems[i].isSelected()) {
                radioMenuItems[i].setSelected(false);
                flag = true;
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
    public static void selectCheck(CheckMenuItem[] objects, ArrayList<String> check) {
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
     * @param check   lista di contatti d'emergenza del lavoratore.
     */
    public static void selectEmergencyContact(Object[] objects, HashSet<EmergencyContact> check) {
        int i = 0;
        for (EmergencyContact contact : check) {
            // Inserimento dati precedenti nella schermata
            ((TextFieldCustom) objects[i]).setText(contact.getName());        // Nome
            ((TextFieldCustom) objects[++i]).setText(contact.getSurName());   // Cognome
            ((TextFieldCustom) objects[++i]).setText(contact.getEmail());     // Email
            ((TextFieldCustom) objects[++i]).setText(contact.getPhone());  // Telefono
            i++;
        }
    }

    /**
     * Utilizzata per effettuare una ricerca, tenendo conto delle opzioni (AND / OR) selezionate
     * @param radio          menù a tendina
     * @param baseLavoratori base di ricerca lavoratori
     * @param condizione     condizione
     * @return HashSet<Lavoratore> baseLavoratori
     */
    public static HashSet<Worker> generalSearch (RadioButtonCustom radio, HashSet<Worker> baseLavoratori, Predicate<Worker> condizione) {
        Business business = Business.getInstance();

        if (radio.isSelected())
            return business.andSearch(baseLavoratori, condizione);
        else
            return business.orSearch(baseLavoratori, condizione);
    }
}