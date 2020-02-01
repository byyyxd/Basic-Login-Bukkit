package me.repizelogin.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.repizelogin.LoginAPI;
import me.repizelogin.mysql.PlayerAPI;

public class RegisterCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if (sender instanceof Player) {
			if (cmd.getName().equalsIgnoreCase("register")) {
				Player p = (Player) sender;
				if (args.length == 0) {
					p.sendMessage("§a§lVoke§f§lPvP §cUtilize /register <senha> <confirmarsenha>");
					return true;
				} else {
					if (!LoginAPI.estaRegistrado(p)) {
						String senhaInicial = args[0];
						if (args.length < 2) {
							p.sendMessage("§a§lVoke§f§lPvP §cDigite a confirmacao de senha.");
							return true;
						} else {
							String confirmarSenha = args[1];
							if (senhaInicial.equals(confirmarSenha)) {
								LoginAPI.registrar(p, confirmarSenha);
								p.sendMessage("§a§lVoke§f§lPvP §aVoce se logou com sucesso.");
								LoginAPI.logar(p);
								if(!PlayerAPI.contains(p)) {
									PlayerAPI.addPlayer(p);
									PlayerAPI.setSenha(p, confirmarSenha);
								} else {
									PlayerAPI.setSenha(p, confirmarSenha);
								}
								return true;
							} else {
								p.sendMessage("§a§lVoke§f§lPvP §cAs senhas nao sao iguais.");
								return true;
							}
						}
					} else {
						p.sendMessage("§a§lVoke§f§lPvP §cVoce ja esta registrado.");
						return true;
					}
				}
			}
		} else {
			return true;
		}
		return false;
	}

}
