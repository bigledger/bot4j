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
import com.restfb.types.send.MediaAttachment;
import com.restfb.types.send.Message;

import ai.nitro.bot4j.middle.domain.send.SendMessage;
import ai.nitro.bot4j.middle.domain.send.payload.VideoSendPayload;
import ai.nitro.bot4j.middle.domain.send.payload.type.SendPayloadTypeEnum;

@Singleton
public class VideoRuleImpl extends AbstractFacebookSendRuleImpl {

	@Override
	public boolean applies(final SendMessage sendMessage) {
		return hasPayloadType(SendPayloadTypeEnum.VIDEO, sendMessage);
	}

	@Override
	public void apply(final SendMessage sendMessage) {
		final VideoSendPayload videoSendPayload = sendMessage.getPayloadWithType(VideoSendPayload.class);

		final MediaAttachment.Type type = MediaAttachment.Type.VIDEO;
		final String videoUrl = videoSendPayload.getVideoUrl();

		final MediaAttachment mediaAttachment = new MediaAttachment(type, videoUrl);
		final Message message = new Message(mediaAttachment);

		final IdMessageRecipient recipient = createIdMessageRecipient(sendMessage.getRecipient());
		publish(sendMessage, message, recipient);
	}
}
