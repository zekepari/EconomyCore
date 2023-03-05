package net.tnemc.core.menu.callbacks.page;

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

import net.tnemc.core.compatibility.PlayerProvider;
import net.tnemc.core.menu.Page;

/**
 * Represents a callback, which is called when a page is closed in a menu. This could be due to a
 * switch, or due to the menu closing.
 *
 * @author creatorfromhell
 * @since 0.1.2.0
 */
public class PageCloseCallback extends PageCallback {

  protected final PlayerProvider player;

  public PageCloseCallback(Page page, PlayerProvider player) {
    super(page);
    this.player = player;
  }

  public PlayerProvider getPlayer() {
    return player;
  }
}