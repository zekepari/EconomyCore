package net.tnemc.paper;
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

import net.tnemc.bukkit.listeners.entity.EntityKilledListener;
import net.tnemc.bukkit.listeners.player.PlayerCloseInventoryListener;
import net.tnemc.bukkit.listeners.player.PlayerExperienceGainListener;
import net.tnemc.bukkit.listeners.player.PlayerJoinListener;
import net.tnemc.bukkit.listeners.player.PlayerQuitListener;
import net.tnemc.bukkit.listeners.server.PluginEnableListener;
import net.tnemc.bukkit.listeners.world.WorldLoadListener;
import net.tnemc.core.TNECore;
import net.tnemc.core.api.callback.TNECallbackProvider;
import net.tnemc.core.io.message.BaseTranslationProvider;
import net.tnemc.item.paper.PaperCalculationsProvider;
import net.tnemc.paper.hook.economy.VaultHook;
import net.tnemc.paper.hook.economy.VaultUnlockedHook;
import net.tnemc.paper.hook.misc.PAPIHook;
import net.tnemc.paper.listener.PlayerInteractListener;
import net.tnemc.plugincore.PluginCore;
import net.tnemc.plugincore.core.compatibility.ServerConnector;
import net.tnemc.plugincore.paper.PaperPluginCore;
import net.tnemc.plugincore.paper.impl.PaperServerProvider;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

/**
 * PaperPlugin
 *
 * @author creatorfromhell
 * @since 0.1.2.0
 */
public class PaperPlugin {

  private PaperPluginCore pluginCore;
  private TNECore core;
  private boolean papiHooked = false;

  public void load(final JavaPlugin plugin) {
    load(plugin, new PaperCore(plugin), new PaperServerProvider(new PaperCalculationsProvider()));
  }

  /**
   * Called when the plugin is loaded by the Bukkit plugin manager.
   * This method should be used to initialize any necessary resources or data.
   */
  public void load(final JavaPlugin plugin, TNECore core, ServerConnector provider) {

    //Initialize our TNE Core Class
    this.core = core;
    this.pluginCore = new PaperPluginCore(plugin, core, provider, new BaseTranslationProvider(), new TNECallbackProvider());

    //Vault
    PluginCore.log().inform("Checking for VaultUnlocked");
    try {
      Class.forName("net.milkbowl.vault2.economy.Economy");
      new VaultUnlockedHook().register();
    } catch(Exception ignore) {
      PluginCore.log().error("Unable to connect to VaultUnlocked!");
    }

    PluginCore.log().inform("Checking for Vault");
    try {
      Class.forName("net.milkbowl.vault.economy.Economy");
      new VaultHook().register();
    } catch(Exception ignore) {
      PluginCore.log().error("Unable to connect to vault!");
    }
  }

  /**
   * Called by the Bukkit plugin manager when the plugin is enabled.
   * This method should be used to enable the functionality provided by this class.
   */
  public void enable(final JavaPlugin plugin) {

    this.pluginCore.enable();

    //Register our event listeners
    Bukkit.getPluginManager().registerEvents(new EntityKilledListener(), plugin);
    Bukkit.getPluginManager().registerEvents(new PlayerExperienceGainListener(), plugin);

    //Player Listeners
    Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), plugin);
    Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), plugin);

    //Menu-related Listeners from TNML
    Bukkit.getPluginManager().registerEvents(new PlayerCloseInventoryListener(), plugin);
    Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), plugin);

    //World Listeners
    Bukkit.getPluginManager().registerEvents(new WorldLoadListener(), plugin);

    //Register our service providers.

    //PAPI
    if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
      new PAPIHook().register();
    } else {
      Bukkit.getPluginManager().registerEvents(new PluginEnableListener(), plugin);
    }

    final Metrics metrics = new Metrics(plugin, 602);

    plugin.getLogger().log(Level.INFO, "The New Economy has been enabled!");
  }

  /**
   * Called by the Bukkit plugin manager when the plugin is disabled.
   * This method should be used to disable the functionality provided by this class.
   */
  public void disable(final JavaPlugin plugin) {
    this.pluginCore.onDisable();
  }

  public boolean isPapiHooked() {
    return papiHooked;
  }

  public void setPapiHooked(boolean papiHooked) {
    this.papiHooked = papiHooked;
  }
}
