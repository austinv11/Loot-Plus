package io.github.austinv11.LootPlus;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class DungeonPopulator extends BlockPopulator{
	LootPlus cc = new LootPlus();
	public void populate(World world, Random random, Chunk source){
		if (cc.getDefaultConfig().getBoolean("Features.extraDungeons") == true){
			//TODO
		}
	}
}
