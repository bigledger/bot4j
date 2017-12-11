/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.telegram.send.rules.impl;

import javax.inject.Singleton;

import org.apache.logging.log4j.util.Strings;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendVideo;

import ai.nitro.bot4j.middle.domain.send.SendMessage;
import ai.nitro.bot4j.middle.domain.send.payload.VideoSendPayload;
import ai.nitro.bot4j.middle.domain.send.payload.type.SendPayloadTypeEnum;

@Singleton
public class VideoRuleImpl extends AbstractTelegramSendRuleImpl {

	@Override
	public boolean applies(final SendMessage sendMessage) {
		return hasPayloadType(SendPayloadTypeEnum.VIDEO, sendMessage);
	}

	@Override
	public void apply(final SendMessage sendMessage) {
		final VideoSendPayload videoSendPayload = sendMessage.getPayloadWithType(VideoSendPayload.class);

		final String title = videoSendPayload.getTitle();
		final String videoUrl = videoSendPayload.getVideoUrl();
		final String recipient = sendMessage.getRecipient().getId();

		final SendVideo sendVideoTelegram = new SendVideo(recipient, videoUrl);

		if (!Strings.isBlank(title)) {
			sendVideoTelegram.caption(title);
		}

		LOG.info("sending video {} to recipient {}", sendVideoTelegram, recipient);
		final TelegramBot client = provideTelegramBot(sendMessage);
		client.execute(sendVideoTelegram);
	}
}
