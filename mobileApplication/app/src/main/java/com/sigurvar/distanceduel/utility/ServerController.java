package com.sigurvar.distanceduel.utility;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerController {

    private static ServerController serverController = new ServerController( );
    private boolean isConnected = false;

    private ServerController() { }
    public static ServerController getInstance( ) {
        return serverController;
    }

    Socket socket;
    public OutputThread outputThread;
    public InputThread inputThread;
    public  void connect() {
        try {
            this.socket = new Socket("10.22.71.86", 8888);
            this.outputThread = new OutputThread( new DataOutputStream(this.socket.getOutputStream()));
            this.inputThread = new InputThread(new DataInputStream(this.socket.getInputStream()), this);
            isConnected=true;
        } catch (IOException e) {
            System.out.println("Could not connect to server");
            e.printStackTrace();
        }
    }

    public boolean getIsConnected(){
        return isConnected;
    }
    public void disconnect(){
        this.isConnected=false;
        try {
            this.outputThread.leaveGame();
            this.inputThread.disconnect();
            this.outputThread.disconnect();
            this.socket.close();
        } catch ( IOException e ) {
            //ignore
        }
    }
}
