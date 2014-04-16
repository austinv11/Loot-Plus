package io.github.austinv11.LootPlus;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkPopulateEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CustomDungeonLootInterface implements Listener{
	FileConfiguration config = Bukkit.getPluginManager().getPlugin("LootPlus").getConfig();
	File dungeonData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomLoot//dungeonLoot.yml");
	FileConfiguration dungeonDatas = YamlConfiguration.loadConfiguration(dungeonData);
	
	public CustomDungeonLootInterface(LootPlus plugin){
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		dungeonInit();
	}
	public void dungeonInit(){//biomes must be Biomes ('all' to be ignored) (defined here: http://jd.bukkit.org/beta/doxygen/dc/d5c/enumorg_1_1bukkit_1_1block_1_1Biome.html)
		dungeonDatas.addDefault("biome", "[all]"); //all means the biome doesn't matter
		dungeonDatas.addDefault("probability", "[0]");
		dungeonDatas.addDefault("loot", "[none]");
		dungeonDatas.options().copyDefaults(true);
		save();
	}
	public void save(){
		try{
			dungeonDatas.save(dungeonData);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public String[] readTable(String table){
		table = table.replace("[","").replace("]", "");
		table = table.replace(" ","");
		table = table.replace("'", "");
		return table.split(",");
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onGeneration(ChunkPopulateEvent event){
		if (config.getBoolean("Options.allowCustomDungeonLoot") == true){
			BlockState[] tileEnts = event.getChunk().getTileEntities();
			for (BlockState state : tileEnts) {
				if (state.getType() != Material.CHEST){
					continue;
				}else{
					Chest chest = (Chest) state;
					Inventory cInv = chest.getInventory();
					String[] biome = readTable(dungeonDatas.getString("biome"));
					String[] probability = readTable(dungeonDatas.getString("probability"));
					String[] loot = readTable(dungeonDatas.getString("loot"));
					if (probability[0] != "0" && loot[0] != "none"){
						if (probability.length == loot.length && biome.length == loot.length){
							for (int i = 0; i < loot.length; i++){
								double lootChance = Math.random();
								double chance = (Double.parseDouble(probability[i].replace("‘", "").replace("’", "")) / 100);
								if (lootChance <= chance){
									Location loc = chest.getLocation().clone();
									Material lookup = Material.getMaterial(loot[i].toUpperCase().replace("‘", "").replace("’", ""));
									String sBiome = biome[i].replace("‘", "").replace("’", "");
									if (sBiome.toLowerCase() == "all"){
										sBiome = loc.getWorld().getBiome(loc.getBlockX(), loc.getBlockZ()).toString();
									}
									if (lookup != null && sBiome == loc.getWorld().getBiome(loc.getBlockX(), loc.getBlockZ()).toString()){
										ItemStack dLoot = new ItemStack(lookup);//TODO add checks
										cInv.addItem(dLoot);
									}else{
										Bukkit.getLogger().info("Error: Material '"+loot[i]+"' does not exist, please make sure it's a valid material");
									}
								}
							}
						}else {
							Bukkit.getLogger().info("Error: Custom dungeon loot configuration is invalid");
						}
					}
				}
			}
		}
	}
}
