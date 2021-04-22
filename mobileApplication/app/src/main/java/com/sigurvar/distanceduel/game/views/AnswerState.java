package com.sigurvar.distanceduel.game.views;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.states.GameState;
import com.sigurvar.distanceduel.utility.StateController;

import java.util.Timer;
import java.util.TimerTask;

public class AnswerState extends GameState {

    Timer timer;
    private int timeCounter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_state);
        StateController.getInstance().setState(this);
        displayQuestion();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerMethod();
            }

        }, 0, 1000);
    }
    public void sendAnswer(View view){
        timer.cancel();
        this.sendAnswer( ((TextView)findViewById(R.id.answer)).getText().toString());
    }
    private void sendAnswer(String answer){
        gameController.sendAnswer(answer);
    }

    public void displayQuestion(){
        ((TextView)findViewById(R.id.question)).setText("Question:\n"+gameModel.getQuestionText());
    }


    private void TimerMethod()
    {
        this.runOnUiThread(Timer_Tick);
    }


    private Runnable Timer_Tick = new Runnable() {
        public void run() {
            timeCounter++;
            System.out.println(timeCounter);
            ((TextView)findViewById(R.id.timer)).setText(String.valueOf(10-timeCounter));
            // TODO: sett timer on displayscreen
            if(timeCounter>10){
                System.out.println("Timer done");
                timer.cancel();
                sendAnswer("0");
            }
        }
    };
}
