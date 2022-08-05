package com.example.networkdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.networkdemo.Main.userName;

public class NameFieldPopUpController {

    @FXML
    Button submitButton;

    @FXML
    TextField nameField;

    // Handle submitButton pressed event
    public void getNameField (ActionEvent event) throws IOException {

        // Create a new stage for pop up window
        Stage stage;

        // Load pop up fxml
        Parent root;

        if (event.getSource() == submitButton) {

            stage = (Stage) submitButton.getScene().getWindow();
            userName = ((TextField)(stage.getScene().lookup("#nameField"))).getText().trim();
            stage.close();
        }

    }

    public void UpdateInvalidNameField(Stage stage) {
        // update the Text to display message for invalid username
        ((Text)(stage.getScene().lookup("#greeting"))).setText("Username exists. Please enter  a different username");
    }

}