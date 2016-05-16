package fr.skybeastmc.hotbarswitcher;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	private static JavaPlugin plugin;
	private static YamlConfiguration config;
	
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
			if(debug) getServer().getPluginManager().registerEvents(new Debug(), this);
			
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
}
