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
     * @param stringa
     * @param gridPane
     * @param colonna
     * @param riga
     */
    public RadioButtonCustom (String stringa, GridPane gridPane, int colonna, int riga) {
        radioButton.setText(stringa);
        if (stringa.equals("NONE"))
            radioButton.fire();
        gridPane.add(radioButton, colonna, riga);
    }

    /**
     * Metodo per reperire il campo radioButton
     * @return
     */
    public RadioButton getRadioButton () {
        return radioButton;
    }

    /**
     * Metodo per inserire fino a tre RadioButton in un gruppo toggle
     * @param toggleGroup
     * @param radioButton1
     * @param radioButton2
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
