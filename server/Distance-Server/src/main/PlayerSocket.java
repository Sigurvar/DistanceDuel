package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class PlayerSocket extends Thread{

	private int id;
	private Socket socket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	
	public PlayerSocket(Socket socket, int id) {
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
		
		this.sendData("Dette er meg");
		this.waitForData();
		this.sendData("Den er god:);!");
		
	}
	
	public void waitForData() {
		byte messageType;
		try {
			while(inputStream.available() != 0);
			messageType = inputStream.readByte();
	        switch(messageType)
	        {
	            case 1: // Type A
	                System.out.println("Recived message: " + inputStream.readUTF());
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
