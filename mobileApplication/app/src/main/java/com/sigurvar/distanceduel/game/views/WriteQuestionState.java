package com.sigurvar.distanceduel.game.views;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.game.Game;
import com.sigurvar.distanceduel.game.controller.WriteQuestionController;
import com.sigurvar.distanceduel.states.GameState;
import com.sigurvar.distanceduel.utility.APIController;
import com.sigurvar.distanceduel.utility.StateController;

import java.util.ArrayList;

public class WriteQuestionState extends GameState {

    APIController autocomplete = new APIController();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_question_state);
        StateController.getInstance().setState(this);

        ((EditText)findViewById(R.id.locationa)).addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length()>2){
                    autocomplete(s.toString());
                }
            }
        });
        ((EditText)findViewById(R.id.locationb)).addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) { }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length()>2){
                    autocomplete(s.toString());
                }
            }
        });
    }

    public void createQuestion(View view){
        String locationA = ((EditText)findViewById(R.id.locationa)).getText().toString();
        String locationB = ((EditText)findViewById(R.id.locationb)).getText().toString();
        ((WriteQuestionController) Game.getInstance().getGameController()).createdQuestion(locationA, locationB);
    }

    public void autocomplete(String text){
        autocomplete.run(text);
    }

    public void displayInfo(ArrayList<String> suggestions){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                StringBuilder text = new StringBuilder();
                for (String s: suggestions){
                    text.append(s);
                }
                TextView tv = findViewById(R.id.displayInfo);
                tv.setText(text.toString());
            }
        });
    }
}
