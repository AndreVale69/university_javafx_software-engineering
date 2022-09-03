package com.univr.graphics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.time.LocalDate;


public class DatePickerExperiments extends Application  {


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("DatePicker Experiment 1");

        DatePicker datePicker = new DatePicker();
        DatePicker datePicker2 = new DatePicker();

        Button button = new Button("Read Date");

        button.setOnAction(action -> {
            LocalDate value = datePicker.getValue();
            LocalDate value2 = datePicker2.getValue();
            System.out.println(value.isAfter(value2));
        });

        HBox hbox = new HBox(datePicker, datePicker2, button);

        Scene scene = new Scene(hbox, 300, 240);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}