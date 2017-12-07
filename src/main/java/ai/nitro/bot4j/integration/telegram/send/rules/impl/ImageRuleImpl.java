/*
 * Copyright (C) 2016, nitro.ai
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.telegram.send.rules.impl;

import javax.inject.Singleton;

import org.apache.logging.log4j.util.Strings;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendPhoto;

import ai.nitro.bot4j.middle.domain.send.SendMessage;
import ai.nitro.bot4j.middle.domain.send.payload.ImageSendPayload;
import ai.nitro.bot4j.middle.domain.send.payload.type.SendPayloadTypeEnum;

@Singleton
public class ImageRuleImpl extends AbstractTelegramSendRuleImpl {

	@Override
	public boolean applies(final SendMessage sendMessage) {
		return hasPayloadType(SendPayloadTypeEnum.IMAGE, sendMessage);
	}

	@Override
	public void apply(final SendMessage sendMessage) {
		final ImageSendPayload imageSendPayload = sendMessage.getPayloadWithType(ImageSendPayload.class);

		final String title = imageSendPayload.getTitle();
		final String imageUrl = imageSendPayload.getImageUrl();
		final String recipient = sendMessage.getRecipient().getId();

		final SendPhoto sendPhotoTelegram = new SendPhoto(recipient, imageUrl);

		if (!Strings.isBlank(title)) {
			sendPhotoTelegram.caption(title);
		}

		LOG.info("sending image {} to recipient {}", sendPhotoTelegram, recipient);
		final TelegramBot client = provideTelegramBot(sendMessage);
		client.execute(sendPhotoTelegram);
	}
}
