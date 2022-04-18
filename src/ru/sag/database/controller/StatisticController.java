package ru.sag.database.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javafx.scene.control.Label;

public class StatisticController {

    @FXML
    private Button course_button;

    @FXML
    private Label courseinfo;

    @FXML
    private Button close_button;

    @FXML
    private Label workerinfo;

    @FXML
    private Label orginfo;

    @FXML
    private Label username_field;

    @FXML
    void initialize() {
        course_button.setOnAction(event -> {

            course_button.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/ru/sag/database/resources/courses.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }
}