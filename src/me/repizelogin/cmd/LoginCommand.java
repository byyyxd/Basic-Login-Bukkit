package me.repizelogin.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.repizelogin.LoginAPI;

public class LoginCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if(sender instanceof Player) {
			if(cmd.getName().equalsIgnoreCase("login")) {
				Player p = (Player)sender;
				if(args.length == 0) {
					p.sendMessage("§a§lVoke§f§lPvP §cUtilize /login <senha>");
					return true;
				} else {
					if(!LoginAPI.estaLogado(p)) {
						String senha = args[0];
						
						if(senha.equals(LoginAPI.getSenha(p))) {
							LoginAPI.logar(p);
							p.sendMessage("§a§lVoke§f§lPvP §aLogado com sucesso.");
							return true;
						} else {
							p.kickPlayer("§a§lVoke§f§lPvP\n\n§cVoce errou sua senha, tente novamente.");
							return true;
						}
					} else {
						p.sendMessage("§a§lVoke§f§lPvP §cVoce ja esta logado");
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
