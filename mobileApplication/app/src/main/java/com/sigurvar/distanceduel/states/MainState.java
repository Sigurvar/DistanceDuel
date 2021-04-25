package com.sigurvar.distanceduel.states;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.utility.APIController;
import com.sigurvar.distanceduel.utility.ServerController;
import com.sigurvar.distanceduel.utility.StateController;

public class MainState extends State {

    ServerController serverController = ServerController.getInstance();
    PopupWindow popupWindow = new PopupWindow();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_state);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        StateController.getInstance().setState(this);
    }

    public void createGame(View view){
        Intent myIntent = new Intent(this, NewGameState.class);
        this.startActivity(myIntent);
    }

    public void joinGame(View view){
        Intent myIntent = new Intent(this, JoinGameState.class);
        this.startActivity(myIntent);
    }

    public void viewInstructions(View view) {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        int height = ((int)(this.getWindowManager().getCurrentWindowMetrics().getBounds().height()*0.9));
        int width = ((int)(this.getWindowManager().getCurrentWindowMetrics().getBounds().width()*0.9));
        popupWindow = new PopupWindow(inflater.inflate(R.layout.popup_view_intructions, null, false), width,height, true);
        popupWindow.showAtLocation(this.findViewById(R.id.info), Gravity.CENTER, 0, 0);
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
    public void closePopup(View view){
        if(popupWindow!=null){
            popupWindow.dismiss();
            popupWindow=null;
        }
    }
    public void goToSettings(View view){
        Intent myIntent = new Intent(this, SettingsState.class);
        this.startActivity(myIntent);
    }

}