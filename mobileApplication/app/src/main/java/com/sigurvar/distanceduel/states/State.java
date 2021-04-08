package com.sigurvar.distanceduel.states;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.utility.ServerController;

public abstract class State extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_state1);
    }

    @Override
    public void onBackPressed() {
        // TODO: Legg til spm om man er sikker på å forlate spillet, skal kanskje inn i GAMEMODE
        ServerController serverController = ServerController.getInstance();
    }

    @Override
    public void onDestroy() {
        ServerController serverController = ServerController.getInstance();
        serverController.disconnect();
        super.onDestroy();
    }

    public void displayInfo(String text){    }
}