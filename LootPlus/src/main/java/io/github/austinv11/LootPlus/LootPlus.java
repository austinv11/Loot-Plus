package io.github.austinv11.LootPlus;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class LootPlus extends JavaPlugin{
	public String CURRENT_VERSION = "1.0.0"; //TODO remember to update
	@Override
	public void onEnable(){
		configInit();
		getLogger().info("Loot on this server is now enhanced by LootPlus V"+CURRENT_VERSION+"!");
	}
	public void configInit(){
		getLogger().info("Initiating config...");
		FileConfiguration config = getConfig();
		config.addDefault("Options.onlyCustomDrops", "TODO");//FIXME
		config.addDefault("Options.allowCustomDrops", "TODO");//FIXME
		config.addDefault("Options.allowCustomXP", "TODO");//FIXME
		config.addDefault("Options.allowCustomDungeonLoot", "TODO");//FIXME
		config.addDefault("Options.allowCustomSpawnRate", "TODO");//FIXME
		config.addDefault("Options.updateNotifications", "TODO");//FIXME
		config.addDefault("Options.setToDefault", "TODO");//FIXME
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
	}
	@Override
	public void onDisable(){
		//TODO
		getLogger().info("LootPlus no longer affects this server");
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		return false;
	}
}
