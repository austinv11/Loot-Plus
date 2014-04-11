package io.github.austinv11.LootPlus;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class InternalDrops extends JavaPlugin implements Listener{
	PluginManager pm = Bukkit.getServer().getPluginManager();
	FileConfiguration config = getConfig();
	public InternalDrops(){
		pm.registerEvents(this, this);
	}
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event){
		if (config.getBoolean("Options.onlyCustomDrops") == true){
			Location items = event.getEntity().getLocation().clone();
			for(Entity e : items.getChunk().getEntities()){
				if (e instanceof Item){
					if (e.getLocation().clone() == items){
						e.remove();
					}
				}
			}
		}
		if (event.getEntityType() == EntityType.BAT){
			
		}else if (event.getEntityType() == EntityType.BLAZE){
			
		}else if (event.getEntityType() == EntityType.CAVE_SPIDER){
			
		}else if (event.getEntityType() == EntityType.CHICKEN){
			
		}else if (event.getEntityType() == EntityType.COW){
			
		}else if (event.getEntityType() == EntityType.CREEPER){
			
		}else if (event.getEntityType() == EntityType.ENDER_DRAGON){
			
		}else if (event.getEntityType() == EntityType.ENDERMAN){
			
		}else if (event.getEntityType() == EntityType.GHAST){
			
		}else if (event.getEntityType() == EntityType.GIANT){
			
		}else if (event.getEntityType() == EntityType.HORSE){
			
		}else if (event.getEntityType() == EntityType.IRON_GOLEM){
			
		}else if (event.getEntityType() == EntityType.MAGMA_CUBE){
			
		}else if (event.getEntityType() == EntityType.MUSHROOM_COW){
			
		}else if (event.getEntityType() == EntityType.OCELOT){
			
		}else if (event.getEntityType() == EntityType.PIG){
			
		}else if (event.getEntityType() == EntityType.PIG_ZOMBIE){
			
		}else if (event.getEntityType() == EntityType.PLAYER){
			
		}else if (event.getEntityType() == EntityType.SHEEP){
			
		}else if (event.getEntityType() == EntityType.SILVERFISH){
			
		}else if (event.getEntityType() == EntityType.SKELETON){
			
		}else if (event.getEntityType() == EntityType.SLIME){
			
		}else if (event.getEntityType() == EntityType.SNOWMAN){
			
		}else if (event.getEntityType() == EntityType.SPIDER){
			
		}else if (event.getEntityType() == EntityType.SQUID){
			
		}else if (event.getEntityType() == EntityType.VILLAGER){
			
		}else if (event.getEntityType() == EntityType.WITCH){
			
		}else if (event.getEntityType() == EntityType.WITHER){
			
		}else if (event.getEntityType() == EntityType.WOLF){
			
		}else if (event.getEntityType() == EntityType.ZOMBIE){
			
		}else {
			getLogger().info("Error: Unknown entity type killed (This could probably be ignored");
		}
		
	}
}
