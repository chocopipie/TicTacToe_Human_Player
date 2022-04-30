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

import static com.example.networkdemo.Main.currentToken;
import static com.example.networkdemo.Main.token;

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


/*    public void writeToken(Button button) {
        if (token == 'X') {
            // Image img = new Image("https://static.vecteezy.com/system/resources/thumbnails/001/200/173/small/x.png");
            Image img = new Image(getClass().getResourceAsStream("/images/x.png"));
            ImageView imageView = new ImageView(img);
            imageView.setPreserveRatio(true);
            imageView.setFitHeight(40);
            // insert image of X to button
            button.setGraphic(imageView);
            button.setUserData("X");
        }
        else {
            //Image img = new Image("https://pngimg.com/uploads/letter_o/letter_o_PNG64.png");
            Image img = new Image(getClass().getResourceAsStream("/images/o.png"));
            ImageView imageView = new ImageView(img);
            imageView.setPreserveRatio(true);
            imageView.setFitHeight(40);
            // insert image O to button
            button.setGraphic(imageView);
            button.setUserData("O");
        }

    }*/

    public void checkMove(ActionEvent event) throws IOException {
        // The source stores a reference to the object where the event initially occurred
        Button box = (Button) event.getSource();

        //char gcToken = Move.getToken();

        // set action when left-click on the box
        if (box.getUserData() == null) {

            if (currentToken==token) {

                int row = board.getRowIndex(box);
                int col = board.getColumnIndex(box);

                System.out.println("row: " + row + " col: " + col);
                System.out.println(token);
                Move move = new Move(row, col, token);
                Object message = new Message(move, HumanTypes.MAKE_MOVE);
                Main.toServer.writeObject(message);
            }

        }

    }


/*    private static Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }
*/

//    public void updateScoreboard() {
//        if (token == 'O') {
//            int playerOScoreUpdated = Integer.parseInt(playerOScore.getText()) + 1;
//            playerOScore.setText(String.valueOf(playerOScoreUpdated));
//        } else if (token == 'X') {
//            int playerOScoreUpdated = Integer.parseInt(playerXScore.getText()) + 1;
//            playerXScore.setText(String.valueOf(playerOScoreUpdated));
//        }
//    }


//    @FXML
//    public void Restart(ActionEvent event) {
//        // The source stores a reference to the object where the event initially occurred
//        resetBoard();
////
////        grid = new char[][] {{ ' ', ' ', ' '},
////                { ' ', ' ', ' '},
////                { ' ', ' ', ' '}};
//
//    }

/*
    @FXML
    public void resetButton() {
        playerlabel.setText("Let's Go!");
        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        button9.setText("");

        // clear the image (X or O) in each box (button)
        #button1.setGraphic(null);
        #button2.setGraphic(null);
        #button3.setGraphic(null);
        #button4.setGraphic(null);
        #button5.setGraphic(null);
        #button6.setGraphic(null);
        #button7.setGraphic(null);
        #button8.setGraphic(null);
        #button9.setGraphic(null);

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
*/


}