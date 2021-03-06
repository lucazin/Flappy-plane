package com.thevnkid93.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.thevnkid93.game.states.MenuState;

/**
 * The main starting point
 */
public class MyGame extends ApplicationAdapter {
	public  static final int WIDTH = 480;
	public  static final int HEIGHT = 800;
	public  static final String GAME_TITLE = "Flappy plane";

	private GameStateManager gsm;
	private SpriteBatch batch;

	private Music music;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		music = Gdx.audio.newMusic(Gdx.files.internal(SoundCons.BACKGROUND_MUSIC));
		music.setLooping(true);
		music.setVolume(.15f);
		music.play();
		Gdx.gl.glClearColor(0, 0, 0, 1); // clearing
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // wiping screen before rendering

		if(!gsm.update(Gdx.graphics.getDeltaTime()) ||
				!gsm.render(batch)){
			dispose();
			Gdx.app.exit();
		}
	}

	/**
	 * Dealocate all resources
	 */
	@Override
	public void dispose() {
		super.dispose();
		music.dispose();
	}
}
