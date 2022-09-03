package com.univr.graphics.components.custom;

import com.univr.anagrafica.Lavoratore;
import com.univr.anagrafica.Lavoro;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;


public abstract class TableViewCustom {

    private TableView<Object> tableView = new TableView<>();

    public TableViewCustom(GridPane gridPane, String[] nomiColonna, String[] nomiValueFactory, int colonna, int riga, int extendColonna, int extendRiga) {

        for (int i = 0; i < nomiColonna.length; i ++) {
            TableColumn<Object, Object> column = new TableColumn<>(nomiColonna[i]);
            column.setCellValueFactory(new PropertyValueFactory<>(nomiValueFactory[i]));
            tableView.getColumns().add(column);
        }

        gridPane.add(tableView, colonna, riga, extendColonna, extendRiga);
    }

    public TableView<Object> getTableView() { return tableView; }

    public abstract void addItems(Object ... objects);

    public void setMaxSizeTableView(int altezza, int larghezza) {
        getTableView().setMaxSize(altezza, larghezza);
    }
}
