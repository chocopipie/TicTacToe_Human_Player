package com.example.networkdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.networkdemo.SceneController.*;

public class RematchPopUp {

    @FXML
    Button rejectButton;

    @FXML
    Button acceptButton;

    // Handle user choice
    public void sendUserChoice (ActionEvent event) throws IOException {

        // Create a new stage for pop up window
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

        // Load pop up fxml
        Parent root;

        SceneController sceneController;

        if (event.getSource() == acceptButton){
            System.out.println("Send REMATCH_ACCEPTED");
            stage.close();
        }
        else {
            System.out.println("Send REMATCH_REJECTED");
            stage.close();
        }

    }

}