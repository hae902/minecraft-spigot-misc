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
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(new KillPlayer(), this);
	}

	@EventHandler
	public void Login(PlayerJoinEvent event) {
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
	int playerInBed = 0;
	@EventHandler
	public void bed(PlayerBedEnterEvent event) {
		Player player = event.getPlayer();
		String name = player.getDisplayName();
		if (event.getBedEnterResult() != BedEnterResult.OK) return;
		playerInBed++;
		if (Bukkit.getOnlinePlayers().size() != 1) {
			Bukkit.broadcastMessage(ChatColor.YELLOW + name + "が 寝ました。");
		}

		if (Bukkit.getOnlinePlayers().size() / 2 <= playerInBed) {
			Bukkit.broadcastMessage(ChatColor.GOLD + "朝になりました。");
			player.getWorld().setTime(1000);
			player.getWorld().setStorm(false);
			player.getWorld().setThundering(false);
		}else {
			Bukkit.broadcastMessage(ChatColor.YELLOW + "あと" + ((Bukkit.getOnlinePlayers().size() / 2) - playerInBed) + "人寝ると朝になります。");
		}
	}

	@EventHandler
	public void BedLeave(PlayerBedLeaveEvent event) {
		playerInBed--;
	}
}
