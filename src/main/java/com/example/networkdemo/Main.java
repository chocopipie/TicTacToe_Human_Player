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
    static char storeToken;
    static String room_id = " ";  // room id of this client
    static char currentToken = 'X';
    static String userName = "VANN";
    static ObjectInputStream fromServer = null;
    static ObjectOutputStream toServer = null;

    static GameRoom currentGame;
    static GameRoom aiGame;

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

        launchPopUp (stage, "NameFieldPopUp", true);

        System.out.println("Username: " + userName);

        //then, connect to server
        connectToServer(stage);
    }

    private void launchPopUp (Stage stage, String popUpName, boolean showAndWait) throws IOException {

        // Create a new stage for pop up window
        Stage popUpStage = new Stage();

        // Load pop up fxml
        Parent popUpRoot = FXMLLoader.load(getClass().getResource("/com/example/networkdemo/" + popUpName +".fxml"));

        // Set pop up scene
        popUpStage.setScene(new Scene(popUpRoot));

        // Removes minimize, maximize and close buttons
        // To add close button but not minimize or maximize buttons use StageStyle.Utility
        popUpStage.initStyle(StageStyle.UNDECORATED);

        // Causes popUpStage to become pop up
        popUpStage.initModality(Modality.APPLICATION_MODAL);

        // Bind popUpStage to its initial owner
        popUpStage.initOwner(stage);
        popUpStage.centerOnScreen();

        popUpStage.setX(stage.getX()+350);
        popUpStage.setY(stage.getY()+200);

        // Wait for pop up to close before returning to Welcome Screen
        if (showAndWait == true) {
            popUpStage.showAndWait();
        }
        else
            popUpStage.show();

    }

    // launch pop up with invalid name message
    private void launchInvalidPopUp(Stage stage) throws IOException {

        // Create a new stage for pop up window
        Stage popUpStage = new Stage();

        // Load pop up fxml
        Parent popUpRoot = FXMLLoader.load(getClass().getResource("/com/example/networkdemo/NameFieldPopUp.fxml"));

        // Set pop up scene
        popUpStage.setScene(new Scene(popUpRoot));

        // change the greeting label to the invalid username message
        NameFieldPopUpController np = new NameFieldPopUpController();
        np.UpdateInvalidNameField(popUpStage);

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
                    // send username at the beginning
                    Message message1 = new Message(userName, HumanTypes.SEND_NEW_USERNAME);
                    toServer.writeObject(message1);
                    toServer.reset();

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

                                    case "USERNAME_EXISTS" :

                                        System.out.println("Username existed.");
                                        launchInvalidPopUp(stage);

                                        // send username at the beginning
                                        Message message2 = new Message(userName, HumanTypes.SEND_NEW_USERNAME);
                                        toServer.writeObject(message2);
                                        toServer.reset();
                                        break;

                                    case "JOIN_LOBBY":
                                        String clientName = (String) message.getData();
                                        System.out.println("clientName:" + clientName);
                                        System.out.println();

                                        System.out.println(clientName);
                                        if (userName.equals(clientName))
                                            editor.switchToLobby(stage);
                                        break;
                                    case "SOLOGAME_CREATED" :
                                        GameRoom aiRoom = (GameRoom) message.getData();
                                        HumanPlayer soloPlayer = aiRoom.getPlayer1();
                                        if (soloPlayer.getUserName().equals(userName)) {
                                            room_id = soloPlayer.getRoom_id();
                                            token = soloPlayer.getToken();
                                            storeToken = token;
                                            System.out.println(token);
                                            System.out.println(room_id);
                                            SceneController.switchToTicTacToeSingleplayer(stage);
                                        }
                                        aiGame = aiRoom;
                                        break;
                                    case "MULTIGAME_CREATED":
                                        GameRoom playersRoom = (GameRoom) message.getData();
                                        HumanPlayer thisPlayer = playersRoom.getPlayer1();
                                        if (thisPlayer.getUserName().equals(userName)) {
                                            room_id = thisPlayer.getRoom_id();
                                            token = thisPlayer.getToken();
                                            storeToken = token;
                                            System.out.println(token);
                                            System.out.println(room_id);

                                            SceneController.switchToTicTacToeMultiplayer(stage);
                                        }
                                        break;
                                    case "JOIN_SUCCESS":
                                        // get room created, the token
                                        GameRoom roomJoined = (GameRoom) message.getData();
                                        HumanPlayer thisPlayer2 = roomJoined.getPlayer2();
//
                                        if (thisPlayer2.getUserName().equals(userName)) {
                                            token = thisPlayer2.getToken();
                                            storeToken = token;
                                            room_id = thisPlayer2.getRoom_id();
                                            System.out.println(token);
                                            System.out.println(room_id);
//                                            currentGame = roomJoined;
                                            SceneController.switchToTicTacToeMultiplayer(stage);
                                        }
                                        currentGame = roomJoined;
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
                                            //editor.resetBoard();
                                            token = 't';

                                        }
                                        break;
                                    case "TIE":
                                        String currentRoomID = (String) message.getData();
                                        if (room_id.equals(currentRoomID))
                                            editor.UpdateTie();
                                            //editor.resetBoard();
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
//                                        if(currentGame.getPlayer1().getUserName() ==" " && currentGame.getPlayer1().getUserName() ==" " )
//                                            editor.switchToLobby(stage);
                                        break;

                                    case "ROOM_ADDED":
                                        RoomList roomList = (RoomList) message.getData();
                                        System.out.println("ROOM LIST SIZE : " + roomList.size());

                                        Vector<GameRoom> list = roomList.getGameRoomList();
                                        LobbyController roomAdded = new LobbyController();
                                        roomAdded.updateLobby(list);
                                        //editor.switchToLobby(stage);
                                        if(currentGame.getPlayer1().getUserName() ==" " && currentGame.getPlayer1().getUserName() ==" " )
                                            editor.switchToLobby(stage);
                                        break;
                                    case "REMATCH_REQUEST":
                                        String rematchReceiver = (String)message.getData();

                                        if(userName.equals(rematchReceiver)){
                                            launchPopUp(stage, "rematchPopUp", false);
                                            System.out.println("This is i\n");
                                        }
                                        break;
                                    case "PLAYAGAIN_ACCEPTED": //same logic as REMATCH_ACCEPTED, only diff humantypes messages
                                    case "REMATCH_ACCEPTED":
                                        String game = (String) message.getData();
                                        if(room_id.equals(game)){
                                            editor.resetBoard(); //clear board visually also
                                            token = storeToken;
                                        }
                                        break;
                                    case "REMATCH_REJECTED":
                                        String s = (String) message.getData();
                                        if(room_id.equals(s)){
                                            editor.switchToLobby(stage);
                                        }
                                        break;
                                    case "SEND_MESSAGE":
                                        TextMessage tm = (TextMessage)message.getData();
                                        String roomID = tm.getRoomID();

                                        //if it is the current game room, then append text to text area
                                        if(currentGame.getRoomID().equals(roomID)){
                                            editor.appendMessage(tm);
                                            System.out.println("Received message");
                                        }
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