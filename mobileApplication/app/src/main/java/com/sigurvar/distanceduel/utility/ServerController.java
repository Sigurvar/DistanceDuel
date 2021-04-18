package com.sigurvar.distanceduel.utility;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerController {

    private static ServerController serverController = new ServerController( );

    private ServerController() { }
    public static ServerController getInstance( ) {
        return serverController;
    }

    Socket socket;
    public OutputThread outputThread;
    public InputThread inputThread;
    public  void connect() {
        try {
            this.socket = new Socket("192.168.10.198", 8888);
            this.outputThread = new OutputThread( new DataOutputStream(this.socket.getOutputStream()));
            this.inputThread = new InputThread(new DataInputStream(this.socket.getInputStream()), this);
        } catch (IOException e) {
            System.out.println("Could not connect to server");
            e.printStackTrace();
        }
    }

    void answerToSlow(){
        //handle user not answering in time
        serverController.outputThread.sendAnswer("-1");
    }
    public void disconnect(){
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
