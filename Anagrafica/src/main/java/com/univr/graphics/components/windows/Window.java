package com.univr.graphics.components.windows;

import com.univr.anagrafica.Worker;
import com.univr.anagrafica.Manager;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public abstract class Window {

    protected Object[] objects = new Object[37];

    /**
     * Utilizzato per impostare il contenuto della finestra
     * @param primaryStage processo della finestra
     * @param worker       lavoratore stagionale
     */
    public abstract void createWindow (Stage primaryStage, Worker worker, Worker old, Manager manager);

    /**
     * Utilizzato per ottenere gli oggetti visuali presenti nella finestra
     * @return array di oggetti
     */
    public Object[] getObjects() { return objects; }

    /**
     * Utilizzato per impostare la finestra con un nuovo BorderPane
     * @param stage  stage
     * @param titolo titolo
     * @param width  larghezza
     * @param height altezza
     * @return il nuovo BorderPane
     */
    protected BorderPane setWindow(Stage stage, String titolo, double width, double height) {
        stage.setTitle(titolo);

        // Creazione root e scena
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, width, height);

        // Settaggio della scena
        stage.setScene(scene);

        // Imposta la schermata al centro a seconda della risoluzione dello schermo
        stage.centerOnScreen();

        return root;
    }
}
