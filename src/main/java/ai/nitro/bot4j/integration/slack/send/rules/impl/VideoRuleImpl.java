/*
 * Copyright (C) 2016, nitro.ai
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.slack.send.rules.impl;

import java.util.List;

import javax.inject.Singleton;

import com.github.seratch.jslack.api.model.Attachment;
import com.github.seratch.jslack.api.webhook.Payload;
import com.google.common.collect.Lists;

import ai.nitro.bot4j.middle.domain.send.SendMessage;
import ai.nitro.bot4j.middle.domain.send.payload.VideoSendPayload;
import ai.nitro.bot4j.middle.domain.send.payload.type.SendPayloadTypeEnum;

@Singleton
public class VideoRuleImpl extends AbstractSlackSendRuleImpl {

	@Override
	public boolean applies(final SendMessage sendMessage) {
		return hasPayloadType(SendPayloadTypeEnum.VIDEO, sendMessage);
	}

	@Override
	public void apply(final SendMessage sendMessage) {
		final VideoSendPayload videoSendPayload = sendMessage.getPayloadWithType(VideoSendPayload.class);

		final String title = videoSendPayload.getTitle();
		final String videoUrl = videoSendPayload.getVideoUrl();

		final Attachment attachment = Attachment.builder().fallback(title).imageUrl(videoUrl).build();
		final List<Attachment> attachments = Lists.newArrayList(attachment);
		final String channel = sendMessage.getRecipient().getId();

		final Payload payload = Payload.builder().channel(channel).attachments(attachments).build();
		chatPostMessage(sendMessage, payload);
	}
}
