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
import com.restfb.types.send.SenderActionEnum;

import ai.nitro.bot4j.middle.domain.send.SendMessage;
import ai.nitro.bot4j.middle.domain.send.payload.TypingSendPayload;
import ai.nitro.bot4j.middle.domain.send.payload.TypingSendPayload.Typing;
import ai.nitro.bot4j.middle.domain.send.payload.type.SendPayloadTypeEnum;

@Singleton
public class TypingRuleImpl extends AbstractFacebookSendRuleImpl {

	@Override
	public boolean applies(final SendMessage sendMessage) {
		return hasPayloadType(SendPayloadTypeEnum.TYPING, sendMessage);
	}

	@Override
	public void apply(final SendMessage sendMessage) {
		final TypingSendPayload typingPayload = sendMessage.getPayloadWithType(TypingSendPayload.class);
		final Typing typingType = typingPayload.getTyping();

		final SenderActionEnum senderActionEnum;

		switch (typingType) {
		case OFF:
			senderActionEnum = SenderActionEnum.typing_off;
			break;
		case ON:
		default:
			senderActionEnum = SenderActionEnum.typing_on;
			break;
		}

		final IdMessageRecipient recipient = createIdMessageRecipient(sendMessage.getRecipient());
		publish(sendMessage, senderActionEnum, recipient);
	}
}
