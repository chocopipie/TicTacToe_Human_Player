//package com.example.networkdemo;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.ListView;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.GridPane;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//import java.util.Objects;
//import java.util.Vector;
//
//import static com.example.networkdemo.Main.toServer;
//import static com.example.networkdemo.Main.token;
//
//
//public class SceneController extends TicTacToe{
//
//    private static Stage stage;
//    private static Scene scene;
//    private static Parent root;
//    private static Object message = new Message(null, null);
//    private static Vector<GameRoom> gameRooms = null;
//    static ListView lv = new ListView();
//
//
//    public void switchToWelcome(ActionEvent event) throws IOException {
//        root = FXMLLoader.load(SceneController.class.getResource("welcome.fxml"));
//        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
//        scene = new Scene(root);
//        stage.setScene(scene);
//    }
//
//    public static void switchToTicTacToeMultiplayer(Stage stage) throws IOException {
//        root = FXMLLoader.load(Objects.requireNonNull(SceneController.class.getResource("tic-tac-toe-multiplayer.fxml")));
//        //stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//
//    }
//
//    public void switchToTicTacToeSingleplayer(ActionEvent event) throws IOException {
////        root = FXMLLoader.load(Objects.requireNonNull(SceneController.class.getResource("tic-tac-toe-singleplayer.fxml")));
////        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
////        scene = new Scene(root);
////        stage.setScene(scene);
//
//    }
//
//
//    public void switchToLobby(ActionEvent event) throws IOException{
//        root = root = FXMLLoader.load(Objects.requireNonNull(SceneController.class.getResource("lobby.fxml")));
//        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
//        //updateLobby(gameRooms);
//        scene = new Scene(root);
//        stage.setScene(scene); //NULL
//        stage.show();
//    }
//
//    //once "Create Game" button is pressed, will send message for GameLauncher
//    public void createGame(ActionEvent actionEvent) throws IOException {
//        //send CREATE_MULTIGAME message to server
//        System.out.println("multi pressed");
//        message = new Message("Multi", HumanTypes.CREATE_MULTIGAME);
//        sendMessage();
//
//    }
//
//
//    //create buttons for all existing Games (both open and closed)
//    public static void updateLobby(Vector<GameRoom> gameRoomList){
//        gameRooms = gameRoomList;
//
//        for(int i = 0; i < gameRoomList.size(); i++){
//            //lv.getItems().add(gameRoomList.get(i).getRoomID());
//            System.out.println(gameRooms.get(i).getRoomID());
//            System.out.println();
//        }
//
//
//
//    }
//
//
//    //will return message
//    public static void sendMessage() throws IOException {
//
//        toServer.writeObject(message);
//    }
//
//    public void RequestMultiGame(ActionEvent event) throws IOException {
//        // later on, username will be the name that user entered
//        // for now, this is hardcoded
//        String username = "VANN";
//        System.out.println("multi pressed");
//        message = new Message(username, HumanTypes.SEND_NAME);
//        sendMessage();
//        message = new Message("Multi", HumanTypes.CREATE_MULTIGAME);
//        sendMessage();
//    }
//
//
//
//    public void RequestSoloGame(ActionEvent event) throws IOException {
////        Object message = new Message("single", HumanTypes.CREATE_SOLOGAME);
//        System.out.println("solo pressed");
//    }
//
//    public void RequestJoinRoom(ActionEvent event) throws IOException{
//        System.out.println("Join pressed");
//        message = new Message("test with different room_id", HumanTypes.JOIN_GAME);
//        sendMessage();
//    }
//
//
//    @FXML
//    GridPane board00;
//
////    @FXML
////    Button button01;
//
//
//    public void updateLabel(char t) {
//        if (token == t) {
//            //int playerOScoreUpdated = Integer.parseInt(playerOScore.getText()) + 1;
//            //((Label)(root.lookup("#playerlabel"))).setText("Currently " + token + "'s Turn");
//            ((Label)(root.lookup("#playerlabel"))).setText("Your Turn");
//        }
//        else {
//            //int playerOScoreUpdated = Integer.parseInt(playerXScore.getText()) + 1;
//            ((Label)(root.lookup("#playerlabel"))).setText("Opponenets's Turn");
//        }
//    }
//
//    public void updateScoreboard(char winner) {
//
//        int tempScore = 0;
//
//        if (winner == 'X' ) {
//
//            tempScore = Integer.valueOf(((Label)(root.lookup("#playerXScore"))).getText()) + 1;
//
//            ((Label)(root.lookup("#playerXScore"))).setText(String.valueOf(tempScore));
//        }
//        else {
//            tempScore = Integer.valueOf(((Label)(root.lookup("#playerOScore"))).getText()) + 1;
//
//            ((Label)(root.lookup("#playerOScore"))).setText(String.valueOf(tempScore));
//        }
//    }
//
//    public void setMove(int row, int col, char token){
//
//        //Button box = (Button)root.lookup("#button05");
//        Button box = null;
//        int temp = 0;
//
//        if (row == 0) {
//            temp = row + col;
//            System.out.println("temp: " + temp);
//            switch (row + col){
//                case 0:
//                    box = (Button)root.lookup("#button1");
//                    break;
//                case 1:
//                    box = (Button)root.lookup("#button2");
//                    break;
//                case 2:
//                    box = (Button)root.lookup("#button3");
//                    break;
//            }}
//        else if (row == 1) {
//            temp = row + col + 2;
//            System.out.println("temp: " + temp);
//            switch (temp) {
//                case 3: box = (Button)root.lookup("#button4");
//                    break;
//                case 4: box = (Button)root.lookup("#button5");
//                    break;
//                case 5: box = (Button)root.lookup("#button6");
//                    break;
//            }
//        }
//        else if (row == 2){
//            temp = row + col + 4;
//            System.out.println("temp: " + temp);
//            switch (temp) {
//                case 6: box = (Button)root.lookup("#button7");
//                    break;
//                case 7: box = (Button)root.lookup("#button8");
//                    break;
//                case 8: box = (Button)root.lookup("#button9");
//                    break;
//            }
//        }
//        else{
//            System.out.println("nothing set");
//        }
//
//        printMove(box, token);
//
//        //Button box = (Button) board00.getChildren().get(1);
//    }
//
//    private void printMove (Button button, char token) {
//
//        if (token == 'X') {
//            // Image img = new Image("https://static.vecteezy.com/system/resources/thumbnails/001/200/173/small/x.png");
//            Image img = new Image(getClass().getResourceAsStream("/images/x.png"));
//            ImageView imageView = new ImageView(img);
//            imageView.setPreserveRatio(true);
//            imageView.setFitHeight(40);
//            // insert image of X to button
//            button.setGraphic(imageView);
//            button.setUserData("X");
//        }
//        else {
//            //Image img = new Image("https://pngimg.com/uploads/letter_o/letter_o_PNG64.png");
//            Image img = new Image(getClass().getResourceAsStream("/images/o.png"));
//            ImageView imageView = new ImageView(img);
//            imageView.setPreserveRatio(true);
//            imageView.setFitHeight(40);
//            // insert image O to button
//            button.setGraphic(imageView);
//            button.setUserData("O");
//        }
//    }
//
//
//    @FXML
//    public void resetBoard(){
//        /* Original test case
//        Button allButtons = (Button)root.lookup("#button1");
//        allButtons.setGraphic(null);
//        */
//
//        // root.lookup finds a node inside root
//        // the node is then downcast into a Button
//        // setGraphic(null) and setUserData(null) clear the button
//        ((Button)(root.lookup("#button1"))).setGraphic(null);
//        ((Button)(root.lookup("#button2"))).setGraphic(null);
//        ((Button)(root.lookup("#button3"))).setGraphic(null);
//        ((Button)(root.lookup("#button4"))).setGraphic(null);
//        ((Button)(root.lookup("#button5"))).setGraphic(null);
//        ((Button)(root.lookup("#button6"))).setGraphic(null);
//        ((Button)(root.lookup("#button7"))).setGraphic(null);
//        ((Button)(root.lookup("#button8"))).setGraphic(null);
//        ((Button)(root.lookup("#button9"))).setGraphic(null);
//
//        ((Button)(root.lookup("#button1"))).setUserData(null);
//        ((Button)(root.lookup("#button2"))).setUserData(null);
//        ((Button)(root.lookup("#button3"))).setUserData(null);
//        ((Button)(root.lookup("#button4"))).setUserData(null);
//        ((Button)(root.lookup("#button5"))).setUserData(null);
//        ((Button)(root.lookup("#button6"))).setUserData(null);
//        ((Button)(root.lookup("#button7"))).setUserData(null);
//        ((Button)(root.lookup("#button8"))).setUserData(null);
//        ((Button)(root.lookup("#button9"))).setUserData(null);
//
//    }
//
//}


package com.example.networkdemo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Vector;

import static com.example.networkdemo.Main.toServer;
import static com.example.networkdemo.Main.token;


public class SceneController extends TicTacToe {

    private static Stage stage;
    private static Scene scene;
    private static Parent root;
    private static Object message = new Message(null, null);
    private static Vector<GameRoom> gameRooms = new Vector<GameRoom>();

    @FXML
    //private static ListView<String> myListView = new ListView<>();
    private ListView<String> myListView;


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

        public void switchToLobby(ActionEvent event) throws IOException{
        root = root = FXMLLoader.load(Objects.requireNonNull(SceneController.class.getResource("lobby.fxml")));
        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene); //NULL
        stage.show();
    }


    public void updateLobby(Vector<GameRoom> list) {//----------going to use observableList for myListView
        gameRooms = list;
        //create string, for listview object
        //Player1 vs. Player2             #of viewers
        //Player1 vs. (OPEN GAME)         #of viewers

//        for (int i = 0; i < gameRooms.size(); i++) {
//            String p1 = gameRooms.get(i).getPlayer1().getUserName();
//            String p2 = gameRooms.get(i).getPlayer2().getUserName();

//           myListView.getItems().add(gameRooms.get(i).getPlayer1().getUserName());
//            System.out.println(gameRooms.get(i).getPlayer1().getUserName());

////            System.out.println();
//        }



        System.out.println("INSIDE updateLobby()\n");
        //String[] s = {"Hello", "Hi", "Buenos dias"};

        //myListView.getItems().addAll(s);
        ObservableList<String> items =FXCollections.observableArrayList (
                "Single", "Double", "Suite", "Family App");
        ((ListView<String>)(root.lookup("myListView"))).setItems(items);
        //System.out.println(myListView.getItems()); //will be printed






    }

    //will return message
    public static void sendMessage() throws IOException {

        toServer.writeObject(message);
        toServer.reset();
    }

    public void RequestMultiGame(ActionEvent event) throws IOException {
        // later on, username will be the name that user entered
        // for now, this is hardcoded
        String username = "VANN";
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
        String username = "KENN";
        message = new Message(username, HumanTypes.SEND_NAME);
        sendMessage();
        // edit - later on will send player's id
        message = new Message("xMrprz5", HumanTypes.JOIN_GAME);

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

    public void setMove(Move move){
        int row = move.getX();
        int col = move.getY();
        char token = move.getToken();
        String current_room_id = move.getRoom_id();

        if (Main.room_id.equals(current_room_id)) {
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