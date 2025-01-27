package de.elia.features.invsee;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class PlayerInventoryHolder implements InventoryHolder {
  private final Player targetPlayer;
  private final Inventory inventory;

  public PlayerInventoryHolder(Player targetPlayer) {
    this.targetPlayer = targetPlayer;
    this.inventory = Bukkit.createInventory(targetPlayer, 54, targetPlayer.getName());
    //add main inventory items
    for (int i = 0; i < targetPlayer.getInventory().getSize(); i++) {
      inventory.setItem(i, targetPlayer.getInventory().getItem(i));
    }

    //add offhand item
    inventory.setItem(40, targetPlayer.getEquipment().getItemInOffHand());

    //add armor items
    inventory.setItem(36, targetPlayer.getEquipment().getHelmet());
    inventory.setItem(37, targetPlayer.getEquipment().getChestplate());
    inventory.setItem(38, targetPlayer.getEquipment().getLeggings());
    inventory.setItem(39, targetPlayer.getEquipment().getBoots());

  }

  @Override
  public Inventory getInventory() {
    return inventory;
  }
}
