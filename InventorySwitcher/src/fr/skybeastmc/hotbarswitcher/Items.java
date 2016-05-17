package fr.skybeastmc.hotbarswitcher;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Items {
	private static ItemStack barrier = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 8);
	private static ItemStack hotbarSwitcher = new ItemStack(Material.TRIPWIRE_HOOK);
	
	static {
		ItemMeta hotbarSwitcherMeta = barrier.getItemMeta();
		hotbarSwitcherMeta.setDisplayName("§bHotbar Switcher");
		List<String> hotbarSwitcherLore = new ArrayList<String>();
		hotbarSwitcherLore.add("§7Click droit/gauche pour switch.");
		hotbarSwitcherLore.add("§7Click molette pour la afficher la configuration.");
		hotbarSwitcherMeta.setLore(hotbarSwitcherLore);
		hotbarSwitcher.setItemMeta(hotbarSwitcherMeta);

		ItemMeta barrierMeta = barrier.getItemMeta();
		barrierMeta.setDisplayName("§1§0§0§0");
		barrier.setItemMeta(barrierMeta);
	}
	
	public static ItemStack getBarrier() {
		return barrier;
	}
	
	public static ItemStack getHotbarSwitcher() {
		return hotbarSwitcher;
	}
}
