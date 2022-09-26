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

public class WarningPopup implements Popup {
    @Override
    public void createPopup(String message) {
        Stage popUpWindow = new Stage();

        popUpWindow.centerOnScreen();

        popUpWindow.setResizable(false);

        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("Attenzione");

        // Aggiunta immagine warning
        ImageView imageView = null;
        try {
            // Creazione di un'immagine
            Image image = new Image(new FileInputStream("src/main/resources/com/univr/anagrafica/warning_logo.png"));
            Image imageTitle = new Image(new FileInputStream("src/main/resources/com/univr/anagrafica/warning_logo.png"));
            popUpWindow.getIcons().add(imageTitle);
            // Setting dell'immagine
            imageView = new ImageView(image);
            // Setting altezza e larghezza giusta dell'immagine
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            // Setting giusto rapporto dell'immagine
            imageView.setPreserveRatio(true);
        } catch (FileNotFoundException ignored) {
        }

        // Messaggio di warning
        Label lblError = new Label(message);

        // Bottone per tornare alla vecchia schermata
        Button btnBackMain = new Button("OK");
        btnBackMain.setOnAction(e -> popUpWindow.close());

        VBox layout = new VBox(15);


        layout.getChildren().addAll(imageView, lblError, btnBackMain);

        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 300);
        popUpWindow.setScene(scene);

        popUpWindow.showAndWait();
    }
}
