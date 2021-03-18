package com.sigurvar.distanceduel;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sigurvar.distanceduel.utility.APIController;
import com.sigurvar.distanceduel.utility.NetworkComponent;
import com.sigurvar.distanceduel.utility.ServerController;
import com.sigurvar.distanceduel.utility.StateController;

public class Main extends ApplicationAdapter {
	private StateController stateController;
	private ServerController serverController;
	SpriteBatch batch;
	Texture img;
	APIController APIController;


	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		serverController = new ServerController();
		serverController.get();
		APIController = new APIController();
		APIController.get(); // API for locations

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
