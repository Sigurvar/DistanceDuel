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
	Server server;

	
	public  void startServer() {
		try {
			server = Server.getInstance();
			server.start(8888);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args)
    {
        Main main = new Main();
        main.startServer();
    }

}
