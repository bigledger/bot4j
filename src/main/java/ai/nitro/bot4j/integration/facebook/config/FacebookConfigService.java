/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.facebook.config;

import ai.nitro.bot4j.middle.domain.receive.ReceiveMessage;
import ai.nitro.bot4j.middle.domain.send.SendMessage;

public interface FacebookConfigService {

	String getAccessToken(ReceiveMessage receiveMessage);

	String getAccessToken(SendMessage sendMessage);

	String getPageId(ReceiveMessage receiveMessage);

	String getPageId(SendMessage sendMessage);
}
