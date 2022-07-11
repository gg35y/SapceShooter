package com.java.game.art;

import java.awt.image.BufferedImage;

public class Art {
	private BufferedImage image;
	
	public Art(BufferedImage image){
		this.image = image;
	} 
	
	public BufferedImage getImage(int col, int row,int width,int height){
		BufferedImage img = image.getSubimage((col*16)-16, (row*16)-16, width, height);
		return img;
	}
}
