package com.univr.graphics.components.custom;

import com.univr.anagrafica.Lavoratore;
import com.univr.graphics.GridPaneCustom;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

/**
 * Classe per la creazione/gestione delle etichette
 */
public class LabelCustom{
    private final Label label = new Label();

    /**
     * Metodo per creazione e posizionamento di una label in cella singola
     * @param stringa
     * @param gridPane
     * @param colonna
     * @param riga
     */
    public LabelCustom (String stringa, GridPane gridPane, int colonna, int riga) {
        label.setText(stringa);
        gridPane.add(label, colonna, riga);
    }

    /**
     * Metodo per creazione e posizionamento di una label in cella singola
     * @param stringa
     * @param gridPane
     * @param colonna
     * @param riga
     */
    public LabelCustom (String stringa, GridPane gridPane, int colonna, int riga, int extendColonna, int extendRiga) {
        label.setText(stringa);
        gridPane.add(label, colonna, riga, extendColonna, extendRiga);
    }

    /**
     * Metodo per creazione e posizionamento di una label in colonne e righe multiple
     * @param stringa
     * @param gridPane
     * @param colonna
     * @param riga
     */
    public LabelCustom (String[] stringa, GridPane gridPane, int[] colonna, int[] riga) {
        for (int i = 0; i < stringa.length; i++) {
            final Label label = new Label();
            label.setText(stringa[i]);
            gridPane.add(label, colonna[i], riga[i]);
        }
    }

    /**
     * Metodo per creazione e posizionamento di una label in righe multiple
     * @param stringa
     * @param gridPane
     * @param colonna
     * @param riga
     */
    public LabelCustom (String[] stringa, GridPane gridPane, int colonna, int[] riga) {
        for (int i = 0; i < stringa.length; i++) {
            final Label label = new Label();
            label.setText(stringa[i]);
            gridPane.add(label, colonna, riga[i]);
        }
    }

    /**
     * Metodo per creazione e posizionamento di una label in colonne multiple
     * @param stringa
     * @param gridPane
     * @param colonna
     * @param riga
     */
    public LabelCustom (String[] stringa, GridPane gridPane, int[] colonna, int riga) {
        for (int i = 0; i < stringa.length; i++) {
            final Label label = new Label();
            label.setText(stringa[i]);
            gridPane.add(label, colonna[i], riga);
        }
    }

    /**
     * Metodo per creazione e posizionamento di una label in colonne multiple
     * @param stringa
     * @param gridPane
     * @param colonna
     * @param riga
     */
    public LabelCustom (ArrayList<String> stringa, GridPane gridPane, int colonna, int riga) {
        gridPane.add(label, colonna, riga);
        int index = 1;

        for (String string : stringa) {
            if (index == stringa.size())
                label.setText(label.getText() + string);
            else
                label.setText(label.getText() + string + ", ");
            index++;
        }
    }

    /**
     * Metodo per impostare lo stile del messaggio di errore
     * @param stile
     */
    public void settingStyle (String stile) {
        label.setStyle(stile);
    }
}