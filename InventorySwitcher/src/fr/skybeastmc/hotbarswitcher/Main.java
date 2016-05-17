package fr.skybeastmc.hotbarswitcher;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

import fr.skybeastmc.hotbarswitcher.listeners.InventoryClickListener;
import fr.skybeastmc.hotbarswitcher.listeners.PlayerInteractListener;
import fr.skybeastmc.hotbarswitcher.listeners.ProtocolPacketListener;

public class Main extends JavaPlugin {
	private static JavaPlugin plugin;
	private static YamlConfiguration config;
	private static ProtocolManager protocolManager;
	
	public void onEnable() {
		try {
			File f = new File(getDataFolder()+File.separator+"config.yml");
			f.getParentFile().mkdirs();
			if(!f.exists()) f.createNewFile();
			
			config = YamlConfiguration.loadConfiguration(f);
			
		} catch (Exception e) {Debug.error(e, "Config file loading", true);}

		try {
			plugin = this;
			
			boolean debug = config.getBoolean("debug");
			Debug.setDebug(debug);
			Debug.info("Debug = "+debug+"!");
			//if(debug) getServer().getPluginManager().registerEvents(new Debug(), this);
			getCommand("switch").setExecutor(new HotbarSwitchCommand());
			getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
			getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
    		protocolManager = ProtocolLibrary.getProtocolManager();
    		protocolManager.addPacketListener(new ProtocolPacketListener(
    				PacketType.Play.Client.SET_CREATIVE_SLOT));
    		Debug.debug("a");
    		
    		
		} catch (Exception e) {Debug.error(e, "Enabling", true);}
		
	}
	
	public void onDisable() {}
	

	public static JavaPlugin getPlugin() {
		return plugin;
	}
	
	public static File getFolder() {
		return plugin.getDataFolder();
	}

	public static void setPlugin(JavaPlugin plugin) {
		Main.plugin = plugin;
	}

	public static YamlConfiguration getConf() {
		return config;
	}

	public static void setConf(YamlConfiguration config) {
		Main.config = config;
	}

	public static ProtocolManager getProtocolManager() {
		return protocolManager;
	}
}
