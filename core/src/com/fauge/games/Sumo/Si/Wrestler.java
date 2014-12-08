package com.fauge.games.Sumo.Si;

import Screens.GameScreen;

import com.badlogic.gdx.Gdx;
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
	float acceleration = 4f;
	boolean looses = false;
	Texture t;
	public Sprite s;
	Circle c;
	boolean player_Controlled = false;
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
			acceleration = -acceleration;
			x = 315+28;
		}
		s = new Sprite(t);
		s.setPosition(x, y);
		c = new Circle(x, y, 28);
	}
	public float getForce(){
		return mass * acceleration;
	}
	public void update(){
		dx+=acceleration * Gdx.graphics.getDeltaTime();
		x+=dx;
		//x = 315;
		y+=dy;
		c.setPosition(x, y);
		s.setPosition(x, y+40);
		if(player_Controlled){
			for(int i = 1;i < 256;i++){
				if(Gdx.input.isKeyJustPressed(i)){
					if(acceleration > 0){
						acceleration+=.5f;
					}
					else {
						acceleration-=.5f;
					}
					mass+=.05f;
				}
			}
		}
	}

}
