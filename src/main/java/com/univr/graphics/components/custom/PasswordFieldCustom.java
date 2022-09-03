package com.univr.graphics.components.custom;

import com.univr.graphics.GridPaneCustom;
import javafx.scene.control.PasswordField;

/**
 * Classe per la creazione/gestione di un PasswordField
 */
public class PasswordFieldCustom {
    private final PasswordField passwordField = new PasswordField();

    /**
     * Metodo per l'inserimento di un PasswordField in GridPane
     * @param gridPane
     * @param colonna
     * @param riga
     */
    public PasswordFieldCustom (GridPaneCustom gridPane, int colonna, int riga) {
        gridPane.getGridPane().add(passwordField, colonna, riga);
    }

    /**
     * Metodo per reperire il testo all'interno del PasswordField
     * @return
     */
    public String getText() {
        return passwordField.getText();
    }

    // TODO RIMUOVERE! SOLO DEBUG
    public void setText(String string) {
        passwordField.setText(string);
    }
}