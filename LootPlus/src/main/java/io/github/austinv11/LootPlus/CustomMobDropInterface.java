package io.github.austinv11.LootPlus;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class CustomMobDropInterface implements Listener{
	FileConfiguration config = Bukkit.getPluginManager().getPlugin("LootPlus").getConfig();
	File mobData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomLoot//mobLoot.yml");
	FileConfiguration mobDatas = YamlConfiguration.loadConfiguration(mobData);

	public CustomMobDropInterface(LootPlus plugin){
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		mobInit();
	}
	public void mobInit(){
		mobDatas.addDefault("BAT.xp", 0);
		mobDatas.addDefault("BAT.probability", "[0,]");
		mobDatas.addDefault("BAT.drops", "[none,]");
		mobDatas.addDefault("BLAZE.xp", 0);
		mobDatas.addDefault("BLAZE.probability", "[0,]");
		mobDatas.addDefault("BLAZE.drops", "[none,]");
		mobDatas.addDefault("CAVE_SPIDER.xp", 0);
		mobDatas.addDefault("CAVE_SPIDER.probability", "[0,]");
		mobDatas.addDefault("CAVE_SPIDER.drops", "[none,]");
		mobDatas.addDefault("CHICKEN.xp", 0);
		mobDatas.addDefault("CHICKEN.probability", "[0,]");
		mobDatas.addDefault("CHICKEN.drops", "[none,]");
		mobDatas.addDefault("COW.xp", 0);
		mobDatas.addDefault("COW.probability", "[0,]");
		mobDatas.addDefault("COW.drops", "[none,]");
		mobDatas.addDefault("CREEPER.xp", 0);
		mobDatas.addDefault("CREEPER.probability", "[0,]");
		mobDatas.addDefault("CREEPER.drops", "[none,]");
		mobDatas.addDefault("ENDER_DRAGON.xp", 0);
		mobDatas.addDefault("ENDER_DRAGON.probability", "[0,]");
		mobDatas.addDefault("ENDER_DRAGON.drops", "[none,]");
		mobDatas.addDefault("ENDERMAN.xp", 0);
		mobDatas.addDefault("ENDERMAN.probability", "[0,]");
		mobDatas.addDefault("ENDERMAN.drops", "[none,]");
		mobDatas.addDefault("GHAST.xp", 0);
		mobDatas.addDefault("GHAST.probability", "[0,]");
		mobDatas.addDefault("GHAST.drops", "[none,]");
		mobDatas.addDefault("GIANT.xp", 0);
		mobDatas.addDefault("GIANT.probability", "[0,]");
		mobDatas.addDefault("GIANT.drops", "[none,]");
		mobDatas.addDefault("HORSE.xp", 0);
		mobDatas.addDefault("HORSE.probability", "[0,]");
		mobDatas.addDefault("HORSE.drops", "[none,]");
		mobDatas.addDefault("IRON_GOLEM.xp", 0);
		mobDatas.addDefault("IRON_GOLEM.probability", "[0,]");
		mobDatas.addDefault("IRON_GOLEM.drops", "[none,]");
		mobDatas.addDefault("MAGMA_CUBE.xp", 0);
		mobDatas.addDefault("MAGMA_CUBE.probability", "[0,]");
		mobDatas.addDefault("MAGMA_CUBE.drops", "[none,]");
		mobDatas.addDefault("MUSHROOM_COW.xp", 0);
		mobDatas.addDefault("MUSHROOM_COW.probability", "[0,]");
		mobDatas.addDefault("MUSHROOM_COW.drops", "[none,]");
		mobDatas.addDefault("OCELOT.xp", 0);
		mobDatas.addDefault("OCELOT.probability", "[0,]");
		mobDatas.addDefault("OCELOT.drops", "[none,]");
		mobDatas.addDefault("PIG.xp", 0);
		mobDatas.addDefault("PIG.probability", "[0,]");
		mobDatas.addDefault("PIG.drops", "[none,]");
		mobDatas.addDefault("PIG_ZOMBIE.xp", 0);
		mobDatas.addDefault("PIG_ZOMBIE.probability", "[0,]");
		mobDatas.addDefault("PIG_ZOMBIE.drops", "[none,]");
		mobDatas.addDefault("PLAYER.xp", 0);
		mobDatas.addDefault("PLAYER.probability", "[0,]");
		mobDatas.addDefault("PLAYER.drops", "[none,]");
		mobDatas.addDefault("SHEEP.xp", 0);
		mobDatas.addDefault("SHEEP.probability", "[0,]");
		mobDatas.addDefault("SHEEP.drops", "[none,]");
		mobDatas.addDefault("SILVERFISH.xp", 0);
		mobDatas.addDefault("SILVERFISH.probability", "[0,]");
		mobDatas.addDefault("SILVERFISH.drops", "[none,]");
		mobDatas.addDefault("SKELETON.xp", 0);
		mobDatas.addDefault("SKELETON.probability", "[0,]");
		mobDatas.addDefault("SKELETON.drops", "[none,]");
		mobDatas.addDefault("SLIME.xp", 0);
		mobDatas.addDefault("SLIME.probability", "[0,]");
		mobDatas.addDefault("SLIME.drops", "[none,]");
		mobDatas.addDefault("SNOWMAN.xp", 0);
		mobDatas.addDefault("SNOWMAN.probability", "[0,]");
		mobDatas.addDefault("SNOWMAN.drops", "[none,]");
		mobDatas.addDefault("SPIDER.xp", 0);
		mobDatas.addDefault("SPIDER.probability", "[0,]");
		mobDatas.addDefault("SPIDER.drops", "[none,]");
		mobDatas.addDefault("SQUID.xp", 0);
		mobDatas.addDefault("SQUID.probability", "[0,]");
		mobDatas.addDefault("SQUID.drops", "[none,]");
		mobDatas.addDefault("VILLAGER.xp", 0);
		mobDatas.addDefault("VILLAGER.probability", "[0,]");
		mobDatas.addDefault("VILLAGER.drops", "[none,]");
		mobDatas.addDefault("WITCH.xp", 0);
		mobDatas.addDefault("WITCH.probability", "[0,]");
		mobDatas.addDefault("WITCH.drops", "[none,]");
		mobDatas.addDefault("WITHER.xp", 0);
		mobDatas.addDefault("WITHER.probability", "[0,]");
		mobDatas.addDefault("WITHER.drops", "[none,]");
		mobDatas.addDefault("WOLF.xp", 0);
		mobDatas.addDefault("WOLF.probability", "[0,]");
		mobDatas.addDefault("WOLF.drops", "[none,]");
		mobDatas.addDefault("ZOMBIE.xp", 0);
		mobDatas.addDefault("ZOMBIE.probability", "[0,]");
		mobDatas.addDefault("ZOMBIE.drops", "[none,]");
		mobDatas.options().copyDefaults(true);
		save();
	}
	public String[] readTable(String table){
		table = table.replace("[","").replace("]", "");
		table = table.replace(" ","");
		return table.split(",");
	}
	private void save(){
		try{
			mobDatas.save(mobData);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDeath(EntityDeathEvent event){
		if (mobDatas.getInt(event.getEntityType().toString()+".xp") == 0 && config.getBoolean("Options.allowCustomXP") == true){
			event.setDroppedExp(0);
		}else if (config.getBoolean("Options.allowCustomXP") == true){
			event.setDroppedExp(mobDatas.getInt(event.getEntityType().toString()+".xp"));//Overrides the disableXPDrops config
		}
		if (config.getBoolean("Options.allowCustomDrops") == true){
			String[] probability = readTable(mobDatas.getString(event.getEntityType().toString()+".probability"));
			String[] drop = readTable(mobDatas.getString(event.getEntityType().toString()+".drops"));
			if (probability[0] != "0" && drop[0] != "none"){
				if (probability.length == drop.length){
					for (int i = 0; i < drop.length; i++){
						double loot = Math.random();
						double chance = (Double.parseDouble(probability[i]) / 100);
						if (loot <= chance){
							Location loc = event.getEntity().getLocation().clone();
							Material lookup = Material.getMaterial(drop[i].toUpperCase());
							if (lookup != null){
								ItemStack lootDrop = new ItemStack(lookup);//TODO add checks
								Item item = loc.getWorld().dropItemNaturally(loc, lootDrop);
								item.setItemStack(lootDrop);
							}else{
								Bukkit.getLogger().info("Error: Material '"+drop[i]+"' does not exist, please make sure it's a valid material");
							}
						}
					}
				}else {
					Bukkit.getLogger().info("Error: Custom drop(s) for mob: '"+event.getEntityType().toString()+"' is invalid");
				}
			}
		}
	}
}
