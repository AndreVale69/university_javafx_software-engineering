package com.univr.graphics.components.custom;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

/**
 * Interfaccia per la estensione dei metodi utilizzati nel settaggio della TableView
 */
public abstract class TableViewCustom {

    private final TableView<Object> tableView = new TableView<>();

    public TableViewCustom(GridPane gridPane, String[] colNames, String[] namesValueFactory, int col, int row, int extendCols, int extendRows) {

        for (int i = 0; i < colNames.length; i ++) {
            TableColumn<Object, Object> column = new TableColumn<>(colNames[i]);
            column.setCellValueFactory(new PropertyValueFactory<>(namesValueFactory[i]));
            tableView.getColumns().add(column);
        }

        gridPane.add(tableView, col, row, extendCols, extendRows);
    }

    public TableView<Object> getTableView() { return tableView; }

    public abstract void addItems(Object ... objects);

    public abstract void setMaxSizeTableView(double width, double height);
}
