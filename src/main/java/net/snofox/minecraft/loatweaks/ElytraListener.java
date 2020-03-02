package net.snofox.minecraft.loatweaks;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Josh on 2020-02-17
 */
public class ElytraListener implements Listener {
    private final int BURN_TIME;

    public ElytraListener() {
        BURN_TIME = LOATweaks.getInstance().getConfig().getInt("elytraBurn", 120);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent ev) {
        if(ev.getPlayer().isGliding() && ev.hasItem() && isRightClick(ev.getAction())) {
            final EquipmentSlot handUsed = ev.getHand();
            final Player player = ev.getPlayer();
            final ItemStack itemUsed = (handUsed == EquipmentSlot.HAND ? player.getInventory().getItemInMainHand() : player.getInventory().getItemInOffHand());
            if(itemUsed.getType().equals(Material.FIREWORK_ROCKET)) {
                if(player.getLocation().getBlock().getType().equals(Material.WATER)) {
                    ev.setUseItemInHand(Event.Result.DENY);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("You failed to light the firework under water"));
                } else {
                    player.setFireTicks(BURN_TIME);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("You set yourself on fire while playing with fireworks in the air"));
                }
            }
        }
    }

    /*
    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
    public void onEntityDamage(final EntityDamageByEntityEvent ev) {
        if(!(ev.getDamager() instanceof Arrow)) return;
        final ProjectileSource shooter = ((Arrow) ev.getDamager()).getShooter();
        if(shooter == null) return;
        final Entity victim = ev.getEntity();
        if(!(victim instanceof Player)) return;
        if(shooter.equals(victim) && ((Player) victim).isGliding())
            ((Arrow) ev.getDamager()).setKnockbackStrength(0);
    }*/

    private boolean isRightClick(final Action action) {
        return action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK;
    }
}
