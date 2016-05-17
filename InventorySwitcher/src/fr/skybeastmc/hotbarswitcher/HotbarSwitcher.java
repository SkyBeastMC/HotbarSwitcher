package fr.skybeastmc.hotbarswitcher;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class HotbarSwitcher {
	
	public static void switchHotbar(Player player) {
		Inventory inventory = player.getInventory();
		Map<Integer, ItemStack> oldInventory = inventoryToMap(inventory);
		for(int i = 0; i < 8; i++) {
			inventory.setItem(i+27, oldInventory.get(i));
		}
		for(int i = 27; i < 35; i++) {
			inventory.setItem(i-9, oldInventory.get(i));
		}
		for(int i = 18; i < 26; i++) {
			inventory.setItem(i-9, oldInventory.get(i));
		}
		for(int i = 9; i < 17; i++) {
			inventory.setItem(i-9, oldInventory.get(i));
		}
		inventory.setItem(8, Items.getHotbarSwitcher());
		inventory.setItem(17, Items.getBarrier());
		inventory.setItem(26, Items.getBarrier());
		inventory.setItem(35, Items.getBarrier());
	}
	
	public static Map<Integer, ItemStack> inventoryToMap(Inventory inventory) {
		HashMap<Integer, ItemStack> map = new HashMap<Integer, ItemStack>();
		
		for(int i = 0; i < inventory.getSize(); i++) {
			map.put(i, inventory.getItem(i));
		}
		
		return map;
	}
}
