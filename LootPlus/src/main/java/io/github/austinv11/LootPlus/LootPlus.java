package io.github.austinv11.LootPlus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LootPlus extends JavaPlugin implements Listener{
	public String CURRENT_VERSION = "1.0.0"; //TODO remember to update
	FileConfiguration config = getConfig();
	PluginManager pm = Bukkit.getServer().getPluginManager();
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
			config.addDefault("Options.onlyCustomDrops", "TODO");//FIXME
			config.addDefault("Options.disableXPDrops", "TODO");//FIXME
			config.addDefault("Options.allowCustomDrops", "TODO");//FIXME
			config.addDefault("Options.allowCustomXP", "TODO");//FIXME
			config.addDefault("Options.allowCustomDungeonLoot", "TODO");//FIXME
			config.addDefault("Options.allowCustomSpawnRate", "TODO");//FIXME
			config.addDefault("Options.updateNotifications", "TODO");//FIXME
			config.addDefault("Options.setToDefault", false);
			config.addDefault("Features.headDrops", "TODO");//FIXME
			config.addDefault("Features.playerHeadDrops", "TODO");//FIXME
			config.addDefault("Features.extraHeadDrops", "TODO");//FIXME
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
			config.set("Options.onlyCustomDrops", "TODO");//FIXME
			config.set("Options.disableXPDrops", "TODO");//FIXME
			config.set("Options.allowCustomDrops", "TODO");//FIXME
			config.set("Options.allowCustomXP", "TODO");//FIXME
			config.set("Options.allowCustomDungeonLoot", "TODO");//FIXME
			config.set("Options.allowCustomSpawnRate", "TODO");//FIXME
			config.set("Options.updateNotifications", "TODO");//FIXME
			config.set("Options.setToDefault", false);
			config.set("Features.headDrops", "TODO");//FIXME
			config.set("Features.playerHeadDrops", "TODO");//FIXME
			config.set("Features.extraHeadDrops", "TODO");//FIXME
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
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
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
}
