package com.univr.graphics;

import com.univr.anagrafica.*;
import com.univr.graphics.components.window.WindowFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Classe per la creazione della finestra (where it all started)
 */
public class Main extends Application {
    final static Agenzia agenzia = Agenzia.getInstance();

    public static void main(String[] args){
        Application.launch(args);
    }

    /**
     * Metodo chiamato dal main al lancio dell'applicazione
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage){
        WindowFactory.getWindow("LOGIN").createWindow(primaryStage, null);
    }

    /**
     * Metodo utilizzato per impostare la finestra con un nuovo BorderPane
     * @param stage
     * @param titolo
     * @param width
     * @param height
     * @return il nuovo BorderPane
     */
    public static BorderPane setWindow(Stage stage, String titolo, double width, double height) {
        stage.setTitle(titolo);

        // Creazione root e scena
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, width, height);

        // Settaggio della scena
        stage.setScene(scene);

        return root;
    }
}
