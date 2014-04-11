package io.github.austinv11.LootPlus;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class InternalDrops extends JavaPlugin implements Listener{
	PluginManager pm = Bukkit.getServer().getPluginManager();
	FileConfiguration config = getConfig();
	float HEAD_DROP_RATE = 1f; //2.5% for wither skeles
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
		if (config.getBoolean("Options.disableXPDrops") == false){
			event.setDroppedExp(0);
		}
		if (event.getEntityType() == EntityType.BAT){
			
		}else if (event.getEntityType() == EntityType.BLAZE){
			
		}else if (event.getEntityType() == EntityType.CAVE_SPIDER){
			
		}else if (event.getEntityType() == EntityType.CHICKEN){
			
		}else if (event.getEntityType() == EntityType.COW){
			
		}else if (event.getEntityType() == EntityType.CREEPER){
			getLogger().info("DEBUG: Creeper killed");
			DamageCause cause = event.getEntity().getLastDamageCause().getCause();
			if (config.getBoolean("Features.headDrops") == true && cause == DamageCause.ENTITY_ATTACK){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= HEAD_DROP_RATE){
					Location loc = event.getEntity().getLocation().clone();
					byte type = 4;
					ItemStack loot = new ItemStack(Material.SKULL_ITEM, 1, type);
					Item item = loc.getWorld().dropItemNaturally(loc, loot);
					item.setItemStack(loot);
				}
			}
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
			DamageCause cause = event.getEntity().getLastDamageCause().getCause();
			if (config.getBoolean("Features.headDrops") == true && cause == DamageCause.ENTITY_ATTACK){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= HEAD_DROP_RATE){
					Location loc = event.getEntity().getLocation().clone();
					byte type = 0;
					ItemStack loot = new ItemStack(Material.SKULL_ITEM, 1, type);
					Item item = loc.getWorld().dropItemNaturally(loc, loot);
					item.setItemStack(loot);
				}
			}
		}else if (event.getEntityType() == EntityType.SLIME){
			
		}else if (event.getEntityType() == EntityType.SNOWMAN){
			
		}else if (event.getEntityType() == EntityType.SPIDER){
			
		}else if (event.getEntityType() == EntityType.SQUID){
			
		}else if (event.getEntityType() == EntityType.VILLAGER){
			
		}else if (event.getEntityType() == EntityType.WITCH){
			
		}else if (event.getEntityType() == EntityType.WITHER){
			
		}else if (event.getEntityType() == EntityType.WOLF){
			
		}else if (event.getEntityType() == EntityType.ZOMBIE){
			DamageCause cause = event.getEntity().getLastDamageCause().getCause();
			if (config.getBoolean("Features.headDrops") == true && cause == DamageCause.ENTITY_ATTACK){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= HEAD_DROP_RATE){
					Location loc = event.getEntity().getLocation().clone();
					byte type = 2;
					ItemStack loot = new ItemStack(Material.SKULL_ITEM, 1, type);
					Item item = loc.getWorld().dropItemNaturally(loc, loot);
					item.setItemStack(loot);
				}
			}
		}else {
			getLogger().info("Error: Unknown entity type killed (This could probably be ignored)");
		}	
	}
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		if (config.getBoolean("Features.playerHeadDrops") == true){
			Random r = new Random();
			float chance = r.nextFloat();
			if (chance <= HEAD_DROP_RATE){
				Player player = event.getEntity();
				Location loc = player.getLocation();
				ItemStack skull = new ItemStack(Material.SKULL_ITEM);
				skull.setDurability((short) 3);
				SkullMeta meta = (SkullMeta) skull.getItemMeta();
				meta.setDisplayName(player.getName()+"'s Head");
				meta.setOwner(player.getName());
				skull.setItemMeta(meta);
				loc.getWorld().dropItemNaturally(loc, skull);
				Item item = loc.getWorld().dropItemNaturally(loc, skull);
				item.setItemStack(skull);
			}
		}
	}
}
