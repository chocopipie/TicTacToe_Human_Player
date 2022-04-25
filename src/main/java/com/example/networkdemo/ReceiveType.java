package com.example.networkdemo;

public enum ReceiveType implements Typess {
    GAME_CREATED ("New game created."),
    GAME_REJECTED ("'Create Game' rejected."),
    JOIN_SUCCESS ("Joined Game Successfully."),
    JOIN_FAILED ("Join Game failed"),
    MOVE_REJECTED ("Move rejected"),
    MOVE_MADE ("Move made."),
    PLAYER_TO_MOVE ("Your turn"),
    WINNER ("You Won!"),
    TIE ("TIED"),
    REMATCH_REQUESTED("Play Again?"),
    REMATCH_REJECTED("Opponent does not want to play with you :("),
    REMATCH_ACCEPTED("Rematch accepted :)"),
    SENT_MESSAGE("Message was sent.");


    private String description;

    private ReceiveType(String description){

        this.description = description;
    }

    public String getDescription(){

        return description;
    }
}
