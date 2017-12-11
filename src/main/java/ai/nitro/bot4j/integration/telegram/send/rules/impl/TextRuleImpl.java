/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.telegram.send.rules.impl;

import javax.inject.Singleton;

import ai.nitro.bot4j.middle.domain.send.SendMessage;
import ai.nitro.bot4j.middle.domain.send.payload.TextSendPayload;
import ai.nitro.bot4j.middle.domain.send.payload.type.SendPayloadTypeEnum;

@Singleton
public class TextRuleImpl extends AbstractTelegramSendRuleImpl {

	@Override
	public boolean applies(final SendMessage sendMessage) {
		return hasPayloadType(SendPayloadTypeEnum.TEXT, sendMessage);
	}

	@Override
	public void apply(final SendMessage sendMessage) {
		final TextSendPayload textSendPayload = sendMessage.getPayloadWithType(TextSendPayload.class);
		final String text = textSendPayload.getText();

		final String recipient = sendMessage.getRecipient().getId();

		final com.pengrad.telegrambot.request.SendMessage sendMessageTelegram = new com.pengrad.telegrambot.request.SendMessage(
				recipient, text);
		super.execute(sendMessage, sendMessageTelegram, recipient);
	}
}
