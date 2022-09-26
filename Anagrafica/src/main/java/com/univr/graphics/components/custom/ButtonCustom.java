package com.univr.graphics.components.custom;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

/**
 * Classe che crea un pulsante e ne consente la modifica/aggiunta a video
 */
public class ButtonCustom {
    private final Button button = new Button();

    /**
     * Utilizzato per inserire pulsante in un GridPane
     * @param string   testo del pulsante
     * @param gridPane gridPane
     * @param col      colonna
     * @param row      riga
     */
    public ButtonCustom (String string, SceneBuilder gridPane, int col, int row) {
        button.setText(string);
        button.setMaxHeight(Double.MAX_VALUE);
        button.setMaxWidth(Double.MAX_VALUE);
        gridPane.getGridPane().add(button, col, row);
    }

    /**
     * Utilizzato per inserire pulsante in un GridPane, imposta il numero di colonne/righe occupate dal pulsante
     * @param string     stringa da visualizzare sul bottone
     * @param gridPane   gridPane
     * @param col        colonna
     * @param row        riga
     * @param extendCols colonne da estendere
     * @param extendRows righe da estendere
     */
    public ButtonCustom (String string, GridPane gridPane, int col, int row, int extendCols, int extendRows) {
        button.setText(string);
        button.setMaxHeight(Double.MAX_VALUE);
        button.setMaxWidth(Double.MAX_VALUE);
        gridPane.add(button, col, row, extendCols, extendRows);
    }

    /**
     * Utilizzato per impostare lo stile del pulsante.
     * @param style stile da impostare
     */
    public void settingStyle (String style) {
        button.setStyle(style);
    }

    /**
     * Si ottiene il bottone.
     * @return bottone.
     */
    public Button getButton() {
        return this.button;
    }
}
