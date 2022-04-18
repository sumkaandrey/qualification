package ru.sag.database.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.sag.database.db.DatabaseHandler;
import ru.sag.database.db.User;

public class RegController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField username_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private CheckBox processing_checkbox;

    @FXML
    private Button authorization_button;

    @FXML
    void initialize() {

        authorization_button.setOnAction(event -> {
            DatabaseHandler dbHandler = new DatabaseHandler();
            if (checkEmpty()) {
                dbHandler.signUpUser(signUpNewUser());
            }

            authorization_button.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/ru/sag/database/resources/login.fxml"));

            try{
                loader.load();
            } catch (IOException e ) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }
    private User signUpNewUser() {
        String username = username_field.getText();
        String password = password_field.getText().trim();
        if (password_field.getText().length() < 4) {
            System.out.println("Ошибка!");
        }

        User user = new User();
        return user;
    }

    private boolean checkEmpty() {
        String username = username_field.getText().trim();
        String password = password_field.getText().trim();
        return !username.equals("") && !password.equals("");
    }
}