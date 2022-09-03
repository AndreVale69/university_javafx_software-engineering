package com.univr.graphics.components.window;

import com.univr.anagrafica.Agenzia;
import com.univr.anagrafica.Lavoratore;
import com.univr.graphics.GridPaneCustom;
import com.univr.graphics.components.custom.*;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LoginWindow extends Window {
    @Override
    public void createWindow(Stage primaryStage, Lavoratore lavoratore) {
        Agenzia agenzia = Agenzia.getInstance();

        // Creazione finestra di Main
        BorderPane root = setWindow(primaryStage, "Main Dipendente", 500, 500);

        final GridPaneCustom gridPaneLogin = new GridPaneCustom( 10, 10);

        // Aggiunta immagine login
        try {
            // Creazione di un'immagine
            Image image = new Image(new FileInputStream("src/main/resources/com/univr/anagrafica/login_logo.jpg"));
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
            System.out.println(exception);
        }

        // Campi inserimento
        final TextFieldCustom txtEmail = new TextFieldCustom(gridPaneLogin, 1, 1);
        final PasswordFieldCustom txtPassword = new PasswordFieldCustom(gridPaneLogin, 1, 2);

        // Testo
        final LabelErrorCustom lblErroreLogin =  new LabelErrorCustom("L'email e/o la password sono errate!", gridPaneLogin,1, 6);

        new LabelCustom("Email", gridPaneLogin.getGridPane(), 0, 1);
        new LabelCustom("Password", gridPaneLogin.getGridPane(), 0, 2);

        // Creazione bottone login
        final ButtonCustom btnLogin = new ButtonCustom("LOGIN", gridPaneLogin, 1, 3);
        btnLogin.settingStyle("-fx-font-weight: bold;");

        // TODO SOLO DEBUG; DA RIMUOVERE
        final ButtonCustom btnTest = new ButtonCustom("TEST", gridPaneLogin, 1, 4);
        btnTest.settingStyle("-fx-font-weight: bold;");
        btnTest.testLoginEvent(txtEmail, txtPassword, btnLogin);

        // TODO SOLO DEBUG; DA RIMUOVERE
        final ButtonCustom btnReset = new ButtonCustom("RESET", gridPaneLogin, 1, 5);
        btnReset.settingStyle("-fx-font-weight: bold;");
        btnReset.testResetEvent(agenzia);

        // Vari eventi
        btnLogin.loginEvent(txtEmail, txtPassword, lblErroreLogin, primaryStage);

        primaryStage.setOnCloseRequest((event) -> agenzia.toFile());

        // Posizione dei campi di Main
        root.setCenter(gridPaneLogin.getGridPane());
        primaryStage.show();
    }

    @Override
    public Object[] getObjects() {
        return null; // TODO to fix
    }
}
