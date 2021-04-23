package com.sigurvar.distanceduel.game.models;

import com.sigurvar.distanceduel.game.views.LobbyState;
import com.sigurvar.distanceduel.utility.StateController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class GameModel {

    protected String gameCode;
    protected boolean isHost;
    private List<Player> players;
    private Stack<Question> questions;
    private boolean noMoreQuestions = false;
    private boolean createMoreQuestions = false;

    public GameModel(String myNickname, String gameCode){
        this.players = new ArrayList<>();
        this.questions = new Stack<>();
        this.gameCode = gameCode;
        this.players.add(new Player(myNickname, true));
    }

    public void setAsHost(){
        this.isHost=true;
        if(( StateController.getInstance().getState()) instanceof  LobbyState){
            ((LobbyState) StateController.getInstance().getState()).canStartGame();
        }
    }
    public boolean isHost(){
        return isHost;
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
        if(( StateController.getInstance().getState()) instanceof  LobbyState){
            ((LobbyState) StateController.getInstance().getState()).setPlayersInGame();
        }
    }
    public void playerLeftTheGame(String playerName){
        for (Player p : players){
            if (p.getNickname().equals(playerName)){
                players.remove(p);
            }
        }
        if(( StateController.getInstance().getState()) instanceof  LobbyState){
            ((LobbyState) StateController.getInstance().getState()).setPlayersInGame();
        }
    }

    public boolean isNoMoreQuestions() {
        return noMoreQuestions;
    }

    public boolean isCreateMoreQuestions() {
        return createMoreQuestions;
    }

    public void newQuestion(String question){
        questions.push(new Question(question));
    }
    public Double getQuestionAnswer(){
        return questions.peek().getCorrectAnswer();
    }

    public String getQuestionText(){
        return questions.peek().getQ();
    }
    public HashMap<String, ArrayList<Double>> getQuestionResult(){
        return  questions.peek().getResult();
    }
    public HashMap<String, ArrayList<Double>> getAndRemoveQuestionResult(){
        return  questions.empty() ?   null: questions.pop().getResult();
    }

    public void gotResultForQuestion(String result){
        Question q = questions.peek();
        try{
            JSONObject jsonObject = new JSONObject(result);
            q.setCorrectAnswer(jsonObject.getDouble("Answer"));
            q.setResult(jsonObject.getJSONObject("Result"));
            noMoreQuestions = jsonObject.getBoolean("Last");
        }catch (JSONException ignored){
            //TODO: gjør noe kult her
            System.out.println("Feil");
        }

    }
}
