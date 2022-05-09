package com.example.networkdemo;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Vector;

import static com.example.networkdemo.Main.toServer;
import static com.example.networkdemo.Main.userName;

public class LobbyController extends SceneController {

    @FXML
    ListView myListView;

    private static Vector<String> updatedRooms = new Vector<String>();
    private static Object message = new Message(null, null);


    public void updateLobby(Vector<GameRoom> list) {
        updatedRooms.clear();
        String temp;

        //create strings and store them in updatedRooms Vector.
        for (int i = 0; i < list.size(); i++) {
            String p1 = list.get(i).getPlayer1().getUserName();
            String p2 = list.get(i).getPlayer2().getUserName();

            if(p2 == ""){
                temp = p1 + " vs. (OPEN GAME)";
            }
            else{
                temp = p1 + " vs. " + p2;
            }
            updatedRooms.add(temp);
        }

        SceneController.setGameChannels(updatedRooms);

    }


//    //will return message
//    public static void sendMessage() throws IOException {
//
//        toServer.writeObject(message);
//        toServer.reset();
//    }
//
//    public void switchToBoard(ActionEvent event) throws IOException {
//
//        System.out.println("Join pressed");
//        message = new Message(userName, HumanTypes.SEND_NAME);
//        sendMessage();
//        // edit - later on will send player's id
//        message = new Message("xMrprz5", HumanTypes.JOIN_GAME);
//
//        sendMessage();
//    }

//    public Vector<String> getRooms(){
//
//        System.out.println("RETURNING OBSERVABLE LIST\n");
//        for(int i = 0; i < items.size(); i++){
//            System.out.println(items.get(i));
//            System.out.println();
//        }
//
//        System.out.println("ending retrun\n");
//
//        return updatedRooms;
//
//
//
//    }

}

