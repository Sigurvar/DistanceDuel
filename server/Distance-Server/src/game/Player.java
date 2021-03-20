package game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Player extends Thread{
	
	private String nickname;
	private int id;
	private Socket socket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;

	public Player(Socket socket, int id) {
		start();
		this.socket=socket;
		this.id=id;
		try {
			this.outputStream = new DataOutputStream(this.socket.getOutputStream());
            this.inputStream = new DataInputStream(this.socket.getInputStream());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.waitForData();
		this.sendData("Den er god:);!");
		this.waitForData();
		
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getNickname() {
		return nickname;
	}


	public void waitForData() {
		byte messageType;
		try {
			while(inputStream.available() != 0);
			messageType = inputStream.readByte();
			String data = inputStream.readUTF();
	        switch(messageType)
	        {
	            case 1: // Nickname
	            	nickname= data;
	                System.out.println("Sett nickname to: " + data);
	                break;
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendData(String message) {
		System.out.println("sending message: " +message);
		try {
			outputStream.writeByte(1);
			outputStream.writeUTF(message);
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
       
	}
	public void disconect() {
		try {
			this.inputStream.close();
			this.outputStream.close();
			this.socket.close();
		} catch ( IOException e ) {
			//ignore
		}
	}

}
