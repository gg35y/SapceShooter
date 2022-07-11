package com.java.game.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.java.game.art.Textures;
import com.java.game.main.GameObject;
import com.java.game.main.Main;

public class Bullet extends GameObject implements Entity{
	private Textures textures;
	@SuppressWarnings("unused")
	private Main game;
	 
	public Bullet(double x,double y,Textures textures,Main game){
		super(x,y);
		this.textures = textures;
		this.game = game;
	}

	public void tick(){
		y-=8;
		
	}

	public void render(Graphics graphics){
		graphics.drawImage(textures.bullet,(int)x+33 ,(int)y-20,30,30,null);
	}

	public double getY(){
		return y;
	}
	
	public double getX() {
		return x;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,32,32);
	}
}