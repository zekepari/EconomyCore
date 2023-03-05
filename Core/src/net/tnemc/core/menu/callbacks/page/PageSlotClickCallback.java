package net.tnemc.core.menu.callbacks.page;

/*
 * The New Economy
 * Copyright (C) 2022 Daniel "creatorfromhell" Vidmar
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

import net.tnemc.core.compatibility.PlayerProvider;
import net.tnemc.core.menu.Menu;
import net.tnemc.core.menu.Page;
import net.tnemc.core.menu.icon.ActionType;

import java.util.Optional;

/**
 * Represents an event that occurs when a slot is clicked in a page.
 *
 * @author creatorfromhell
 * @since 0.1.2.0
 */
public class PageSlotClickCallback extends PageCallback {

  protected final Menu menu;
  protected final Page page;
  protected final PlayerProvider player;
  protected final ActionType type;
  protected final int slot;

  public PageSlotClickCallback(Menu menu, Page page, ActionType type, PlayerProvider player) {
    this(menu, page, type, player, -1);
  }

  public PageSlotClickCallback(Menu menu, Page page, ActionType type, PlayerProvider player, int slot) {
    super(page);
    this.menu = menu;
    this.page = page;
    this.player = player;
    this.type = type;
    this.slot = slot;
  }

  public Menu getMenu() {
    return menu;
  }

  public Page getPage() {
    return page;
  }

  public PlayerProvider getPlayer() {
    return player;
  }

  public Optional<Integer> getSlot() {
    if(slot == -1) return Optional.empty();
    return Optional.of(slot);
  }
}