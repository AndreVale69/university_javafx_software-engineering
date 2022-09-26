package com.univr.graphics.components.windows;

import com.univr.anagrafica.Business;
import com.univr.anagrafica.Worker;
import com.univr.anagrafica.Manager;
import com.univr.graphics.components.custom.SceneBuilder;
import com.univr.graphics.components.custom.*;
import com.univr.graphics.components.popup.PopupFactory;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LoginWindow extends Window {

    @Override
    public void createWindow(Stage primaryStage, Worker worker, Worker old, Manager manager) {
        Business business = Business.getInstance();

        // Creazione finestra di Main
        BorderPane root = setWindow(primaryStage, "Main Dipendente", 500, 500);

        final SceneBuilder gridPaneLogin = new SceneBuilder( 10, 10);
        gridPaneLogin.getGridPane().setAlignment(Pos.CENTER);

        // Aggiunta immagine login
        try {
            // Creazione di un'immagine
            Image image = new Image(new FileInputStream("src/main/resources/com/univr/anagrafica/login_logo.png"));
            Image imageTitle = new Image(new FileInputStream("src/main/resources/com/univr/anagrafica/login_title_logo.png"));
            primaryStage.getIcons().add(imageTitle);
            // Setting dell'immagine
            ImageView imageView = new ImageView(image);
            // Setting altezza e larghezza giusta dell'immagine
            imageView.setFitHeight(225);
            imageView.setFitWidth(225);
            // Setting giusto rapporto dell'immagine
            imageView.setPreserveRatio(true);
            // Aggiunta nel gridPane
            gridPaneLogin.getGridPane().add(imageView, 1, 0);
        } catch (FileNotFoundException exception) {
            PopupFactory.getPopup("ERROR").createPopup("Immagine della window non trovata: " + exception);
        }

        // Campi inserimento
        final TextFieldCustom txtEmail = new TextFieldCustom(gridPaneLogin, 1, 1);
        objects[0] = txtEmail;
        final PasswordFieldCustom txtPassword = new PasswordFieldCustom(gridPaneLogin, 1, 2);
        objects[1] = txtPassword;

        // Testo
        final LabelErrorCustom lblErroreLogin =  new LabelErrorCustom("L'email e/o la password sono errate!", gridPaneLogin,1, 6);
        objects[2] = lblErroreLogin;

        new LabelCustom("Email", gridPaneLogin.getGridPane(), 0, 1);
        new LabelCustom("Password", gridPaneLogin.getGridPane(), 0, 2);

        // Creazione bottone login
        final ButtonCustom btnLogin = new ButtonCustom("LOGIN", gridPaneLogin, 1, 3);
        btnLogin.settingStyle("-fx-font-weight: bold;");
        objects[3] = btnLogin;

        // TODO SOLO DEBUG; DA RIMUOVERE
        final ButtonCustom btnTest = new ButtonCustom("TEST", gridPaneLogin, 1, 4);
        btnTest.settingStyle("-fx-font-weight: bold;");
        Events.testLoginEvent(btnTest.getButton(), txtEmail, txtPassword, btnLogin);

        // TODO SOLO DEBUG; DA RIMUOVERE
        final ButtonCustom btnReset = new ButtonCustom("RESET", gridPaneLogin, 1, 5);
        btnReset.settingStyle("-fx-font-weight: bold;");
        Events.resetEvent(btnReset.getButton(), business);

        // Definizione di una shortcut (INVIO) da tastiera per premere il tasto LOGIN
        primaryStage.getScene().getRoot().setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                btnLogin.getButton().fire();
            }
        });

        // Vari eventi
        Events.loginEvent(btnLogin.getButton(), txtEmail, txtPassword, lblErroreLogin, primaryStage);

        // Posizione dei campi di Main
        root.setCenter(gridPaneLogin.getGridPane());
        primaryStage.show();
    }
}
