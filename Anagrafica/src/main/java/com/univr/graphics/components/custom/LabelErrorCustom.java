package com.univr.graphics.components.custom;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * Classe per la creazione/gestione dei messaggi d'errore
 */
public class LabelErrorCustom {
    private final Label label = new Label();

    /**
     * Metodo per creazione e posizionamento di un errore in cella singola
     * @param stringa  stringa
     * @param gridPane gridPane
     * @param colonna  colonna
     * @param riga     riga
     */
    public LabelErrorCustom (String stringa, SceneBuilder gridPane, int colonna, int riga) {
        label.setText(stringa);
        label.setVisible(false);
        settingStyle();
        gridPane.getGridPane().add(label, colonna, riga);
    }

    /**
     * Metodo per creazione e posizionamento di un errore in cella singola
     * @param stringa  stringa
     * @param gridPane gridPane
     * @param colonna  colonna
     * @param riga     riga
     */
    public LabelErrorCustom (String stringa, GridPane gridPane, int colonna, int riga, int extendColumns, int extendRows) {
        label.setText(stringa);
        label.setVisible(false);
        settingStyle();
        gridPane.add(label, colonna, riga, extendColumns, extendRows);
    }

    /**
     * Ritorna il testo
     */
    public String getText() {
        return this.label.getText();
    }

    /**
     * Metodo per impostare lo stile del messaggio di errore
     */
    private void settingStyle () {
        final String errorStyle = """
                    -fx-text-fill: red;
                    -fx-font-weight: bold;
                    """;
        label.setStyle(errorStyle);
    }

    /**
     * Metodo per visualizzare il messaggio di errore
     * @param message messaggio
     */
    public void activatingError (String message) {
        label.setText(message);
        label.setVisible(true);
    }

    /**
     * Metodo per visualizzare il messaggio di errore
     */
    public void activatingError () {
        label.setVisible(true);
    }

    /**
     * Metodo per visualizzare il messaggio di errore
     */
    public void deactivatingError () {
        label.setVisible(false);
    }
}
