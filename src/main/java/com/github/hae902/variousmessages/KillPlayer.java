package com.github.hae902.variousmessages;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class KillPlayer implements Listener {

	@EventHandler
	public void death(EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player)) return;
		Entity damager = event.getDamager();
		Player killed = (Player) event.getEntity();

		Player killer = null;
		if (damager instanceof Player) {
			killer = (Player) damager;
		}else if (damager instanceof Arrow) {

			//ダメージ元が矢だった場合矢を発射したエンティティがプレイヤーかどうか判定する。
			Arrow arrow = (Arrow) damager;
			if (arrow.getShooter() instanceof Player) {
				killer = (Player) arrow.getShooter();
			}else {
				return;
			}
		}else {
			return;
		}

		if ((killed.getHealth() - event.getDamage()) <= 0) {
			Bukkit.broadcastMessage(killed.getDisplayName() + " は殺害されそうになったが " + killer.getDisplayName() + " にも慈悲はあった...。");
			event.setCancelled(true);
		}
	}
}