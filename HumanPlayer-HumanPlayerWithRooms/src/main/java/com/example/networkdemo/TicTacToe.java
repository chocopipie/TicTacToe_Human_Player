package com.example.networkdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static com.example.networkdemo.Main.*;

//import static com.example.networkdemo.Main.toServer;


public class TicTacToe {

    private static Stage stage;
    private static Scene scene;
    private static Parent root;

    static int player = 0;

//    private char token = 'z';     //X or O
//    char [][]grid = new char[][] {{ ' ', ' ', ' '},
//            { ' ', ' ', ' '},
//            { ' ', ' ', ' '}};
//    char continuePlay = 'X';

    @FXML
    GridPane board;

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


    public void checkMove(ActionEvent event) throws IOException {
        // The source stores a reference to the object where the event initially occurred
        Button box = (Button) event.getSource();

        // set action when left-click on the box
        if (box.getUserData() == null) {

            if (currentToken==token) {

                int row = board.getRowIndex(box);
                int col = board.getColumnIndex(box);

                System.out.println("row: " + row + " col: " + col);
                System.out.println(token);
                Move move = new Move(row, col, token, room_id);
                Object message = new Message(move, HumanTypes.MAKE_MOVE);
                Main.toServer.writeObject(message);
            }
        }

    }


}