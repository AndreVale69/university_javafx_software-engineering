package com.univr.graphics;

import com.univr.anagrafica.Business;
import com.univr.graphics.components.windows.WindowFactory;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * Classe per la creazione della finestra (where it all started)
 */
public class Main extends Application {

    public static void main(String[] args){
        Application.launch(args);
    }

    /**
     * Metodo chiamato dal main al lancio dell'applicazione
     * @param primaryStage stage dell'applicazione
     */
    @Override
    public void start(Stage primaryStage){

        // Creazione di finestra di warning con conferma al momento della chiusura
        primaryStage.setOnCloseRequest(windowEvent -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.getButtonTypes().remove(ButtonType.CANCEL);
            alert.getButtonTypes().remove(ButtonType.OK);
            alert.getButtonTypes().add(ButtonType.NO);
            alert.getButtonTypes().add(ButtonType.YES);
            alert.setTitle("Uscita");
            alert.setContentText("Sicuro di voler uscire?");
            alert.setHeaderText("");
            Optional<ButtonType> res = alert.showAndWait();

            if(res.isPresent() && res.get().equals(ButtonType.YES))
                Business.getInstance().toFile();
            else
                windowEvent.consume();
        });

        WindowFactory.getWindow("LOGIN").createWindow(primaryStage, null, null, null);
    }
}
