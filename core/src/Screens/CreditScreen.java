package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class CreditScreen  implements Screen{
	
	
	final String Line1 = "Coding: Elijah Kaufaman";
	final String Line2 = "Art: Elijah Kaufman";
	final String Line3 = "Game Design: Elijah Kaufman";
	OrthographicCamera cam;
	SpriteBatch batch;
	Viewport view;
	Game game;
	FontUtility font;
	private float timer = 0f;
	private float time = 2f;
	public CreditScreen(Game game, FontUtility font)
	{
		this.game = game;
		this.font = font;
		font.setFont(Color.TEAL, 24);
	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		font.setColor(Color.TEAL);
		font.setScale(3);
		font.draw(batch, Line1, 0/2, 600);
		font.draw(batch, Line2, 0, 500);
		font.draw(batch, Line3, 0, 400);
		font.setColor(Color.TEAL);
		batch.end();
		if(timer > time)
		{
			game.setScreen(new MenuScreen(game, font));
		}
		else
		{
			timer+=Gdx.graphics.getDeltaTime();
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		view.update(width, height);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		batch = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.position.set(1024/2, 768/2, 0);
		view = new StretchViewport(1024,768,cam);
		font.setScale(4);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
