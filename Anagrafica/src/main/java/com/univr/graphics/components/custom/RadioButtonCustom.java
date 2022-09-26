package com.univr.graphics.components.custom;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

/**
 * Classe per la creazione/gestione dei RadioButton
 */
public class RadioButtonCustom {
    private final RadioButton radioButton = new RadioButton();

    /**
     * Metodo per l'inserimento di un RadioButton in GridPane
     * @param string   stringa
     * @param gridPane gridPane
     * @param col      colonna
     * @param row      riga
     */
    public RadioButtonCustom (String string, GridPane gridPane, int col, int row) {
        radioButton.setText(string);
        if (string.equals("NONE"))
            radioButton.fire();
        gridPane.add(radioButton, col, row);
    }

    /**
     * Metodo per reperire il campo radioButton
     * @return oggetto
     */
    public RadioButton getRadioButton () {
        return radioButton;
    }

    /**
     * Metodo per inserire fino a tre RadioButton in un gruppo toggle
     * @param toggleGroup  gruppo toggle
     * @param radioButton1 primo bottone
     * @param radioButton2 secondo bottone
     */
    public void setMultiToggleGroup (ToggleGroup toggleGroup, RadioButton radioButton1, RadioButton radioButton2) {
        radioButton.setToggleGroup(toggleGroup);
        radioButton1.setToggleGroup(toggleGroup);
        radioButton2.setToggleGroup(toggleGroup);
    }

    public boolean isSelected() {
        return radioButton.isSelected();
    }
}
