package io.github.austinv11.LootPlus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.gravitydevelopment.updater.Updater;

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
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class LootPlus extends JavaPlugin implements Listener{
	public String CURRENT_VERSION = "1.2.0"; //TODO remember to update
	public String CURRENT_GAME_VERSION = "CB 1.7.2-R0.3"; //TODO remember to update
	int id = 77925;
	FileConfiguration config = getConfig();
	float HEAD_DROP_RATE = 0.025f; //2.5% for wither skeles
	float PLAYER_HEAD_DROP_RATE = 0.025f; //2.5% for wither skeles
	float CHICKEN_BEAK_RATE = 0.025f; //Same as head drop rate, currently
	float DRAGON_EYE_RATE = 0.1f;
	float CREEPER_POWDER_CLUMP_RATE = 0.35f;
	float GHAST_FIREBALL_RATE = 0.5f;
	float HORSE_GLUE_RATE = 0.5f;
	float HORSE_MEAT_RATE = 0.25f;
	float MUSHROOM_RATE = 0.5f;
	float PET_ITEM_RATE = 0.45f;
	float BONEMEAL_RATE = 0.66f;//also used for blaze powder
	float EMERALD_DROP_RATE = 0.025f;//TODO balance
	float ZOMBIE_FEATHER_RATE = 0.3f;
	@Override
	public void onEnable(){
		//Big thanks to Gravity for his autoupdater! 
		//http://forums.bukkit.org/threads/updater-2-1-easy-safe-and-policy-compliant-auto-updating-for-your-plugins-new.96681/
		configInit(false);
		if (config.getBoolean("Options.setToDefault") == true){
			configInit(true);
		}
		if (config.getBoolean("Options.updates") == true){
			Updater updater = new Updater(this, id, this.getFile(), Updater.UpdateType.NO_DOWNLOAD, false);
			if (updater.getLatestGameVersion() == CURRENT_GAME_VERSION && config.getBoolean("Options.autoUpdate") == true){
				Updater updaterAuto = new Updater(this, id, this.getFile(), Updater.UpdateType.DEFAULT, true);
			}else if (updater.getLatestGameVersion() == CURRENT_GAME_VERSION && updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE){
				getLogger().info("An update is available for LootPlus, for Bukkit version" +updater.getLatestGameVersion()+ " available at " + updater.getLatestFileLink());
			}
		}
		new CustomMobDropInterface(this);
		new CustomDungeonLootInterface(this);
		new DungeonChestPopulator(this);
		getServer().getPluginManager().registerEvents(this, this);
		if (config.getBoolean("Options.playersAlwaysDropHeads") == true){
			PLAYER_HEAD_DROP_RATE = 1f;
		}
		if (config.getBoolean("Options.mobsAlwaysDropHeads") == true){
			HEAD_DROP_RATE = 1f;
		}
		getLogger().info("Loot on this server is now enhanced by LootPlus V"+CURRENT_VERSION+"!");
	}
	public FileConfiguration getDefaultConfig(){
		return getConfig();
	}
	public void log(String message){
		getLogger().info(message);
	}
	public void configInit(boolean override){
		if (override == false){
			getLogger().info("Initiating config...");
			config.addDefault("Options.onlyCustomDrops", false);
			config.addDefault("Options.disableXPDrops", false);
			config.addDefault("Options.allowCustomDrops", false);
			config.addDefault("Options.allowCustomXP", false);
			config.addDefault("Options.allowCustomDungeonLoot", false);
			//config.addDefault("Options.allowCustomSpawnRate", "TODO");//FIXME
			config.addDefault("Options.playersAlwaysDropHeads", false);
			config.addDefault("Options.mobsAlwaysDropHeads", false);
			config.addDefault("Options.updates", true);
			config.addDefault("Options.autoUpdate", true);
			config.addDefault("Options.setToDefault", false);
			config.addDefault("Features.headDrops", true);
			config.addDefault("Features.playerHeadDrops", true);
			config.addDefault("Features.extraHeadDrops", true);
			config.addDefault("Features.extraDungeonLoot", true);
			config.addDefault("Features.extraMobDrops", true);
			//config.addDefault("Features.extraEnchantments", "TODO");//FIXME
			//config.addDefault("Features.extraDungeons", "TODO");//FIXME
			config.addDefault("Features.easterEggs", true);
			//config.addDefault("Features.bossMobs", "TODO");//FIXME
			config.options().copyDefaults(true);
			saveConfig();
			getLogger().info("Initiated config!");
		} else if (override == true){
			getLogger().info("Reverting config to defaults...");
			config.set("Options.onlyCustomDrops", false);
			config.set("Options.disableXPDrops", false);
			config.set("Options.allowCustomDrops", false);
			config.set("Options.allowCustomXP", false);
			config.set("Options.allowCustomDungeonLoot", false);
			//config.set("Options.allowCustomSpawnRate", "TODO");//FIXME
			config.set("Options.playersAlwaysDropHeads", false);
			config.set("Options.mobsAlwaysDropHeads", false);
			config.set("Options.updates", true);
			config.set("Options.autoUpdate", true);
			config.set("Options.setToDefault", false);
			config.set("Features.headDrops", true);
			config.set("Features.playerHeadDrops", true);
			config.set("Features.extraHeadDrops", true);
			config.set("Features.extraDungeonLoot", true);
			config.set("Features.extraMobDrops", true);
			//config.set("Features.extraEnchantments", "TODO");//FIXME
			//config.set("Features.extraDungeons", "TODO");//FIXME
			config.set("Features.easterEggs", true);
			//config.set("Features.bossMobs", "TODO");//FIXME
			saveConfig();
			getLogger().info("Reverted config!");
		}
	}
	@Override
	public void onDisable(){
		//TODO
		getLogger().info("LootPlus no longer affects this server");
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		if (config.getBoolean("Options.updates") == true){
			Updater updater = new Updater(this, id, this.getFile(), Updater.UpdateType.NO_DOWNLOAD, false);
			if (player.isOp() && updater.getLatestGameVersion() == CURRENT_GAME_VERSION && config.getBoolean("Options.autoUpdate") == true){
				player.sendMessage("An update has been found for "+ChatColor.YELLOW+"LootPlus"+ChatColor.WHITE+"!");
				player.sendMessage("Type /lpupdate to update the plugin!");
			}
		}
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
				if (config.getBoolean("Options.playersAlwaysDropHeads") == true){
					sender.sendMessage(ChatColor.GREEN+"+Players will always drop a head");
				}else{
					sender.sendMessage(ChatColor.GREEN+"+Players have a chance of dropping heads");
				}
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
			if (config.getBoolean("Features.easterEggs") == true){
				sender.sendMessage(ChatColor.GREEN+"+Some very special things :)");//TODO better (funny) description
			}
			if (config.getBoolean("Features.bossMobs") == true){
				sender.sendMessage(ChatColor.GREEN+"+Random chance of a 'boss mob' to spawn (with cool loot!)");
			}
			return true;
		}else if (cmd.getName().equalsIgnoreCase("lpupdate")){
			if (config.getBoolean("Options.updates") == true){
				sender.sendMessage("Forcefully updating the LootPlus plugin");
				Updater updater = new Updater(this, id, this.getFile(), Updater.UpdateType.NO_VERSION_CHECK, true);
				sender.sendMessage("Done!");
			}else{
				sender.sendMessage("Sorry, updates has been disabled via the config");
			}
			return true;
		}
		return false;
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void onEntityDeath(EntityDeathEvent event){//FIXME mob head names
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
		if (config.getBoolean("Options.disableXPDrops") == true){
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
					Item item = loc.getWorld().dropItemNaturally(loc, skull);
					item.setItemStack(skull);
				}
			}
			if (config.getBoolean("Features.extraMobDrops") == true && cause == DamageCause.ENTITY_ATTACK){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= BONEMEAL_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack loot = new ItemStack(Material.BLAZE_POWDER);
					ItemMeta meta = loot.getItemMeta();
					meta.setDisplayName("Blaze Rod Shavings");
					loot.setItemMeta(meta);
					Item item = loc.getWorld().dropItemNaturally(loc, loot);
					item.setItemStack(loot);
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
					Item item = loc.getWorld().dropItemNaturally(loc, skull);
					item.setItemStack(skull);
				}
			}
			if (config.getBoolean("Features.easterEggs") == true && cause == DamageCause.ENTITY_ATTACK){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= CHICKEN_BEAK_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack beak = new ItemStack(Material.INK_SACK);
					List<String> lores = new ArrayList<String>();
					lores.add("This is disgusting...");
					beak.setDurability((short) 11);
					ItemMeta meta = beak.getItemMeta();
					meta.setDisplayName("Chicken Beak");
					meta.setLore(lores);
					beak.setItemMeta(meta);
					Item item = loc.getWorld().dropItemNaturally(loc, beak);
					item.setItemStack(beak);
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
			if (config.getBoolean("Features.extraMobDrops") == true){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= CREEPER_POWDER_CLUMP_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack loot = new ItemStack(Material.FIREWORK_CHARGE);
					List<String> lores = new ArrayList<String>();
					lores.add("Maybe this could be used for fireworks...");
					ItemMeta meta = loot.getItemMeta();
					meta.setDisplayName("Clump Of Gunpowder");
					meta.setLore(lores);
					loot.setItemMeta(meta);
					Item item = loc.getWorld().dropItemNaturally(loc, loot);
					item.setItemStack(loot);
				}
			}
		}else if (event.getEntityType() == EntityType.ENDER_DRAGON){//FIXME add head
			if (config.getBoolean("Features.extraMobDrops") == true){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= DRAGON_EYE_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack loot = new ItemStack(Material.EYE_OF_ENDER);
					List<String> lores = new ArrayList<String>();
					lores.add("You could've sworn it was staring at you");
					ItemMeta meta = loot.getItemMeta();
					meta.setDisplayName("Dragon Eye");
					meta.setLore(lores);
					loot.setItemMeta(meta);
					Item item = loc.getWorld().dropItemNaturally(loc, loot);
					item.setItemStack(loot);
				}
			}
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
					Item item = loc.getWorld().dropItemNaturally(loc, skull);
					item.setItemStack(skull);
				}
			}
			if (config.getBoolean("Features.extraMobDrops") == true){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= GHAST_FIREBALL_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack loot = new ItemStack(Material.FIREBALL);
					List<String> lores = new ArrayList<String>();
					lores.add("It's warm to the touch");
					ItemMeta meta = loot.getItemMeta();
					meta.setDisplayName("Ghast Fireball");
					meta.setLore(lores);
					loot.setItemMeta(meta);
					Item item = loc.getWorld().dropItemNaturally(loc, loot);
					item.setItemStack(loot);
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
			if (config.getBoolean("Features.extraMobDrops") == true){//TODO add rare drops (like iron)
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= 0.67f){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack loot = new ItemStack(Material.ROTTEN_FLESH, 3);//FIXME Change amount of drops
					Item item = loc.getWorld().dropItemNaturally(loc, loot);
					item.setItemStack(loot);
				}
			}
			if (config.getBoolean("Features.easterEggs") == true){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= ZOMBIE_FEATHER_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack loot = new ItemStack(Material.FEATHER);//FIXME Change amount of drops
					List<String> lores = new ArrayList<String>();
					lores.add("Ahh, the nostalgia");
					ItemMeta meta = loot.getItemMeta();
					meta.setLore(lores);
					loot.setItemMeta(meta);
					Item item = loc.getWorld().dropItemNaturally(loc, loot);
					item.setItemStack(loot);
				}
			}
		}else if (event.getEntityType() == EntityType.HORSE){//FIXME add head
			if (config.getBoolean("Features.easterEggs") == true){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= HORSE_GLUE_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack loot = new ItemStack(Material.SLIME_BALL);
					List<String> lores = new ArrayList<String>();
					lores.add("Is it wrong that I think this is funny?");
					ItemMeta meta = loot.getItemMeta();
					meta.setDisplayName("Glue");
					meta.setLore(lores);
					loot.setItemMeta(meta);
					Item item = loc.getWorld().dropItemNaturally(loc, loot);
					item.setItemStack(loot);
				}
			}
			if (config.getBoolean("Features.easterEggs") == true){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= HORSE_MEAT_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack loot = new ItemStack(Material.RAW_BEEF);
					List<String> lores = new ArrayList<String>();
					lores.add("What is this?");
					ItemMeta meta = loot.getItemMeta();
					meta.setDisplayName("Mystery Meat");
					meta.setLore(lores);
					loot.setItemMeta(meta);
					Item item = loc.getWorld().dropItemNaturally(loc, loot);
					item.setItemStack(loot);
				}
			}
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
					Item item = loc.getWorld().dropItemNaturally(loc, skull);
					item.setItemStack(skull);
				}
			}
			if (config.getBoolean("Features.extraMobDrops") == true){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= MUSHROOM_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack loot = new ItemStack(Material.RED_MUSHROOM);
					Item item = loc.getWorld().dropItemNaturally(loc, loot);
					item.setItemStack(loot);
				}
			}
			if (config.getBoolean("Features.extraMobDrops") == true){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= MUSHROOM_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack loot = new ItemStack(Material.BROWN_MUSHROOM);
					Item item = loc.getWorld().dropItemNaturally(loc, loot);
					item.setItemStack(loot);
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
					Item item = loc.getWorld().dropItemNaturally(loc, skull);
					item.setItemStack(skull);
				}
			}
			if (config.getBoolean("Features.easterEggs") == true && cause == DamageCause.ENTITY_ATTACK){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= PET_ITEM_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack loot = new ItemStack(Material.RAW_FISH);
					List<String> lores = new ArrayList<String>();
					lores.add("You monster! You killed a helpless cat!");
					ItemMeta meta = loot.getItemMeta();
					meta.setLore(lores);
					loot.setItemMeta(meta);
					Item item = loc.getWorld().dropItemNaturally(loc, loot);
					item.setItemStack(loot);
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
					Item item = loc.getWorld().dropItemNaturally(loc, skull);
					item.setItemStack(skull);
				}
			}
		}else if (event.getEntityType() == EntityType.PLAYER){
			//TODO
		}else if (event.getEntityType() == EntityType.SHEEP){//TODO add food drop
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
			if (config.getBoolean("Features.extraMobDrops") == true && cause == DamageCause.ENTITY_ATTACK){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= BONEMEAL_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack loot = new ItemStack(Material.INK_SACK);
					loot.setDurability((short) 15);
					ItemMeta meta = loot.getItemMeta();
					meta.setDisplayName("Bone Shavings");
					loot.setItemMeta(meta);
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
					Item item = loc.getWorld().dropItemNaturally(loc, skull);
					item.setItemStack(skull);
				}
			}
		}else if (event.getEntityType() == EntityType.SNOWMAN){//FIXME change head
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
					Item item = loc.getWorld().dropItemNaturally(loc, skull);
					item.setItemStack(skull);
				}
			}
			if (config.getBoolean("Features.extraMobDrops") == true && cause == DamageCause.ENTITY_ATTACK){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= 1f){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack loot = new ItemStack(Material.PUMPKIN);
					List<String> lores = new ArrayList<String>();
					lores.add("How dare you decapitate the Magic Snowman!");
					ItemMeta meta = loot.getItemMeta();
					meta.setLore(lores);
					loot.setItemMeta(meta);
					Item item = loc.getWorld().dropItemNaturally(loc, loot);
					item.setItemStack(loot);
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
					Item item = loc.getWorld().dropItemNaturally(loc, skull);
					item.setItemStack(skull);
				}
			}
			if (config.getBoolean("Features.extraMobDrops") == true && cause == DamageCause.ENTITY_ATTACK){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= EMERALD_DROP_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack loot = new ItemStack(Material.EMERALD);
					List<String> lores = new ArrayList<String>();
					lores.add("I guess this is blood money");
					ItemMeta meta = loot.getItemMeta();
					meta.setLore(lores);
					loot.setItemMeta(meta);
					Item item = loc.getWorld().dropItemNaturally(loc, loot);
					item.setItemStack(loot);
				}
			}
			if (config.getBoolean("Features.easterEggs") == true && cause == DamageCause.ENTITY_ATTACK){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= EMERALD_DROP_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack loot = new ItemStack(Material.INK_SACK);
					List<String> lores = new ArrayList<String>();
					lores.add("Squidward?");
					ItemMeta meta = loot.getItemMeta();
					meta.setLore(lores);
					loot.setItemMeta(meta);
					Item item = loc.getWorld().dropItemNaturally(loc, loot);
					item.setItemStack(loot);
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
					byte type = 1;
					ItemStack loot = new ItemStack(Material.SKULL_ITEM, 1, type);
					Item item = loc.getWorld().dropItemNaturally(loc, loot);
					item.setItemStack(loot);
				}
			}
		}else if (event.getEntityType() == EntityType.WOLF){//FIXME add head
			DamageCause cause = event.getEntity().getLastDamageCause().getCause();
			if (config.getBoolean("Features.easterEggs") == true && cause == DamageCause.ENTITY_ATTACK){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= PET_ITEM_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack loot = new ItemStack(Material.BONE);
					List<String> lores = new ArrayList<String>();
					lores.add("Come on, give a dog a bone");
					ItemMeta meta = loot.getItemMeta();
					meta.setLore(lores);
					loot.setItemMeta(meta);
					Item item = loc.getWorld().dropItemNaturally(loc, loot);
					item.setItemStack(loot);
				}
			}
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
			if (config.getBoolean("Features.easterEggs") == true){
				Random r = new Random();
				float chance = r.nextFloat();
				if (chance <= ZOMBIE_FEATHER_RATE){
					Location loc = event.getEntity().getLocation().clone();
					ItemStack loot = new ItemStack(Material.FEATHER);//FIXME Change amount of drops
					List<String> lores = new ArrayList<String>();
					lores.add("Ahh, the nostalgia");
					ItemMeta meta = loot.getItemMeta();
					meta.setLore(lores);
					loot.setItemMeta(meta);
					Item item = loc.getWorld().dropItemNaturally(loc, loot);
					item.setItemStack(loot);
				}
			}
		}else {
			getLogger().info("Error: Unknown entity type killed (This could probably be ignored)");
		}	
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerDeath(PlayerDeathEvent event) {
		DamageCause cause = event.getEntity().getLastDamageCause().getCause();
		if (config.getBoolean("Features.playerHeadDrops") == true && cause == DamageCause.ENTITY_ATTACK){
			Random r = new Random();
			float chance = r.nextFloat();
			if (chance <= PLAYER_HEAD_DROP_RATE){
				Player player = event.getEntity();
				Location loc = player.getLocation();
				ItemStack skull = new ItemStack(Material.SKULL_ITEM);
				skull.setDurability((short) 3);
				SkullMeta meta = (SkullMeta) skull.getItemMeta();
				meta.setDisplayName(player.getName()+"'s Head");
				meta.setOwner(player.getName());
				skull.setItemMeta(meta);
				Item item = loc.getWorld().dropItemNaturally(loc, skull);
				item.setItemStack(skull);
			}
		}
	}
}
