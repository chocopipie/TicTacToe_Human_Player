package com.example.networkdemo;


public class HumanPlayer {


    private String userName;
    private String room_id;
    private int port = 8000;

    //Constructor
    public HumanPlayer (String name){
        userName = name;
        String room_id  = " "; //always initialized as empty string, will get this value
                               //once player joins a room to play.
    }

    //setters
    public void setUserName (String s){
        this.userName = s;
    }

    public void setRoom_id (String s){
        this.room_id = s;
    }

    //getters
    public String getUserName(){
        return userName;
    }

    public String getRoom_id(){
        return room_id;
    }


}
