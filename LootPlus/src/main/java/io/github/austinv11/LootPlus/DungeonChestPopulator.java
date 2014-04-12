package io.github.austinv11.LootPlus;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class DungeonChestPopulator extends BlockPopulator{
	ConfigClass cc = new ConfigClass();
	public void populate(World world, Random random, Chunk source){
		if (cc.getDefaultConfig().getBoolean("Features.extraDungeonLoot") == true){
			//TODO
		}
	}
}
