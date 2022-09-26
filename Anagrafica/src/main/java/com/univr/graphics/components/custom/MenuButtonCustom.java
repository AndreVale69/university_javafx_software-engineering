package com.univr.graphics.components.custom;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Classe per la creazione/gestione dei menù a tendina.
 */
public class MenuButtonCustom {
    private final MenuButton menuButton = new MenuButton();
    private String pathAttuale;

    /**
     * Crea un menù all'interno di una cella di GridPane.
     * @param string  impostata come setText.
     * @param gridPane in cui inserire il menù.
     * @param col  indice.
     * @param row     indice.
     */
    public MenuButtonCustom (String string, GridPane gridPane, int col, int row) {
        menuButton.setText(string);
        menuButton.setMaxHeight(Double.MAX_VALUE);
        menuButton.setMaxWidth(Double.MAX_VALUE);
        gridPane.add(menuButton, col, row);
    }

    /**
     * Crea un menù all'interno di una cella di GridPane con possibilità di estendere colonne e righe.
     * @param string       impostata come setText.
     * @param gridPane      in cui inserire il menù.
     * @param col       indice.
     * @param row          indice.
     * @param extendCols quante colonne si estende.
     * @param extendRows    quante righe si estende.
     */
    public MenuButtonCustom (String string, GridPane gridPane, int col, int row, int extendCols, int extendRows) {
        menuButton.setText(string);
        menuButton.setMaxHeight(Double.MAX_VALUE);
        menuButton.setMaxWidth(Double.MAX_VALUE);
        gridPane.add(menuButton, col, row, extendCols, extendRows);
    }

    /**
     * Creazione di un menù con selezione multipla.
     * @param fileName nome del file da cui prendere le opzioni.
     * @return array con le voci del menù.
     */
    public CheckMenuItem[] checkMenuItems (String fileName) {
        pathAttuale = System.getProperty("user.dir") + "\\src\\main\\resources\\com\\univr\\anagrafica\\" + fileName;

        return createCheckMenuItem(pathAttuale);
    }

    /**
     * Supporto per la creazione di un menù con selezione multipla.
     * @param filePath posizione del file all'interno del progetto.
     * @return array con le voci del menù.
     */
    private CheckMenuItem[] createCheckMenuItem (String filePath) {
        String read;
        String[] items;

        try {
            read = Files.readString(Path.of(filePath));

            items = read.split(",");

            final CheckMenuItem[] checkMenuItems = new CheckMenuItem[items.length];

            // Creazione tendina
            for (int j = 0; j < items.length ; j++) {
                // Crea oggetto
                checkMenuItems[j] = new CheckMenuItem(items[j]);
                // Aggiunge elemento al menu
                menuButton.getItems().add(checkMenuItems[j]);
            }

            return checkMenuItems;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo per la creazione di un menù a scelta singola
     * @param fileName nome file
     * @return menù a tendina
     */
    public RadioMenuItem[] radioMenuItems (String fileName) {
        pathAttuale = System.getProperty("user.dir") + "\\src\\main\\resources\\com\\univr\\anagrafica\\" + fileName;

        return createRadioMenuItem(pathAttuale);
    }

    /**
     * Metodo di supporto per la creazione di un menù a scelta singola
     * @param filePath percorso del file
     * @return menù a tendina
     */
    private RadioMenuItem[] createRadioMenuItem (String filePath) {
        String read;
        String[] items;

        try {
            read = Files.readString(Path.of(filePath));

            items = read.split(",");

            final RadioMenuItem[] radioMenuItems = new RadioMenuItem[items.length];

            // Creazione tendina
            ToggleGroup toggleGroup = new ToggleGroup();
            for (int j = 0; j < items.length ; j++) {
                // Crea oggetto
                radioMenuItems[j] = new RadioMenuItem(items[j]);
                toggleGroup.getToggles().add(radioMenuItems[j]);
                // Aggiunge elemento al menu
                menuButton.getItems().add(radioMenuItems[j]);
            }

            return radioMenuItems;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
