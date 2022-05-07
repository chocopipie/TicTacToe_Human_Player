package com.example.networkdemo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    static ObjectInputStream fromServer = null;
    static ObjectOutputStream toServer = null;
    private List<HumanPlayer> human=new ArrayList<HumanPlayer>();

    static String playerUserName;

    @Override
    public void start(Stage stage) throws IOException {

        //Initially, go to Welcome Page
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/welcome.fxml"));
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        scene.setFill(Color.BLUE);
        stage.show();

        // Prompt User for a name, and set that as the users name.
        launchPopUp(stage);

        // Test playerUserName is being set after NamePopUp is closed.
        System.out.println("Player Name set to: " + playerUserName);

        // Connect to server
        connectToServer(stage);
    }

    private void launchPopUp(Stage stage) throws IOException {

        // Create a new stage for pop up window
        Stage popUpStage = new Stage();

        // Load pop up fxml
        Parent popUpRoot = FXMLLoader.load(getClass().getResource("/fxml/NameFieldPopUp.fxml"));

        // Set pop up scene
        popUpStage.setScene(new Scene(popUpRoot));

        // Removes minimize, maximize and close buttons
        // To add close button but not minimize or maximize buttons use StageStyle.Utility
        popUpStage.initStyle(StageStyle.UNDECORATED);

        // Causes popUpStage to become pop up
        popUpStage.initModality(Modality.APPLICATION_MODAL);

        // Bind popUpStage to its initial owner
        popUpStage.initOwner(stage);

        // Wait for pop up to close before returning to Welcome Screen
        popUpStage.showAndWait();

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
                                        SceneController.switchToTicTacToeMultiplayer(stage);
                                        //token = (char)message.getData();
                                        //System.out.println(token);

                                        // get room created, the token
                                        GameRoom playersRoom = (GameRoom) message.getData();
                                        HumanPlayer thisPlayer = playersRoom.getPlayer1();
                                        token = thisPlayer.getToken();
                                        System.out.println(token);
                                        break;
                                    case "ROOM_ADDED":
                                        String room_id = (String) message.getData();
                                        // later, this room will be added to the list of rooms on ui
                                        System.out.println(room_id);
                                        break;
                                    case "JOIN_SUCCESS":
                                        // get room created, the token
                                        GameRoom roomJoined = (GameRoom) message.getData();
                                        HumanPlayer thisPlayer2 = roomJoined.getPlayer2();
                                        token = thisPlayer2.getToken();
                                        System.out.println(token);
                                        SceneController.switchToTicTacToeMultiplayer(stage);
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