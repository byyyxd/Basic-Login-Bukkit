package me.repizelogin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.repizelogin.cmd.LoginCommand;
import me.repizelogin.cmd.RegisterCommand;
import me.repizelogin.events.PlayerListener;
import me.repizelogin.mysql.Conexao;

public class Main extends JavaPlugin {
	
	public static Main instance;
	public static Main getInstance() {
		return instance;
	}
	
	public static void setInstance(Main instance) {
		Main.instance = instance;
	}
	
	@Override
	public void onEnable() {
		setInstance(this);
		saveDefaultConfig();
		Bukkit.getConsoleSender().sendMessage("§a[REPIZE] Logins");
		Conexao.open();
		
		getCommand("login").setExecutor(new LoginCommand());
		getCommand("register").setExecutor(new RegisterCommand());
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("§c[REPIZE] Logins");
	}
	
}
