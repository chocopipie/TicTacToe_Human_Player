package com.example.networkdemo;

public enum sendType implements Types{
    CREATE_GAME ("Create a new game"),
    JOIN_GAME ("Join an existing game"),
    MAKE_MOVE ("Make a move"),
    QUIT ("Quit Game"),
    REMATCH_REQUEST ("Request rematch"),
    REMATCH_REJECT ("Reject rematch"),
    REMATCH_ACCEPT ("Accept Rematch");


    private String description;

    private sendType(String description){

        this.description = description;
    }

    public String getDescription(){

        return description;
    }

}
