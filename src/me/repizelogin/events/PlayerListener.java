package me.repizelogin.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.repizelogin.LoginAPI;
import me.repizelogin.Main;

public class PlayerListener implements Listener {
	
	@EventHandler
	private void aoEntrar(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(!LoginAPI.estaRegistrado(p)) {
			p.sendMessage("§a§lVoke§f§lPvP §eVoce precisa se registrar, utilize /register <senha> <confirmarsenha>");
		} else {
			p.sendMessage("§a§lVoke§f§lPvP §eVoce precisa se logar, utilize /login <senha>");
		}
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if(!LoginAPI.estaLogado(p)) {
					p.kickPlayer("§a§lVoke§f§lPvP\n\n§cVoce demorou para se logar, tente novamente.");
				}
			}
		}.runTaskLater(Main.getInstance(), 20*15);
	}
	
	@EventHandler
	private void naoMover(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if(!LoginAPI.estaLogado(p)) {
			p.teleport(e.getFrom());
		}
	}
	
	@EventHandler
	private void naolevarDano(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if(!LoginAPI.estaLogado(p)) {
				e.setCancelled(true);
			}
		}
	}
	
}
