package com.sigurvar.distanceduel.states;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public abstract class State extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void displayErrorMessage(String error){
        runOnUiThread(new Runnable() {
            public void run() {
                final Toast toast = Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}