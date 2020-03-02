package net.snofox.minecraft.loatweaks;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by Josh on 2020-02-18
 */
public class EnderChestListener implements Listener {
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onPlayerInteract(final PlayerInteractEvent ev) {
        if(ev.getPlayer().hasPermission("loatweaks.useenderchests")) return;
        if(ev.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            final Block clickedBlock = ev.getClickedBlock();
            if(clickedBlock == null) return;
            if(clickedBlock.getType().equals(Material.ENDER_CHEST)) {
                ev.setUseInteractedBlock(Event.Result.DENY);
                ev.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("Pretty chest. Do you know how to open it?"));
            }
        }
    }
}
