package com.sigurvar.distanceduel.game.models;

import com.sigurvar.distanceduel.game.views.LobbyState;
import com.sigurvar.distanceduel.utility.StateController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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

    public String getQuestionText(){
        return questions.peek().getQ();
    }
    public String getQuestionResult(){
        System.out.println("Getting result");
        System.out.println(questions.peek().getResult());
        return  questions.peek().getResult();
    }
    public String getFinalResult(){
        StringBuilder out = new StringBuilder();
        while(!questions.empty()){
            out.append(questions.pop().getResult());
        }
        return out.toString();
    }


    public void gotResultForQuestion(String result){
        Question q = questions.peek();
        try{
            JSONObject jsonObject = new JSONObject(result);
            q.setAnswer(jsonObject.getString("Answer"));
            q.setResult(jsonObject.getString("Result"));
            noMoreQuestions = jsonObject.getBoolean("Last");
            // createMoreQuestions = jsonObject.getBoolean("More")
        }catch (JSONException ignored){
            //TODO: gjør noe kult her
            System.out.println("Feil");
        }

    }
}
