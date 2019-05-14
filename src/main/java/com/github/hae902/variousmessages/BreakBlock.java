package com.github.hae902.variousmessages;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakBlock implements Listener{
	@EventHandler
	public void block(BlockBreakEvent event) {
		FileConfiguration config = Main.config;
		Player player = event.getPlayer();
		String loca = "UserConfig." + player.getUniqueId().toString() + ".smallEXP";
		int point = config.getInt(loca);
		switch (event.getBlock().getType()) {
		case DIRT:
		case GRASS_BLOCK:
			config.set(loca, point + 1);
			break;
		default:
			break;
		}
		point = config.getInt(loca);
		if (point >= 10) {
			player.giveExp((int) Math.floor(point / 10));
			config.set(loca, point % 10);
		}
	}
}