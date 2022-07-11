package com.java.game.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.java.game.art.Textures;
import com.java.game.main.Controller;
import com.java.game.main.GameObject;
import com.java.game.main.Main;
import com.java.game.main.Physics;
import com.java.game.sound.Sound;

public class Enemy extends GameObject implements EntityComponent{
	Random random = new Random();
	@SuppressWarnings("unused")
	private Textures textures;
	private int speed = random.nextInt(3)+1;
	private Main game;
	private Controller controller; 
	private static Sound soundExplosion = new Sound("res/sound/explosion.wav",0.6);
	
	public Enemy(double x,double y,Textures textures, Controller controller, Main game){
		super(x,y);
		this.textures = textures;
		this.controller = controller;
		this.game = game;
	}
	
	@SuppressWarnings("static-access")
	public void tick(){
		y+=speed;
	
		if(y>Main.HIEGHT * Main.SCALE){
			speed = random.nextInt(3)+1;
			x=random.nextInt(640);
			y = -10;	
		}
	
		for(int i = 0; i < game.entity.size(); i++){
			Entity ent = game.entity.get(i);
			
			if(Physics.Collision(this,ent)){
				soundExplosion.audio();
				soundExplosion.setVolume();
				controller.removeEntity(this);
				controller.removeEntity(ent);
				game.setEnemyKilled(game.getEnemyKilled()+1);			
				Main.score++;
			}
		}	
		
	}	
	
	public void render(Graphics graphics){
		graphics.drawImage(Textures.enemy,(int)x,(int)y,100,100,null);
			
	}

	public double getY(){
		return y;
	}

	public void setY(double y){
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,32,32);
	}
	
}