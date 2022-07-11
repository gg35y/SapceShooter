package com.java.game.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import com.java.game.main.Main;

public class Menu{
	int w = 800,h = 800;
	public Rectangle playButton = new Rectangle((Main.WIDTH/2)+256,150,100,50);
	public Rectangle quitButton = new Rectangle((Main.WIDTH/2)+256,210,100,50);
	private Image menu = new ImageIcon("res/tex/back/menu.png").getImage();	

	public void render(Graphics graphics){
		graphics.drawImage(menu,0,0,w,h,null);
		
		Graphics2D graphics2d = (Graphics2D)graphics;
		Font textButton = new Font("arial", Font.BOLD, 25);
		graphics.setColor(Color.white);
		graphics.setFont(textButton);
		graphics.drawString("Играть", playButton.x+10,playButton.y + 35);
		graphics2d.draw(playButton);
		graphics.drawString("Выйти", quitButton.x+10,quitButton.y + 35);
		graphics2d.draw(quitButton);
		 
	}
}