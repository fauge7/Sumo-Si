package com.fauge.games.Sumo.Si;

import Screens.GameScreen;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;

public class Wrestler {
	
	float x;
	float y;
	float dx;
	float dy;
	float mass;
	float velX = 4f;
	float velY = 0f;
	boolean looses = false;
	Texture t;
	public Sprite s;
	Circle c;
	boolean player_Controlled = false;
	Winner color;
	public Wrestler(boolean red,boolean player){
		this.player_Controlled = player;
		if(player_Controlled){
			if(red){
				GameScreen.player = Winner.RED;
			}
			else{
				GameScreen.player = Winner.BLUE;
			}
		}
		if(red)
			color = Winner.RED;
		else
			color = Winner.BLUE;
		x = 240;
		y = 235;
		dx = 0;
		dy = 0;
		mass = MathUtils.random(15/2.2f, 50/2.2f);
		if(red){
			t = new Texture("red.png");
			x = 130+28;
		}
		else{
			t = new Texture("blue.png");
			velX = -velX;
			x = 315+28;
		}
		s = new Sprite(t);
		s.setPosition(x, y);
		c = new Circle(x, y, 28);
	}
	public float getForce(){
		return mass * velX;
	}
	public void update(){
		if(!player_Controlled){
			if(color == Winner.RED){
				if(World.Blue.y-y > 5){
					velY+=.05f;
				}
				else if(World.Blue.y-y < -5){
					velY-=.05f;
				}
				else{
					velY*=.75f;
				}
			}
		}
		else{
			if(Gdx.app.getType() == ApplicationType.Desktop){
				System.out.println(velY);
				if(Gdx.input.isKeyPressed(Keys.LEFT)){
					velX-=.1f;
				}
				else if(Gdx.input.isKeyPressed(Keys.RIGHT)){
					velX+=.1f;
				}
				if(Gdx.input.isKeyPressed(Keys.UP)){
					velY+=.1f;
				}
				else if(Gdx.input.isKeyPressed(Keys.DOWN)){
					velY-=.1f;
				}
			}
			else if(Gdx.app.getType() == ApplicationType.Android){
				if(player_Controlled){
					if(World.up.contains(GameScreen.getTouch())){
						velY+=.1f;
						
					}
					else if(World.down.contains(GameScreen.getTouch())){
						velY-=.1f;

					}
					else if(World.left.contains(GameScreen.getTouch())){
						velX-=.1f;
					}
					else if(World.right.contains(GameScreen.getTouch())){
						velX+=.1f;
					}
				}
			}
		}
		dx+=velX * Gdx.graphics.getDeltaTime();
		dy+=velY * Gdx.graphics.getDeltaTime();
		x+=dx;
		y+=dy;
		c.setPosition(x, y);
		s.setPosition(x, y+40);
	}

}
