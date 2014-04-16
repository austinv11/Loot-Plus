package io.github.austinv11.LootPlus;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;

public class CustomMobDropInterface implements Listener{
	FileConfiguration config = Bukkit.getPluginManager().getPlugin("LootPlus").getConfig();
	File mobData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomLoot//mobLoot.yml");
	FileConfiguration mobDatas = YamlConfiguration.loadConfiguration(mobData);

	public CustomMobDropInterface(LootPlus plugin){
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		mobInit();
	}
	public void mobInit(){
		mobDatas.addDefault("bat.xp", "[]");
		mobDatas.addDefault("bat.probablility", "[]");
		mobDatas.options().copyDefaults(true);
		save();
	}
	public String[] readTable(String table){
		table = table.replace("[","").replace("]", "");
		return table.split(",");
	}
	private void save(){
		try{
			mobDatas.save(mobData);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
