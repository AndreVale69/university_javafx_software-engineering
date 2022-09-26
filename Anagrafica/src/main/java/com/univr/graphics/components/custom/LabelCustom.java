package com.univr.graphics.components.custom;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * Classe per la creazione/gestione delle etichette
 */
public class LabelCustom{
    private final Label label = new Label();

    /**
     * Metodo per creazione e posizionamento di una label in cella singola
     * @param string   stringa
     * @param gridPane gridPane
     * @param col      colonna
     * @param row      riga
     */
    public LabelCustom (String string, GridPane gridPane, int col, int row) {
        label.setText(string);
        gridPane.add(label, col, row);
    }

    /**
     * Metodo per creazione e posizionamento di una label in cella singola
     * @param string   stringa
     * @param gridPane gridPane
     * @param col      colonna
     * @param row      riga
     */
    public LabelCustom (String string, GridPane gridPane, int col, int row, int extendCols, int extendRows) {
        label.setText(string);
        gridPane.add(label, col, row, extendCols, extendRows);
    }

    /**
     * Metodo per creazione e posizionamento di una label in righe multiple
     * @param string   stringa
     * @param gridPane gridPane
     * @param col      colonna
     * @param row      riga
     */
    public LabelCustom (String[] string, GridPane gridPane, int col, int[] row) {
        for (int i = 0; i < string.length; i++) {
            final Label label = new Label();
            label.setText(string[i]);
            gridPane.add(label, col, row[i]);
        }
    }

    /**
     * Metodo per impostare lo stile del messaggio di errore
     * @param style stile
     */
    public void settingStyle (String style) {
        label.setStyle(style);
    }
}