package de.elia.features.invsee;

import org.bukkit.entity.Player;

public class View {
  private Player viewer;
  private Player target;

  public View (Player viewer, Player target){
    this.viewer = viewer;
    this.target = target;
  }

  public Player getViewer() {
    return viewer;
  }

  public Player getTarget() {
    return target;
  }
}
