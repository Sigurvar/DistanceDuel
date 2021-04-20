package com.sigurvar.distanceduel.states;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.utility.ServerController;

public abstract class State extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void displayInfo(String text){    }
}