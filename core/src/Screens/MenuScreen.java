package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MenuScreen  implements Screen{
	
	
	final String Line1 = "Sumo Si";
	final String Line2 = "New Game";
	final String Line3 = "Credits";
	String Line4;
	Rectangle r1;
	Rectangle r2;
	Rectangle r3;
	Rectangle r4;
	OrthographicCamera cam;
	SpriteBatch batch;
	Viewport view;
	Game game;
	Preferences pref;
	FontUtility font;
	public MenuScreen(Game game,FontUtility font)
	{
		this.game = game;
		pref = Gdx.app.getPreferences("Sumo-Si");
		Line4 = "High Score: " + pref.getInteger("score",0);
		this.font = font;
		font.setFont(Color.BLACK, 24);
	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		r2 = new Rectangle(550, 500-50, font.getBounds(Line2).width, font.getBounds(Line2).height);
		r3 = new Rectangle(350, 300-50, font.getBounds(Line3).width, font.getBounds(Line3).height);
		font.setScale(5);
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		font.setColor(Color.ORANGE);
		font.draw(batch, Line1, 50, 700);
		font.setScale(4);
		font.setColor(Color.GREEN);
		isTouching(r2,1);
		font.draw(batch, Line2, 550, 500);
		isTouching(r3,2);
		font.draw(batch, Line3, 350, 300);
		font.setColor(Color.GREEN);
		font.draw(batch, Line4, 150, 100);
		batch.end();
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
		//font.setScale(2);
		r2 = new Rectangle(550, 500-50, font.getBounds(Line2).width, font.getBounds(Line2).height);
		r3 = new Rectangle(350, 300-50, font.getBounds(Line3).width, font.getBounds(Line3).height);
		pref = Gdx.app.getPreferences("Sumo-Si");
		Line4 = "High Score: " + pref.getInteger("score",0);
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
	public void isTouching(Rectangle r,int screen)
	{
		Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		//touch.set(touch.x, Math.abs(touch.y-Gdx.graphics.getHeight()), 0);
		touch = cam.unproject(touch);
		if(r.contains(touch.x, touch.y))
		{
			font.setColor(Color.RED);
			if(Gdx.input.justTouched())
			{
				switch(screen)
				{
				case 1 :
					game.setScreen(new ChooseScreen(game,font));
					break;
				case 2 :
					game.setScreen(new CreditScreen(game,font));
					break;
				}
			}
		}
		else
		{
			font.setColor(Color.GREEN);
		}
	}

}
