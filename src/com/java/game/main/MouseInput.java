package com.java.game.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		
	}
	
	public void mouseReleased(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(mx >= (Main.WIDTH /2) + 256 && mx <= (Main.WIDTH / 2) + 356){
			if(my >= 150 && my <= 200){
				Main.status = Main.STATUS.GAME;
			}
		}
		
		if(mx >= (Main.WIDTH /2) + 246 && mx <= (Main.WIDTH / 2) + 356){
			if(my >= 200 && my <= 250){
				System.exit(1);
			}
		}
		
	}
	
	public void mouseEntered(MouseEvent e) {
		
	}

	
	public void mouseExited(MouseEvent e) {
		
	}

}