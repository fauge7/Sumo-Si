package com.fauge.games.Sumo.Si;

import Screens.FontUtility;
import Screens.GameScreen;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class World {
	public static Wrestler Red;
	public static Wrestler Blue;
	Circle bounds;
	public static boolean collided;
	public static boolean collision;
	public static String winner;
	public static boolean paused = false;
	public static Rectangle up;
	public static Rectangle down;
	public static Rectangle left;
	public static Rectangle right;
	public World(boolean playerRed){
		Red = new Wrestler(true,playerRed);
		Blue = new Wrestler(false,!playerRed);
		bounds = new Circle(254,255,232);
		collided = false;
		collision = false;
		winner = "";
		//rend.rect(75,0,75,75);
		//rend.rect(75, 150, 75, 75);
		//rend.rect(0, 75, 75, 75);
		//rend.rect(150, 75, 75, 75);
		int width = 75;
		int height = 75;
		up = new Rectangle(75,150,width,height);
		down = new Rectangle(75,0, width, height);
		left = new Rectangle(0,75, width, height);
		right = new Rectangle(150,75, width, height);
	}
	public void update(SpriteBatch b,ShapeRenderer rend,FontUtility font){
		if(!collision && Red.c.overlaps(Blue.c))
			collision = true;
		else if(!Red.c.overlaps(Blue.c)){
			collision = false;
			collided = false;
			if(Red.x > Blue.x){
				Red.velX -=1f; 
				Blue.velX +=1f;
			}
			else{
				Red.velX +=1f; 
				Blue.velX -=1f;
			}
		}
		
		if(!collided && collision){
			float newAccelerationX = (Red.mass*Red.velX + Blue.mass*Blue.velX)/(Red.mass+Blue.mass);
			float newAccelerationY = (Red.mass*Red.velY + Blue.mass*Blue.velY)/(Red.mass+Blue.mass);
			Red.dx = 0;
			Blue.dx = 0;
			Red.velX = 	newAccelerationX;
			Blue.velX = newAccelerationX;
			Red.velY = newAccelerationY;
			Blue.velY = newAccelerationY;
			collided = true;
		}
		if(!paused){
			Red.update();
			Blue.update();
		}
		
		//match over check
		if(!bounds.contains(Red.c)){
			Red.looses = true;
			GameScreen.win = Winner.BLUE;
			font.setColor(Color.BLUE);
			if(Red.player_Controlled){
				winner = "Blue wins!";
			}
			else{
				winner = "Player wins!";
			}
			GameScreen.gameOver = true;
			paused = true;
		}
		else if(!bounds.contains(Blue.c)){
			GameScreen.win = Winner.RED;
			Blue.looses = true;
			if(Red.player_Controlled){
				winner = "Player wins!";
			}
			else{
				winner = "Red wins!";
			}
			font.setColor(Color.RED);
			GameScreen.gameOver = true;
			paused = true;
		}
		
		//draw the screen with shapes
		//wrestlers
		rend.begin(ShapeType.Filled);
		if(Gdx.app.getType() == ApplicationType.Android){
			rend.setColor(Color.GRAY);
			rend.rect(75,0,75,75);
			rend.rect(75, 150, 75, 75);
			rend.rect(0, 75, 75, 75);
			rend.rect(150, 75, 75, 75);
		}
		rend.setColor(Color.BLUE);
		rend.circle(Blue.c.x, Blue.y, 30);
		rend.setColor(Color.RED);
		rend.circle(Red.c.x, Red.y, 30);
		rend.setColor(Color.BLACK);
		rend.circle(Blue.c.x,Blue.c.y,25);
		rend.circle(Red.c.x,Red.c.y,25);
		rend.end();
		
	}
}
