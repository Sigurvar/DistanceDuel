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
    private int activeAutoCompleteBox =0;
    private boolean clicked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_question_state);
        StateController.getInstance().setState(this);

        ((EditText)findViewById(R.id.locationa)).addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length()>2 && !clicked){
                    activeAutoCompleteBox = R.id.locationa;
                    getAutocompleteSuggestion(s.toString());
                }
                clicked=false;
            }
        });
        ((EditText)findViewById(R.id.locationb)).addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) { }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length()>2 && !clicked){
                    activeAutoCompleteBox = R.id.locationb;
                    getAutocompleteSuggestion(s.toString());
                }
                clicked=false;
            }
        });
    }

    public void createQuestion(View view){
        String locationA = ((EditText)findViewById(R.id.locationa)).getText().toString();
        String locationB = ((EditText)findViewById(R.id.locationb)).getText().toString();
        ((WriteQuestionController) Game.getInstance().getGameController()).createdQuestion(locationA, locationB);
    }

    public void getAutocompleteSuggestion(String text){
        autocomplete.run(text);
    }

    public void displayInfo(ArrayList<String> suggestions){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView tv1= findViewById(R.id.suggestion1);
                TextView tv2= findViewById(R.id.suggestion2);
                TextView tv3= findViewById(R.id.suggestion3);
                if (suggestions.size()>0){
                    findViewById(R.id.include).setVisibility(View.VISIBLE);
                    tv1.setText(suggestions.get(0));
                    if (suggestions.size()>1){
                        tv2.setText(suggestions.get(1));
                        if (suggestions.size()>2){
                            tv3.setText(suggestions.get(2));
                        }else{
                            tv3.setText("");
                        }
                    }else{
                        tv3.setText("");
                        tv2.setText("");
                    }
                }else{
                    findViewById(R.id.include).setVisibility(View.INVISIBLE);
                    tv3.setText("");
                    tv2.setText("");
                    tv1.setText("");
                }
            }
        });
    }
    public void onclickSuggestion(View view){
        clicked=true;

        ((TextView)findViewById(activeAutoCompleteBox)).setText(((TextView)view).getText());
        displayInfo(new ArrayList<>());
        //System.out.println(((TextView)view).getText());
    }

}
