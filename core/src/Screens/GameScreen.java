package Screens;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fauge.games.Sumo.Si.Winner;
import com.fauge.games.Sumo.Si.World;

public class GameScreen implements Screen{

	SpriteBatch batch;
	Texture img;
	World w;
	ShapeRenderer rend;
	Game game;
	FontUtility font;
	Rectangle goRect;
	static OrthographicCamera cam;
	Viewport view;
	public static Winner win;
	public static Winner player;
	static boolean playerRed;
	public static boolean gameOver = false;
	public static int winStreak;
	public GameScreen(Game game,FontUtility font, boolean b) {
		// TODO Auto-generated constructor stub
		this.game = game;
		this.font = font;
		GameScreen.playerRed = b;
		System.out.println(b);
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(cam.combined);
		rend.setProjectionMatrix(cam.combined);
		cam.position.set(256, 256, 1);
		cam.update();
		batch.begin();
		batch.draw(img, 0, 0);
		if(win == Winner.RED)
			font.setColor(Color.RED);
		else
			font.setColor(Color.BLUE);
		font.draw(batch, World.winner, 256-font.getBounds(World.winner).width/2, 400);
		batch.end();
		w.update(batch,rend,font);
		if(gameOver){
			Preferences pref = Gdx.app.getPreferences("Sumo-Si");
			pref.putInteger("score", winStreak);
			pref.flush();
			
			rend.begin(ShapeType.Filled);
			rend.setColor(Color.MAGENTA);
			rend.rect(256-font.getBounds("Play Again?").width/2-25,110,font.getBounds("Play Again?").width+50,font.getBounds("Play Again?").height+50);
			rend.end();
			batch.begin();
			font.setColor(Color.GREEN);
			font.draw(batch, "Play Again?", 256-font.getBounds("Play Again?").width/2, 175);
			batch.end();
			
			if(Gdx.input.justTouched()){
				if(goRect.contains(getTouch())){
					if(GameScreen.win != GameScreen.player){
						gameOver = false;
						game.setScreen(new MenuScreen(game, font));
					}
					else{
						winStreak++;
						save(winStreak);
						gameOver = false;
						game.setScreen(new ChooseScreen(game, font));
					}
				}
			}
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		cam = new OrthographicCamera(512,512);
		view = new StretchViewport(512,512,cam);
		batch = new SpriteBatch();
		rend = new ShapeRenderer();
		img = new Texture("backround.png");
		w = new World(playerRed);
		Preferences pref = Gdx.app.getPreferences("Sumo-Si");
		winStreak = pref.getInteger("score", 0);
		gameOver = false;
		World.paused = false;
		goRect = new Rectangle(256-font.getBounds("Play Again?").width/2-25,110,font.getBounds("Play Again?").width+50,font.getBounds("Play Again?").height+50);
		win = Winner.NONE;
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
	public static Vector2 getTouch(){
		Vector3 temp = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
		temp = cam.unproject(temp);
		return new Vector2(temp.x,temp.y);
	}
	public static void save(int i){
		Preferences pref = Gdx.app.getPreferences("Sumo-Si");
		if(i > pref.getInteger("score"))
			pref.putInteger("score", i);
	}

}
