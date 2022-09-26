package com.univr.graphics.components.custom;

import com.univr.anagrafica.Business;
import com.univr.anagrafica.Worker;
import com.univr.graphics.components.windows.Window;
import com.univr.graphics.components.windows.WindowFactory;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class TableViewWorker extends TableViewCustom {

    public TableViewWorker(GridPane gridPane, String[] colNames, String[] namesValueFactory, int col, int row, int extendCols, int extendRows) {
        super(gridPane, colNames, namesValueFactory, col, row, extendCols, extendRows);
    }
/*
    String 0 nome,
    String 1 cognome,
    ArrayList<String> 2 lingue,
    String 3 residenza,
    ArrayList<String> 4 patenti,
    boolean 5 automunito,
    Button 6 btnModify,
    Button 7 btnDelete,
    Lavoratore 8 lavoratore
*/
    @Override
    public void addItems(Object ... objects) {
        // TODO MIGLIORARE IL BACK-END!!!
        String lingue = objects[2].toString();
        String patenti = objects[4].toString();
        String automunito = ((boolean)objects[5]) ? "Sì" : "No";

        lingue = lingue.substring(lingue.indexOf("[") + 1,  lingue.indexOf("]"));
        patenti = patenti.substring(patenti.indexOf("[") + 1,  patenti.indexOf("]"));


        WorkerRow tableLavoratore = new WorkerRow(((String)objects[0]),
                                                            ((String)objects[1]),
                                                            lingue,
                                                            ((String)objects[3]),
                                                            patenti,
                                                            automunito,
                                                            ((Button)objects[6]),
                                                            ((Button)objects[7]));

        // Aggiunta alla tabella visiva il lavoro stagionale
        getTableView().getItems().add(tableLavoratore);

        Worker tmpWorker = ((Worker)objects[8]);
        Stage tmpStage = ((Stage)objects[9]);

        // Eventuale eliminazione dal file e visivamente
        ((Button)objects[7]).setOnAction(actionEvent -> {
            Business.getInstance().removeWorker(tmpWorker);
            Business.getWorkersBackup().remove(tmpWorker);
            getTableView().getItems().remove(tableLavoratore);
        });

        // Bottone "Modifica" per aggiornare l'anagrafica di un lavoratore
        ((Button)objects[6]).setOnAction(actionEvent -> {
            Window window = WindowFactory.getWindow("MODIFYWORKER");
            window.createWindow(tmpStage, null, tmpWorker, null);
            Object[] fields = window.getObjects();
            Events.autoFillWorker(fields, tmpWorker);
        });
    }

    public static class WorkerRow {
        private final String nome;
        private final String cognome;
        private final String lingue;
        private final String residenza;
        private final String patenti;
        private final String automunito;
        private final Button btnModify;
        private final Button btnDelete;

        /**
         * Settaggio della tabella contenente i lavoratori
         * @param nome       nome
         * @param cognome    cognome
         * @param lingue     lingue
         * @param residenza  residenza
         * @param patenti    patenti
         * @param automunito automunito
         * @param btnModify  bottone modifica
         * @param btnDelete  bottone elimina
         */
        WorkerRow(String nome, String cognome, String lingue, String residenza, String patenti,
                  String automunito, Button btnModify, Button btnDelete) {
            this.nome = nome;
            this.cognome = cognome;
            this.lingue = lingue;
            this.residenza = residenza;
            this.patenti = patenti;
            this.automunito = automunito;
            this.btnModify = btnModify;
            this.btnDelete = btnDelete;
        }

        public String getNome(){ return nome; }
        public String getCognome() { return cognome; }
        public String getLingue() { return lingue; }
        public String getResidenza() { return residenza; }
        public String getPatenti() { return patenti; }
        public String getAutomunito() { return automunito; }
        public Button getBtnModify() { return btnModify; }
        public Button getBtnDelete() { return btnDelete; }
    }

    public void setMaxSizeTableView(double width, double height) {
        getTableView().setMinSize(width, height);
        getTableView().setMaxSize(width, height);

        for (TableColumn<Object, ?> column : getTableView().getColumns())
            column.prefWidthProperty().bind(getTableView().widthProperty().multiply(0.125));
    }
}
