package com.java.game.art;

import java.awt.image.BufferedImage;

import com.java.game.main.Main;

public class Textures {
	public BufferedImage bullet;
	public static BufferedImage player;
	public static BufferedImage enemy;
	public static BufferedImage gameOver;
	public static BufferedImage win;
	private Art art;
	
	public Textures(Main game){
		art  = new Art(game.getTexture());	
		getTexture();
	}
	
	private void getTexture(){
		player = art.getImage(1, 1, 32, 32);
		enemy = art.getImage(3, 1, 32, 32);
		bullet = art.getImage(5, 1, 16, 16);
		gameOver = art.getImage(1, 4, 32, 32);
		win = art.getImage(3, 4, 32, 32);
	}
}