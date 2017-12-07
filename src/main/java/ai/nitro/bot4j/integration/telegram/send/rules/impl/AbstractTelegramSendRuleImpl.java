/*
 * Copyright (C) 2016, nitro.ai
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.telegram.send.rules.impl;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;

import ai.nitro.bot4j.integration.telegram.config.TelegramConfigService;
import ai.nitro.bot4j.integration.telegram.send.rules.TelegramSendRule;
import ai.nitro.bot4j.middle.domain.send.SendMessage;
import ai.nitro.bot4j.middle.domain.send.payload.AbstractSendPayload;
import ai.nitro.bot4j.middle.domain.send.payload.type.SendPayloadType;

@Singleton
public abstract class AbstractTelegramSendRuleImpl implements TelegramSendRule {

	final static Logger LOG = LogManager.getLogger(AbstractTelegramSendRuleImpl.class);

	@Inject
	protected TelegramConfigService telegramConfigService;

	protected void execute(final SendMessage sendMessage, final com.pengrad.telegrambot.request.SendMessage message,
			final String recipient) {
		LOG.info("sending to {} message {}", recipient, message);
		final TelegramBot client = provideTelegramBot(sendMessage);
		client.execute(message);
	}

	protected boolean hasPayloadType(final SendPayloadType sendPayloadtype, final SendMessage sendMessage) {
		final AbstractSendPayload payload = sendMessage.getPayload();
		final boolean result = payload != null && sendPayloadtype.equals(payload.getSendPayloadType());
		return result;
	}

	protected TelegramBot provideTelegramBot(final SendMessage sendMessage) {
		final String telegramAccessToken = telegramConfigService.getAccessToken(sendMessage);
		final TelegramBot result = TelegramBotAdapter.build(telegramAccessToken);
		return result;
	}
}
