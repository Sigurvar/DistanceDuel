package com.sigurvar.distanceduel.utility;

import java.io.IOException;

public class ServerController extends NetworkComponent {

    public  ServerController(){
        super();
    }

    public void get() {
        try {
            System.out.println(super.SendRequest("http://ip.jsontest.com/", ""));
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
