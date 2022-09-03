package com.univr.graphics.components.custom;

import com.univr.anagrafica.Lavoratore;
import com.univr.anagrafica.Lavoro;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;


public class TableViewLavoro extends TableViewCustom {

    public TableViewLavoro(GridPane gridPane, String[] nomiColonna, String[] nomiValueFactory, int colonna, int riga, int extendColonna, int extendRiga) {
        super(gridPane, nomiColonna, nomiValueFactory, colonna, riga, extendColonna, extendRiga);
    }

    public void addItems(Object ... objects) {
        TableLavoro tableLavoro = new TableLavoro(((Button)objects[0]), ((String)objects[1]), ((String)objects[2]), ((String)objects[3]), ((String)objects[4]), ((String)objects[5]), ((String)objects[6]));

        // Aggiunta alla tabella visiva il lavoro stagionale
        getTableView().getItems().add(tableLavoro);

        Lavoro tmpLavoro = ((Lavoro)objects[8]);
        Lavoratore tmpLavoratore = ((Lavoratore)objects[7]);

        // Eventuale eliminazione dal file e visivamente
        ((Button)objects[0]).setOnAction(actionEvent -> {
            tmpLavoratore.delLavoro(tmpLavoro);
            Lavoro.getLavoriBackup().remove(tmpLavoro);
            getTableView().getItems().remove(tableLavoro);
        });
    }

    public static class TableLavoro {
        private final Button btnDelete;
        private final String periodoInizio;
        private final String periodoFine;
        private final String nomeAzienda;
        private final String mansioniSvolte;
        private final String luogoLavoro;
        private final String retribuzione;

        TableLavoro(Button btnDelete, String periodoInizio, String periodoFine, String nomeAzienda, String mansioniSvolte, String luogoLavoro, String retribuzione) {
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
