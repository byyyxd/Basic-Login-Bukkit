package me.repizelogin.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerAPI extends Conexao {

	// §

	public static boolean contains(Player p) {
		PreparedStatement stm = null;
		
		try {
			stm = con.prepareStatement("select * from logins where UUID = '" + p.getUniqueId().toString() + "'");
			ResultSet rs = stm.executeQuery();
			if(rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public static void addPlayer(Player p) {
		PreparedStatement stm = null;

		try {
			stm = con.prepareStatement(
					"insert into logins(UUID, senha) values ('" + p.getUniqueId().toString() + "', 'Nenhuma definida'");
			Bukkit.getConsoleSender().sendMessage(
					"§a§lVoke§f§lPvP §fO jogador §c" + p.getName() + " §ffoi registrado no local 'logins'");
			stm.executeUpdate();
		} catch (SQLException e) {
			Bukkit.getConsoleSender().sendMessage(
					"§a§lVoke§f§lPvP §cNao foi possivel adicionar o jogador §a" + p.getName() + " §cno local 'logins'");
		}
	}

	public static String getSenha(Player p) {
		PreparedStatement stm = null;

		try {
			stm = con.prepareStatement("select * from logins where UUID = '" + p.getUniqueId().toString() + "'");
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				return rs.getString("senha");
			}
			return null;
		} catch (SQLException e) {
			return null;
		}
	}

	public static void setSenha(Player p, String pass) {
		PreparedStatement stm = null;

		try {
			stm = con.prepareStatement(
					"update logins set senha = '" + pass + "' where UUID = '" + p.getUniqueId().toString() + "'");
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
