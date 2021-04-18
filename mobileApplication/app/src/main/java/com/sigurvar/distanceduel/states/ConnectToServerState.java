package com.sigurvar.distanceduel.states;
import android.widget.TextView;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.utility.ServerController;

public abstract class ConnectToServerState extends State{

    /** Game Modes **/
    protected static final int NORMAL_MODE = 1;
    protected static final int WRITE_QUESTION_MODE = 2;

    protected String nickname = "";
    protected ServerController serverController = ServerController.getInstance();

    protected void connectToServer(){
        connect();
        setNickname();
    }

    private void setNickname(){
        nickname = ((TextView)findViewById(R.id.nickname)).getText().toString();
        //TODO: validate nickname
        serverController.outputThread.sendNickname(nickname);
    }
    private void connect(){
        serverController.connect();
    }
}
