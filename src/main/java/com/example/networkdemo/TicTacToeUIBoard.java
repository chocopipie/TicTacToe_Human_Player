package com.example.networkdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

//ui board state
public class TicTacToeUIBoard {

    private static Stage stage;
    private static Scene scene;
    private static Parent root;

    static int player = 0;

    char token = 'z';     //X or O
    char continuePlay = 'X';


    @FXML
    GridPane board;

    @FXML
    Button button1;

    @FXML
    Button button2;

    @FXML
    Button button3;

    @FXML
    Button button4;

    @FXML
    Button button5;

    @FXML
    Button button6;

    @FXML
    Button button7;

    @FXML
    Button button8;

    @FXML
    Button button9;

    @FXML
    Label playerlabel;

    @FXML
    Label playerOScore;

    @FXML
    Label playerXScore;

    @FXML
    MenuItem ExitButton;

    @FXML
    private void closeButtonAction(){
        // get a handle to the stage

        // do what you have to do
        stage.close();
    }


    public void writeX(Button button) {
        // Image img = new Image("https://static.vecteezy.com/system/resources/thumbnails/001/200/173/small/x.png");
        Image img = new Image(getClass().getResourceAsStream("/images/x.png"));
        ImageView imageView = new ImageView(img);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(40);
        // insert image of X to button
        button.setGraphic(imageView);
        button.setUserData("X");
    }

    public void writeO(Button button) {
        //Image img = new Image("https://pngimg.com/uploads/letter_o/letter_o_PNG64.png");
        Image img = new Image(getClass().getResourceAsStream("/images/o.png"));
        ImageView imageView = new ImageView(img);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(40);
        // insert image O to button
        button.setGraphic(imageView);
        button.setUserData("O");
    }


    public void updateScoreboard() {
        if (token == 'O') {
            int playerOScoreUpdated = Integer.parseInt(playerOScore.getText()) + 1;
            playerOScore.setText(String.valueOf(playerOScoreUpdated));
        } else if (token == 'X') {
            int playerOScoreUpdated = Integer.parseInt(playerXScore.getText()) + 1;
            playerXScore.setText(String.valueOf(playerOScoreUpdated));
        }
    }

    @FXML
    public void Restart(ActionEvent event) {
        // The source stores a reference to the object where the event initially occurred
        resetButton();
        player = 0;
        token = 'z';
        continuePlay = 'X';
    }


    @FXML
    public void resetButton() {
        playerlabel.setText("Let's Go!");

        // clear the image (X or O) in each box (button)
        button1.setGraphic(null);
        button2.setGraphic(null);
        button3.setGraphic(null);
        button4.setGraphic(null);
        button5.setGraphic(null);
        button6.setGraphic(null);
        button7.setGraphic(null);
        button8.setGraphic(null);
        button9.setGraphic(null);

        // set user data of each box (button) to null
        button1.setUserData(null);
        button2.setUserData(null);
        button3.setUserData(null);
        button4.setUserData(null);
        button5.setUserData(null);
        button6.setUserData(null);
        button7.setUserData(null);
        button8.setUserData(null);
        button9.setUserData(null);
    }

}
