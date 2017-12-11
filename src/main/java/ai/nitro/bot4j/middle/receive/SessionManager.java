/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.receive;

import ai.nitro.bot4j.middle.domain.Session;
import ai.nitro.bot4j.middle.domain.receive.ReceiveMessage;
import ai.nitro.bot4j.middle.domain.send.SendMessage;

public interface SessionManager {

	Session getSession(ReceiveMessage receiveMessage);

	Session getSession(SendMessage sendMessage);
}
