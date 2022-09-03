package com.univr.graphics.components.custom;

import com.univr.graphics.GridPaneCustom;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * Classe per la creazione/gestione dei messaggi d'errore
 */
public class LabelErrorCustom {
    private final Label label = new Label();

    /**
     * Metodo per creazione e posizionamento di un errore in cella singola
     * @param stringa
     */
    public LabelErrorCustom (String stringa) {
        label.setText(stringa);
        settingStyle();
    }

    /**
     * Metodo per creazione e posizionamento di un errore in cella singola
     * @param stringa
     * @param gridPane
     * @param colonna
     * @param riga
     */
    public LabelErrorCustom (String stringa, GridPaneCustom gridPane, int colonna, int riga) {
        label.setText(stringa);
        label.setVisible(false);
        settingStyle();
        gridPane.getGridPane().add(label, colonna, riga);
    }

    /**
     * Metodo per creazione e posizionamento di un errore in cella singola
     * @param stringa
     * @param gridPane
     * @param colonna
     * @param riga
     */
    public LabelErrorCustom (String stringa, GridPane gridPane, int colonna, int riga, int extendColumns, int extendRows) {
        label.setText(stringa);
        label.setVisible(false);
        settingStyle();
        gridPane.add(label, colonna, riga, extendColumns, extendRows);
    }

    /**
     * Metodo per creazione e posizionamento di un errore in colonne e righe multiple
     * @param stringa
     * @param gridPane
     * @param colonna
     * @param riga
     */
    public LabelErrorCustom (String[] stringa, GridPaneCustom gridPane, int[] colonna, int[] riga) {
        for (int i = 0; i < stringa.length; i++) {
            label.setText(stringa[i]);
            label.setVisible(false);
            settingStyle();
            gridPane.getGridPane().add(label, colonna[i], riga[i]);
        }
    }

    /**
     * Metodo per creazione e posizionamento di un errore in righe multiple
     * @param stringa
     * @param gridPane
     * @param colonna
     * @param riga
     */
    public LabelErrorCustom (String[] stringa, GridPaneCustom gridPane, int colonna, int[] riga) {
        for (int i = 0; i < stringa.length; i++) {
            label.setText(stringa[i]);
            label.setVisible(false);
            settingStyle();
            gridPane.getGridPane().add(label, colonna, riga[i]);
        }
    }

    /**
     * Metodo per creazione e posizionamento di un errore in colonne multiple
     * @param stringa
     * @param gridPane
     * @param colonna
     * @param riga
     */
    public LabelErrorCustom (String[] stringa, GridPaneCustom gridPane, int[] colonna, int riga) {
        for (int i = 0; i < stringa.length; i++) {
            label.setText(stringa[i]);
            label.setVisible(false);
            settingStyle();
            gridPane.getGridPane().add(label, colonna[i], riga);
        }
    }

    /**
     * Ritorna l'oggetto
     */
    public Label getLabel() {
        return this.label;
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
        final String stileErrore = new String("""
                    -fx-text-fill: red;
                    -fx-font-weight: bold;
                    """);
        label.setStyle(stileErrore);
    }

    /**
     * Metodo per visualizzare il messaggio di errore
     * @param messaggio
     */
    public void activatingError (String messaggio) {
        label.setText(messaggio);
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
