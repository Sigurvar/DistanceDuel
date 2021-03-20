package com.sigurvar.distanceduel.utility;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerController extends NetworkComponent {

    Socket socket;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;
    public  ServerController() throws IOException {
        this.socket = new Socket("10.0.2.2", 8888);
        this.outputStream = new DataOutputStream(this.socket.getOutputStream());
        this.inputStream = new DataInputStream(socket.getInputStream());

    }

    public void get() {
        try {
            System.out.println(super.SendRequest("http://ip.jsontest.com/"));
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public void waitForData(){
        try{
            while(inputStream.available() != 0){}
            byte messageType = inputStream.readByte();
            switch(messageType)
            {
                case 1: // Type A
                    System.out.println("Received message: " + inputStream.readUTF());
                    break;
                case 2: // Type B
                    System.out.println("Message B: " + inputStream.readUTF());
                    break;
                case 3: // Type C
                    System.out.println("Message C [1]: " + inputStream.readUTF());
                    System.out.println("Message C [2]: " + inputStream.readUTF());
                    break;
        }
            // TODO: Insert logic which use the recived message (textMessage)
        } catch (IOException e1){
            e1.printStackTrace();
        }
    }

    public void sendData(String message) {

        try {
            outputStream.writeByte(1);
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
