/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.telegram.receive.impl;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Audio;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Document;
import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.model.Location;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Sticker;
import com.pengrad.telegrambot.model.Video;
import com.pengrad.telegrambot.model.Voice;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.response.GetFileResponse;

import ai.nitro.bot4j.integration.telegram.config.TelegramConfigService;
import ai.nitro.bot4j.integration.telegram.receive.TelegramReceivePayloadFactory;
import ai.nitro.bot4j.middle.domain.receive.ReceiveMessage;
import ai.nitro.bot4j.middle.domain.receive.payload.CoordinateReceivePayload;
import ai.nitro.bot4j.middle.domain.receive.payload.PostbackReceivePayload;
import ai.nitro.bot4j.middle.domain.receive.payload.TextReceivePayload;
import ai.nitro.bot4j.middle.domain.receive.payload.UrlAttachmentReceivePayload;
import ai.nitro.bot4j.middle.payload.PostbackPayload;
import ai.nitro.bot4j.middle.payload.PostbackPayloadService;

@Singleton
public class TelegramReceivePayloadFactoryImpl implements TelegramReceivePayloadFactory {

	@Inject
	protected PostbackPayloadService postbackPayloadService;

	@Inject
	protected TelegramConfigService telegramConfigService;

	@Override
	public UrlAttachmentReceivePayload createAudio(final ReceiveMessage receiveMessage, final Audio audio) {
		final String fileId = audio.fileId();
		final String url = getFileUrl(receiveMessage, fileId);

		final UrlAttachmentReceivePayload urlAttachmentReceivePayload = new UrlAttachmentReceivePayload();
		urlAttachmentReceivePayload.setTitle(fileId);
		urlAttachmentReceivePayload.setUrl(url);

		return urlAttachmentReceivePayload;
	}

	@Override
	public UrlAttachmentReceivePayload createDocument(final ReceiveMessage receiveMessage, final Message message) {
		final Document document = message.document();
		final String fileId = document.fileId();
		final String url = getFileUrl(receiveMessage, fileId);

		final UrlAttachmentReceivePayload urlAttachmentReceivePayload = new UrlAttachmentReceivePayload();
		createTitle(message, fileId, urlAttachmentReceivePayload);
		urlAttachmentReceivePayload.setUrl(url);

		return urlAttachmentReceivePayload;
	}

	@Override
	public UrlAttachmentReceivePayload createPhoto(final ReceiveMessage receiveMessage, final Message message) {
		final PhotoSize[] photo = message.photo();
		final String fileId = photo[0].fileId();
		final String url = getFileUrl(receiveMessage, fileId);

		final UrlAttachmentReceivePayload urlAttachmentReceivePayload = new UrlAttachmentReceivePayload();
		createTitle(message, fileId, urlAttachmentReceivePayload);
		urlAttachmentReceivePayload.setUrl(url);

		return urlAttachmentReceivePayload;
	}

	@Override
	public PostbackReceivePayload createPostback(final CallbackQuery callbackQuery) {
		final PostbackReceivePayload result = new PostbackReceivePayload();
		final String serializedPayload = callbackQuery.data();

		final PostbackPayload postbackPayload = postbackPayloadService.deserialize(serializedPayload);
		result.setName(postbackPayload.name);
		result.setPayload(postbackPayload.payload);

		return result;
	}

	@Override
	public UrlAttachmentReceivePayload createSticker(final ReceiveMessage receiveMessage, final Sticker sticker) {
		final String fileId = sticker.fileId();
		final String url = getFileUrl(receiveMessage, fileId);

		final UrlAttachmentReceivePayload urlAttachmentReceivePayload = new UrlAttachmentReceivePayload();
		urlAttachmentReceivePayload.setTitle(fileId);
		urlAttachmentReceivePayload.setUrl(url);

		return urlAttachmentReceivePayload;
	}

	@Override
	public TextReceivePayload createTextPayload(final String text) {
		final TextReceivePayload result = new TextReceivePayload();
		result.setText(text);
		return result;
	}

	protected void createTitle(final Message message, final String fileId,
			final UrlAttachmentReceivePayload urlAttachmentReceivePayload) {
		if (message.caption() != null) {
			urlAttachmentReceivePayload.setTitle(message.caption());
		} else {
			urlAttachmentReceivePayload.setTitle(fileId);
		}
	}

	@Override
	public UrlAttachmentReceivePayload createVideo(final ReceiveMessage receiveMessage, final Message message) {
		final Video video = message.video();
		final String fileId = video.fileId();
		final String url = getFileUrl(receiveMessage, fileId);

		final UrlAttachmentReceivePayload urlAttachmentReceivePayload = new UrlAttachmentReceivePayload();
		createTitle(message, fileId, urlAttachmentReceivePayload);
		urlAttachmentReceivePayload.setUrl(url);

		return urlAttachmentReceivePayload;
	}

	@Override
	public UrlAttachmentReceivePayload createVoice(final ReceiveMessage receiveMessage, final Voice voice) {
		final String fileId = voice.fileId();
		final String url = getFileUrl(receiveMessage, fileId);

		final UrlAttachmentReceivePayload urlAttachmentReceivePayload = new UrlAttachmentReceivePayload();
		urlAttachmentReceivePayload.setTitle(fileId);
		urlAttachmentReceivePayload.setUrl(url);

		return urlAttachmentReceivePayload;
	}

	@Override
	public CoordinateReceivePayload getCoordinationPayload(final Location location) {
		final double latVal = location.latitude();
		final double lonVal = location.longitude();

		final CoordinateReceivePayload result = new CoordinateReceivePayload();
		result.setLatVal(latVal);
		result.setLonVal(lonVal);

		return result;
	}

	protected String getFileUrl(final ReceiveMessage receiveMessage, final String fileId) {
		final TelegramBot bot = provideTelegramBot(receiveMessage);

		final GetFile getFile = new GetFile(fileId);
		final GetFileResponse response = bot.execute(getFile);
		final File file = response.file();
		final String url = bot.getFullFilePath(file);

		return url;
	}

	protected TelegramBot provideTelegramBot(final ReceiveMessage receiveMessage) {
		final String telegramAccessToken = telegramConfigService.getAccessToken(receiveMessage);
		final TelegramBot result = TelegramBotAdapter.build(telegramAccessToken);
		return result;
	}
}
