package main;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;

import com.sun.net.httpserver.HttpsParameters;
import com.sun.net.httpserver.HttpsServer;

import game.Game;
import game.Player;
import game.Question;

public class Server {
	
	SSLContext sslContext;
	HttpsServer server;
	
	private ArrayList<Game> activeGames;


	public Server() {
		try {
			SSLContext sslContext = SSLContext.getInstance("yesyes");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			HttpsServer server = HttpsServer.create();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private SSLContext getSSLContext() {
			return this.sslContext;
	}
	

	public static void main(String[] args) {
		try {
			HttpsServer.create();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void sendCode(Player player, String code) {
		
	}
	
	public void sendQuestion(Player player, Question question) {
		
	}
	
	public void partialResult(Player player, HashMap<Player,Integer> result, float solution) {
		
	}
	
	public void finishGame(Player player) {
		//player.getIp()
	}
	
	//public void finalResult(Player player, ArrayList<HashMap<Player,Integer>> results) {
	//}

}



/*
 * server.setHttpsConfigurator(new HttpsConfigurator(sslContext) { public void
 * configure (HttpsParameters params) {
 * 
 * // get the remote address if needed InetSocketAddress remote =
 * params.getClientAddress();
 * 
 * SSLContext c = getSSLContext();
 * 
 * // get the default parameters SSLParameters sslparams =
 * c.getDefaultSSLParameters(); if (remote.equals() ) { // modify the default
 * set for client x }
 * 
 * params.setSSLParameters(sslparams); } });
 */
