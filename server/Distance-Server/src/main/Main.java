package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import game.Game;
import game.NormalMode;
import game.Player;
import game.Unit;

public class Main {
	
	private static Main main = new Main( );
    private Main() { }
    
    public static Main getInstance( ) {
        return main;
    }
    // TODO: Har bare en server, hvordan blir det hvis vi utvider med flere 
	Server server;
	// TODO Har begrensning på antall spillere, trenger det være begrensning på antall spill??

	
	public  void startServer() {
		try {
			server = Server.getInstance();
			server.start(8888);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args)
    {
        Main main = new Main();
        main.startServer();
    }

}
