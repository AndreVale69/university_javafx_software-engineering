package com.univr.graphics.components.popup;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ErrorPopup implements Popup {
    @Override
    public void createPopup (String message) {
        Stage popUpWindow = new Stage();

        popUpWindow.centerOnScreen();

        popUpWindow.setResizable(false);

        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("Errore");

        // Aggiunta immagine errore
        ImageView imageView = null;
        try {
            // Creazione di un'immagine
            Image image = new Image(new FileInputStream("src/main/resources/com/univr/anagrafica/error_boy.png"));
            Image imageTitle = new Image(new FileInputStream("src/main/resources/com/univr/anagrafica/error_logo.png"));
            popUpWindow.getIcons().add(imageTitle);
            // Setting dell'immagine
            imageView = new ImageView(image);
            // Setting altezza e larghezza giusta dell'immagine
            imageView.setFitHeight(400);
            imageView.setFitWidth(400);
            // Setting giusto rapporto dell'immagine
            imageView.setPreserveRatio(true);
        } catch (FileNotFoundException ignored) {
        }

        // Messaggio d'errore
        Label lblError = new Label(message);

        // Bottone per tornare alla vecchia schermata
        Button btnBackMain = new Button("OK");
        btnBackMain.setOnAction(e -> popUpWindow.close());

        VBox layout = new VBox(15);

        layout.getChildren().addAll(new Label(""), new Label(""), lblError, btnBackMain, imageView);

        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 390, 450);
        popUpWindow.setScene(scene);

        popUpWindow.showAndWait();
    }
}
