/*
 * Copyright (C) 2016, nitro.ai
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.telegram.receive;

import com.pengrad.telegrambot.model.Audio;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Location;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Sticker;
import com.pengrad.telegrambot.model.Voice;

import ai.nitro.bot4j.middle.domain.receive.ReceiveMessage;
import ai.nitro.bot4j.middle.domain.receive.payload.CoordinateReceivePayload;
import ai.nitro.bot4j.middle.domain.receive.payload.PostbackReceivePayload;
import ai.nitro.bot4j.middle.domain.receive.payload.TextReceivePayload;
import ai.nitro.bot4j.middle.domain.receive.payload.UrlAttachmentReceivePayload;

public interface TelegramReceivePayloadFactory {

	UrlAttachmentReceivePayload createAudio(ReceiveMessage receiveMessage, Audio audio);

	UrlAttachmentReceivePayload createDocument(ReceiveMessage receiveMessage, Message message);

	UrlAttachmentReceivePayload createPhoto(ReceiveMessage receiveMessage, Message message);

	PostbackReceivePayload createPostback(CallbackQuery callbackQuery);

	UrlAttachmentReceivePayload createSticker(ReceiveMessage receiveMessage, Sticker sticker);

	TextReceivePayload createTextPayload(String text);

	UrlAttachmentReceivePayload createVideo(ReceiveMessage receiveMessage, Message message);

	UrlAttachmentReceivePayload createVoice(ReceiveMessage receiveMessage, Voice voice);

	CoordinateReceivePayload getCoordinationPayload(Location location);
}
