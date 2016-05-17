package fr.skybeastmc.hotbarswitcher.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import fr.skybeastmc.hotbarswitcher.Debug;
import fr.skybeastmc.hotbarswitcher.HotbarSwitcher;
import fr.skybeastmc.hotbarswitcher.Items;
import fr.skybeastmc.hotbarswitcher.Main;

public class InventoryClickListener implements Listener {
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		try {
			Debug.bc(event.getSlot());
			Debug.bc(event.getCursor().equals(Items.getHotbarSwitcher()));
			if(event.getCursor().getItemMeta() != null) Debug.bc(event.getCursor().getItemMeta().getDisplayName());
			else Debug.debug(null);
			Debug.bc(event.getCurrentItem().equals(Items.getHotbarSwitcher()));
			if(event.getCurrentItem().getItemMeta() != null) Debug.bc(event.getCurrentItem().getItemMeta().getDisplayName());
			else Debug.debug(null);
			if(event.getSlot() == 3 && event.getClick() != ClickType.MIDDLE) {
				event.setCancelled(true);
				HotbarSwitcher.switchHotbar((Player) event.getWhoClicked());
				fixItem(Items.getHotbarSwitcher(), event);
			} else if(event.getSlot() == 17 || event.getSlot() == 26 || event.getSlot() == 35) {
				event.setCancelled(true);
				fixItem(Items.getBarrier(), event);
			}
		} catch(Exception e) {Debug.error(e, "InventoryClick listener", false);}
	}
	
	public void fixItem(final ItemStack item, final InventoryClickEvent event) {
		Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable() {

			@Override
			public void run() {
				event.getView().setCursor(new ItemStack(Material.AIR));
				event.setCurrentItem(item);
				event.getWhoClicked().getInventory().setItem(8, Items.getHotbarSwitcher());
				event.getWhoClicked().getInventory().setItem(17, Items.getBarrier());
				event.getWhoClicked().getInventory().setItem(26, Items.getBarrier());
				event.getWhoClicked().getInventory().setItem(35, Items.getBarrier());
			}
			
		}, 3L);
	}
}
