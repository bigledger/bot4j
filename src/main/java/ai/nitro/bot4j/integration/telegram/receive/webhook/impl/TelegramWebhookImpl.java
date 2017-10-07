/*
 * Copyright (C) 2016, nitro.ai
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.telegram.receive.webhook.impl;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import com.pengrad.telegrambot.BotUtils;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SetWebhook;

import ai.nitro.bot4j.integration.telegram.config.TelegramConfigService;
import ai.nitro.bot4j.integration.telegram.receive.TelegramReceiveHandler;
import ai.nitro.bot4j.integration.telegram.receive.webhook.TelegramWebhook;
import ai.nitro.bot4j.middle.domain.send.SendMessage;

public class TelegramWebhookImpl implements TelegramWebhook {

	private final static Logger LOG = LogManager.getLogger(TelegramWebhookImpl.class);

	@Inject
	protected TelegramConfigService telegramConfigService;

	@Inject
	protected TelegramReceiveHandler telegramReceiveHandler;

	private void handleException(final Exception e) {
		LOG.error(e.getMessage(), e);
	}

	@Inject
	protected void init() {
		final String webhookUrl = telegramConfigService.getWebhookUrl(null);

		if (Strings.isBlank(webhookUrl)) {
		} else {
			final SetWebhook request = new SetWebhook().url(webhookUrl);
			final TelegramBot bot = provideTelegramBot();
			bot.execute(request);
		}
	}

	@Override
	public String post(final HttpServletRequest req, final HttpServletResponse res) {
		LOG.info("received Message");
		final String result = "";

		try {
			final Map<String, String[]> params = req.getParameterMap();

			final Update update = BotUtils.parseUpdate(req.getReader());
			telegramReceiveHandler.handleUpdateMessage(update, params);
		} catch (final Exception e) {
			handleException(e);
		}

		return result;
	}

	protected TelegramBot provideTelegramBot() {
		final String telegramAccessToken = telegramConfigService.getAccessToken((SendMessage) null);
		final TelegramBot result = TelegramBotAdapter.build(telegramAccessToken);
		return result;
	}
}
