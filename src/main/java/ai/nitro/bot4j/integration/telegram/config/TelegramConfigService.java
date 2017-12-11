/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.telegram.config;

import ai.nitro.bot4j.middle.domain.receive.ReceiveMessage;
import ai.nitro.bot4j.middle.domain.send.SendMessage;

public interface TelegramConfigService {

	String getAccessToken(ReceiveMessage receiveMessage);

	String getAccessToken(SendMessage sendMessage);

	String getWebhookUrl(ReceiveMessage receiveMessage);

	String getWebhookUrl(SendMessage sendMessage);
}