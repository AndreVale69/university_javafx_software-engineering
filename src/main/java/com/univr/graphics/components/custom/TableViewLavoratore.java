package com.univr.graphics.components.custom;

import com.univr.anagrafica.Agenzia;
import com.univr.anagrafica.Lavoratore;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;


public class TableViewLavoratore extends TableViewCustom {

    public TableViewLavoratore(GridPane gridPane, String[] nomiColonna, String[] nomiValueFactory, int colonna, int riga, int extendColonna, int extendRiga) {
        super(gridPane, nomiColonna, nomiValueFactory, colonna, riga, extendColonna, extendRiga);
    }

    // String 0 nome, String 1 cognome, ArrayList<String> 2 lingue, String 3 residenza, ArrayList<String> 4 patenti, boolean 5 automunito, Button 6 btnModify, Button 7 btnDelete, Lavoratore 8 lavoratore

    @Override
    public void addItems(Object ... objects) {
        // TODO MIGLIORARE IL BACK-END!!!
        String lingue = ((ArrayList)objects[2]).toString();
        String patenti = ((ArrayList)objects[4]).toString();

        lingue = lingue.substring(lingue.indexOf("[") + 1,  lingue.indexOf("]"));
        patenti = patenti.substring(patenti.indexOf("[") + 1,  patenti.indexOf("]"));


        TableLavoratore tableLavoratore = new TableLavoratore(((String)objects[0]), ((String)objects[1]), lingue, ((String)objects[3]), patenti, ((boolean)objects[5]), ((Button)objects[6]), ((Button)objects[7]));

        // Aggiunta alla tabella visiva il lavoro stagionale
        getTableView().getItems().add(tableLavoratore);

        //
        Lavoratore tmp = ((Lavoratore)objects[8]);

        // Eventuale eliminazione dal file e visivamente
        ((Button)objects[7]).setOnAction(actionEvent -> {
            Agenzia.getInstance().delLavoratore(tmp);
            Agenzia.getLavoratoriBackup().remove(tmp);
            getTableView().getItems().remove(tableLavoratore);
        });

        // TODO: apertura pagina modifica
        ((Button)objects[6]).setOnAction(actionEvent -> {
            // TODO
        });
    }

    public static class TableLavoratore {
        private final String nome;
        private final String cognome;
        private final String lingue;
        private final String residenza;
        private final String patenti;
        private final boolean automunito;
        private final Button btnModify;
        private final Button btnDelete;

        TableLavoratore(String nome, String cognome, String lingue, String residenza, String patenti,
                        boolean automunito, Button btnModify, Button btnDelete) {
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
        public boolean getAutomunito() { return automunito; }
        public Button getBtnModify() { return btnModify; }
        public Button getBtnDelete() { return btnDelete; }
    }
}
