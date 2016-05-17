package fr.skybeastmc.hotbarswitcher;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class HotbarSwitcherOld {
	
	public static void switchHotbar(Player player) throws IOException {// ./plugins/InventorySwitcher/inventory
		Debug.debug("Part 1: file creation/loading");
		File file = new File(Main.getFolder()+File.separator+"inventory"+File.separator+player.getUniqueId().toString()+".yml");
		if(!file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

		Debug.debug("Part 2: loading saved hotbar");
		Map<Integer, ItemStack> hotbar = getHotbar(player, config);
		Debug.debug("Part 3: saving new hotbar");
		saveHotbar(player, config);
		config.save(file);
		Debug.debug("Part 4: setting old hotbar");
		setHotbar(player, hotbar);
		Debug.debug("Done!");
	}
	
	public static void saveHotbar(Player player, ConfigurationSection config) {
		Inventory inventory = player.getInventory();
		
		for(int i = 0; i < 7; i++) {
			ItemStack item = inventory.getItem(i);
			if(item != null) {
				config.set(i+"", item);
			}
		}
	}
	
	public static Map<Integer, ItemStack> getHotbar(Player player, ConfigurationSection config) {
		
		HashMap<Integer, ItemStack> hotbar = new HashMap<Integer, ItemStack>();
		for(String key : config.getKeys(false)) {
			hotbar.put(Integer.valueOf(key), config.getItemStack(key));
		}
		return hotbar;
	}
	
	public static void setHotbar(Player player, Map<Integer, ItemStack> hotbar) {
		Inventory i = player.getInventory();
		for(Entry<Integer, ItemStack> item : hotbar.entrySet()) {
			i.setItem(item.getKey(), item.getValue());
		}
	}
}
