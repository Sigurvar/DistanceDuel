package com.sigurvar.distanceduel.utility;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerController extends Thread {

    private static ServerController serverController = new ServerController( );

    /* A private Constructor prevents any other
     * class from instantiating.
     */
    private ServerController() { }

    /* Static 'instance' method */
    public static ServerController getInstance( ) {
        return serverController;
    }
    Socket socket;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;
    private boolean startGame = false;
    public boolean connected = false;
    public  void connect() {
        try {
            this.socket = new Socket("10.0.2.2", 8888);
            this.outputStream = new DataOutputStream(this.socket.getOutputStream());
            this.inputStream = new DataInputStream(socket.getInputStream());
            connected =true;
        } catch (IOException e) {
            System.out.println("Could not connect to server");
            e.printStackTrace();
        }



    }
    @Override
    public void run(){
        while(!this.isInterrupted()) {
            try {
                while (inputStream.available() != 0) {
                    if (startGame) {
                        sendStartGame();
                        startGame = false;
                    }
                }
                byte messageType = inputStream.readByte();
                String message = inputStream.readUTF();
                switch (messageType) {
                    case 1: // Players in game
                        System.out.println("Players in game: " + message);
                        break;
                    case 2: // Game code
                        System.out.println("Game code received: " + message);

                        return;
                    case 3: // New player joined
                        System.out.println(message + " joined the game");
                        break;
                    case 4: // received question
                        new java.util.Timer().schedule(
                                new java.util.TimerTask() {
                                    @Override
                                    public void run() {
                                        ServerController serverController = ServerController.getInstance();
                                        serverController.sendAnswer("-1");
                                    }
                                },
                                10000
                        );
                        System.out.println("Received question: " + message);
                        break;
                    case 5: // received result
                        System.out.println("Received result: " + message);
                        break;
                    case 6: // game done
                        System.out.println("Game done: " + message);
                        this.disconnect();
                        break;
                }
                // TODO: Insert logic which use the recived message (textMessage)
            } catch (IOException e1) {
                e1.printStackTrace();
                return;
            }
        }
    }


    public void sendNickname(String nickname){
        this.sendData(1, nickname);
    }
    public void sendNewGame(String setting){
        this.sendData(2, setting);
    }
    public void sendGameCode(String code){
        this.sendData(3, code);
    }
    public void sendAnswer(String answer){
        this.sendData(4, answer);
    }
    public void sendStartGame(){
        this.sendData(5, "");
    }


    private void sendData(int type, String message) {

        try {
            outputStream.writeByte(type);
            outputStream.writeUTF(message);
            outputStream.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Sent message "+message);
    }

    public void disconnect(){
        try {
            this.inputStream.close();
            this.outputStream.close();
            this.socket.close();
        } catch ( IOException e ) {
            //ignore
        }
    }
}
