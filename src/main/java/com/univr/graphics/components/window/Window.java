package com.univr.graphics.components.window;

import com.univr.anagrafica.Lavoratore;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public abstract class Window {

    /**
     * Metodo utilizzato per impostare il contenuto della finestra
     * @param primaryStage processo della finestra
     * @param lavoratore
     */
    public abstract void createWindow (Stage primaryStage, Lavoratore lavoratore);

    /**
     * Metodo utilizzato per ottenere gli oggetti visuali presenti nella finestra
     * @return array di oggetti
     */
    public abstract Object[] getObjects();

    /**
     * Metodo utilizzato per impostare la finestra con un nuovo BorderPane
     * @param stage
     * @param titolo
     * @param width
     * @param height
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
