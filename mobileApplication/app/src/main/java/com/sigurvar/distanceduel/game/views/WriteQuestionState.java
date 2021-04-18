package com.sigurvar.distanceduel.game.views;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.states.GameState;
import com.sigurvar.distanceduel.utility.APIController;
import com.sigurvar.distanceduel.utility.StateController;

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

    public void autocomplete(String text){
        displayInfo(autocomplete.suggestPlace(text));
    }

    public void displayInfo(String text){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView tv = findViewById(R.id.displayInfo);
                tv.setText(text);
            }
        });
    }
}
