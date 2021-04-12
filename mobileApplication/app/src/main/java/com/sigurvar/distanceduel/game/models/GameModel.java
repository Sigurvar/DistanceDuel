package com.sigurvar.distanceduel.game.models;

public class GameModel {

    protected String gameCode;
    protected boolean isHost;
    private GameMode gameMode;

    public GameModel(String gameCode, boolean isHost, GameMode gameMode){
        this.gameCode = gameCode;
        this.isHost = isHost;
        this.gameMode = gameMode;
    }


    public void setAsHost(){
        this.isHost=true;
    }
    public boolean isHost(){
        return isHost;
    }
    public void setGameCode(String gameCode){
        this.gameCode = gameCode;
    }
    public String getGameCode() {
        return gameCode;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }
}
