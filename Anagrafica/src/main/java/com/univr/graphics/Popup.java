package com.univr.graphics;

import com.univr.graphics.components.custom.LabelErrorCustom;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Popup {

    /**
     * Mostra una finestra di errore
     * @param message
     */
    public static void display(String message)
    {
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
        } catch (FileNotFoundException exception) {
            System.out.println(exception);
        }

        // Messaggio d'errore
        Label lblError = new Label(message);
        //LabelErrorCustom lblError = new LabelErrorCustom(message);

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

