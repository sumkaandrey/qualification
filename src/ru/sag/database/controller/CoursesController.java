package ru.sag.database.controller;

import java.io.IOException;
import java.lang.String;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.sag.database.db.Courses;
import ru.sag.database.db.DatabaseHandler;

public class CoursesController {

    @FXML
    private TextField surname_field;

    @FXML
    private TextField name_field;

    @FXML
    private TextField patronymic_field;

    @FXML
    private TextField address_field;

    @FXML
    private CheckBox man_checkbox;

    @FXML
    private CheckBox woman_checkbox;

    @FXML
    private ComboBox<?> nameOrg_combo;

    @FXML
    private ComboBox<?> type_combo;

    @FXML
    private String common;

    @FXML
    private String fan;

    @FXML
    private String vip;

    @FXML
    private String twice;

    @FXML
    private ComboBox<?> nameDisc_combo;

    @FXML
    private String common11;

    @FXML
    private String fan11;

    @FXML
    private String vip11;

    @FXML
    private String twice11;

    @FXML
    private ComboBox<?> post_combo;

    @FXML
    private Label zapis_label;

    @FXML
    private Label courses_label;

    @FXML
    private Label username_label;

    @FXML
    private Button zapis_button;

    @FXML
    void initialize() {
                zapis_button.setOnAction(event -> {
                    Courses();

                    zapis_button.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/ru/sag/database/resources/statistic.fxml"));

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
    private void Courses() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String surname =  surname_field.getText();
        String name = name_field.getText();
        String patronymic = patronymic_field.getText();
        String address = address_field.getText();
        Object nameOrg = nameOrg_combo.getValue();
        Object nameDiscipline = nameDisc_combo.getValue();
        Object typeOrg = type_combo.getValue();
        Object post = post_combo.getValue();
        Object gender_male = man_checkbox.getText();
        Object gender_female = woman_checkbox.getText();

        Courses courses = new Courses(surname, name, patronymic,  nameOrg, typeOrg, nameDiscipline,
                post, address, gender_male, gender_female);

        dbHandler.newCourse(courses);
        }
    }