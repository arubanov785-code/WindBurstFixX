package com.example.windburstfix;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public final class WindBurstFix extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("WindBurstFix активирован!");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        Player player = (Player) event.getDamager();

        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType() != Material.MACE) return;

        if (!item.containsEnchantment(Enchantment.WIND_BURST)) return;

        if (player.getFallDistance() <= 1.5) return;

        int level = item.getEnchantmentLevel(Enchantment.WIND_BURST);
        double boost = 0.35 * level + 1.2; 

        Vector velocity = player.getVelocity();
        velocity.setY(boost);
        player.setVelocity(velocity);
    }
}
