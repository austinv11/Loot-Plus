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
		mobDatas.addDefault("bat.xp", 0);
		mobDatas.addDefault("bat.probablility", "[0,]");
		mobDatas.addDefault("bat.drops", "[none,]");
		mobDatas.addDefault("blaze.xp", 0);
		mobDatas.addDefault("blaze.probablility", "[0,]");
		mobDatas.addDefault("blaze.drops", "[none,]");
		mobDatas.addDefault("caveSpider.xp", 0);
		mobDatas.addDefault("caveSpider.probablility", "[0,]");
		mobDatas.addDefault("caveSpider.drops", "[none,]");
		mobDatas.addDefault("chicken.xp", 0);
		mobDatas.addDefault("chicken.probablility", "[0,]");
		mobDatas.addDefault("chicken.drops", "[none,]");
		mobDatas.addDefault("cow.xp", 0);
		mobDatas.addDefault("cow.probablility", "[0,]");
		mobDatas.addDefault("cow.drops", "[none,]");
		mobDatas.addDefault("creeper.xp", 0);
		mobDatas.addDefault("creeper.probablility", "[0,]");
		mobDatas.addDefault("creeper.drops", "[none,]");
		mobDatas.addDefault("enderDragon.xp", 0);
		mobDatas.addDefault("enderDragon.probablility", "[0,]");
		mobDatas.addDefault("enderDragon.drops", "[none,]");
		mobDatas.addDefault("enderman.xp", 0);
		mobDatas.addDefault("enderman.probablility", "[0,]");
		mobDatas.addDefault("enderman.drops", "[none,]");
		mobDatas.addDefault("ghast.xp", 0);
		mobDatas.addDefault("ghast.probablility", "[0,]");
		mobDatas.addDefault("ghast.drops", "[none,]");
		mobDatas.addDefault("giant.xp", 0);
		mobDatas.addDefault("giant.probablility", "[0,]");
		mobDatas.addDefault("giant.drops", "[none,]");
		mobDatas.addDefault("horse.xp", 0);
		mobDatas.addDefault("horse.probablility", "[0,]");
		mobDatas.addDefault("horse.drops", "[none,]");
		mobDatas.addDefault("ironGolem.xp", 0);
		mobDatas.addDefault("ironGolem.probablility", "[0,]");
		mobDatas.addDefault("ironGolem.drops", "[none,]");
		mobDatas.addDefault("magmaCube.xp", 0);
		mobDatas.addDefault("magmaCube.probablility", "[0,]");
		mobDatas.addDefault("magmaCube.drops", "[none,]");
		mobDatas.addDefault("mooshroom.xp", 0);
		mobDatas.addDefault("mooshroom.probablility", "[0,]");
		mobDatas.addDefault("mooshroom.drops", "[none,]");
		mobDatas.addDefault("ocelot.xp", 0);
		mobDatas.addDefault("ocelot.probablility", "[0,]");
		mobDatas.addDefault("ocelot.drops", "[none,]");
		mobDatas.addDefault("pig.xp", 0);
		mobDatas.addDefault("pig.probablility", "[0,]");
		mobDatas.addDefault("pig.drops", "[none,]");
		mobDatas.addDefault("zombiePigman.xp", 0);
		mobDatas.addDefault("zombiePigman.probablility", "[0,]");
		mobDatas.addDefault("zombiePigman.drops", "[none,]");
		mobDatas.addDefault("player.xp", 0);
		mobDatas.addDefault("player.probablility", "[0,]");
		mobDatas.addDefault("player.drops", "[none,]");
		mobDatas.addDefault("sheep.xp", 0);
		mobDatas.addDefault("sheep.probablility", "[0,]");
		mobDatas.addDefault("sheep.drops", "[none,]");
		mobDatas.addDefault("silverfish.xp", 0);
		mobDatas.addDefault("silverfish.probablility", "[0,]");
		mobDatas.addDefault("silverfish.drops", "[none,]");
		mobDatas.addDefault("skeleton.xp", 0);
		mobDatas.addDefault("skeleton.probablility", "[0,]");
		mobDatas.addDefault("skeleton.drops", "[none,]");
		mobDatas.addDefault("slime.xp", 0);
		mobDatas.addDefault("slime.probablility", "[0,]");
		mobDatas.addDefault("slime.drops", "[none,]");
		mobDatas.addDefault("snowGolem.xp", 0);
		mobDatas.addDefault("snowGolem.probablility", "[0,]");
		mobDatas.addDefault("snowGolem.drops", "[none,]");
		mobDatas.addDefault("spider.xp", 0);
		mobDatas.addDefault("spider.probablility", "[0,]");
		mobDatas.addDefault("spider.drops", "[none,]");
		mobDatas.addDefault("squid.xp", 0);
		mobDatas.addDefault("squid.probablility", "[0,]");
		mobDatas.addDefault("squid.drops", "[none,]");
		mobDatas.addDefault("villager.xp", 0);
		mobDatas.addDefault("villager.probablility", "[0,]");
		mobDatas.addDefault("villager.drops", "[none,]");
		mobDatas.addDefault("witch.xp", 0);
		mobDatas.addDefault("witch.probablility", "[0,]");
		mobDatas.addDefault("witch.drops", "[none,]");
		mobDatas.addDefault("wither.xp", 0);
		mobDatas.addDefault("wither.probablility", "[0,]");
		mobDatas.addDefault("wither.drops", "[none,]");
		mobDatas.addDefault("wolf.xp", 0);
		mobDatas.addDefault("wolf.probablility", "[0,]");
		mobDatas.addDefault("wolf.drops", "[none,]");
		mobDatas.addDefault("zombie.xp", 0);
		mobDatas.addDefault("zombie.probablility", "[0,]");
		mobDatas.addDefault("zombie.drops", "[none,]");
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
