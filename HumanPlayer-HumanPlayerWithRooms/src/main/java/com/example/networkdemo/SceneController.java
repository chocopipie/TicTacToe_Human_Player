package com.example.networkdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static com.example.networkdemo.Main.toServer;
import static com.example.networkdemo.Main.token;


public class SceneController extends TicTacToe{

    private static Stage stage;
    private static Scene scene;
    private static Parent root;
    private static Object message = new Message(null, null);

    public void switchToWelcome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(SceneController.class.getResource("welcome.fxml"));
        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public static void switchToTicTacToeMultiplayer(Stage stage) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(SceneController.class.getResource("tic-tac-toe-multiplayer.fxml")));
        //stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToTicTacToeSingleplayer(ActionEvent event) throws IOException {
//        root = FXMLLoader.load(Objects.requireNonNull(SceneController.class.getResource("tic-tac-toe-singleplayer.fxml")));
//        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
//        scene = new Scene(root);
//        stage.setScene(scene);

    }

    //will return message
    public static void sendMessage() throws IOException {

        toServer.writeObject(message);
    }

    public void RequestMultiGame(ActionEvent event) throws IOException {
        // later on, username will be the name that user entered
        // for now, this is hardcoded
        String username = "VAN";
        System.out.println("multi pressed");
        message = new Message(username, HumanTypes.SEND_NAME);
        sendMessage();
        message = new Message("Multi", HumanTypes.CREATE_MULTIGAME);
        sendMessage();
    }

    public void RequestSoloGame(ActionEvent event) throws IOException {
//        Object message = new Message("single", HumanTypes.CREATE_SOLOGAME);
        System.out.println("solo pressed");
    }

    public void RequestJoinRoom(ActionEvent event) throws IOException{
        System.out.println("Join pressed");
        String username = "VY";
        message = new Message(username, HumanTypes.SEND_NAME);
        sendMessage();
        // edit - later on will send player's id
        message = new Message("CVHiGql", HumanTypes.JOIN_GAME);

        sendMessage();
    }


    @FXML
    GridPane board00;

//    @FXML
//    Button button01;


    public void updateLabel(char t) {
         if (token == t) {
            //int playerOScoreUpdated = Integer.parseInt(playerOScore.getText()) + 1;
             //((Label)(root.lookup("#playerlabel"))).setText("Currently " + token + "'s Turn");
             ((Label)(root.lookup("#playerlabel"))).setText("Your Turn");
         }
         else {
            //int playerOScoreUpdated = Integer.parseInt(playerXScore.getText()) + 1;
             ((Label)(root.lookup("#playerlabel"))).setText("Opponenets's Turn");
         }
    }

    public void updateScoreboard(char winner) {

        int tempScore = 0;

        if (winner == 'X' ) {

            tempScore = Integer.valueOf(((Label)(root.lookup("#playerXScore"))).getText()) + 1;

            ((Label)(root.lookup("#playerXScore"))).setText(String.valueOf(tempScore));
        }
        else {
            tempScore = Integer.valueOf(((Label)(root.lookup("#playerOScore"))).getText()) + 1;

            ((Label)(root.lookup("#playerOScore"))).setText(String.valueOf(tempScore));
        }
    }

    public void setMove(int row, int col, char token){

        //Button box = (Button)root.lookup("#button05");
        Button box = null;
        int temp = 0;

        if (row == 0) {
            temp = row + col;
            System.out.println("temp: " + temp);
            switch (row + col){
                case 0:
                    box = (Button)root.lookup("#button1");
                    break;
                case 1:
                    box = (Button)root.lookup("#button2");
                    break;
                case 2:
                    box = (Button)root.lookup("#button3");
                    break;
        }}
        else if (row == 1) {
            temp = row + col + 2;
            System.out.println("temp: " + temp);
            switch (temp) {
                case 3: box = (Button)root.lookup("#button4");
                    break;
                case 4: box = (Button)root.lookup("#button5");
                    break;
                case 5: box = (Button)root.lookup("#button6");
                    break;
            }
        }
        else if (row == 2){
            temp = row + col + 4;
            System.out.println("temp: " + temp);
            switch (temp) {
                case 6: box = (Button)root.lookup("#button7");
                    break;
                case 7: box = (Button)root.lookup("#button8");
                    break;
                case 8: box = (Button)root.lookup("#button9");
                    break;
            }
        }
        else{
            System.out.println("nothing set");
        }

        printMove(box, token);

        //Button box = (Button) board00.getChildren().get(1);
    }

    private void printMove (Button button, char token) {

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
    }

    @FXML
    public void resetBoard(){
        /* Original test case
        Button allButtons = (Button)root.lookup("#button1");
        allButtons.setGraphic(null);
        */

        // root.lookup finds a node inside root
        // the node is then downcast into a Button
        // setGraphic(null) and setUserData(null) clear the button
        ((Button)(root.lookup("#button1"))).setGraphic(null);
        ((Button)(root.lookup("#button2"))).setGraphic(null);
        ((Button)(root.lookup("#button3"))).setGraphic(null);
        ((Button)(root.lookup("#button4"))).setGraphic(null);
        ((Button)(root.lookup("#button5"))).setGraphic(null);
        ((Button)(root.lookup("#button6"))).setGraphic(null);
        ((Button)(root.lookup("#button7"))).setGraphic(null);
        ((Button)(root.lookup("#button8"))).setGraphic(null);
        ((Button)(root.lookup("#button9"))).setGraphic(null);

        ((Button)(root.lookup("#button1"))).setUserData(null);
        ((Button)(root.lookup("#button2"))).setUserData(null);
        ((Button)(root.lookup("#button3"))).setUserData(null);
        ((Button)(root.lookup("#button4"))).setUserData(null);
        ((Button)(root.lookup("#button5"))).setUserData(null);
        ((Button)(root.lookup("#button6"))).setUserData(null);
        ((Button)(root.lookup("#button7"))).setUserData(null);
        ((Button)(root.lookup("#button8"))).setUserData(null);
        ((Button)(root.lookup("#button9"))).setUserData(null);

    }

}
