package com.univr.graphics.components.custom;

import javafx.scene.control.PasswordField;

/**
 * Classe per la creazione/gestione di un PasswordField
 */
public class PasswordFieldCustom {
    private final PasswordField passwordField = new PasswordField();

    /**
     * Metodo per l'inserimento di un PasswordField in GridPane
     * @param gridPane gridPane
     * @param col      colonna
     * @param row      riga
     */
    public PasswordFieldCustom (SceneBuilder gridPane, int col, int row) {
        gridPane.getGridPane().add(passwordField, col, row);
    }

    /**
     * Metodo per reperire il testo all'interno del PasswordField
     * @return password
     */
    public String getText() {
        return passwordField.getText();
    }

    // TODO RIMUOVERE! SOLO DEBUG
    public void setText(String string) {
        passwordField.setText(string);
    }
}