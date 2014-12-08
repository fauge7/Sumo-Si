package com.fauge.games.Sumo.Si;

import Screens.FontUtility;
import Screens.SplashScreen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;

public class SumoSi extends Game implements ApplicationListener {
	
	@Override
	public void create () {
		FontUtility util = new FontUtility();
		this.setScreen(new SplashScreen(this,util));
	}

	@Override
	public void render () {
		super.render();
		
	}
}
