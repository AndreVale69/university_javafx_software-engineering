package com.univr.graphics.components.custom;

import com.univr.anagrafica.Business;
import com.univr.anagrafica.Worker;
import com.univr.anagrafica.Work;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * Classe per la generazione della tabella contenente i lavori stagionali
 */

public class TableViewWork extends TableViewCustom {

    public TableViewWork(GridPane gridPane, String[] colNames, String[] namesValueFactory, int col, int row, int extendCols, int extendRows) {
        super(gridPane, colNames, namesValueFactory, col, row, extendCols, extendRows);
    }

    public void addItems(Object ... objects) {
        WorkRow workRow = new WorkRow(((Button)objects[0]), ((String)objects[1]), ((String)objects[2]), ((String)objects[3]), ((String)objects[4]), ((String)objects[5]), ((String)objects[6]));

        // Aggiunta alla tabella visiva il lavoro stagionale
        getTableView().getItems().add(workRow);

        Work tmpWork = ((Work)objects[8]);
        Worker tmpWorker = ((Worker)objects[7]);

        // Eventuale eliminazione dal file e visivamente
        ((Button)objects[0]).setOnAction(actionEvent -> {
            tmpWorker.delWork(tmpWork);
            Business.getWorksBackup().remove(tmpWork);
            getTableView().getItems().remove(workRow);
        });
    }

    public void setMaxSizeTableView(double width, double height) {
        getTableView().setMaxSize(width, height);
    }

    public static class WorkRow {
        private final Button btnDelete;
        private final String periodoInizio;
        private final String periodoFine;
        private final String nomeAzienda;
        private final String mansioniSvolte;
        private final String luogoLavoro;
        private final String retribuzione;

        WorkRow(Button btnDelete, String periodoInizio, String periodoFine, String nomeAzienda, String mansioniSvolte, String luogoLavoro, String retribuzione) {
            this.btnDelete = btnDelete;
            this.periodoInizio = periodoInizio;
            this.periodoFine = periodoFine;
            this.nomeAzienda = nomeAzienda;
            this.mansioniSvolte = mansioniSvolte;
            this.luogoLavoro = luogoLavoro;
            this.retribuzione = retribuzione;
        }

        public Button getBtnDelete(){
            return btnDelete;
        }
        public String getPeriodoInizio() {
            return periodoInizio;
        }
        public String getPeriodoFine() {
            return periodoFine;
        }
        public String getNomeAzienda() {
            return nomeAzienda;
        }
        public String getRetribuzione() {
            return retribuzione;
        }
        public String getMansioniSvolte() {
            return mansioniSvolte;
        }
        public String getLuogoLavoro() {
            return luogoLavoro;
        }
    }
}
