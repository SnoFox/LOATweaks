package net.snofox.minecraft.loatweaks;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Josh on 2020-02-17
 */
public class AppleListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onPlayerItemConsume(final PlayerItemConsumeEvent ev) {
        final ItemStack item = ev.getItem();
        if(isGoldenApple(item)) {
            ev.setItem(new ItemStack(Material.APPLE, item.getAmount()));
            ev.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("The gold fell off all your apples"));
        }
    }

    public boolean isGoldenApple(final ItemStack itemStack) {
        return itemStack.getType().equals(Material.GOLDEN_APPLE)
                || itemStack.getType().equals(Material.ENCHANTED_GOLDEN_APPLE);
    }
}
