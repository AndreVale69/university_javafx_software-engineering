package com.univr.graphics.components.custom;

import com.univr.anagrafica.DateCustom;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;

/**
 * Classe per la creazione/gestione dei DatePicker
 */
public class DatePickerCustom {
    private final DatePicker datePicker = new DatePicker();

    /**
     * Metodo per l'inserimento di un DatePicker in GridPane
     * @param gridPane gridPane
     * @param col      colonna
     * @param row      riga
     */
    public DatePickerCustom (GridPane gridPane, int col, int row) {
        datePicker.setEditable(false);
        datePicker.setShowWeekNumbers(false);
        gridPane.add(datePicker, col, row);
    }

    /**
     * Metodo per l'inserimento di un DatePicker in GridPane con un testo di prompt
     * @param promptText prompt text
     * @param gridPane   gridPane
     * @param col        colonna
     * @param row        riga
     */
    public DatePickerCustom (String promptText, GridPane gridPane, int col, int row) {
        datePicker.setEditable(false);
        datePicker.setShowWeekNumbers(false);
        datePicker.setPromptText(promptText);
        gridPane.add(datePicker, col, row);
    }

    /**
     * Metodo per ottenere la conversione del valore in Data
     * @return Data
     */
    public DateCustom getDateCustom() {
        LocalDate date = datePicker.getValue();
        try {
            return new DateCustom(date.getDayOfMonth(), date.getMonthValue(), date.getYear());
        } catch (Exception e) {
            throw new IllegalArgumentException("Data non valida o mancante");
        }
    }

    /**
     * Metodo per ottenere il valore del DatePicker
     * @return LocalDate della data selezionata
     */
    public LocalDate getValue() {
        return datePicker.getValue();
    }

    /**
     * Metodo utilizzato per impostare un testo di prompt
     * @param string stringa prompt
     */
    public void setPromptText(String string) {
        datePicker.setPromptText(string);
    }

    // TODO DA RIMUOVERE; SOLO DEBUG
    public void setDatePicker(LocalDate localDate) {
        datePicker.setValue(localDate);
    }
}