package com.univr.graphics.components.custom;

import com.univr.anagrafica.Data;
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
     * @param gridPane
     * @param colonna
     * @param riga
     */
    public DatePickerCustom (GridPane gridPane, int colonna, int riga) {
        datePicker.setShowWeekNumbers(false);
        gridPane.add(datePicker, colonna, riga);
    }

    /**
     * Metodo per l'inserimento di un DatePicker in GridPane con un testo di prompt
     * @param promptText
     * @param gridPane
     * @param colonna
     * @param riga
     */
    public DatePickerCustom (String promptText, GridPane gridPane, int colonna, int riga) {
        datePicker.setShowWeekNumbers(false);
        datePicker.setPromptText(promptText);
        gridPane.add(datePicker, colonna, riga);
    }

    /**
     * Metodo per ottenere la conversione del valore in Data
     * @return
     */
    public Data getData () {
        LocalDate data = datePicker.getValue();
        try {
            return new Data(data.getDayOfMonth(), data.getMonthValue(), data.getYear());
        } catch (Exception e) {
            throw new IllegalArgumentException("Data non valida o mancante");
        }
    }

    /**
     * Metodo per ottenere il valore del DatePicker
     * @return
     */
    public LocalDate getValue() {
        return datePicker.getValue();
    }

    /**
     * Metodo per ottenere il DatePicker
     * @return
     */
    public DatePicker getDatePicker () {
        return datePicker;
    }

    /**
     * Metodo utilizzato per impostare un testo di prompt
     * @param stringa
     */
    public void setPromptText(String stringa) {
        datePicker.setPromptText(stringa);
    }

    // TODO DA RIMUOVERE; SOLO DEBUG
    public void setDatePicker(LocalDate localDate) {
        datePicker.setValue(localDate);
    }
}