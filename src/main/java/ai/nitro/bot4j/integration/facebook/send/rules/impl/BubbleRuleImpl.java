/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.facebook.send.rules.impl;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.restfb.types.send.GenericTemplatePayload;
import com.restfb.types.send.IdMessageRecipient;
import com.restfb.types.send.Message;
import com.restfb.types.send.TemplateAttachment;

import ai.nitro.bot4j.integration.facebook.send.FacebookSendButtonFactory;
import ai.nitro.bot4j.middle.domain.send.SendMessage;
import ai.nitro.bot4j.middle.domain.send.bubble.Bubble;
import ai.nitro.bot4j.middle.domain.send.button.AbstractSendButton;
import ai.nitro.bot4j.middle.domain.send.payload.BubbleSendPayload;
import ai.nitro.bot4j.middle.domain.send.payload.type.SendPayloadTypeEnum;

@Singleton
public class BubbleRuleImpl extends AbstractFacebookSendRuleImpl {

	@Inject
	protected FacebookSendButtonFactory facebookSendButtonFactory;

	@Override
	public boolean applies(final SendMessage sendMessage) {
		return hasPayloadType(SendPayloadTypeEnum.BUBBLE, sendMessage);
	}

	@Override
	public void apply(final SendMessage sendMessage) {
		final GenericTemplatePayload payload = new GenericTemplatePayload();

		final BubbleSendPayload bubbleSendPayload = sendMessage.getPayloadWithType(BubbleSendPayload.class);

		for (final Bubble bubble : bubbleSendPayload.getBubbles()) {
			payload.addBubble(createBubble(bubble));
		}

		final TemplateAttachment templateAttachment = new TemplateAttachment(payload);
		final Message message = new Message(templateAttachment);

		final IdMessageRecipient recipient = createIdMessageRecipient(sendMessage.getRecipient());
		publish(sendMessage, message, recipient);
	}

	protected com.restfb.types.send.Bubble createBubble(final Bubble bubbleSendPayload) {
		final String title = bubbleSendPayload.getTitle();
		final com.restfb.types.send.Bubble result = new com.restfb.types.send.Bubble(title);
		result.setImageUrl(bubbleSendPayload.getImageUrl());
		result.setSubtitle(bubbleSendPayload.getText());
		result.setItemUrl(bubbleSendPayload.getUrl());

		for (final AbstractSendButton abstractSendButton : bubbleSendPayload.getButtons()) {
			result.addButton(facebookSendButtonFactory.createAbstractButton(abstractSendButton));
		}

		return result;
	}
}
