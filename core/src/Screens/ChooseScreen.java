package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ChooseScreen  implements Screen{
	
	
	final String Line1 = "Choose Your Player";
	final String Line2 = "Red";
	final String Line3 = "Blue";
	Rectangle r1;
	Rectangle r2;
	OrthographicCamera cam;
	SpriteBatch batch;
	Viewport view;
	Game game;
	Preferences pref;
	FontUtility font;
	ShapeRenderer rend;
	public ChooseScreen(Game game,FontUtility font)
	{
		this.game = game;
		this.font = font;
		font.setFont(Color.BLACK, 24);
	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cam.position.set(256, 256, 0);
		cam.update();
		font.setScale(2);
		r1 = new Rectangle(60, 150, 140, 200);
		r2 = new Rectangle(325, 150, 140,200);
		batch.setProjectionMatrix(cam.combined);
		rend.setProjectionMatrix(cam.combined);
		shape();
		batch.begin();
		font.setColor(Color.PURPLE);
		font.draw(batch, Line1, 20, 470);
		font.setColor(Color.RED);
		isTouching(r1,1);
		font.draw(batch, Line2, 86, 325);
		font.setColor(Color.BLUE);
		isTouching(r2,2);
		font.draw(batch, Line3, 340, 325);
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
		cam.position.set(0,0,0);
		view = new StretchViewport(512,512,cam);
		rend = new ShapeRenderer();
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
	public void isTouching(Rectangle r,int intColor)
	{
		Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		//touch.set(touch.x, Math.abs(touch.y-Gdx.graphics.getHeight()), 0);
		touch = cam.unproject(touch);
		if(r.contains(touch.x, touch.y))
		{
			if(intColor == 1){
				font.setColor(Color.RED);
			}
			else{
				font.setColor(Color.BLUE);
			}
			if(Gdx.input.justTouched())
			{
				switch(intColor)
				{
				case 1 :
					game.setScreen(new GameScreen(game,font,true));
					break;
				case 2 :
					game.setScreen(new GameScreen(game,font,false));
					break;
				}
			}
		}
		else
		{
			font.setColor(Color.GRAY);
		}
	}
	public void shape(){
		float redx = 125;
		float bluex = 387;
		float width = 140;
		float height = 200;
		rend.begin(ShapeType.Filled);
		rend.setColor(Color.WHITE);
		rend.rect(60, 150, width,height);
		rend.rect(325, 150, width,height);
		rend.setColor(Color.BLUE);
		rend.circle(bluex,200, 30);
		rend.setColor(Color.RED);
		rend.circle(redx,200,30);
		rend.setColor(Color.BLACK);
		rend.circle(redx,200,25);
		rend.circle(bluex,200,25);
		rend.end();
	}

}
