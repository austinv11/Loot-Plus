package io.github.austinv11.LootPlus;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkPopulateEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class DungeonChestPopulator extends JavaPlugin implements Listener{
	FileConfiguration config = getConfig();
	
	@EventHandler
	public void onGeneration(ChunkPopulateEvent event){
		if (config.getBoolean("Features.extraDungeonLoot") == true){
			BlockState[] tileEnts = event.getChunk().getTileEntities();
			for (BlockState state : tileEnts) {
				if (state.getType() != Material.CHEST)
					continue;
				Chest chest = (Chest) state;
				Inventory cInv = chest.getInventory();
				double lootPasses = Math.random();
				int passes;
				if (lootPasses <= 0.1){//10% of nothing new
					passes = 0;
				} else if (lootPasses <= 0.3){//20% of one new thing
					passes = 1;
				}else if (lootPasses <= 0.6){//30% of two new things
					passes = 2;
				}else if (lootPasses <= 0.9){//30% of three new things
					passes = 3;
				}else{//10% of 4 new things
					passes = 4;
				}
				
			}
		}
	}
	public ItemStack randLoot(){
		double lootType = Math.random();
		if (lootType <= 0.1){//10% skele skull
			byte type = 0;
			ItemStack loot = new ItemStack(Material.SKULL_ITEM, 1, type);
			List<String> lores = new ArrayList<String>();
			lores.add("Does this head look familiar?");
			ItemMeta meta = loot.getItemMeta();
			meta.setLore(lores);
			loot.setItemMeta(meta);
			return loot;
		}else if (lootType <= 0.19){//9% for extra records
			double recordType = Math.random();
			if (recordType <= 0.1){
				ItemStack loot = new ItemStack(Material.RECORD_3);
				return loot;
			}else if (recordType <= 0.2){
				ItemStack loot = new ItemStack(Material.RECORD_4);
				return loot;
			}else if (recordType <= 0.3){
				ItemStack loot = new ItemStack(Material.RECORD_5);
				return loot;
			}else if (recordType <= 0.4){
				ItemStack loot = new ItemStack(Material.RECORD_6);
				return loot;
			}else if (recordType <= 0.5){
				ItemStack loot = new ItemStack(Material.RECORD_7);
				return loot;
			}else if (recordType <= 0.6){
				ItemStack loot = new ItemStack(Material.RECORD_8);
				return loot;
			}else if (recordType <= 0.7){
				ItemStack loot = new ItemStack(Material.RECORD_9);
				return loot;
			}else if (recordType <= 0.8){
				ItemStack loot = new ItemStack(Material.RECORD_10);
				return loot;
			}else if (recordType <= 0.9){
				ItemStack loot = new ItemStack(Material.RECORD_11);
				return loot;
			}else if (recordType <= 1){
				ItemStack loot = new ItemStack(Material.RECORD_12);
				return loot;
			}
		}else if (lootType <= 0.2){ //1% for Notch's apple
			byte type = 1;
			ItemStack loot = new ItemStack(Material.GOLDEN_APPLE, 1, type);
			List<String> lores = new ArrayList<String>();
			lores.add("May Notch bless your stomach");
			ItemMeta meta = loot.getItemMeta();
			meta.setLore(lores);
			meta.setDisplayName("Notched Golden Apple");
			loot.setItemMeta(meta);
			return loot;
		}else if (lootType <= 0.25){//5% for cobweb
			ItemStack loot = new ItemStack(Material.WEB);
			return loot;
		}else if (lootType <= 0.28){//3% for Nemo
			byte type = 2;
			ItemStack loot = new ItemStack(Material.RAW_FISH, 1, type);
			List<String> lores = new ArrayList<String>();
			lores.add("You found Nemo!");
			lores.add("Now where's Dory...");
			ItemMeta meta = loot.getItemMeta();
			meta.setLore(lores);
			meta.setDisplayName("Nemo");
			loot.setItemMeta(meta);
			return loot;
		}else if (lootType <= 38){//10%
			
		}
	}
	public ItemStack tieredLoot(String level){
		if (level == "Legendary"){//Red
			
		}else if (level == "Rare"){//Orange
			
		}else if (level == "Uncommon"){//Green
			
		}else if (level == "Common"){//White
			
		}else if (level == "Poor"){//Grey
			
		}
	}
}
