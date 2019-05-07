package com.github.hae902.variousmessages;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedEnterEvent.BedEnterResult;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public void Login(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String name = player.getDisplayName();
		if (!player.hasPlayedBefore()) {
			player.teleport(new Location(player.getWorld(), 179.5, 64, 79.5));
			event.setJoinMessage(ChatColor.YELLOW + name + "が 初めてサーバーにログインしました");
			player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
		}else {
			event.setJoinMessage(ChatColor.YELLOW + name + "が ログインしました。");
			player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1.1f);

		}
	}
	@EventHandler
	public void bed(PlayerBedEnterEvent event) {
		Player player = event.getPlayer();
		String name = player.getDisplayName();
		if (event.getBedEnterResult() != BedEnterResult.OK) return;
		Bukkit.broadcastMessage(name + "が 寝ました。");
	}
}
