package fr.skybeastmc.hotbarswitcher.listeners;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;

import fr.skybeastmc.hotbarswitcher.Debug;
import fr.skybeastmc.hotbarswitcher.HotbarSwitcher;
import fr.skybeastmc.hotbarswitcher.Items;
import fr.skybeastmc.hotbarswitcher.Main;

public class ProtocolPacketListener extends PacketAdapter {
	private ProtocolManager protocolManager = Main.getProtocolManager();

	public ProtocolPacketListener(PacketType... types) {
		super(Main.getPlugin(), types);
	}

	@Override
	public void onPacketSending(PacketEvent paramPacketEvent) {}

	@Override
	public void onPacketReceiving(PacketEvent event) {
		try {
			Debug.debug("Packet!");
			
			int slot = networkSlotToDataSlot(event.getPacket().getIntegers().read(0));

			Debug.bc(slot);
			ItemStack item1 = event.getPacket().getItemModifier().read(0);
			if(item1 != null) {
				Debug.bc(item1.equals(Items.getHotbarSwitcher()));
				if(item1.getItemMeta() != null)
					Debug.bc(event.getPacket().getItemModifier().read(0).getItemMeta().getDisplayName());
				else
					Debug.bc(null);
			} else {
				Debug.bc(null);
				Debug.bc(null);
			}
			
			ItemStack item2 = event.getPlayer().getInventory().getItem(event.getPacket().getIntegers().read(0));
			if(item2 != null) {
				Debug.bc(item2.equals(Items.getHotbarSwitcher()));
				if(item2.getItemMeta() != null)
					Debug.bc(item2.getItemMeta().getDisplayName());
				else
					Debug.bc(null);
			} else {
				Debug.bc(null);
				Debug.bc(null);
			}
			Debug.bc("------------");
			
			if(slot == 8 || slot == 17 || slot == 26 || slot == 35) {
				
				event.setCancelled(true);
				PacketContainer packet1 = protocolManager.createPacket(PacketType.Play.Server.SET_SLOT);
				packet1.getIntegers().write(0, -2);
				Debug.debug(event.getPacket().getIntegers().read(0));
				Debug.debug(networkSlotToDataSlot(event.getPacket().getIntegers().read(0)));
				packet1.getIntegers().write(1, slot);
				
				ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName("§f"+slot);
				item.setItemMeta(meta);
				
				packet1.getItemModifier().write(0, item);
				protocolManager.sendServerPacket(event.getPlayer(), packet1);
				
				PacketContainer packet2 = protocolManager.createPacket(PacketType.Play.Server.SET_SLOT);
				packet2.getIntegers().write(0, -1);
				packet2.getIntegers().write(1, -1);
				packet2.getItemModifier().write(0, new ItemStack(Material.AIR));
				protocolManager.sendServerPacket(event.getPlayer(), packet2);
				
				if(slot == 8) HotbarSwitcher.switchHotbar(event.getPlayer());
			}
			

		} catch(Exception e) {Debug.error(e, "Packet listener", false);}
	}
	
    @SuppressWarnings("unused")
	private static int dataSlotToNetworkSlot(int index) {
        if (index <= 8)
            index += 36;
        else if (index == 100)
            index = 8;
        else if (index == 101)
            index = 7;
        else if (index == 102)
            index = 6;
        else if (index == 103)
            index = 5;
        else if (index >= 80 && index <= 83)
            index -= 79;
        return index;
    }
	
    private static int networkSlotToDataSlot(int index) {
        if (index >= 36 && index <= 44)
            index -= 36;
        else if (index == 8)
            index = 36;
        else if (index == 7)
            index = 37;
        else if (index == 6)
            index = 38;
        else if (index == 5)
            index = 39;
        else if (index == 45)
            index = 40;
        return index;
    }


}
