package me.repizelogin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class LoginAPI {
	
	private static Main login;
	
	static {
		login = Main.getInstance();
	}
	
	private static List<Player> jogadoresLogados = new ArrayList<>();

	
	public static boolean estaLogado(Player p) {
		return jogadoresLogados.contains(p);
	}
	
	public static void logar(Player p) {
		jogadoresLogados.add(p);
	}
	
	public static void deslogar(Player p) {
		jogadoresLogados.remove(p);
	}
	
	public static List<Player> getJogadoresLogados() {
		return jogadoresLogados;
	}

	public static boolean estaRegistrado(Player p) {
		return login.getConfig().contains(p.getName().toLowerCase());
	}
	
	public static String getSenha(Player p) {
		return login.getConfig().getString(p.getName().toLowerCase());
	}
	
	public static void setJogadoresLogados(List<Player> jogadoresLogados) {
		LoginAPI.jogadoresLogados = jogadoresLogados;
	}
	
	public static void desregistrar(String conta) {
		login.getConfig().set(conta.toLowerCase(), null);
		login.saveConfig();
	}
	
	public static void registrar(Player p, String senha) {
		login.getConfig().set(p.getName().toLowerCase(), senha);
		login.saveConfig();
	}
	
}
