/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.slack.send.rules.impl;

import javax.inject.Singleton;

import com.github.seratch.jslack.api.webhook.Payload;

import ai.nitro.bot4j.middle.domain.send.SendMessage;
import ai.nitro.bot4j.middle.domain.send.payload.TextSendPayload;
import ai.nitro.bot4j.middle.domain.send.payload.type.SendPayloadTypeEnum;

@Singleton
public class TextRuleImpl extends AbstractSlackSendRuleImpl {

	@Override
	public boolean applies(final SendMessage sendMessage) {
		return hasPayloadType(SendPayloadTypeEnum.TEXT, sendMessage);
	}

	@Override
	public void apply(final SendMessage sendMessage) {
		final TextSendPayload textPayload = sendMessage.getPayloadWithType(TextSendPayload.class);

		final String text = textPayload.getText();
		final String channel = sendMessage.getRecipient().getId();

		final Payload payload = Payload.builder().text(text).channel(channel).build();
		chatPostMessage(sendMessage, payload);
	}
}
