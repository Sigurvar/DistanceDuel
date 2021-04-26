package com.sigurvar.distanceduel.states;
import android.widget.TextView;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.utility.ServerController;

import java.io.IOException;

public abstract class ConnectToServerState extends State{

    protected String nickname = "";
    protected ServerController serverController = ServerController.getInstance();

    protected void connectToServer(){
        connect();
        setNickname();
    }

    private void setNickname(){
        nickname = ((TextView)findViewById(R.id.nickname)).getText().toString();
        serverController.outputThread.sendNickname(nickname);
    }
    private void connect(){
        try {
            serverController.connect();
        }catch (IOException e){
            displayErrorMessage((String) getText(R.string.error_could_not_connect_to_server));
        }
    }
}
