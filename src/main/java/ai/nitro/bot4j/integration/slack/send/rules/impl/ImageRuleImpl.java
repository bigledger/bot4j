/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.slack.send.rules.impl;

import java.util.List;

import javax.inject.Singleton;

import com.github.seratch.jslack.api.model.Attachment;
import com.github.seratch.jslack.api.webhook.Payload;
import com.google.common.collect.Lists;

import ai.nitro.bot4j.middle.domain.send.SendMessage;
import ai.nitro.bot4j.middle.domain.send.payload.ImageSendPayload;
import ai.nitro.bot4j.middle.domain.send.payload.type.SendPayloadTypeEnum;

@Singleton
public class ImageRuleImpl extends AbstractSlackSendRuleImpl {

	@Override
	public boolean applies(final SendMessage sendMessage) {
		return hasPayloadType(SendPayloadTypeEnum.IMAGE, sendMessage);
	}

	@Override
	public void apply(final SendMessage sendMessage) {
		final ImageSendPayload imageSendPayload = sendMessage.getPayloadWithType(ImageSendPayload.class);

		final String title = imageSendPayload.getTitle();
		final String imageUrl = imageSendPayload.getImageUrl();

		final Attachment attachment = Attachment.builder().fallback(title).imageUrl(imageUrl).build();
		final List<Attachment> attachments = Lists.newArrayList(attachment);
		final String channel = sendMessage.getRecipient().getId();

		final Payload payload = Payload.builder().channel(channel).attachments(attachments).build();
		chatPostMessage(sendMessage, payload);
	}
}
