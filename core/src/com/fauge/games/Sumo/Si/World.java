package com.fauge.games.Sumo.Si;

import Screens.FontUtility;
import Screens.GameScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;

public class World {
	Wrestler Red;
	Wrestler Blue;
	Circle bounds;
	public static boolean collided;
	public static boolean collision;
	public static String winner;
	public static boolean paused = false;
	public World(boolean playerRed){
		Red = new Wrestler(true,playerRed);
		Blue = new Wrestler(false,!playerRed);
		bounds = new Circle(254,255,232);
		collided = false;
		collision = false;
		winner = "";
	}
	public void update(SpriteBatch b,ShapeRenderer rend,FontUtility font){
		if(!collision && Red.c.overlaps(Blue.c))
			collision = true;
		else if(!Red.c.overlaps(Blue.c)){
			collision = false;
			collided = false;
			if(Red.x > Blue.x){
				Red.acceleration -=1f; 
				Blue.acceleration +=1f;
			}
			else{
				Red.acceleration +=1f; 
				Blue.acceleration -=1f;
			}
		}
		
		if(!collided && collision){
			float newAcceleration = (Red.mass*Red.acceleration + Blue.mass*Blue.acceleration)/(Red.mass+Blue.mass);
			Red.dx = 0;
			Blue.dx = 0;
			Red.acceleration = 	newAcceleration;
			Blue.acceleration = newAcceleration;
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
		rend.begin(ShapeType.Filled);
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
