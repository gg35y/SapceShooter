package com.java.game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import com.java.game.art.BufferedImageLoader;
import com.java.game.art.Textures;
import com.java.game.entities.Bullet;
import com.java.game.entities.Entity;
import com.java.game.entities.EntityComponent;
import com.java.game.entities.Player;
import com.java.game.menu.Menu;
import com.java.game.sound.Sound;

public class Main extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 160;
	public static final int HIEGHT = 160;
	public static final int SCALE = 5;
	private static final String NAME = "SpaceShooter";
	private Thread thread;
	private boolean running = false;
	@SuppressWarnings("unused")
	private BufferedImage image = new BufferedImage(WIDTH,HIEGHT,BufferedImage.TYPE_INT_BGR);
	private BufferedImage texture = null;
	private Image back = new ImageIcon("res/tex/back/space.png").getImage();
	private Player player;
	private Controller controller;
	private Menu menu;
	private boolean shooting = false;
	private Textures textures;
	private int enemyCount = 6;
	private int enemyKilled = 0; 
	private static Sound soundShooting = new Sound("res/sound/laserShoot.wav",0.6);

	public LinkedList<Entity> entity;
	public LinkedList<EntityComponent> entityComponent;
	
	public static int HEALTH = 50; 
	public static int score = 0;
	int x,y, w = 800,h = 800;
	
	public static enum STATUS{
		MENU,GAME,GAMEOVER,END
	};
	
	public static STATUS status = STATUS.MENU;
	
	public void init(){
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			texture = loader.loadImage("res/tex/texture.png");
		}catch(IOException e){
			e.printStackTrace();
		}
		
		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInput());
		player = new Player(350,600,textures,this,controller);
		controller = new Controller(textures, this); 
		textures = new Textures(this);
		menu = new Menu();
		controller.addEnemy(enemyCount); 
		entity = controller.getEntity();
		entityComponent = controller.getEntityComponent();
		
	}

	public static void main(String [] arr){
		Main game = new Main();
		JFrame jFrame = new JFrame(NAME);
		
		game.setPreferredSize(new Dimension(WIDTH*SCALE,HIEGHT*SCALE));
		game.setMaximumSize(new Dimension(WIDTH*SCALE,HIEGHT*SCALE));
		game.setMinimumSize(new Dimension(WIDTH*SCALE,HIEGHT*SCALE));
		jFrame.add(game);
		jFrame.pack();
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);		
		jFrame.setVisible(true);
		
		game.start();
	}
	
	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double amountOfticks = 60.0;
		double ns = 1000000000.0 /amountOfticks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while(running){
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime = now;
			if(delta>=1){
				tick();
				updates++;
				delta--;
				
			}

			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			render();
			frames++;
			
			if(System.currentTimeMillis()-timer>1000){
				timer+=1000;
				System.out.println("ticks " + updates+", fps " + frames);
				updates=0;
				frames=0;
			}
			
		}		
		stop();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null){
			createBufferStrategy(3);
			return;
		}
		
		Graphics graphics = bs.getDrawGraphics();
		
		graphics.drawImage(back,0,0,getWidth(),getHeight(),this);
		
		if(status == STATUS.GAME){
			player.render(graphics);
			controller.render(graphics);
			
			graphics.setColor(Color.gray);
			graphics.fillRect(5,5,150,50);
			graphics.setColor(Color.red);
			graphics.fillRect(5,5,(HEALTH-50)+150,50);
			graphics.setColor(Color.gray);
			graphics.drawRect(5,5,150,50);
			graphics.drawString("Счёт: ",5,80);
			graphics.drawString(String.valueOf(score),40,80);
			
		}else if(status == STATUS.MENU){
			menu.render(graphics);
			graphics.drawString("Управление WASD/стрелочки и стрелять на ПРОБЕЛ", WIDTH/2,y+300);
			graphics.drawString("Игра сделана gg35y [c] 2021", WIDTH/2,y+340);
			
		}else if(status == STATUS.GAMEOVER){
			graphics.drawImage(Textures.gameOver,290,250,200,200,null);
		}else if(status == STATUS.END){
			graphics.drawImage(Textures.win,230,250,300,300,null);
		}
		
		graphics.dispose();
		bs.show();

	}

	private void tick(){
		if(status == STATUS.GAME){
			player.tick();
			controller.tick();
		}
		if(enemyKilled >= enemyCount){
			enemyCount+=2;
			enemyKilled=0;
			controller.addEnemy(enemyCount);
		}
	}

	public synchronized void start(){
		if(running) return;
		
		running = true;
		thread = new Thread(this);	
		thread.start();	
	}
	
	public synchronized void stop(){
		if(!running) return;
	
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	public BufferedImage getTexture(){
		return texture;
	}
	
	public void setEnemyCount(int enemyCount){
		this.enemyCount = enemyCount;
	}
	
	public int getEnemyCount(){
		return enemyCount;

	}
	
	public void setEnemyKilled(int enemyKilled){
		this.enemyKilled = enemyKilled;
	}
	
	public int getEnemyKilled(){
		return enemyKilled;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();

			if(status == STATUS.GAME){
		
			if(key==KeyEvent.VK_RIGHT | key==KeyEvent.VK_D){
				player.setVelX(5);
			}
		
			if(key==KeyEvent.VK_LEFT| key==KeyEvent.VK_A){
				player.setVelX(-5);		
			}
		
			if(key==KeyEvent.VK_DOWN| key==KeyEvent.VK_S){
				player.setVelY(5);		
			}
		
			if(key==KeyEvent.VK_UP| key==KeyEvent.VK_W){
				player.setVelY(-5);				
			}
		
			if(key==KeyEvent.VK_SPACE && !shooting){
				shooting = true;
				controller.addEntity(new Bullet(player.getX(), player.getY(), textures, this));
				soundShooting.audio();
				soundShooting.setVolume();
			}
		}
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();

		if(key==KeyEvent.VK_RIGHT | key==KeyEvent.VK_D){
			player.setVelX(0);
		}
		
		if(key==KeyEvent.VK_LEFT| key==KeyEvent.VK_A){
			player.setVelX(0);		
		}
		
		if(key==KeyEvent.VK_DOWN| key==KeyEvent.VK_S){
			player.setVelY(0);		
		}
		
		if(key==KeyEvent.VK_UP| key==KeyEvent.VK_W){
			player.setVelY(0);		
		
		}
		
		if(key==KeyEvent.VK_SPACE){
			shooting = false;
		}
	}
}