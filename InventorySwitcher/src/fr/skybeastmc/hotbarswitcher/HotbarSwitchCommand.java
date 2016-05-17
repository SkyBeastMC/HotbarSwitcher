package fr.skybeastmc.hotbarswitcher;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HotbarSwitchCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		try {
			HotbarSwitcher.switchHotbar((Player) sender);
			return true;
		} catch(Exception e) {Debug.error(e, "Switch command", false);}
		return false;
	}

}
