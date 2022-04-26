package com.example.networkdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class SceneController extends TicTacToeUIBoard{

    private static Stage stage;
    private static Scene scene;
    private static Parent root;


    public void switchToWelcome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(SceneController.class.getResource("welcome.fxml"));
        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void switchToTicTacToeMultiplayer(ActionEvent event) throws IOException {
        root = FXMLLoader.load(SceneController.class.getResource("tic-tac-toe-multiplayer.fxml"));

        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());

        scene = new Scene(root);
        stage.setScene(scene);

    }

    public void switchToTicTacToeSingleplayer(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(SceneController.class.getResource("tic-tac-toe-singleplayer.fxml")));

        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());

        scene = new Scene(root);
        stage.setScene(scene);

//      Css files are loaded from fxml file. This commented out code is left for future reference.
//      Calling Structure: .java -> .fxml -> .css
//      String css = getClass().getResource("/com/tictactoe/tictactoe/boardStyle.css").toExternalForm();
//      scene.getStylesheets().add(css);
    }


}
