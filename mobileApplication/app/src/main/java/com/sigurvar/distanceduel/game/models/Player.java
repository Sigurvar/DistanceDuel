package com.sigurvar.distanceduel.game.models;

public class Player {
    private String nickname;
    private boolean isMe;

    public Player(String nickname, boolean isMe){
        this.nickname = nickname;
        this.isMe = isMe;
    }

    public String getNickname(){
        return nickname;
    }

    public boolean isMe(){
        return isMe;
    }
}
