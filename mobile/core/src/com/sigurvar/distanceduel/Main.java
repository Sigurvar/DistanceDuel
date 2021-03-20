package com.sigurvar.distanceduel;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sigurvar.distanceduel.utility.ServerController;
import com.sigurvar.distanceduel.utility.StateController;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main extends ApplicationAdapter {
	private StateController stateController;
	private ServerController serverController;
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		try {
			serverController = new ServerController();
		}catch (IOException e){
			System.out.println("Could not connect to service");
		}
		serverController.get();
		serverController.sendData("nick1");
		serverController.waitForData();
		serverController.sendData("Dette er en test");
		serverController.disconnect();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
