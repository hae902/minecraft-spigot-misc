package com.github.hae902.variousmessages;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class FirstDescription extends BukkitRunnable{
	Player player;
	int secondCount = 0;
	public FirstDescription(Player player){
		this.player = player;
	}

	void chat(String sender, Player player, String boby) {
		player.sendMessage(ChatColor.YELLOW + "[" + sender + "] " + boby);
		player.getWorld().playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1f);
	}

	String sender = "ERROR!!";
	String boby = "ERROR!!";
	@Override
	public void run() {
		boolean isChat = true;
		switch (secondCount) {
		case 0:
			sender = "システム";
			boby = "サーバーへようこそ！！";
			break;
		case 3:
			boby = "みんなで楽しくやっていきましょう！！";
			cancel();
			break;
		default:
			isChat = false;
			break;
		}
		if (isChat) {
			chat(sender, player, boby);
		}
		secondCount++;
	}

}