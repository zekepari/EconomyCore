package net.tnemc.bukkit.listeners;

/*
 * The New Economy
 * Copyright (C) 2022 - 2023 Daniel "creatorfromhell" Vidmar
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

import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.tnemc.bukkit.TNE;
import net.tnemc.bukkit.impl.BukkitPlayerProvider;
import net.tnemc.core.handlers.PlayerJoinHandler;
import net.tnemc.core.io.message.MessageData;
import net.tnemc.core.io.message.MessageHandler;
import net.tnemc.core.utils.HandlerResponse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * PlayerJoinListener
 *
 * @author creatorfromhell
 * @since 0.1.2.0
 */
public class PlayerJoinListener implements Listener {

  @EventHandler(priority = EventPriority.HIGHEST)
  public void onJoin(final PlayerJoinEvent event) {
    final BukkitPlayerProvider provider = new BukkitPlayerProvider(event.getPlayer());
    final HandlerResponse handle = new PlayerJoinHandler()
        .handle(provider);

    if(handle.isCancelled()) {
      event.getPlayer().kickPlayer(handle.getResponse());
    }

    MessageHandler.translate(new MessageData("Locale: " + event.getPlayer().getLocale()), event.getPlayer().getUniqueId(), BukkitAudiences.create(TNE.instance()).player(event.getPlayer()));

    MessageHandler.translate(new MessageData("Hello <rainbow>world</rainbow>, TNE messages just got <hover:show_text:'<red>EXTREMELY</red>'>a lot</hover> better!"), event.getPlayer().getUniqueId(), BukkitAudiences.create(TNE.instance()).player(event.getPlayer()));
    MessageHandler.translate(new MessageData("<gradient:green:blue>Gradients are the best!</gradient>"), event.getPlayer().getUniqueId(), BukkitAudiences.create(TNE.instance()).player(event.getPlayer()));
  }
}