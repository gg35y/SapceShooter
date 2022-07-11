package com.java.game.main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import com.java.game.art.Textures;
import com.java.game.entities.Enemy;
import com.java.game.entities.Entity;
import com.java.game.entities.EntityComponent;

public class Controller{
	private LinkedList<Entity> entity = new LinkedList<Entity>();
	private static LinkedList<EntityComponent> entityComponent = new LinkedList<EntityComponent>();
	private Textures textures;
	private Main game;
	
	Entity e;
	EntityComponent entityC;
	Random random = new Random();
	
	public Controller(Textures textures, Main game){
		this.textures = textures;
		this.game = game;
	}

	public void addEnemy(int enemyCount){
		for(int i = 0; i < enemyCount; i++){
			addEntity(new Enemy(random.nextInt(640),-19,textures,this,game));
		}
	}
	
	public void tick(){
		for(int i = 0; i < entity.size(); i++){
			e = entity.get(i);
			e.tick();
		}

		for(int i = 0; i < entityComponent.size(); i++){
			entityC = entityComponent.get(i);
			entityC.tick();
		}
	
	}
	
	public void render(Graphics graphics){
		for(int i = 0; i < entity.size(); i++){
			e = entity.get(i);
			e.render(graphics);			
		}
	
		for(int i = 0; i < entityComponent.size(); i++){
			entityC = entityComponent.get(i);
			entityC.render(graphics);			
		}		
	}
	
	public void addEntity(Entity ent){
		entity.add(ent);	
	}

	public void removeEntity(Entity ent){
		entity.remove(ent);	
	}
	
	public static void addEntity(EntityComponent ent){
		entityComponent.add(ent);	
	}

	public static void removeEntity(EntityComponent ent){
		entityComponent.remove(ent);	
	}
	
	public LinkedList<Entity> getEntity(){
		return entity;
	}

	public LinkedList<EntityComponent> getEntityComponent(){
		return entityComponent;
	}
}