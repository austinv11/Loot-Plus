package io.github.austinv11.LootPlus;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkPopulateEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public final class DungeonChestPopulator implements Listener{
	public DungeonChestPopulator(LootPlus plugin){
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	FileConfiguration config = Bukkit.getPluginManager().getPlugin("LootPlus").getConfig();
	@EventHandler(priority = EventPriority.HIGH)
	public void onGeneration(ChunkPopulateEvent event){
		if (config.getBoolean("Features.extraDungeonLoot") == true){
			BlockState[] tileEnts = event.getChunk().getTileEntities();
			for (BlockState state : tileEnts) {
				if (state.getType() != Material.CHEST){
					continue;
				}else{
					Chest chest = (Chest) state;
					Bukkit.getLogger().info("Enhancing dungeon loot at dungeon chest- X: "+chest.getX()+" Y: "+chest.getY()+" Z: "+chest.getZ());
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
					for (int i = 0; i < passes; i++){
						cInv.addItem(randLoot());
					}
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
		}else if (lootType <= .33){//5%
			ItemStack loot = tieredLoot("Legendary");
			return loot;
		}else if (lootType <= .43){//10%
			ItemStack loot = tieredLoot("Rare");
			return loot;
		}else if (lootType <= .78){//35%
			ItemStack loot = tieredLoot("Common");
			return loot;
		}else if (lootType <= 1){//22%
			ItemStack loot = tieredLoot("Poor");
			return loot;
		}
		return null;
	}
	public ItemStack tieredLoot(String level){//TODO add bows, add custom names
		ItemStack item;
		if (level == "Legendary"){//Dark Red
			double itemKind = Math.random();
			if (itemKind <= 0.02){//2% for fishing rod
				item = new ItemStack(Material.FISHING_ROD);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.DARK_RED+"Legendary");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Magical Fishing Rod");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(61);
					item.addUnsafeEnchantment(newEnchant, 5);
					Enchantment newEnchant2 = new EnchantmentWrapper(62);
					item.addUnsafeEnchantment(newEnchant2, 5);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 3);
				}else if (enchants <= 0.85){
					Enchantment newEnchant = new EnchantmentWrapper(61);
					item.addUnsafeEnchantment(newEnchant, 5);
					Enchantment newEnchant2 = new EnchantmentWrapper(62);
					item.addUnsafeEnchantment(newEnchant2, 5);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(61);
					item.addUnsafeEnchantment(newEnchant, 3);
					Enchantment newEnchant2 = new EnchantmentWrapper(62);
					item.addUnsafeEnchantment(newEnchant2, 3);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 3);
				}
				return item;
			}else if (itemKind <= 0.12){//10% for shovel
				item = new ItemStack(Material.GOLD_SPADE);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.DARK_RED+"Legendary");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Magically Gold-Plated Shovel");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant, 7);
					Enchantment newEnchant2 = new EnchantmentWrapper(35);
					item.addUnsafeEnchantment(newEnchant2, 4);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 3);
				}else if (enchants <= 0.85){
					Enchantment newEnchant = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant, 6);
					Enchantment newEnchant2 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant2, 2);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(33);
					item.addUnsafeEnchantment(newEnchant, 1);
					Enchantment newEnchant2 = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant2, 5);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 3);
				}
				return item;
			}else if (itemKind <= 0.22){//10% for axe
				item = new ItemStack(Material.GOLD_AXE);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.DARK_RED+"Legendary");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Magically Gold-Plated Axe");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant, 7);
					Enchantment newEnchant2 = new EnchantmentWrapper(35);
					item.addUnsafeEnchantment(newEnchant2, 4);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 3);
				}else if (enchants <= 0.85){
					Enchantment newEnchant = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant, 6);
					Enchantment newEnchant2 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant2, 2);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(33);
					item.addUnsafeEnchantment(newEnchant, 1);
					Enchantment newEnchant2 = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant2, 5);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 3);
				}
				return item;
			}else if (itemKind <= 0.37){//15% for pick
				item = new ItemStack(Material.GOLD_PICKAXE);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.DARK_RED+"Legendary");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Magically Gold-Plated Pickaxe");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant, 7);
					Enchantment newEnchant2 = new EnchantmentWrapper(35);
					item.addUnsafeEnchantment(newEnchant2, 4);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 3);
				}else if (enchants <= 0.85){
					Enchantment newEnchant = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant, 6);
					Enchantment newEnchant2 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant2, 2);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(33);
					item.addUnsafeEnchantment(newEnchant, 1);
					Enchantment newEnchant2 = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant2, 5);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 3);
				}
				return item;
			}else if (itemKind <= 0.54){//17% for Sword
				item = new ItemStack(Material.GOLD_SWORD);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.DARK_RED+"Legendary");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Magically Gold-Plated Sword");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(16);
					item.addUnsafeEnchantment(newEnchant, 7);
					Enchantment newEnchant2 = new EnchantmentWrapper(20);
					item.addUnsafeEnchantment(newEnchant2, 3);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 3);
				}else if (enchants <= 0.85){
					Enchantment newEnchant = new EnchantmentWrapper(17);
					item.addUnsafeEnchantment(newEnchant, 6);
					Enchantment newEnchant2 = new EnchantmentWrapper(18);
					item.addUnsafeEnchantment(newEnchant2, 6);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(16);
					item.addUnsafeEnchantment(newEnchant, 6);
					Enchantment newEnchant2 = new EnchantmentWrapper(21);
					item.addUnsafeEnchantment(newEnchant2, 4);
				}
				return item;
			}else if (itemKind <= 0.65){//11% for boots
				item = new ItemStack(Material.GOLD_BOOTS);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.DARK_RED+"Legendary");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Magically Gold-Plated Boots");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(0);
					item.addUnsafeEnchantment(newEnchant, 7);
					Enchantment newEnchant2 = new EnchantmentWrapper(2);
					item.addUnsafeEnchantment(newEnchant2, 7);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 3);
				}else if (enchants <= 0.85){
					Enchantment newEnchant = new EnchantmentWrapper(1);
					item.addUnsafeEnchantment(newEnchant, 6);
					Enchantment newEnchant2 = new EnchantmentWrapper(3);
					item.addUnsafeEnchantment(newEnchant2, 6);
					Enchantment newEnchant3 = new EnchantmentWrapper(4);
					item.addUnsafeEnchantment(newEnchant3, 6);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(0);
					item.addUnsafeEnchantment(newEnchant, 6);
					Enchantment newEnchant2 = new EnchantmentWrapper(2);
					item.addUnsafeEnchantment(newEnchant2, 5);
				}
				return item;
			}else if (itemKind <= 0.72){//7% for pants
				item = new ItemStack(Material.GOLD_LEGGINGS);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.DARK_RED+"Legendary");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Magically Gold-Plated Leggings");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(0);
					item.addUnsafeEnchantment(newEnchant, 7);
					Enchantment newEnchant2 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant2, 3);
				}else if (enchants <= 0.85){
					Enchantment newEnchant = new EnchantmentWrapper(1);
					item.addUnsafeEnchantment(newEnchant, 6);
					Enchantment newEnchant2 = new EnchantmentWrapper(3);
					item.addUnsafeEnchantment(newEnchant2, 6);
					Enchantment newEnchant3 = new EnchantmentWrapper(4);
					item.addUnsafeEnchantment(newEnchant3, 6);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(0);
					item.addUnsafeEnchantment(newEnchant, 6);
				}
				return item;
			}else if (itemKind <= 0.83){//11% for chestplate
				item = new ItemStack(Material.GOLD_CHESTPLATE);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.DARK_RED+"Legendary");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Magically Gold-Plated Chestplate");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(0);
					item.addUnsafeEnchantment(newEnchant, 7);
					Enchantment newEnchant2 = new EnchantmentWrapper(7);
					item.addUnsafeEnchantment(newEnchant2, 6);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 3);
				}else if (enchants <= 0.85){
					Enchantment newEnchant = new EnchantmentWrapper(1);
					item.addUnsafeEnchantment(newEnchant, 6);
					Enchantment newEnchant2 = new EnchantmentWrapper(3);
					item.addUnsafeEnchantment(newEnchant2, 6);
					Enchantment newEnchant3 = new EnchantmentWrapper(4);
					item.addUnsafeEnchantment(newEnchant3, 6);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(0);
					item.addUnsafeEnchantment(newEnchant, 6);
					Enchantment newEnchant2 = new EnchantmentWrapper(7);
					item.addUnsafeEnchantment(newEnchant2, 4);
				}
				return item;
			}else if (itemKind <= 0.9){//7% for Helmet
				item = new ItemStack(Material.GOLD_HELMET);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.DARK_RED+"Legendary");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Magically Gold-Plated Helmet");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(0);
					item.addUnsafeEnchantment(newEnchant, 7);
					Enchantment newEnchant2 = new EnchantmentWrapper(5);
					item.addUnsafeEnchantment(newEnchant2, 5);
					Enchantment newEnchant3 = new EnchantmentWrapper(6);
					item.addUnsafeEnchantment(newEnchant3, 3);
					Enchantment newEnchant4 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant4, 3);
				}else if (enchants <= 0.85){
					Enchantment newEnchant = new EnchantmentWrapper(1);
					item.addUnsafeEnchantment(newEnchant, 6);
					Enchantment newEnchant2 = new EnchantmentWrapper(3);
					item.addUnsafeEnchantment(newEnchant2, 6);
					Enchantment newEnchant3 = new EnchantmentWrapper(4);
					item.addUnsafeEnchantment(newEnchant3, 6);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(0);
					item.addUnsafeEnchantment(newEnchant, 6);
					Enchantment newEnchant2 = new EnchantmentWrapper(5);
					item.addUnsafeEnchantment(newEnchant2, 4);
					Enchantment newEnchant3 = new EnchantmentWrapper(6);
					item.addUnsafeEnchantment(newEnchant3, 2);
				}
				return item;
			}else if (itemKind <= 1){//10% for battlesign
				item = new ItemStack(Material.SIGN);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.DARK_RED+"Legendary");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Magical Zistonian Battle Sign");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(16);
					item.addUnsafeEnchantment(newEnchant, 4);
					Enchantment newEnchant2 = new EnchantmentWrapper(19);
					item.addUnsafeEnchantment(newEnchant2, 20);
					Enchantment newEnchant3 = new EnchantmentWrapper(20);
					item.addUnsafeEnchantment(newEnchant3, 3);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(16);
					item.addUnsafeEnchantment(newEnchant, 4);
					Enchantment newEnchant2 = new EnchantmentWrapper(19);
					item.addUnsafeEnchantment(newEnchant2, 15);
					Enchantment newEnchant3 = new EnchantmentWrapper(20);
					item.addUnsafeEnchantment(newEnchant3, 2);
				}
				return item;
			}
		}else if (level == "Rare"){//red
			double itemKind = Math.random();
			if (itemKind <= 0.02){//2% for fishing rod
				item = new ItemStack(Material.FISHING_ROD);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.RED+"Rare");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Imbued Fishing Rod");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(61);
					item.addUnsafeEnchantment(newEnchant, 3);
					Enchantment newEnchant2 = new EnchantmentWrapper(62);
					item.addUnsafeEnchantment(newEnchant2, 3);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 2);
				}else if (enchants <= 0.85){
					Enchantment newEnchant = new EnchantmentWrapper(61);
					item.addUnsafeEnchantment(newEnchant, 3);
					Enchantment newEnchant2 = new EnchantmentWrapper(62);
					item.addUnsafeEnchantment(newEnchant2, 3);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(61);
					item.addUnsafeEnchantment(newEnchant, 2);
					Enchantment newEnchant2 = new EnchantmentWrapper(62);
					item.addUnsafeEnchantment(newEnchant2, 2);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 3);
				}
				return item;
			}else if (itemKind <= 0.12){//10% for shovel
				item = new ItemStack(Material.DIAMOND_SPADE);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.RED+"Rare");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Runic Shovel");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant, 5);
					Enchantment newEnchant2 = new EnchantmentWrapper(35);
					item.addUnsafeEnchantment(newEnchant2, 3);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 3);
				}else if (enchants <= 0.85){
					Enchantment newEnchant = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant, 5);
					Enchantment newEnchant2 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant2, 2);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(33);
					item.addUnsafeEnchantment(newEnchant, 1);
					Enchantment newEnchant2 = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant2, 4);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 2);
				}
				return item;
			}else if (itemKind <= 0.22){//10% for axe
				item = new ItemStack(Material.DIAMOND_AXE);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.RED+"Rare");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Runic Axe");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant, 5);
					Enchantment newEnchant2 = new EnchantmentWrapper(35);
					item.addUnsafeEnchantment(newEnchant2, 3);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 3);
				}else if (enchants <= 0.85){
					Enchantment newEnchant = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant, 5);
					Enchantment newEnchant2 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant2, 2);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(33);
					item.addUnsafeEnchantment(newEnchant, 1);
					Enchantment newEnchant2 = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant2, 4);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 2);
				}
				return item;
			}else if (itemKind <= 0.37){//15% for pick
				item = new ItemStack(Material.DIAMOND_PICKAXE);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.RED+"Rare");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Runic Pickaxe");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant, 5);
					Enchantment newEnchant2 = new EnchantmentWrapper(35);
					item.addUnsafeEnchantment(newEnchant2, 3);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 3);
				}else if (enchants <= 0.85){
					Enchantment newEnchant = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant, 5);
					Enchantment newEnchant2 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant2, 2);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(33);
					item.addUnsafeEnchantment(newEnchant, 1);
					Enchantment newEnchant2 = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant2, 4);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 2);
				}
				return item;
			}else if (itemKind <= 0.54){//17% for Sword
				item = new ItemStack(Material.DIAMOND_SWORD);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.RED+"Rare");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Runic Sword");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(16);
					item.addUnsafeEnchantment(newEnchant, 5);
					Enchantment newEnchant2 = new EnchantmentWrapper(20);
					item.addUnsafeEnchantment(newEnchant2, 2);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 3);
				}else if (enchants <= 0.85){
					Enchantment newEnchant = new EnchantmentWrapper(17);
					item.addUnsafeEnchantment(newEnchant, 5);
					Enchantment newEnchant2 = new EnchantmentWrapper(18);
					item.addUnsafeEnchantment(newEnchant2, 5);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(16);
					item.addUnsafeEnchantment(newEnchant, 5);
					Enchantment newEnchant2 = new EnchantmentWrapper(21);
					item.addUnsafeEnchantment(newEnchant2, 3);
				}
				return item;
			}else if (itemKind <= 0.65){//11% for boots
				item = new ItemStack(Material.DIAMOND_BOOTS);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.RED+"Rare");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Runic Boots");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(0);
					item.addUnsafeEnchantment(newEnchant, 5);
					Enchantment newEnchant2 = new EnchantmentWrapper(2);
					item.addUnsafeEnchantment(newEnchant2, 5);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 3);
				}else if (enchants <= 0.85){
					Enchantment newEnchant = new EnchantmentWrapper(1);
					item.addUnsafeEnchantment(newEnchant, 4);
					Enchantment newEnchant2 = new EnchantmentWrapper(3);
					item.addUnsafeEnchantment(newEnchant2, 4);
					Enchantment newEnchant3 = new EnchantmentWrapper(4);
					item.addUnsafeEnchantment(newEnchant3, 4);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(0);
					item.addUnsafeEnchantment(newEnchant, 4);
					Enchantment newEnchant2 = new EnchantmentWrapper(2);
					item.addUnsafeEnchantment(newEnchant2, 4);
				}
				return item;
			}else if (itemKind <= 0.72){//7% for pants
				item = new ItemStack(Material.DIAMOND_LEGGINGS);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.RED+"Rare");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Runic Leggings");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(0);
					item.addUnsafeEnchantment(newEnchant, 5);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 3);
				}else if (enchants <= 0.85){
					Enchantment newEnchant = new EnchantmentWrapper(1);
					item.addUnsafeEnchantment(newEnchant, 4);
					Enchantment newEnchant2 = new EnchantmentWrapper(3);
					item.addUnsafeEnchantment(newEnchant2, 4);
					Enchantment newEnchant3 = new EnchantmentWrapper(4);
					item.addUnsafeEnchantment(newEnchant3, 4);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(0);
					item.addUnsafeEnchantment(newEnchant, 4);
				}
				return item;
			}else if (itemKind <= 0.83){//11% for chestplate
				item = new ItemStack(Material.DIAMOND_CHESTPLATE);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.RED+"Rare");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Runic Chestplate");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(0);
					item.addUnsafeEnchantment(newEnchant, 5);
					Enchantment newEnchant2 = new EnchantmentWrapper(7);
					item.addUnsafeEnchantment(newEnchant2, 4);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 3);
				}else if (enchants <= 0.85){
					Enchantment newEnchant = new EnchantmentWrapper(1);
					item.addUnsafeEnchantment(newEnchant, 4);
					Enchantment newEnchant2 = new EnchantmentWrapper(3);
					item.addUnsafeEnchantment(newEnchant2, 4);
					Enchantment newEnchant3 = new EnchantmentWrapper(4);
					item.addUnsafeEnchantment(newEnchant3, 4);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(0);
					item.addUnsafeEnchantment(newEnchant, 4);
					Enchantment newEnchant2 = new EnchantmentWrapper(2);
					item.addUnsafeEnchantment(newEnchant2, 3);
				}
				return item;
			}else if (itemKind <= 0.9){//7% for Helmet
				item = new ItemStack(Material.DIAMOND_HELMET);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.RED+"Rare");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Runic Helmet");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(0);
					item.addUnsafeEnchantment(newEnchant, 6);
					Enchantment newEnchant2 = new EnchantmentWrapper(5);
					item.addUnsafeEnchantment(newEnchant2, 4);
					Enchantment newEnchant3 = new EnchantmentWrapper(6);
					item.addUnsafeEnchantment(newEnchant3, 2);
					Enchantment newEnchant4 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant4, 3);
				}else if (enchants <= 0.85){
					Enchantment newEnchant = new EnchantmentWrapper(1);
					item.addUnsafeEnchantment(newEnchant, 4);
					Enchantment newEnchant2 = new EnchantmentWrapper(3);
					item.addUnsafeEnchantment(newEnchant2, 4);
					Enchantment newEnchant3 = new EnchantmentWrapper(4);
					item.addUnsafeEnchantment(newEnchant3, 4);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(0);
					item.addUnsafeEnchantment(newEnchant, 5);
					Enchantment newEnchant2 = new EnchantmentWrapper(5);
					item.addUnsafeEnchantment(newEnchant2, 3);
					Enchantment newEnchant3 = new EnchantmentWrapper(6);
					item.addUnsafeEnchantment(newEnchant3, 2);
				}
				return item;
			}else if (itemKind <= 1){//10% for battlesign
				item = new ItemStack(Material.SIGN);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.RED+"Rare");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Runic Zistonian Battle Sign");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(16);
					item.addUnsafeEnchantment(newEnchant, 3);
					Enchantment newEnchant2 = new EnchantmentWrapper(19);
					item.addUnsafeEnchantment(newEnchant2, 10);
					Enchantment newEnchant3 = new EnchantmentWrapper(20);
					item.addUnsafeEnchantment(newEnchant3, 2);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(16);
					item.addUnsafeEnchantment(newEnchant, 2);
					Enchantment newEnchant2 = new EnchantmentWrapper(19);
					item.addUnsafeEnchantment(newEnchant2, 10);
					Enchantment newEnchant3 = new EnchantmentWrapper(20);
					item.addUnsafeEnchantment(newEnchant3, 1);
				}
				return item;
			}
		}else if (level == "Common"){//White
			double itemKind = Math.random();
			if (itemKind <= 0.02){//2% for fishing rod
				item = new ItemStack(Material.FISHING_ROD);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.WHITE+"Common");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Common Fishing Rod");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(61);
					item.addUnsafeEnchantment(newEnchant, 2);
					Enchantment newEnchant2 = new EnchantmentWrapper(62);
					item.addUnsafeEnchantment(newEnchant2, 2);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 2);
				}else if (enchants <= 0.85){
					Enchantment newEnchant = new EnchantmentWrapper(61);
					item.addUnsafeEnchantment(newEnchant, 2);
					Enchantment newEnchant2 = new EnchantmentWrapper(62);
					item.addUnsafeEnchantment(newEnchant2, 1);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(61);
					item.addUnsafeEnchantment(newEnchant, 1);
					Enchantment newEnchant2 = new EnchantmentWrapper(62);
					item.addUnsafeEnchantment(newEnchant2, 2);
				}
				return item;
			}else if (itemKind <= 0.12){//10% for shovel
				item = new ItemStack(Material.IRON_SPADE);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.WHITE+"Common");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Common Shovel");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant, 3);
					Enchantment newEnchant2 = new EnchantmentWrapper(35);
					item.addUnsafeEnchantment(newEnchant2, 2);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 2);
				}else if (enchants <= 0.85){
					Enchantment newEnchant = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant, 2);
					Enchantment newEnchant2 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant2, 1);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(33);
					item.addUnsafeEnchantment(newEnchant, 1);
					Enchantment newEnchant2 = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant2, 2);
				}
				return item;
			}else if (itemKind <= 0.22){//10% for axe
				item = new ItemStack(Material.IRON_AXE);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.WHITE+"Common");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Common Axe");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant, 3);
					Enchantment newEnchant2 = new EnchantmentWrapper(35);
					item.addUnsafeEnchantment(newEnchant2, 2);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 2);
				}else if (enchants <= 0.85){
					Enchantment newEnchant = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant, 2);
					Enchantment newEnchant2 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant2, 1);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(33);
					item.addUnsafeEnchantment(newEnchant, 1);
					Enchantment newEnchant2 = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant2, 2);
				}
				return item;
			}else if (itemKind <= 0.37){//15% for pick
				item = new ItemStack(Material.IRON_PICKAXE);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.WHITE+"Common");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Common Pickaxe");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant, 3);
					Enchantment newEnchant2 = new EnchantmentWrapper(35);
					item.addUnsafeEnchantment(newEnchant2, 2);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 2);
				}else if (enchants <= 0.85){
					Enchantment newEnchant = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant, 2);
					Enchantment newEnchant2 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant2, 1);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(33);
					item.addUnsafeEnchantment(newEnchant, 1);
					Enchantment newEnchant2 = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant2, 2);
				}
				return item;
			}else if (itemKind <= 0.54){//17% for Sword
				item = new ItemStack(Material.IRON_SWORD);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.WHITE+"Common");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Common Sword");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(16);
					item.addUnsafeEnchantment(newEnchant, 3);
					Enchantment newEnchant2 = new EnchantmentWrapper(20);
					item.addUnsafeEnchantment(newEnchant2, 1);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 2);
				}else if (enchants <= 0.85){
					Enchantment newEnchant = new EnchantmentWrapper(17);
					item.addUnsafeEnchantment(newEnchant, 3);
					Enchantment newEnchant2 = new EnchantmentWrapper(18);
					item.addUnsafeEnchantment(newEnchant2, 3);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(16);
					item.addUnsafeEnchantment(newEnchant, 3);
					Enchantment newEnchant2 = new EnchantmentWrapper(21);
					item.addUnsafeEnchantment(newEnchant2, 2);
				}
				return item;
			}else if (itemKind <= 0.65){//11% for boots
				item = new ItemStack(Material.CHAINMAIL_BOOTS);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.WHITE+"Common");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Common Boots");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(0);
					item.addUnsafeEnchantment(newEnchant, 3);
					Enchantment newEnchant2 = new EnchantmentWrapper(2);
					item.addUnsafeEnchantment(newEnchant2, 3);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 2);
				}else if (enchants <= 0.85){
					Enchantment newEnchant = new EnchantmentWrapper(1);
					item.addUnsafeEnchantment(newEnchant, 2);
					Enchantment newEnchant2 = new EnchantmentWrapper(3);
					item.addUnsafeEnchantment(newEnchant2, 2);
					Enchantment newEnchant3 = new EnchantmentWrapper(4);
					item.addUnsafeEnchantment(newEnchant3, 2);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(0);
					item.addUnsafeEnchantment(newEnchant, 3);
					Enchantment newEnchant2 = new EnchantmentWrapper(2);
					item.addUnsafeEnchantment(newEnchant2, 2);
				}
				return item;
			}else if (itemKind <= 0.72){//7% for pants
				item = new ItemStack(Material.CHAINMAIL_LEGGINGS);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.WHITE+"Common");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Common Leggings");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(0);
					item.addUnsafeEnchantment(newEnchant, 3);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 2);
				}else if (enchants <= 0.85){
					Enchantment newEnchant = new EnchantmentWrapper(1);
					item.addUnsafeEnchantment(newEnchant, 2);
					Enchantment newEnchant2 = new EnchantmentWrapper(3);
					item.addUnsafeEnchantment(newEnchant2, 2);
					Enchantment newEnchant3 = new EnchantmentWrapper(4);
					item.addUnsafeEnchantment(newEnchant3, 2);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(0);
					item.addUnsafeEnchantment(newEnchant, 3);
				}
				return item;
			}else if (itemKind <= 0.83){//11% for chestplate
				item = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.WHITE+"Common");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Common Chestplate");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(0);
					item.addUnsafeEnchantment(newEnchant, 3);
					Enchantment newEnchant2 = new EnchantmentWrapper(7);
					item.addUnsafeEnchantment(newEnchant2, 2);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 2);
				}else if (enchants <= 0.85){
					Enchantment newEnchant = new EnchantmentWrapper(1);
					item.addUnsafeEnchantment(newEnchant, 2);
					Enchantment newEnchant2 = new EnchantmentWrapper(3);
					item.addUnsafeEnchantment(newEnchant2, 2);
					Enchantment newEnchant3 = new EnchantmentWrapper(4);
					item.addUnsafeEnchantment(newEnchant3, 2);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(0);
					item.addUnsafeEnchantment(newEnchant, 3);
					Enchantment newEnchant2 = new EnchantmentWrapper(7);
					item.addUnsafeEnchantment(newEnchant2, 2);
				}
				return item;
			}else if (itemKind <= 0.9){//7% for Helmet
				item = new ItemStack(Material.CHAINMAIL_HELMET);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.WHITE+"Common");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Common Helmet");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(0);
					item.addUnsafeEnchantment(newEnchant, 3);
					Enchantment newEnchant2 = new EnchantmentWrapper(5);
					item.addUnsafeEnchantment(newEnchant2, 2);
					Enchantment newEnchant3 = new EnchantmentWrapper(6);
					item.addUnsafeEnchantment(newEnchant3, 1);
					Enchantment newEnchant4 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant4, 2);
				}else if (enchants <= 0.85){
					Enchantment newEnchant = new EnchantmentWrapper(1);
					item.addUnsafeEnchantment(newEnchant, 2);
					Enchantment newEnchant2 = new EnchantmentWrapper(3);
					item.addUnsafeEnchantment(newEnchant2, 2);
					Enchantment newEnchant3 = new EnchantmentWrapper(4);
					item.addUnsafeEnchantment(newEnchant3, 2);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(0);
					item.addUnsafeEnchantment(newEnchant, 2);
					Enchantment newEnchant2 = new EnchantmentWrapper(5);
					item.addUnsafeEnchantment(newEnchant2, 2);
					Enchantment newEnchant3 = new EnchantmentWrapper(6);
					item.addUnsafeEnchantment(newEnchant3, 1);
				}
				return item;
			}else if (itemKind <= 1){//10% for battlesign
				item = new ItemStack(Material.SIGN);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.WHITE+"Common");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Common Zistonian Battle Sign");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(16);
					item.addUnsafeEnchantment(newEnchant, 2);
					Enchantment newEnchant2 = new EnchantmentWrapper(19);
					item.addUnsafeEnchantment(newEnchant2, 7);
					Enchantment newEnchant3 = new EnchantmentWrapper(20);
					item.addUnsafeEnchantment(newEnchant3, 1);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(16);
					item.addUnsafeEnchantment(newEnchant, 1);
					Enchantment newEnchant2 = new EnchantmentWrapper(19);
					item.addUnsafeEnchantment(newEnchant2, 5);
				}
				return item;
			}
		}else if (level == "Poor"){//Grey
			double itemKind = Math.random();
			if (itemKind <= 0.02){//2% for fishing rod
				item = new ItemStack(Material.FISHING_ROD);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.GRAY+"Poor");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				item.setItemMeta(meta);
				Enchantment newEnchant = new EnchantmentWrapper(61);
				item.addUnsafeEnchantment(newEnchant, 1);
				Enchantment newEnchant2 = new EnchantmentWrapper(34);
				item.addUnsafeEnchantment(newEnchant2, 1);
				return item;
			}else if (itemKind <= 0.12){//10% for shovel
				item = new ItemStack(Material.STONE_SPADE);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.GRAY+"Poor");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Poor Shovel");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant, 2);
					Enchantment newEnchant2 = new EnchantmentWrapper(35);
					item.addUnsafeEnchantment(newEnchant2, 1);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant, 1);
				}
				return item;
			}else if (itemKind <= 0.22){//10% for axe
				item = new ItemStack(Material.STONE_AXE);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.GRAY+"Poor");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Poor Axe");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant, 2);
					Enchantment newEnchant2 = new EnchantmentWrapper(35);
					item.addUnsafeEnchantment(newEnchant2, 1);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant, 1);
				}
				return item;
			}else if (itemKind <= 0.37){//15% for pick
				item = new ItemStack(Material.STONE_PICKAXE);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.GRAY+"Poor");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Poor Pickaxe");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant, 2);
					Enchantment newEnchant2 = new EnchantmentWrapper(35);
					item.addUnsafeEnchantment(newEnchant2, 1);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(32);
					item.addUnsafeEnchantment(newEnchant, 1);
				}
				return item;
			}else if (itemKind <= 0.54){//17% for Sword
				item = new ItemStack(Material.STONE_SWORD);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.GRAY+"Poor");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Poor Sword");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(16);
					item.addUnsafeEnchantment(newEnchant, 1);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 1);
				}else if (enchants <= 0.85){
					Enchantment newEnchant = new EnchantmentWrapper(17);
					item.addUnsafeEnchantment(newEnchant, 1);
					Enchantment newEnchant2 = new EnchantmentWrapper(18);
					item.addUnsafeEnchantment(newEnchant2, 1);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(16);
					item.addUnsafeEnchantment(newEnchant, 1);
					Enchantment newEnchant2 = new EnchantmentWrapper(21);
					item.addUnsafeEnchantment(newEnchant2, 1);
				}
				return item;
			}else if (itemKind <= 0.65){//11% for boots
				item = new ItemStack(Material.LEATHER_BOOTS);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.GRAY+"Poor");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Poor Boots");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(0);
					item.addUnsafeEnchantment(newEnchant, 1);
					Enchantment newEnchant2 = new EnchantmentWrapper(2);
					item.addUnsafeEnchantment(newEnchant2, 1);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 1);
				}else if (enchants <= 0.85){
					Enchantment newEnchant = new EnchantmentWrapper(1);
					item.addUnsafeEnchantment(newEnchant, 1);
					Enchantment newEnchant2 = new EnchantmentWrapper(3);
					item.addUnsafeEnchantment(newEnchant2, 1);
					Enchantment newEnchant3 = new EnchantmentWrapper(4);
					item.addUnsafeEnchantment(newEnchant3, 1);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(0);
					item.addUnsafeEnchantment(newEnchant, 1);
					Enchantment newEnchant2 = new EnchantmentWrapper(2);
					item.addUnsafeEnchantment(newEnchant2, 1);
				}
				return item;
			}else if (itemKind <= 0.72){//7% for pants
				item = new ItemStack(Material.LEATHER_LEGGINGS);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.GRAY+"Poor");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Poor Leggings");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(0);
					item.addUnsafeEnchantment(newEnchant, 1);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 1);
				}else if (enchants <= 0.85){
					Enchantment newEnchant = new EnchantmentWrapper(1);
					item.addUnsafeEnchantment(newEnchant, 1);
					Enchantment newEnchant2 = new EnchantmentWrapper(3);
					item.addUnsafeEnchantment(newEnchant2, 1);
					Enchantment newEnchant3 = new EnchantmentWrapper(4);
					item.addUnsafeEnchantment(newEnchant3, 1);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(0);
					item.addUnsafeEnchantment(newEnchant, 1);
				}
				return item;
			}else if (itemKind <= 0.83){//11% for chestplate
				item = new ItemStack(Material.LEATHER_CHESTPLATE);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.GRAY+"Poor");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Poor Boots");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(0);
					item.addUnsafeEnchantment(newEnchant, 1);
					Enchantment newEnchant2 = new EnchantmentWrapper(7);
					item.addUnsafeEnchantment(newEnchant2, 1);
					Enchantment newEnchant3 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant3, 1);
				}else if (enchants <= 0.85){
					Enchantment newEnchant = new EnchantmentWrapper(1);
					item.addUnsafeEnchantment(newEnchant, 1);
					Enchantment newEnchant2 = new EnchantmentWrapper(3);
					item.addUnsafeEnchantment(newEnchant2, 1);
					Enchantment newEnchant3 = new EnchantmentWrapper(4);
					item.addUnsafeEnchantment(newEnchant3, 1);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(0);
					item.addUnsafeEnchantment(newEnchant, 1);
					Enchantment newEnchant2 = new EnchantmentWrapper(7);
					item.addUnsafeEnchantment(newEnchant2, 1);
				}
				return item;
			}else if (itemKind <= 0.9){//7% for Helmet
				item = new ItemStack(Material.LEATHER_HELMET);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.GRAY+"Poor");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Poor Helmet");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(0);
					item.addUnsafeEnchantment(newEnchant, 1);
					Enchantment newEnchant2 = new EnchantmentWrapper(5);
					item.addUnsafeEnchantment(newEnchant2, 1);
					Enchantment newEnchant3 = new EnchantmentWrapper(6);
					item.addUnsafeEnchantment(newEnchant3, 1);
					Enchantment newEnchant4 = new EnchantmentWrapper(34);
					item.addUnsafeEnchantment(newEnchant4, 1);
				}else if (enchants <= 0.85){
					Enchantment newEnchant = new EnchantmentWrapper(1);
					item.addUnsafeEnchantment(newEnchant, 1);
					Enchantment newEnchant2 = new EnchantmentWrapper(3);
					item.addUnsafeEnchantment(newEnchant2, 1);
					Enchantment newEnchant3 = new EnchantmentWrapper(4);
					item.addUnsafeEnchantment(newEnchant3, 1);
				}else{
					Enchantment newEnchant = new EnchantmentWrapper(0);
					item.addUnsafeEnchantment(newEnchant, 1);
					Enchantment newEnchant2 = new EnchantmentWrapper(5);
					item.addUnsafeEnchantment(newEnchant2, 1);
					Enchantment newEnchant3 = new EnchantmentWrapper(6);
					item.addUnsafeEnchantment(newEnchant3, 1);
				}
				return item;
			}else if (itemKind <= 1){//10% for battlesign
				item = new ItemStack(Material.SIGN);
				List<String> lores = new ArrayList<String>();
				lores.add("Item Value: "+ChatColor.GRAY+"Poor");
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lores);
				meta.setDisplayName("Poor Zistonian Battle Sign");
				item.setItemMeta(meta);
				double enchants = Math.random();
				if (enchants <= 0.35){
					Enchantment newEnchant = new EnchantmentWrapper(16);
					item.addUnsafeEnchantment(newEnchant, 1);
					Enchantment newEnchant2 = new EnchantmentWrapper(19);
					item.addUnsafeEnchantment(newEnchant2, 3);
				}else{
					Enchantment newEnchant2 = new EnchantmentWrapper(19);
					item.addUnsafeEnchantment(newEnchant2, 1);
				}
				return item;
			}
		}
		return null;
	}
}