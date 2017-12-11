/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.telegram.config.impl;

import javax.inject.Inject;
import javax.inject.Singleton;

import ai.nitro.bot4j.integration.telegram.config.TelegramConfig;
import ai.nitro.bot4j.integration.telegram.config.TelegramConfigService;
import ai.nitro.bot4j.middle.domain.receive.ReceiveMessage;
import ai.nitro.bot4j.middle.domain.send.SendMessage;

@Singleton
public class TelegramConfigServiceImpl implements TelegramConfigService {

	@Inject
	protected TelegramConfig telegramConfig;

	@Override
	public String getAccessToken(final ReceiveMessage receiveMessage) {
		return telegramConfig.getAccessToken();
	}

	@Override
	public String getAccessToken(final SendMessage sendMessage) {
		return telegramConfig.getAccessToken();
	}

	@Override
	public String getWebhookUrl(final ReceiveMessage receiveMessage) {
		return telegramConfig.getWebhookUrl();
	}

	@Override
	public String getWebhookUrl(final SendMessage sendMessage) {
		return telegramConfig.getWebhookUrl();
	}
}
