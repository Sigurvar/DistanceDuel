package com.sigurvar.distanceduel.game.views;

import android.os.Bundle;
import android.widget.TextView;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.states.GameState;
import com.sigurvar.distanceduel.utility.StateController;

public class WaitResultState extends GameState {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_state);
        StateController.getInstance().setState(this);
        ((TextView)findViewById(R.id.header)).setText(getText(R.string.wait_for_answers));
    }
}
