package com.sigurvar.distanceduel.game.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.states.GameState;
import com.sigurvar.distanceduel.states.MainState;
import com.sigurvar.distanceduel.utility.StateController;

public class FinalResultState extends GameState {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result_state);
        StateController.getInstance().setState(this);
        displayResult();
    }

    public void displayResult(){
        ((TextView)findViewById(R.id.result)).setText("Result:\n"+gameModel.getFinalResult());
    }

    public void goHome(View view){
        //TODO: go to main and delete onBackButtonPressedStack
        Intent intent = new Intent(this, MainState.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
