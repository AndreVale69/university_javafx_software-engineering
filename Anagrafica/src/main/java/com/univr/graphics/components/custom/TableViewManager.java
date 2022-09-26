package com.univr.graphics.components.custom;

import com.univr.anagrafica.Business;
import com.univr.anagrafica.Manager;
import com.univr.graphics.components.windows.Window;
import com.univr.graphics.components.windows.WindowFactory;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TableViewManager extends TableViewCustom {

    public TableViewManager(GridPane gridPane, String[] colNames, String[] namesValueFactory, int col, int row, int extendCols, int extendRows) {
        super(gridPane, colNames, namesValueFactory, col, row, extendCols, extendRows);
    }

    // Nome, cognome, luogo di nascita, data di nascita, nazionalità, email, telefono,
    // bottone modifica, bottone elimina, responsabile
    @Override
    public void addItems(Object... objects) {
        ManagerRow managerRow = new ManagerRow(
                ((String)objects[0]),
                ((String)objects[1]),
                ((String)objects[2]),
                ((String)objects[3]),
                ((String)objects[4]),
                ((String)objects[5]),
                ((String)objects[6]),
                ((Button)objects[7]),
                ((Button)objects[8]));

        // Aggiunta alla tabella visiva il responsabile
        getTableView().getItems().add(managerRow);

        Manager tmpManager = ((Manager)objects[9]);
        Stage tmpStage = ((Stage) objects[10]);

        // Modifica del responsabile
        ((Button)objects[7]).setOnAction(actionEvent -> {
            Window window = WindowFactory.getWindow("MODIFYMANAGER");
            window.createWindow(tmpStage, null, null, tmpManager);
            Object[] fields = window.getObjects();
            // Riempimento in automatico dei campi
            Events.autoFillManager(fields, tmpManager);
        });

        // Eliminazione dal file e dalla tabella
        ((Button)objects[8]).setOnAction(actionEvent -> {
            Business.getInstance().removeManager(tmpManager);
            getTableView().getItems().remove(managerRow);
        });
    }

    @Override
    public void setMaxSizeTableView(double width, double height) {
        getTableView().setMinSize(width, height);
        getTableView().setMaxSize(width, height);

        for (TableColumn<Object, ?> column : getTableView().getColumns())
            column.prefWidthProperty().bind(getTableView().widthProperty().multiply(0.111));
    }

    public static class ManagerRow {
        private final String nome;
        private final String cognome;
        private final String luogoDiNascita;
        private final String dataDiNascita;
        private final String nazionalita;
        private final String email;
        private final String telefono;
        private final Button btnModify;
        private final Button btnDelete;

        ManagerRow(String nome,
                   String cognome,
                   String luogoDiNascita,
                   String dataDiNascita,
                   String nazionalita,
                   String email,
                   String telefono,
                   Button btnModify,
                   Button btnDelete) {
            this.nome = nome;
            this.cognome = cognome;
            this.luogoDiNascita = luogoDiNascita;
            this.dataDiNascita = dataDiNascita;
            this.nazionalita = nazionalita;
            this.email = email;
            this.telefono = telefono;
            this.btnModify = btnModify;
            this.btnDelete = btnDelete;
        }

        public String getNome() {
            return nome;
        }
        public String getCognome() {
            return cognome;
        }
        public String getLuogoDiNascita() {
            return luogoDiNascita;
        }
        public String getDataDiNascita() {
            return dataDiNascita;
        }
        public String getNazionalita() {
            return nazionalita;
        }
        public String getEmail() {
            return email;
        }
        public String getTelefono() {
            return telefono;
        }
        public Button getBtnModify(){
            return btnModify;
        }
        public Button getBtnDelete(){
            return btnDelete;
        }
    }
}
