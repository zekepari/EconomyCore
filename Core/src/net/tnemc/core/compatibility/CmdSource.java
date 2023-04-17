package net.tnemc.core.compatibility;

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

import net.tnemc.core.TNECore;
import net.tnemc.core.account.Account;
import net.tnemc.core.io.message.MessageData;

import java.util.Optional;
import java.util.UUID;

/**
 * CmdSource
 *
 * @author creatorfromhell
 * @since 0.1.2.0
 * @see PlayerProvider
 */
public interface CmdSource {

  /**
   * The UUID of this command source.
   * @return The UUID of this command source.
   */
  UUID identifier();

  /**
   * The name of this command source.
   * @return The name of this command source.
   */
  String name();

  /**
   * Used to get the related {@link PlayerProvider} for this command source.
   * @return An optional containing the related {@link PlayerProvider} if this command source is a
   * player, otherwise an empty {@link Optional}.
   */
  Optional<PlayerProvider> player();

  /**
   * Used to get the account associated with this specific {@link CmdSource}.
   * @return An Optional containing the {@link Account account} class, or an empty Optional.
   */
  default Optional<Account> account() {
    return TNECore.eco().account().findAccount(identifier());
  }

  /**
   * Used to send a message to this command source.
   * @param messageData The message data to utilize for this translation.
   */
  void message(final MessageData messageData);

  /**
   * Used to get the world for this command source.
   * @return The name of the world that this command source is in.
   */
  default String region() {
    if(player().isPresent()) {
      return TNECore.eco().region().getMode().region(player().get());
    }
    return TNECore.eco().region().defaultRegion();
  }
}