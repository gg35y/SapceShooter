package com.java.game.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.java.game.art.Textures;
import com.java.game.main.Controller;
import com.java.game.main.GameObject;
import com.java.game.main.Main;
import com.java.game.main.Physics;

public class Player extends GameObject implements Entity{
	private double velX = 0,velY = 0;
	@SuppressWarnings("unused")
	private Textures textures;
	
	Main game; 
	Controller controller;
	
	public Player(double x, double y,Textures textures, Main game, Controller controller){
		super(x,y);
		this.textures = textures;
		this.game = game;
		this.controller = controller;
	} 
	
	public void render(Graphics graphics){
		graphics.drawImage(Textures.player,(int)x,(int)y,100,100,null);
	
	}

	public void tick(){
		x+=velX;
		y+=velY;
	
		if(x<=0)x=0;
		if(x>=715)x=715;
		if(y<=0)y=0;
		if(y>=700)y=700;
	
		
		for(int i = 0; i < game.entityComponent.size();i++ ){
			EntityComponent entCom = game.entityComponent.get(i);
			if(Physics.Collision(this, entCom)){
				Controller.removeEntity(entCom);
				Controller.addEntity(entCom);
				Main.HEALTH -= 5;	
			}else if(Main.HEALTH == 0){
				Main.status = Main.STATUS.GAMEOVER;
			}else if(Main.score == 150){
				Main.status = Main.STATUS.END;
			}
		}
	}

	public double getX(){
		return x;
	}

	public double getY(){
		return y;
	}	

	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public void setVelX(double velX){
		this.velX = velX;
	}
	
	public void setVelY(double velY){
		this.velY = velY;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,32,32);
	}
	
}