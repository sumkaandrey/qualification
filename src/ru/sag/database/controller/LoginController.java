package ru.sag.database.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.sag.database.animation.Shake;
import ru.sag.database.db.DatabaseHandler;
import ru.sag.database.db.User;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private TextField username_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button login_button;

    @FXML
    private Button authorization_button;
    @FXML
    void initialize() {
        login_button.setOnAction(event -> {
        String username = username_field.getText().trim();
        String password = password_field.getText().trim();

        if(!username.equals("") && !password.equals(""))
            loginUser(username,password);
                    else
            System.out.println("Логин и пароль пусты");
        });

        authorization_button.setOnAction(event -> {
            openNewWindow("/ru/sag/database/resources/registr.fxml");
        });
    }

    private void loginUser(String username, String password) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        ResultSet result = dbHandler.getUser(user);

        int counter = 0;

        while (true) {
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            counter++;
        }
        if (counter >= 1) {
            openNewWindow("/ru/sag/database/resources/courses.fxml");
        } else {
            Shake loginAnimation = new Shake(username_field);
            Shake passwordAnimation = new Shake(password_field);
            loginAnimation.playAnimation();
            passwordAnimation.playAnimation();
        }
    }
    public void openNewWindow(String window){
        login_button.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try{
            loader.load();
        } catch (IOException e ) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}
