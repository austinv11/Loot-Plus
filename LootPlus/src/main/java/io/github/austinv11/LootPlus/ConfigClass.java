package io.github.austinv11.LootPlus;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigClass extends JavaPlugin{
	public FileConfiguration getDefaultConfig(){
		FileConfiguration config = getConfig();
		return config;
	}
}
