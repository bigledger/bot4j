/*
 * Copyright (C) 2016, nitro.ai
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.slack.send.rules.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.github.seratch.jslack.api.model.Attachment;
import com.github.seratch.jslack.api.webhook.Payload;

import ai.nitro.bot4j.integration.slack.send.SlackSendAttachmentFactory;
import ai.nitro.bot4j.middle.domain.send.SendMessage;
import ai.nitro.bot4j.middle.domain.send.button.AbstractSendButton;
import ai.nitro.bot4j.middle.domain.send.payload.ButtonsSendPayload;
import ai.nitro.bot4j.middle.domain.send.payload.type.SendPayloadTypeEnum;

@Singleton
public class ButtonsRuleImpl extends AbstractSlackSendRuleImpl {

	@Inject
	protected SlackSendAttachmentFactory slackSendAttachmentFactory;

	@Override
	public boolean applies(final SendMessage sendMessage) {
		return hasPayloadType(SendPayloadTypeEnum.BUTTONS, sendMessage);
	}

	@Override
	public void apply(final SendMessage sendMessage) {
		final ButtonsSendPayload buttonsSendPayload = sendMessage.getPayloadWithType(ButtonsSendPayload.class);
		final List<Attachment> attachments = new ArrayList<Attachment>();

		for (final AbstractSendButton sendButton : buttonsSendPayload.getButtons()) {
			final Attachment attachment = slackSendAttachmentFactory.createAttachment(sendButton);
			attachments.add(attachment);
		}

		final String channel = sendMessage.getRecipient().getId();
		final Payload payload = Payload.builder().channel(channel).attachments(attachments).build();
		chatPostMessage(sendMessage, payload);
	}
}
