package io.github.austinv11.LootPlus;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;

public class CustomMobDropInterface implements Listener{
	FileConfiguration config = Bukkit.getPluginManager().getPlugin("LootPlus").getConfig();
	File batData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomDrops//bat.yml");
	FileConfiguration batDatas = YamlConfiguration.loadConfiguration(batData);
	
	File blazeData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomDrops//blaze.yml");
	FileConfiguration blazeDatas = YamlConfiguration.loadConfiguration(blazeData);
	
	File caveSpiderData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomDrops//caveSpider.yml");
	FileConfiguration caveSpiderDatas = YamlConfiguration.loadConfiguration(caveSpiderData);
	
	File chickenData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomDrops//chicken.yml");
	FileConfiguration chickenDatas = YamlConfiguration.loadConfiguration(chickenData);
	
	File cowData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomDrops//cow.yml");
	FileConfiguration cowDatas = YamlConfiguration.loadConfiguration(cowData);
	
	File creeperData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomDrops//creeper.yml");
	FileConfiguration creeperDatas = YamlConfiguration.loadConfiguration(creeperData);
	
	File enderDragonData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomDrops//enderDragon.yml");
	FileConfiguration enderDragonDatas = YamlConfiguration.loadConfiguration(enderDragonData);
	
	File endermanData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomDrops//enderman.yml");
	FileConfiguration endermanDatas = YamlConfiguration.loadConfiguration(endermanData);
	
	File ghastData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomDrops//ghast.yml");
	FileConfiguration ghastDatas = YamlConfiguration.loadConfiguration(ghastData);
	
	File giantData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomDrops//giant.yml");
	FileConfiguration giantDatas = YamlConfiguration.loadConfiguration(giantData);
	
	File horseData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomDrops//horse.yml");
	FileConfiguration horseDatas = YamlConfiguration.loadConfiguration(horseData);
	
	File ironGolemData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomDrops//ironGolem.yml");
	FileConfiguration ironGolemDatas = YamlConfiguration.loadConfiguration(ironGolemData);
	
	File magmaCubeData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomDrops//magmaCube.yml");
	FileConfiguration magmaCubeDatas = YamlConfiguration.loadConfiguration(magmaCubeData);
	
	File mooshroomData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomDrops//mooshroom.yml");
	FileConfiguration mooshroomDatas = YamlConfiguration.loadConfiguration(mooshroomData);
	
	File ocelotData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomDrops//ocelot.yml");
	FileConfiguration ocelotDatas = YamlConfiguration.loadConfiguration(ocelotData);
	
	File pigData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomDrops//pig.yml");
	FileConfiguration pigDatas = YamlConfiguration.loadConfiguration(pigData);
	
	File zombiePigmanData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomDrops//zombiePigman.yml");
	FileConfiguration zombiePigmanDatas = YamlConfiguration.loadConfiguration(zombiePigmanData);
	
	File playerData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomDrops//player.yml");
	FileConfiguration playerDatas = YamlConfiguration.loadConfiguration(playerData);
	
	File sheepData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomDrops//sheep.yml");
	FileConfiguration sheepDatas = YamlConfiguration.loadConfiguration(sheepData);
	
	File silverfishData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomDrops//silverfish.yml");
	FileConfiguration silverfishDatas = YamlConfiguration.loadConfiguration(silverfishData);
	
	File skeletonData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomDrops//skeleton.yml");
	FileConfiguration skeletonDatas = YamlConfiguration.loadConfiguration(skeletonData);
	
	File slimeData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomDrops//slime.yml");
	FileConfiguration slimeDatas = YamlConfiguration.loadConfiguration(slimeData);
	
	File snowGolemData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomDrops//snowGolem.yml");
	FileConfiguration snowGolemDatas = YamlConfiguration.loadConfiguration(snowGolemData);
	
	File spiderData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomDrops//spider.yml");
	FileConfiguration spiderDatas = YamlConfiguration.loadConfiguration(spiderData);
	
	File squidData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomDrops//squid.yml");
	FileConfiguration squidDatas = YamlConfiguration.loadConfiguration(squidData);
	
	File villagerData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomDrops//villager.yml");
	FileConfiguration villagerDatas = YamlConfiguration.loadConfiguration(villagerData);
	
	File witchData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomDrops//witch.yml");
	FileConfiguration witchDatas = YamlConfiguration.loadConfiguration(witchData);
	
	File witherData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomDrops//wither.yml");
	FileConfiguration witherDatas = YamlConfiguration.loadConfiguration(witherData);
	
	File wolfData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomDrops//wolf.yml");
	FileConfiguration wolfDatas = YamlConfiguration.loadConfiguration(wolfData);
	
	File zombieData = new File(Bukkit.getPluginManager().getPlugin("LootPlus").getDataFolder(), "CustomDrops//zombie.yml");
	FileConfiguration zombieDatas = YamlConfiguration.loadConfiguration(zombieData);
	//Phew that's a lot of mobs
	public CustomMobDropInterface(LootPlus plugin){
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		ymlInit();
	}
	public void ymlInit(){
		
	}
}
