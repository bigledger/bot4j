/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.telegram.receive;

import java.util.Map;

import com.pengrad.telegrambot.model.Update;

public interface TelegramReceiveHandler {

	void handleUpdateMessage(Update update, Map<String, String[]> params);
}