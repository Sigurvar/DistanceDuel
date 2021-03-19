package com.sigurvar.distanceduel.utility;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerController extends NetworkComponent {

    Socket s;
    private DataOutputStream dOut;
    public  ServerController(){
        super();
    }

    public void get() {
        try {
            System.out.println(super.SendRequest("http://ip.jsontest.com/"));
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public void connect(){
        try{
            this.s = new Socket("10.0.2.2", 8888);
            try {
                this.dOut = new DataOutputStream(this.s.getOutputStream());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            while(true) {
                DataInputStream dIn = new DataInputStream(s.getInputStream());
                boolean done = false;
                while(!done) {
                    byte messageType = dIn.readByte();

                    switch(messageType)
                    {
                        case 1: // Type A
                            System.out.println("Received message: " + dIn.readUTF());
                            sendData("responding");
                            break;
                        case 2: // Type B
                            System.out.println("Message B: " + dIn.readUTF());
                            break;
                        case 3: // Type C
                            System.out.println("Message C [1]: " + dIn.readUTF());
                            System.out.println("Message C [2]: " + dIn.readUTF());
                            break;
            }}}
            // TODO: Insert logic which use the recived message (textMessage)
        }catch (UnknownHostException e1){
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void sendData(String message) {

        try {
            dOut.writeByte(1);
            dOut.writeUTF(message);
            dOut.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Sent message "+message);

    }
}
