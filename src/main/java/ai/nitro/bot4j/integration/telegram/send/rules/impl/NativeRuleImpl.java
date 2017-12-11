/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.telegram.send.rules.impl;

import javax.inject.Singleton;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.BaseRequest;

import ai.nitro.bot4j.integration.telegram.domain.TelegramPlatformEnum;
import ai.nitro.bot4j.middle.domain.send.SendMessage;

@Singleton
public class NativeRuleImpl extends AbstractTelegramSendRuleImpl {

	@Override
	public boolean applies(final SendMessage sendMessage) {
		final boolean result = sendMessage.getNativePayload(TelegramPlatformEnum.TELEGRAM) != null;
		return result;
	}

	@Override
	public void apply(final SendMessage sendMessage) {
		final BaseRequest message = (BaseRequest) sendMessage.getNativePayload(TelegramPlatformEnum.TELEGRAM);
		final TelegramBot client = provideTelegramBot(sendMessage);
		client.execute(message);
	}
}
