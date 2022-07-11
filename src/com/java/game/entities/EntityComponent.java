package com.java.game.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntityComponent{
	public void tick();	
	public void render(Graphics graphics);
	public Rectangle getBounds(); 
	public double getX();
	public double getY();
}