/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.telegram.receive.impl;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.pengrad.telegrambot.model.Update;

import ai.nitro.bot4j.integration.telegram.receive.TelegramReceiveHandler;
import ai.nitro.bot4j.integration.telegram.receive.TelegramReceiveMessageFactory;
import ai.nitro.bot4j.middle.domain.receive.ReceiveMessage;
import ai.nitro.bot4j.middle.receive.MessageReceiver;

@Singleton
public class TelegramReceiveHandlerImpl implements TelegramReceiveHandler {

	@Inject
	protected MessageReceiver messageReceiver;

	@Inject
	protected TelegramReceiveMessageFactory telegramReceiveMessageFactory;

	@Override
	public void handleUpdateMessage(final Update update, final Map<String, String[]> params) {
		final ReceiveMessage receiveMessage = telegramReceiveMessageFactory.createReceiveMessage(update, params);

		if (receiveMessage != null) {
			messageReceiver.receive(receiveMessage);
		}
	}
}
