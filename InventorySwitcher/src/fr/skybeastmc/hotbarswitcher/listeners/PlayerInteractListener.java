package fr.skybeastmc.hotbarswitcher.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.skybeastmc.hotbarswitcher.Debug;
import fr.skybeastmc.hotbarswitcher.HotbarSwitcher;
import fr.skybeastmc.hotbarswitcher.Items;

public class PlayerInteractListener implements Listener {
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		try {
			if(event.getItem() != null && event.getItem().equals(Items.getHotbarSwitcher())) {
				event.setCancelled(true);
				HotbarSwitcher.switchHotbar(event.getPlayer());
			}
		} catch(Exception e) {Debug.error(e, "PlayerInteract listener", false);}
	}
}
