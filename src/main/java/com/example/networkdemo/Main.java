package com.example.networkdemo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

//client
public class Main extends Application {

    static char token;  // token of this client
    static String room_id;  // room id of this client
    static char currentToken = 'X';
    static String userName = "VANN";
    static ObjectInputStream fromServer = null;
    static ObjectOutputStream toServer = null;

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

        launchPopUp(stage);

        System.out.println("Username: " + userName);

        //then, connect to server
        connectToServer(stage);
    }

    private void launchPopUp(Stage stage) throws IOException {

        // Create a new stage for pop up window
        Stage popUpStage = new Stage();

        // Load pop up fxml
        Parent popUpRoot = FXMLLoader.load(getClass().getResource("/com/example/networkdemo/NameFieldPopUp.fxml"));

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

                                    case "JOIN_LOBBY":
                                        String clientName = (String) message.getData();
                                        System.out.println(clientName);
                                        if (userName.equals(clientName))
                                            editor.switchToLobby(stage);
                                        break;
                                    case "SOLOGAME_CREATED" :
                                        GameRoom aiRoom = (GameRoom) message.getData();
                                        HumanPlayer aiPlayer = aiRoom.getPlayer1();
                                        if (aiPlayer.getUserName().equals(userName)) {
                                            room_id = aiPlayer.getRoom_id();
                                            token = aiPlayer.getToken();
                                            System.out.println(token);
                                            System.out.println(room_id);
                                            SceneController.switchToTicTacToeSingleplayer(stage);
                                        }
                                        break;
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
                                    case "JOIN_SUCCESS":
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
                                        System.out.println("MY ID: " + room_id);
                                        System.out.println("CURRENT ID: " + move.getRoom_id());
                                        //editor.setMove(move.getX(), move.getY(), move.getToken());
                                        if (room_id.equals(move.getRoom_id()))
                                            editor.setMove(move);
                                        break;
                                    case "MOVE_REJECTED" :
                                        break;
                                    case "PLAYER_TURN" :
                                        Move moveWithTokenChanged = (Move) message.getData();
                                        if (room_id.equals(moveWithTokenChanged.getRoom_id())) {
                                            currentToken = moveWithTokenChanged.getToken();
                                            editor.updateLabel(currentToken);
                                            System.out.println("Current token is: " + currentToken);
                                        }
                                        break;
                                    case "WINNER" :
                                        Move moveWithWinner = (Move) message.getData();
                                        if (room_id.equals(moveWithWinner.getRoom_id())) {
                                            editor.updateScoreboard(moveWithWinner.getToken());
                                            editor.resetBoard();
                                        }
                                        break;
                                    case "TIE":
                                        String currentRoomID = (String) message.getData();
                                        if (room_id.equals(currentRoomID))
                                            editor.resetBoard();
                                        break;
                                    case "SEND_GAMECHANNEL":
                                        RoomList rl = (RoomList) message.getData();
                                        for(int i = 0; i < rl.size(); i++){
                                            System.out.println("CHECKKK : " + rl.getGameRoomList().get(i).getRoomID());
                                            System.out.println();
                                        }

                                        Vector<GameRoom> l = rl.getGameRoomList();
                                        LobbyController lc = new LobbyController();
                                        //lobbyCont.updateLobby(l,stage);
                                        lc.updateLobby(l);
                                        //editor.switchToLobby(stage);
                                        break;

                                    case "ROOM_ADDED":
                                        RoomList roomList = (RoomList) message.getData();
                                        System.out.println("ROOM LIST SIZE : " + roomList.size());

                                        Vector<GameRoom> list = roomList.getGameRoomList();
                                        LobbyController roomAdded = new LobbyController();
                                        roomAdded.updateLobby(list);
                                        //editor.switchToLobby(stage);
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