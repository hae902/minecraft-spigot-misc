package com.github.hae902.variousmessages;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class LoginMessage implements Listener{
	@EventHandler
	public void login(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String name = player.getDisplayName();
		if (!player.hasPlayedBefore()) {
			player.teleport(new Location(player.getWorld(), 179.5, 64, 79.5));
			Bukkit.broadcastMessage(ChatColor.YELLOW + name + "が なかまになりたそうに こちらを見ている！");
			event.setJoinMessage("なかまにしますか？ → はい");

			player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
		}else {
			event.setJoinMessage(ChatColor.YELLOW + name + "が あらわれた！");
			player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1.1f);
		}
	}
	@EventHandler
	public void logout (PlayerQuitEvent event) {
		Player player = event.getPlayer();
		String name = player.getDisplayName();
		Bukkit.broadcastMessage(ChatColor.YELLOW + name  + " は にげだした！");
	}
}
