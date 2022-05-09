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

import static com.example.networkdemo.Main.userName;

public class LobbyController extends SceneController{

    @FXML
    ListView myListView;

    public void updateLobby(Vector<GameRoom> list, Stage stage) throws IOException {
        //root = FXMLLoader.load(getClass().getResource("lobby.fxml"));
        //Above: doesnt display strings anymore, but after first human_player, gets "root is null" error
        root = FXMLLoader.load(getClass().getResource("lobby.fxml"));
        //Above: doesnt display strings anymore, but after first human_player, gets "root is null" error
        stage.getScene().getWindow();
        Scene sc = new Scene(root);
        stage.setScene(sc); //NULL

        gameRooms.clear();
        String temp;
        //create string, for listview object
        //Player1 vs. Player2             #of viewers
        //Player1 vs. (OPEN GAME)         #of viewers

        for (int i = 0; i < list.size(); i++) {
            String p1 = list.get(i).getPlayer1().getUserName();
            String p2 = list.get(i).getPlayer2().getUserName();

            if(p2 == ""){
                temp = p1 + " vs. (OPEN GAME)";
            }
            else{
                temp = p1 + " vs. " + p2;
            }
            gameRooms.add(temp);
        }

        //ObservableList automatically changes ListView
        ObservableList<String> items = FXCollections.observableArrayList (gameRooms);

        if(root != null){
            ListView<String> listOfGames = ((ListView<String>)(root.lookup("#myListView")));

            listOfGames.setItems(items);

            System.out.println(root);
//        System.out.println(myListView);
        }
        else
            System.out.println("root null");

    }

//    @FXML
//    ListView myListView;
//
//    public void updateLobby( Vector<GameRoom> list, Stage s) throws IOException {
//        //root = FXMLLoader.load(Objects.requireNonNull(SceneController.class.getResource("lobby.fxml")));
//        root = FXMLLoader.load(getClass().getResource("lobby.fxml"));
//        //Above: doesnt display strings anymore, but after first human_player, gets "root is null" error
//        s.getScene().getWindow();
//        Scene sc = new Scene(root);
//        s.setScene(sc); //NULL
        //s.show();
//
//        gameRooms.clear();
//        String temp;
//        //create string, for listview object
//        //Player1 vs. Player2             #of viewers
//        //Player1 vs. (OPEN GAME)         #of viewers
//
//        for (int i = 0; i < list.size(); i++) {
//            String p1 = list.get(i).getPlayer1().getUserName();
//            String p2 = list.get(i).getPlayer2().getUserName();
//
//            if(p2 == ""){
//                temp = p1 + " vs. (OPEN GAME)";
//            }
//            else{
//                temp = p1 + " vs. " + p2;
//            }
//            gameRooms.add(temp);
//        }
//
//        //ObservableList automatically changes ListView
//        ObservableList<String> items = FXCollections.observableArrayList (gameRooms);
//        ListView<String> listOfGames = ((ListView<String>)(root.lookup("#myListView")));
//
//
////
//    }
}

