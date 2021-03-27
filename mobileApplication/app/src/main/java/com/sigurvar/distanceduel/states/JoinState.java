package com.sigurvar.distanceduel.states;


import android.os.Bundle;
import com.sigurvar.distanceduel.R;

public class JoinState extends State {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_state);
    }
}