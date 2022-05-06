package com.example.networkdemo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

//client
public class Main extends Application {

    static char token;  // token of this client
    static String room_id;  // room id of this client
    static char currentToken = 'X';
    static String userName = "VAN";
    static ObjectInputStream fromServer = null;
    static ObjectOutputStream toServer = null;
    private List<HumanPlayer> human=new ArrayList<HumanPlayer>();

    @Override
    public void start(Stage stage) throws IOException {

        //Initially, go to Welcome Page
        Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        scene.setFill(Color.BLUE);
        stage.show();

        //then, connect to server
        connectToServer(stage);
    }

    private void connectToServer(Stage stage) throws IOException {

        //create human => think it needs to be changed later
        human.add (new HumanPlayer("Player#" + human.size()+1));

        try {
            // Create a socket to connect to the server
            Socket socket = new Socket("localhost", 8000);

            // Create input/output stream
            toServer = new ObjectOutputStream(socket.getOutputStream());
            fromServer = new ObjectInputStream(socket.getInputStream());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        //send message to server (initially, message set from either RequestMultiGame or ReuestSoloGame
//        Message msg = (Message) SceneController.sendMessage();
//
//        System.out.println("msg ts: " + String.valueOf(msg.getType()));
//
//        toServer.writeObject(msg);

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    while (true) {

                        // read the message sent to this client
                        Object response = fromServer.readObject();

                        // Downcast message from Object
                        Message message = (Message) response;
                        String type = String.valueOf(message.getType());
                        System.out.println("type of message: " + type);

                        Platform.runLater(() -> {
                            // Display to the text area
//                        ta.appendText(mess.getType().getDescription() + "\n");

                            SceneController editor = new SceneController();

                            try {
                                switch (type) {
                                    case "MULTIGAME_CREATED":
                                        //token = (char)message.getData();
                                        //System.out.println(token);
                                        // get room created, the token
                                        GameRoom playersRoom = (GameRoom) message.getData();
                                        HumanPlayer thisPlayer = playersRoom.getPlayer1();
                                        if (thisPlayer.getUserName().equals(userName)) {
                                            room_id = thisPlayer.getRoom_id();
                                            token = thisPlayer.getToken();
                                            System.out.println(token);
                                            System.out.println(room_id);
                                            SceneController.switchToTicTacToeMultiplayer(stage);
                                        }
                                        break;
//                                    case "ROOM_UPDATED":
////                                        //System.out.println(existingRoom.getRoomID());
////                                        System.out.println("Room " + existingRoom.getRoomID() +
////                                                " is joined by " + existingRoom.getPlayer2().getUserName());
//                                        GameRoom existingRoom = (GameRoom) message.getData();
//                                        System.out.println("Room " + existingRoom.getRoomID() +" is joined by "
//                                                + existingRoom.getPlayer2().getUserName());
 //                                       break;
                                    case "JOIN_SUCCESS":
                                        userName = "VY";
                                        // get room created, the token
                                        GameRoom roomJoined = (GameRoom) message.getData();
                                        HumanPlayer thisPlayer2 = roomJoined.getPlayer2();
//                                        System.out.println("Room id: " + roomJoined.getRoomID());
//                                        System.out.println("Player 1: " + roomJoined.getPlayer1().getUserName());
//                                        System.out.println("Player 2: " + roomJoined.getPlayer2().getUserName());
                                        if (thisPlayer2.getUserName().equals(userName)) {
                                            token = thisPlayer2.getToken();
                                            room_id = thisPlayer2.getRoom_id();
                                            System.out.println(token);
                                            System.out.println(room_id);
                                            SceneController.switchToTicTacToeMultiplayer(stage);
                                        }
                                        break;
                                    case "JOIN_FAIL":
                                        System.out.println(message.getData());
                                        if (message.getData().equals("full"))
                                            System.out.println("Room is full");
                                        else if (message.getData().equals("room not found"))
                                            System.out.println("Room not available");
                                        break;
                                    case "MOVE_MADE" :
                                        Move move = (Move)message.getData();
                                        editor.setMove(move.getX(), move.getY(), move.getToken());
                                        break;
                                    case "MOVE_REJECTED" :
                                        break;
                                    case "PLAYER_TURN" :
                                        currentToken = (char) message.getData();
                                        editor.updateLabel(currentToken);
                                        System.out.println("Current token is: " + currentToken);
                                        break;
                                    case "WINNER" :
                                        editor.updateScoreboard((char) message.getData());
                                        editor.resetBoard();
                                        break;
                                    case "TIE":
                                        editor.resetBoard();
                                        break;
                                    case "ROOM_ADDED":
                                        RoomList roomList = (RoomList) message.getData();
                                        System.out.println("ROOM LIST SIZE : " + roomList.size());
                                        // later, this room will be added to the list of rooms on ui
                                        roomList.printList();
                                        break;
                                    default:
                                        //System.out.println("Invalid Message Type\n");
                                }
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        });
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();




    }

    public static void main(String[] args) {
        launch(args);
    }
}