package com.example.networkdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;
import static com.example.networkdemo.Main.toServer;

public class RematchPopUp {

    @FXML
    Button rejectButton;

    @FXML
    Button acceptButton;

    // Handle user choice
    public void sendUserChoice (ActionEvent event) throws IOException {

        // Create a new stage for pop up window
        //Stage stage = (Stage) event.getSource().getScene().getWindow();
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

        // Load pop up fxml
        Parent root;

        SceneController sceneController;

        if (event.getSource() == acceptButton){
            System.out.println("Send REMATCH_ACCEPTED");
            //send room id, with message type
            Object message = new Message(Main.currentGame.getRoomID(), HumanTypes.REMATCH_ACCEPT);
            toServer.writeObject(message);
            toServer.reset();
            stage.close();
        }
        else {
            System.out.println("Send REMATCH_REJECTED");
            //send room id, with message type
            Object message = new Message(Main.currentGame.getRoomID(), HumanTypes.REMATCH_REJECT);

            toServer.writeObject(message);
            toServer.reset();
            stage.close();
        }

    }

}