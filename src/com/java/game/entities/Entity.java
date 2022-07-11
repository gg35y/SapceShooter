package com.java.game.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface Entity {
	public void tick();
	public void render(Graphics graphics);
	public double getX();
	public double getY();
	public Rectangle getBounds(int i, int j);

}
