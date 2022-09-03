package com.univr.graphics.components.custom;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Classe per la creazione/gestione dei menù a tendina
 */
public class MenuButtonCustom {
    private final MenuButton menuButton = new MenuButton();
    private String pathAttuale;

    /**
     * Costruttore che crea un menù della dimensione massima possibile
     * @param stringa
     */
    public MenuButtonCustom (String stringa) {
        menuButton.setText(stringa);
        menuButton.setMaxHeight(Double.MAX_VALUE);
        menuButton.setMaxWidth(Double.MAX_VALUE);
    }

    /**
     * Costruttore che crea un menù all'interno di una cella di GridPane
     * @param stringa
     * @param gridPane
     * @param colonna
     * @param riga
     */
    public MenuButtonCustom (String stringa, GridPane gridPane, int colonna, int riga) {
        menuButton.setText(stringa);
        menuButton.setMaxHeight(Double.MAX_VALUE);
        menuButton.setMaxWidth(Double.MAX_VALUE);
        gridPane.add(menuButton, colonna, riga);
    }

    /**
     * Metodo per la creazione di un menù a scelta multipla
     * @param nomeFile nome del file da cui prendere le opzioni
     * @return
     */
    public CheckMenuItem[] checkMenuItems (String nomeFile) {
        pathAttuale = System.getProperty("user.dir") + "\\src\\main\\resources\\com\\univr\\anagrafica\\" + nomeFile;

        return createCheckMenuItem(pathAttuale);
    }

    /**
     * Metodo di supporto per la creazione di un menù a scelta multipla
     * @param filePath
     * @return
     */
    private CheckMenuItem[] createCheckMenuItem (String filePath) {
        String read = "";
        String[] items = null;

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
     * @param nomeFile
     * @return
     */
    public RadioMenuItem[] radioMenuItems (String nomeFile) {
        pathAttuale = System.getProperty("user.dir") + "\\src\\main\\resources\\com\\univr\\anagrafica\\" + nomeFile;

        return createRadioMenuItem(pathAttuale);
    }

    /**
     * Metodo di supporto per la creazione di un menù a scelta singola
     * @param filePath
     * @return
     */
    private RadioMenuItem[] createRadioMenuItem (String filePath) {
        String read = "";
        String[] items = null;

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
