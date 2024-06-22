package net.tnemc.bukkit.listeners.player;

/*
 * The New Economy
 * Copyright (C) 2022 - 2024 Daniel "creatorfromhell" Vidmar
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import net.tnemc.core.handlers.player.PlayerJoinHandler;
import net.tnemc.plugincore.PluginCore;
import net.tnemc.plugincore.core.compatibility.PlayerProvider;
import net.tnemc.plugincore.core.utils.HandlerResponse;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * PlayerJoinListener
 *
 * @author creatorfromhell
 * @since 0.1.2.0
 */
public class PlayerJoinListener implements Listener {

  @EventHandler(priority = EventPriority.HIGHEST)
  public void onJoin(final PlayerJoinEvent event) {
    final PlayerProvider provider = PluginCore.server().initializePlayer(event.getPlayer());
    final HandlerResponse handle = new PlayerJoinHandler()
        .handle(provider);

    if(handle.isCancelled()) {
      event.getPlayer().kickPlayer(handle.getResponse());
    }

    ItemStack stack = new ItemStack(Material.PLAYER_HEAD);
    if(stack.hasItemMeta() && stack.getItemMeta() instanceof SkullMeta skullMeta) {

      //stack.getItemMeta().has

    }

    //Is john joining?
    //easter egg
    if(event.getPlayer().getUniqueId().toString().equalsIgnoreCase("e8e32707-8120-4c48-ad16-81d3fce9346d")) {
      event.getPlayer().chat("Hi my name is JustJohns. I like anime!");
    }
  }
}