package com.github.hae902.variousmessages;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedEnterEvent.BedEnterResult;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	public static FileConfiguration config;

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(new LoginMessage(), this);
		getServer().getPluginManager().registerEvents(new KillPlayer(), this);
		getServer().getPluginManager().registerEvents(new BreakBlock(), this);
		this.saveDefaultConfig();
		config = this.getConfig();
		config.options().copyDefaults(true);
	}
	@Override
	public void onDisable() {
		saveConfig();
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
