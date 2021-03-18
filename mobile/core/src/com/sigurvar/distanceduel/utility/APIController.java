package com.sigurvar.distanceduel.utility;

import java.io.IOException;

public class APIController extends NetworkComponent {

    public  APIController(){
        super();
    }

    public void get() {
        try {
            super.SendRequest("http://api.positionstack.com/v1/forward?access_key=bbf5216ec5ff418aea03e7544e54729d&query=lomveien%207%203124%20t%C3%B8nsberg", "");
        }catch (IOException e){
            System.out.println(e);
            status=1;
        }
    }

}
