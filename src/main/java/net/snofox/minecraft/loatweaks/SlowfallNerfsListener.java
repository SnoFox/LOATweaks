package net.snofox.minecraft.loatweaks;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.LingeringPotionSplashEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.Iterator;

/**
 * Created by Josh on 2020-02-18
 */
public class SlowfallNerfsListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onEntityDamageByEntity(final EntityDamageByEntityEvent ev) {
        if(ev.getDamager() instanceof Arrow && ev.getEntity() instanceof Player) {
            final Arrow arrow = (Arrow) ev.getDamager();
            if(arrow.getBasePotionData().getType().equals(PotionType.SLOW_FALLING)) {
                arrow.setBasePotionData(new PotionData(PotionType.AWKWARD, false, false));
                if(arrow.getShooter() instanceof Player) {
                    ((Player) arrow.getShooter()).spigot().sendMessage(ChatMessageType.ACTION_BAR,
                            TextComponent.fromLegacyText("The potion failed to take hold on " + ((Player) ev.getEntity()).getDisplayName()));
                }
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onPotionSplash(final PotionSplashEvent ev) {
        final boolean modified = ev.getAffectedEntities().removeIf(Player.class::isInstance);
        if(modified && ev.getEntity().getShooter() instanceof Player) {
            ((Player) ev.getEntity().getShooter()).spigot().sendMessage(ChatMessageType.ACTION_BAR,
                    TextComponent.fromLegacyText("The potion failed to take hold on some targets"));
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onLingeringPotionSplash(final LingeringPotionSplashEvent ev) {
        ev.getAreaEffectCloud().setBasePotionData(new PotionData(PotionType.AWKWARD));
        ev.getAreaEffectCloud().addCustomEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 25, 0), true);
    }
}
