package net.tnemc.core.menu.icons.myeco;
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

import net.tnemc.core.currency.Denomination;
import net.tnemc.core.currency.item.ItemDenomination;
import net.tnemc.core.menu.icons.shared.SwitchPageIcon;
import net.tnemc.menu.core.icon.action.ActionType;
import net.tnemc.menu.core.icon.action.impl.DataAction;
import net.tnemc.plugincore.PluginCore;

import java.util.Collections;

import static net.tnemc.core.menu.MyEcoMenu.DENOMINATION_EDIT_PAGE;

/**
 * DenominationIcon
 *
 * @author creatorfromhell
 * @since 0.1.2.0
 */
public class DenominationIcon extends SwitchPageIcon {

  public DenominationIcon(int slot, final Denomination denomination) {
    super(slot, PluginCore.server().stackBuilder().of((denomination instanceof ItemDenomination)? ((ItemDenomination)denomination).getMaterial() : "PAPER", 1)
                    .display(denomination.weight().toString()).lore(Collections.singletonList("Click to edit denomination")),
            "my_eco", DENOMINATION_EDIT_PAGE, ActionType.ANY, false);


    actions.add(new DataAction("DENOMINATION_WEIGHT", denomination.weight()));
    actions.add(new DataAction("DENOMINATION_SINGULAR", denomination.singular()));
    actions.add(new DataAction("DENOMINATION_PLURAL", denomination.plural()));

    if(denomination instanceof ItemDenomination itemDenomination) {
      actions.add(new DataAction("DENOMINATION_MATERIAL", itemDenomination.getMaterial()));
      actions.add(new DataAction("DENOMINATION_DISPLAY", itemDenomination.getName()));
    }

    super.addActions();
  }
}
