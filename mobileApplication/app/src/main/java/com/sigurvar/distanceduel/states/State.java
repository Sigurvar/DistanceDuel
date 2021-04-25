package com.sigurvar.distanceduel.states;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.utility.ServerController;

public abstract class State extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void displayInfo(String text){    }


    public void displayErrorMessage(String error){
        runOnUiThread(new Runnable() {
            public void run() {
                final Toast toast = Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}