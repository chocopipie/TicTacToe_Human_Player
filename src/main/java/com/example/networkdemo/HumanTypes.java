package com.example.networkdemo;

public enum HumanTypes implements Typess{
    CREATE_GAME ("Create a new game"),
    JOIN_GAME ("Join an existing game"),
    MAKE_MOVE ("Make a move"),
    QUIT ("Quit Game"),
    REMATCH_REQUEST ("Request rematch"),
    REMATCH_REJECT ("Reject rematch"),
    REMATCH_ACCEPT ("Accept Rematch");


    private String description;

    private HumanTypes(String description){

        this.description = description;
    }

    public String getDescription(){

        return description;
    }

}
