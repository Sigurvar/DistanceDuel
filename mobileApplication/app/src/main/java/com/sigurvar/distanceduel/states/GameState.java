package com.sigurvar.distanceduel.states;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.game.Game;
import com.sigurvar.distanceduel.game.controller.GameController;
import com.sigurvar.distanceduel.game.models.GameModel;
import com.sigurvar.distanceduel.utility.ServerController;

public abstract class GameState extends State {

    protected GameController gameController = Game.getInstance().getGameController();
    protected GameModel gameModel = Game.getInstance().getGameModel();
    private PopupWindow popupWindow = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);
    }

    @Override
    public void onBackPressed() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        int height = ((int)(this.getWindowManager().getCurrentWindowMetrics().getBounds().height()*0.2));
        int width = ((int)(this.getWindowManager().getCurrentWindowMetrics().getBounds().width()*0.9));
        popupWindow = new PopupWindow(inflater.inflate(R.layout.popup_leave_game, null, false), width,height, true);
        popupWindow.showAtLocation(this.findViewById(R.id.main), Gravity.CENTER, 0, 0);
    }

    public void leaveGame(View view){
        closePopup(view);
        ServerController.getInstance().outputThread.leaveGame();
        ServerController.getInstance().disconnect();
        Intent intent = new Intent(this, MainState.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public void closePopup(View view){
        if(popupWindow!=null){
            popupWindow.dismiss();
            popupWindow=null;

        }
    }
}