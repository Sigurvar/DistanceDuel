package game;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Player extends Thread{
	
	private String nickname;
	private int id;
	private Socket socket;
	private PrintWriter writer;
	private DataInputStream dIn;
	private DataOutputStream dOut;

	public Player(Socket connection, int id) {
		this.socket=connection;
		this.id=id;
		this.nickname = "test";
		try {
			this.dOut = new DataOutputStream(this.socket.getOutputStream());
            this.dIn = new DataInputStream(this.socket.getInputStream());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.sendData("Hello i'am "+this.nickname);
		this.run();
		this.sendData("melding nr2");
		this.run();
	}

	public String getNickname() {
		return nickname;
	}


	@Override
	public void run() {
		System.out.println("running");
		byte messageType;
		try {
			messageType = dIn.readByte();
		

        switch(messageType)
        {
            case 1: // Type A
                System.out.println("Recived message: " + dIn.readUTF());
                break;
            case 2: // Type B
                System.out.println("Message B: " + dIn.readUTF());
                break;
            case 3: // Type C
                System.out.println("Message C [1]: " + dIn.readUTF());
                System.out.println("Message C [2]: " + dIn.readUTF());
                break;
        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			//process a client request
			//this is for you to implement
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
		
		System.out.println("sending message: " +message);
       
	}

	//as an example, if you read String messages from your client,
	//just call this method from the run() method to process the client request
	public void process( String message ) {

	}


	/**
	 * terminates the connection with this client (i.e. stops serving it)
	 */
	public void disconect() {
		try {
			this.writer.close();
			this.socket.close();
		} catch ( IOException e ) {
			//ignore
		}
	}

}
