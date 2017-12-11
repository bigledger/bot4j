/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.facebook.send.rules.impl;

import javax.inject.Singleton;

import com.restfb.types.send.IdMessageRecipient;
import com.restfb.types.send.Message;

import ai.nitro.bot4j.middle.domain.send.SendMessage;
import ai.nitro.bot4j.middle.domain.send.payload.TextSendPayload;
import ai.nitro.bot4j.middle.domain.send.payload.type.SendPayloadTypeEnum;

@Singleton
public class TextRuleImpl extends AbstractFacebookSendRuleImpl {

	@Override
	public boolean applies(final SendMessage sendMessage) {
		return hasPayloadType(SendPayloadTypeEnum.TEXT, sendMessage);
	}

	@Override
	public void apply(final SendMessage sendMessage) {
		final TextSendPayload textPayload = sendMessage.getPayloadWithType(TextSendPayload.class);
		final String text = textPayload.getText();
		final Message message = new Message(text);

		final IdMessageRecipient recipient = createIdMessageRecipient(sendMessage.getRecipient());
		publish(sendMessage, message, recipient);
	}
}
