/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.telegram.receive.impl;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import com.pengrad.telegrambot.model.Audio;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Location;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Sticker;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.Voice;

import ai.nitro.bot4j.integration.telegram.domain.TelegramPlatformEnum;
import ai.nitro.bot4j.integration.telegram.receive.TelegramReceiveMessageFactory;
import ai.nitro.bot4j.integration.telegram.receive.TelegramReceivePayloadFactory;
import ai.nitro.bot4j.middle.domain.Participant;
import ai.nitro.bot4j.middle.domain.receive.ReceiveMessage;
import ai.nitro.bot4j.middle.domain.receive.payload.CoordinateReceivePayload;
import ai.nitro.bot4j.middle.domain.receive.payload.PostbackReceivePayload;
import ai.nitro.bot4j.middle.domain.receive.payload.TextReceivePayload;
import ai.nitro.bot4j.middle.domain.receive.payload.UrlAttachmentReceivePayload;

@Singleton
public class TelegramReceiveMessageFactoryImpl implements TelegramReceiveMessageFactory {

	private static final String GET_STARTED = "GET_STARTED";

	final static Logger LOG = LogManager.getLogger(TelegramReceiveMessageFactoryImpl.class);

	@Inject
	protected TelegramReceivePayloadFactory telegramReceivePayloadFactory;

	@Override
	public ReceiveMessage createReceiveMessage(final Update update, final Map<String, String[]> params) {
		final ReceiveMessage result = new ReceiveMessage();
		result.setNativePayload(TelegramPlatformEnum.TELEGRAM, update);

		// has to be here, as the params are required in the following method calls
		if (params != null) {
			result.getParams().putAll(params);
		}

		if (update.message() != null) {
			handleMessage(update.message(), result);
			handleSender(update.message().chat().id().intValue(), result);
		}

		if (update.callbackQuery() != null) {
			handlePostback(update.callbackQuery(), result);
			handleSender(update.callbackQuery().from().id(), result);
		}

		return result;
	}

	protected void handleAudio(final Audio audio, final ReceiveMessage result) {
		final UrlAttachmentReceivePayload urlAttachmentReceivePayload = telegramReceivePayloadFactory
				.createAudio(result, audio);
		result.addPayload(urlAttachmentReceivePayload);
	}

	protected void handleDocument(final Message message, final ReceiveMessage result) {
		final UrlAttachmentReceivePayload urlAttachmentReceivePayload = telegramReceivePayloadFactory
				.createDocument(result, message);
		result.addPayload(urlAttachmentReceivePayload);
	}

	protected void handleLocation(final Location location, final ReceiveMessage result) {
		final CoordinateReceivePayload coordinationReceivePayload = telegramReceivePayloadFactory
				.getCoordinationPayload(location);
		result.addPayload(coordinationReceivePayload);
	}

	protected void handleMessage(final Message message, final ReceiveMessage result) {
		final String messageId = String.valueOf(message.messageId());
		result.setMessageId(messageId);

		if (message.audio() != null) {
			handleAudio(message.audio(), result);
		}

		if (message.document() != null) {
			handleDocument(message, result);
		}

		if (message.location() != null) {
			handleLocation(message.location(), result);
		}

		if (message.photo() != null) {
			handlePhoto(message, result);
		}

		if (message.sticker() != null) {
			handleSticker(message.sticker(), result);
		}

		if (!Strings.isBlank(message.text())) {
			if ("/start".equals(message.text())) {
				handleStartMessage(message.text(), result);
			} else {
				handleText(message.text(), result);
			}
		}

		if (message.video() != null) {
			handleVideo(message, result);
		}

		if (message.voice() != null) {
			handleVoice(message.voice(), result);
		}
	}

	protected void handlePhoto(final Message message, final ReceiveMessage result) {
		final UrlAttachmentReceivePayload urlAttachmentReceivePayload = telegramReceivePayloadFactory
				.createPhoto(result, message);
		result.addPayload(urlAttachmentReceivePayload);
	}

	protected void handlePostback(final CallbackQuery callbackQuery, final ReceiveMessage result) {
		final PostbackReceivePayload postback = telegramReceivePayloadFactory.createPostback(callbackQuery);
		result.addPayload(postback);
	}

	protected void handleSender(final Integer sender, final ReceiveMessage result) {
		final Participant participant = new Participant();
		participant.setPlatform(TelegramPlatformEnum.TELEGRAM);
		participant.setId(String.valueOf(sender));
		result.setSender(participant);
	}

	protected void handleStartMessage(final String text, final ReceiveMessage result) {
		final PostbackReceivePayload postbackReceivePayload = new PostbackReceivePayload();
		postbackReceivePayload.setName(GET_STARTED);

		result.addPayload(postbackReceivePayload);
	}

	protected void handleSticker(final Sticker sticker, final ReceiveMessage result) {
		final UrlAttachmentReceivePayload urlAttachmentReceivePayload = telegramReceivePayloadFactory
				.createSticker(result, sticker);
		result.addPayload(urlAttachmentReceivePayload);
	}

	protected void handleText(final String text, final ReceiveMessage result) {
		final TextReceivePayload textPayload = telegramReceivePayloadFactory.createTextPayload(text);
		result.addPayload(textPayload);
	}

	protected void handleVideo(final Message message, final ReceiveMessage result) {
		final UrlAttachmentReceivePayload urlAttachmentReceivePayload = telegramReceivePayloadFactory
				.createVideo(result, message);
		result.addPayload(urlAttachmentReceivePayload);
	}

	protected void handleVoice(final Voice voice, final ReceiveMessage result) {
		final UrlAttachmentReceivePayload urlAttachmentReceivePayload = telegramReceivePayloadFactory
				.createVoice(result, voice);
		result.addPayload(urlAttachmentReceivePayload);
	}
}
