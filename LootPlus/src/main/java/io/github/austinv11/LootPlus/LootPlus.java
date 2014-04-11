package io.github.austinv11.LootPlus;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LootPlus extends JavaPlugin implements Listener{
	public String CURRENT_VERSION = "1.0.0"; //TODO remember to update
	FileConfiguration config = getConfig();
	PluginManager pm = Bukkit.getServer().getPluginManager();
	float HEAD_DROP_RATE = .025f; //2.5% for wither skeles
	@Override
	public void onEnable(){
		configInit(false);
		if (config.getBoolean("Options.setToDefault") == true){
			configInit(true);
		}
		pm.registerEvents(this, this);
		getLogger().info("Loot on this server is now enhanced by LootPlus V"+CURRENT_VERSION+"!");
	}
	public void configInit(boolean override){
		if (override == false){
			getLogger().info("Initiating config...");
			config.addDefault("Options.onlyCustomDrops", false);
			config.addDefault("Options.disableXPDrops", false);
			config.addDefault("Options.allowCustomDrops", "TODO");//FIXME
			config.addDefault("Options.allowCustomXP", "TODO");//FIXME
			config.addDefault("Options.allowCustomDungeonLoot", "TODO");//FIXME
			config.addDefault("Options.allowCustomSpawnRate", "TODO");//FIXME
			config.addDefault("Options.updateNotifications", "TODO");//FIXME
			config.addDefault("Options.setToDefault", false);
			config.addDefault("Features.headDrops", true);
			config.addDefault("Features.playerHeadDrops", true);
			config.addDefault("Features.extraHeadDrops", true);
			config.addDefault("Features.extraDungeonLoot", "TODO");//FIXME
			config.addDefault("Features.extraMobDrops", "TODO");//FIXME
			config.addDefault("Features.extraEnchantments", "TODO");//FIXME
			config.addDefault("Features.extraDungeons", "TODO");//FIXME
			config.addDefault("Features.bossMobs", "TODO");//FIXME
			config.options().copyDefaults(true);
			saveConfig();
			getLogger().info("Initiated config!");
		} else if (override == true){
			getLogger().info("Reverting config to defaults...");
			config.set("Options.onlyCustomDrops", true);
			config.set("Options.disableXPDrops", false);
			config.set("Options.allowCustomDrops", "TODO");//FIXME
			config.set("Options.allowCustomXP", "TODO");//FIXME
			config.set("Options.allowCustomDungeonLoot", "TODO");//FIXME
			config.set("Options.allowCustomSpawnRate", "TODO");//FIXME
			config.set("Options.updateNotifications", "TODO");//FIXME
			config.set("Options.setToDefault", false);
			config.set("Features.headDrops", true);
			config.set("Features.playerHeadDrops", true);
			config.set("Features.extraHeadDrops", true);
			config.set("Features.extraDungeonLoot", "TODO");//FIXME
			config.set("Features.extraMobDrops", "TODO");//FIXME
			config.set("Features.extraEnchantments", "TODO");//FIXME
			config.set("Features.extraDungeons", "TODO");//FIXME
			config.set("Features.bossMobs", "TODO");//FIXME
			saveConfig();
			getLogger().info("Reverted config!");
		}
	}
	@Override
	public void onDisable(){
		//TODO
		getLogger().info("LootPlus no longer affects this server");
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){//TODO remember to update
		if (cmd.getName().equalsIgnoreCase("tweaks")) {
			sender.sendMessage(ChatColor.YELLOW+"Current tweaks implemented by LootPlus on this server:");
			if (config.getBoolean("Options.onlyCustomDrops") == true){
				sender.sendMessage(ChatColor.RED+"-Only custom mob loot is dropped");
			} 
			if (config.getBoolean("Options.disableXPDrops") == true){
				sender.sendMessage(ChatColor.RED+"-No xp is dropped upon mob death");
			}
			if (config.getBoolean("Options.allowCustomDrops") == true){
				sender.sendMessage(ChatColor.GREEN+"+Custom mob drops");
			}
			if (config.getBoolean("Options.allowCustomXP") == true){
				sender.sendMessage(ChatColor.GREEN+"+Custom xp drops");
			}
			if (config.getBoolean("Options.allowCustomDungeonLoot") == true){
				sender.sendMessage(ChatColor.GREEN+"+Custom dungeon loot");
			}
			if (config.getBoolean("Options.allowCustomSpawnRate") == true){
				sender.sendMessage(ChatColor.GREEN+"+Custom mob spawning rates");
			}
			if (config.getBoolean("Features.headDrops") == true){
				sender.sendMessage(ChatColor.GREEN+"+All mob heads have a chance at being dropped");
			}
			if (config.getBoolean("Features.playerHeadDrops") == true){
				sender.sendMessage(ChatColor.GREEN+"+Players drop heads");
			}
			if (config.getBoolean("Features.extraHeadDrops") == true){
				sender.sendMessage(ChatColor.GREEN+"+Mobs without implemented heads drop custom player heads");
			}
			if (config.getBoolean("Features.extraDungeonLoot") == true){
				sender.sendMessage(ChatColor.GREEN+"+Special dungeon loot");
			}
			if (config.getBoolean("Features.extraMobDrops") == true){
				sender.sendMessage(ChatColor.GREEN+"+Mobs have special drops");
			}
			if (config.getBoolean("Features.extraEnchantments") == true){
				sender.sendMessage(ChatColor.GREEN+"+New enchantments are available");
			}
			if (config.getBoolean("Features.extraDungeons") == true){
				sender.sendMessage(ChatColor.GREEN+"+New dungeons now spawn in worldgen");
			}
			if (config.getBoolean("Features.bossMobs") == true){
				sender.sendMessage(ChatColor.GREEN+"+Random chance of a 'boss mob' to spawn (with cool loot!)");
			}
			return true;
		}
		return false;
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
		if (event.getEntityType() == EntityType.BAT){//FIXME mob head
			
		}else if (event.getEntityType() == EntityType.BLAZE){
			DamageCause cause = event.getEntity().getLastDamageCause().getCause();
			if (config.getBoolean("Features.extraHeadDrops") == true && cause == DamageCause.ENTITY_ATTACK){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= HEAD_DROP_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack skull = new ItemStack(Material.SKULL_ITEM);
					skull.setDurability((short) 3);
					SkullMeta meta = (SkullMeta) skull.getItemMeta();
					meta.setDisplayName("Blaze Head");
					meta.setOwner("MHF_Blaze");
					skull.setItemMeta(meta);
					loc.getWorld().dropItemNaturally(loc, skull);
					Item item = loc.getWorld().dropItemNaturally(loc, skull);
					item.setItemStack(skull);
				}
			}
		}else if (event.getEntityType() == EntityType.CAVE_SPIDER){
			DamageCause cause = event.getEntity().getLastDamageCause().getCause();
			if (config.getBoolean("Features.extraHeadDrops") == true && cause == DamageCause.ENTITY_ATTACK){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= HEAD_DROP_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack skull = new ItemStack(Material.SKULL_ITEM);
					skull.setDurability((short) 3);
					SkullMeta meta = (SkullMeta) skull.getItemMeta();
					meta.setDisplayName("Cave Spider Head");
					meta.setOwner("MHF_CaveSpider");
					skull.setItemMeta(meta);
					loc.getWorld().dropItemNaturally(loc, skull);
					Item item = loc.getWorld().dropItemNaturally(loc, skull);
					item.setItemStack(skull);
				}
			}
		}else if (event.getEntityType() == EntityType.CHICKEN){
			DamageCause cause = event.getEntity().getLastDamageCause().getCause();
			if (config.getBoolean("Features.extraHeadDrops") == true && cause == DamageCause.ENTITY_ATTACK){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= HEAD_DROP_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack skull = new ItemStack(Material.SKULL_ITEM);
					skull.setDurability((short) 3);
					SkullMeta meta = (SkullMeta) skull.getItemMeta();
					meta.setDisplayName("Chicken Head");
					meta.setOwner("MHF_Chicken");
					skull.setItemMeta(meta);
					loc.getWorld().dropItemNaturally(loc, skull);
					Item item = loc.getWorld().dropItemNaturally(loc, skull);
					item.setItemStack(skull);
				}
			}
		}else if (event.getEntityType() == EntityType.COW){
			DamageCause cause = event.getEntity().getLastDamageCause().getCause();
			if (config.getBoolean("Features.extraHeadDrops") == true && cause == DamageCause.ENTITY_ATTACK){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= HEAD_DROP_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack skull = new ItemStack(Material.SKULL_ITEM);
					skull.setDurability((short) 3);
					SkullMeta meta = (SkullMeta) skull.getItemMeta();
					meta.setDisplayName("Cow Head");
					meta.setOwner("MHF_Cow");
					skull.setItemMeta(meta);
					loc.getWorld().dropItemNaturally(loc, skull);
					Item item = loc.getWorld().dropItemNaturally(loc, skull);
					item.setItemStack(skull);
				}
			}
		}else if (event.getEntityType() == EntityType.CREEPER){
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
		}else if (event.getEntityType() == EntityType.ENDER_DRAGON){//FIXME add head
			
		}else if (event.getEntityType() == EntityType.ENDERMAN){
			DamageCause cause = event.getEntity().getLastDamageCause().getCause();
			if (config.getBoolean("Features.extraHeadDrops") == true && cause == DamageCause.ENTITY_ATTACK){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= HEAD_DROP_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack skull = new ItemStack(Material.SKULL_ITEM);
					skull.setDurability((short) 3);
					SkullMeta meta = (SkullMeta) skull.getItemMeta();
					meta.setDisplayName("Enderman Head");
					meta.setOwner("MHF_Enderman");
					skull.setItemMeta(meta);
					loc.getWorld().dropItemNaturally(loc, skull);
					Item item = loc.getWorld().dropItemNaturally(loc, skull);
					item.setItemStack(skull);
				}
			}
		}else if (event.getEntityType() == EntityType.GHAST){
			DamageCause cause = event.getEntity().getLastDamageCause().getCause();
			if (config.getBoolean("Features.extraHeadDrops") == true && cause == DamageCause.ENTITY_ATTACK){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= HEAD_DROP_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack skull = new ItemStack(Material.SKULL_ITEM);
					skull.setDurability((short) 3);
					SkullMeta meta = (SkullMeta) skull.getItemMeta();
					meta.setDisplayName("Ghast Head");
					meta.setOwner("MHF_Ghast");
					skull.setItemMeta(meta);
					loc.getWorld().dropItemNaturally(loc, skull);
					Item item = loc.getWorld().dropItemNaturally(loc, skull);
					item.setItemStack(skull);
				}
			}
		}else if (event.getEntityType() == EntityType.GIANT){
			DamageCause cause = event.getEntity().getLastDamageCause().getCause();
			if (config.getBoolean("Features.extraHeadDrops") == true && cause == DamageCause.ENTITY_ATTACK){
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
		}else if (event.getEntityType() == EntityType.HORSE){//FIXME add head
			
		}else if (event.getEntityType() == EntityType.IRON_GOLEM){
			DamageCause cause = event.getEntity().getLastDamageCause().getCause();
			if (config.getBoolean("Features.extraHeadDrops") == true && cause == DamageCause.ENTITY_ATTACK){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= HEAD_DROP_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack skull = new ItemStack(Material.SKULL_ITEM);
					skull.setDurability((short) 3);
					SkullMeta meta = (SkullMeta) skull.getItemMeta();
					meta.setDisplayName("Iron Golem Head");
					meta.setOwner("MHF_Golem");
					skull.setItemMeta(meta);
					loc.getWorld().dropItemNaturally(loc, skull);
					Item item = loc.getWorld().dropItemNaturally(loc, skull);
					item.setItemStack(skull);
				}
			}
		}else if (event.getEntityType() == EntityType.MAGMA_CUBE){
			DamageCause cause = event.getEntity().getLastDamageCause().getCause();
			if (config.getBoolean("Features.extraHeadDrops") == true && cause == DamageCause.ENTITY_ATTACK){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= HEAD_DROP_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack skull = new ItemStack(Material.SKULL_ITEM);
					skull.setDurability((short) 3);
					SkullMeta meta = (SkullMeta) skull.getItemMeta();
					meta.setDisplayName("Magma Cube Head");
					meta.setOwner("MHF_LavaSlime");
					skull.setItemMeta(meta);
					loc.getWorld().dropItemNaturally(loc, skull);
					Item item = loc.getWorld().dropItemNaturally(loc, skull);
					item.setItemStack(skull);
				}
			}
		}else if (event.getEntityType() == EntityType.MUSHROOM_COW){
			DamageCause cause = event.getEntity().getLastDamageCause().getCause();
			if (config.getBoolean("Features.extraHeadDrops") == true && cause == DamageCause.ENTITY_ATTACK){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= HEAD_DROP_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack skull = new ItemStack(Material.SKULL_ITEM);
					skull.setDurability((short) 3);
					SkullMeta meta = (SkullMeta) skull.getItemMeta();
					meta.setDisplayName("Mooshroom Head");
					meta.setOwner("MHF_MushroomCow");
					skull.setItemMeta(meta);
					loc.getWorld().dropItemNaturally(loc, skull);
					Item item = loc.getWorld().dropItemNaturally(loc, skull);
					item.setItemStack(skull);
				}
			}
		}else if (event.getEntityType() == EntityType.OCELOT){//FIXME drops for pet cats
			DamageCause cause = event.getEntity().getLastDamageCause().getCause();
			if (config.getBoolean("Features.extraHeadDrops") == true && cause == DamageCause.ENTITY_ATTACK){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= HEAD_DROP_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack skull = new ItemStack(Material.SKULL_ITEM);
					skull.setDurability((short) 3);
					SkullMeta meta = (SkullMeta) skull.getItemMeta();
					meta.setDisplayName("Ocelot Head");
					meta.setOwner("MHF_Ocelot");
					skull.setItemMeta(meta);
					loc.getWorld().dropItemNaturally(loc, skull);
					Item item = loc.getWorld().dropItemNaturally(loc, skull);
					item.setItemStack(skull);
				}
			}
		}else if (event.getEntityType() == EntityType.PIG){
			DamageCause cause = event.getEntity().getLastDamageCause().getCause();
			if (config.getBoolean("Features.extraHeadDrops") == true && cause == DamageCause.ENTITY_ATTACK){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= HEAD_DROP_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack skull = new ItemStack(Material.SKULL_ITEM);
					skull.setDurability((short) 3);
					SkullMeta meta = (SkullMeta) skull.getItemMeta();
					meta.setDisplayName("Pig Head");
					meta.setOwner("MHF_Pig");
					skull.setItemMeta(meta);
					loc.getWorld().dropItemNaturally(loc, skull);
					Item item = loc.getWorld().dropItemNaturally(loc, skull);
					item.setItemStack(skull);
				}
			}
		}else if (event.getEntityType() == EntityType.PIG_ZOMBIE){
			DamageCause cause = event.getEntity().getLastDamageCause().getCause();
			if (config.getBoolean("Features.extraHeadDrops") == true && cause == DamageCause.ENTITY_ATTACK){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= HEAD_DROP_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack skull = new ItemStack(Material.SKULL_ITEM);
					skull.setDurability((short) 3);
					SkullMeta meta = (SkullMeta) skull.getItemMeta();
					meta.setDisplayName("Zombie Pigman Head");
					meta.setOwner("MHF_PigZombie");
					skull.setItemMeta(meta);
					loc.getWorld().dropItemNaturally(loc, skull);
					Item item = loc.getWorld().dropItemNaturally(loc, skull);
					item.setItemStack(skull);
				}
			}
		}else if (event.getEntityType() == EntityType.PLAYER){
			//TODO
		}else if (event.getEntityType() == EntityType.SHEEP){
			DamageCause cause = event.getEntity().getLastDamageCause().getCause();
			if (config.getBoolean("Features.extraHeadDrops") == true && cause == DamageCause.ENTITY_ATTACK){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= HEAD_DROP_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack skull = new ItemStack(Material.SKULL_ITEM);
					skull.setDurability((short) 3);
					SkullMeta meta = (SkullMeta) skull.getItemMeta();
					meta.setDisplayName("Sheep Head");
					meta.setOwner("MHF_Sheep");
					skull.setItemMeta(meta);
					loc.getWorld().dropItemNaturally(loc, skull);
					Item item = loc.getWorld().dropItemNaturally(loc, skull);
					item.setItemStack(skull);
				}
			}
		}else if (event.getEntityType() == EntityType.SILVERFISH){//FIXME add head
			
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
			DamageCause cause = event.getEntity().getLastDamageCause().getCause();
			if (config.getBoolean("Features.extraHeadDrops") == true && cause == DamageCause.ENTITY_ATTACK){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= HEAD_DROP_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack skull = new ItemStack(Material.SKULL_ITEM);
					skull.setDurability((short) 3);
					SkullMeta meta = (SkullMeta) skull.getItemMeta();
					meta.setDisplayName("Slime Head");
					meta.setOwner("MHF_Slime");
					skull.setItemMeta(meta);
					loc.getWorld().dropItemNaturally(loc, skull);
					Item item = loc.getWorld().dropItemNaturally(loc, skull);
					item.setItemStack(skull);
				}
			}
		}else if (event.getEntityType() == EntityType.SNOWMAN){
			DamageCause cause = event.getEntity().getLastDamageCause().getCause();
			if (config.getBoolean("Features.extraHeadDrops") == true && cause == DamageCause.ENTITY_ATTACK){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= HEAD_DROP_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack skull = new ItemStack(Material.SKULL_ITEM);
					skull.setDurability((short) 3);
					SkullMeta meta = (SkullMeta) skull.getItemMeta();
					meta.setDisplayName("Snow Golem Head");
					meta.setOwner("MHF_Pumpkin");
					skull.setItemMeta(meta);
					loc.getWorld().dropItemNaturally(loc, skull);
					Item item = loc.getWorld().dropItemNaturally(loc, skull);
					item.setItemStack(skull);
				}
			}
		}else if (event.getEntityType() == EntityType.SPIDER){
			DamageCause cause = event.getEntity().getLastDamageCause().getCause();
			if (config.getBoolean("Features.extraHeadDrops") == true && cause == DamageCause.ENTITY_ATTACK){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= HEAD_DROP_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack skull = new ItemStack(Material.SKULL_ITEM);
					skull.setDurability((short) 3);
					SkullMeta meta = (SkullMeta) skull.getItemMeta();
					meta.setDisplayName("Spider Head");
					meta.setOwner("MHF_Spider");
					skull.setItemMeta(meta);
					loc.getWorld().dropItemNaturally(loc, skull);
					Item item = loc.getWorld().dropItemNaturally(loc, skull);
					item.setItemStack(skull);
				}
			}
		}else if (event.getEntityType() == EntityType.SQUID){
			DamageCause cause = event.getEntity().getLastDamageCause().getCause();
			if (config.getBoolean("Features.extraHeadDrops") == true && cause == DamageCause.ENTITY_ATTACK){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= HEAD_DROP_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack skull = new ItemStack(Material.SKULL_ITEM);
					skull.setDurability((short) 3);
					SkullMeta meta = (SkullMeta) skull.getItemMeta();
					meta.setDisplayName("Squid Head");
					meta.setOwner("MHF_Squid");
					skull.setItemMeta(meta);
					loc.getWorld().dropItemNaturally(loc, skull);
					Item item = loc.getWorld().dropItemNaturally(loc, skull);
					item.setItemStack(skull);
				}
			}
		}else if (event.getEntityType() == EntityType.VILLAGER){
			DamageCause cause = event.getEntity().getLastDamageCause().getCause();
			if (config.getBoolean("Features.extraHeadDrops") == true && cause == DamageCause.ENTITY_ATTACK){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= HEAD_DROP_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack skull = new ItemStack(Material.SKULL_ITEM);
					skull.setDurability((short) 3);
					SkullMeta meta = (SkullMeta) skull.getItemMeta();
					meta.setDisplayName("Villager Head");
					meta.setOwner("MHF_Villager");
					skull.setItemMeta(meta);
					loc.getWorld().dropItemNaturally(loc, skull);
					Item item = loc.getWorld().dropItemNaturally(loc, skull);
					item.setItemStack(skull);
				}
			}
		}else if (event.getEntityType() == EntityType.WITCH){//FIXME add head
			
		}else if (event.getEntityType() == EntityType.WITHER){
			DamageCause cause = event.getEntity().getLastDamageCause().getCause();
			if (config.getBoolean("Features.extraHeadDrops") == true && cause == DamageCause.ENTITY_ATTACK){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= HEAD_DROP_RATE){
					Location loc = event.getEntity().getLocation().clone();
					//byte type = 2; FIXME check
					ItemStack loot = new ItemStack(Material.SKULL_ITEM, 1);
					Item item = loc.getWorld().dropItemNaturally(loc, loot);
					item.setItemStack(loot);
				}
			}
		}else if (event.getEntityType() == EntityType.WOLF){//FIXME add head
			
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
		DamageCause cause = event.getEntity().getLastDamageCause().getCause();
		if (config.getBoolean("Features.playerHeadDrops") == true && cause == DamageCause.ENTITY_ATTACK){
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
