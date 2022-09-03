package com.univr.graphics.components.custom;

import com.univr.graphics.GridPaneCustom;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Classe per la creazione/gestione dei TextField
 */
public class TextFieldCustom{
    private final TextField textField = new TextField();

    /**
     * Metodo per l'inserimento del TextField nel GridPane
     * @param gridPane
     * @param colonna
     * @param riga
     */
    public TextFieldCustom (GridPaneCustom gridPane, int colonna, int riga) {
        gridPane.getGridPane().add(textField, colonna, riga);
    }

    /**
     * Metodo per l'inserimento del TextField nel GridPane con un testo di prompt
     * @param promptText
     * @param gridPane
     * @param colonna
     * @param riga
     */
    public TextFieldCustom (String promptText, GridPane gridPane, int colonna, int riga) {
        textField.setPromptText(promptText);
        gridPane.add(textField, colonna, riga);
    }

    /**
     * Metodo per reperire il testo all'interno del TextField
     * @return
     */
    public String getText() {
        return textField.getText();
    }

    // TODO DA RIMUOVERE; SOLO DEBUG
    public void setText(String string) {
        textField.setText(string);
    }
}
