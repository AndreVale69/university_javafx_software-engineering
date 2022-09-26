package com.univr.graphics.components.custom;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Classe per la creazione/gestione dei TextField
 */
public class TextFieldCustom{
    private final TextField textField = new TextField();

    /**
     * Inserimento del TextField nel GridPane
     * @param gridPane gridPane
     * @param col      colonna
     * @param row      riga
     */
    public TextFieldCustom (SceneBuilder gridPane, int col, int row) {
        gridPane.getGridPane().add(textField, col, row);
    }

    /**
     * Inserimento del TextField nel GridPane con un testo di prompt.
     * @param promptText prompt text
     * @param gridPane   gridPane
     * @param col        colonna
     * @param row        riga
     */
    public TextFieldCustom (String promptText, GridPane gridPane, int col, int row) {
        textField.setPromptText(promptText);
        gridPane.add(textField, col, row);
    }

    /**
     * Inserimento del TextField nel GridPane con un testo di prompt e possibilità di estendere colonne e righe.
     * @param promptText prompt text
     * @param gridPane   gridPane
     * @param col        colonna
     * @param row        riga
     * @param extendCols numero di colonne da estendere
     * @param extendRows numero di righe da estendere
     */
    public TextFieldCustom (String promptText, GridPane gridPane, int col, int row, int extendCols, int extendRows) {
        textField.setPromptText(promptText);
        gridPane.add(textField, col, row, extendCols, extendRows);
    }

    /**
     * Metodo per reperire il testo all'interno del TextField
     * @return testo
     */
    public String getText() {
        if (textField.getText() == null)
            textField.setText("");
        if (textField.getText().contains(",") || textField.getText().contains("-") || textField.getText().contains(";"))
            throw new IllegalArgumentException ("Non sono ammessi i caratteri \" , ; - \" : " + textField.getPromptText());

        return textField.getText();
    }

    /**
     * Ritorna il contenuto inserito dallo user nel textfield di riferimento
     * @return test
     */
    // TODO: semplificazione usando una regex
    public String getTextTasks() {
        if (textField.getText().contains(",") || textField.getText().contains("-"))
            throw new IllegalArgumentException ("Non sono ammessi i caratteri \" , - \" : Mansioni svolte");

        //Pattern special = Pattern.compile ("[,;!@#$%&*()_+=|<>?{}\\[\\]~-]");

        return textField.getText();
    }

    // TODO DA RIMUOVERE; SOLO DEBUG
    public void setText(String string) {
        textField.setText(string);
    }
}
