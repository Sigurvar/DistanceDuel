package com.sigurvar.distanceduel.game.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Stack;

public class GameModel extends Observable {

    protected String gameCode;
    protected boolean isHost;
    private List<Player> players;
    private Stack<Question> questions;

    public GameModel(String myNickname, String gameCode){
        this.players = new ArrayList<>();
        this.questions = new Stack<>();
        this.gameCode = gameCode;
        this.players.add(new Player(myNickname, true));
    }



    public void setAsHost(){
        this.isHost=true;
    }//TODO make observable
    public boolean isHost(){
        return isHost;
    }
    public void setGameCode(String gameCode){
        this.gameCode = gameCode;
    }
    public String getGameCode() {
        return gameCode;
    }

    public String getAllPlayersInGame(){
        StringBuilder str = new StringBuilder();
        for(Player p: players) str.append(p.getNickname()).append("\n");
        return str.toString();
    }

    public void addNewPlayer(String playerName){
        this.players.add(new Player(playerName, false));
        setChanged();
        notifyObservers(); //TODO ikke nødvendig å ha observer her, kan gjøres fint uten
    }
    public void playerLeftTheGame(String playerName){
        this.players.add(new Player(playerName, false));
        setChanged();
        notifyObservers();
    }

    public void newQuestion(String question){
        questions.push(new Question(question));
    }

    public String getQuestionText(){
        return questions.peek().getQ();
    }
    public String getQuestionResult(){
        System.out.println("Getting result");
        System.out.println(questions.peek().getResult());
        return  questions.peek().getResult();
    }


    public void gotResultForQuestion(String result){
        questions.peek().setResult(result);
    }
}
