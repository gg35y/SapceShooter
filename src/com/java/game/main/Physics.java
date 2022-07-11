package com.java.game.main;

import com.java.game.entities.Entity;
import com.java.game.entities.EntityComponent;

public class Physics {
	public static boolean Collision(Entity entity, EntityComponent entityComponent){
			if(entity.getBounds(32, 32).intersects(entityComponent.getBounds())){
				return true;
			
		}
		return false;
	}
	
	public static boolean Collision(EntityComponent entityComponent, Entity entity){
			if(entityComponent.getBounds().intersects(entity.getBounds(32,32))){
				return true;
		}
		return false;
	}
}