/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.bot.impl;

import javax.inject.Inject;
import javax.inject.Singleton;

import ai.nitro.bot4j.bot.Bot;
import ai.nitro.bot4j.middle.domain.Participant;
import ai.nitro.bot4j.middle.domain.receive.ReceiveMessage;
import ai.nitro.bot4j.middle.domain.receive.payload.AbstractReceivePayload;
import ai.nitro.bot4j.middle.domain.receive.payload.AbstractReceivePayload.Type;
import ai.nitro.bot4j.middle.domain.receive.payload.CoordinateReceivePayload;
import ai.nitro.bot4j.middle.domain.receive.payload.DataReceivePayload;
import ai.nitro.bot4j.middle.domain.receive.payload.DeliveryNotificationReceivePayload;
import ai.nitro.bot4j.middle.domain.receive.payload.FormReceivePayload;
import ai.nitro.bot4j.middle.domain.receive.payload.PostbackReceivePayload;
import ai.nitro.bot4j.middle.domain.receive.payload.QuickReplyReceivePayload;
import ai.nitro.bot4j.middle.domain.receive.payload.ReadNotificationReceivePayload;
import ai.nitro.bot4j.middle.domain.receive.payload.TextReceivePayload;
import ai.nitro.bot4j.middle.domain.receive.payload.UrlAttachmentReceivePayload;
import ai.nitro.bot4j.middle.domain.send.SendMessage;
import ai.nitro.bot4j.middle.domain.send.payload.ImageSendPayload;
import ai.nitro.bot4j.middle.domain.send.payload.TextSendPayload;
import ai.nitro.bot4j.middle.domain.send.payload.TypingSendPayload;
import ai.nitro.bot4j.middle.domain.send.payload.TypingSendPayload.Typing;
import ai.nitro.bot4j.middle.send.MessageSender;

@Singleton
public class BotImpl implements Bot {

	@Inject
	protected MessageSender messageSender;

	/**
	 * Determines the recipient for the answer, i. e. the original sender.
	 */
	protected Participant determineRecipient(final AbstractReceivePayload payload) {
		final ReceiveMessage receiveMessage = payload.getReceiveMessage();
		return receiveMessage.getSender();
	}

	/**
	 * Determines the sender for the answer, i. e. the original recipient.
	 */
	protected Participant determineSender(final AbstractReceivePayload payload) {
		final ReceiveMessage receiveMessage = payload.getReceiveMessage();
		return receiveMessage.getRecipient();
	}

	@Override
	public void onMessage(final ReceiveMessage message) throws Exception {
		for (final AbstractReceivePayload payload : message.getPayloads()) {
			onReceivePayload(payload);
		}
	}

	protected void onReceiveAttachment(final UrlAttachmentReceivePayload payload) {

	}

	protected void onReceiveCoordinate(final CoordinateReceivePayload payload) throws Exception {

	}

	protected void onReceiveData(final DataReceivePayload payload) {

	}

	protected void onReceiveDeliveryNotification(final DeliveryNotificationReceivePayload payload) {

	}

	protected void onReceiveForm(final FormReceivePayload payload) {

	}

	protected void onReceivePayload(final AbstractReceivePayload payload) throws Exception {
		final Type type = payload.getType();

		switch (type) {
		case COORDINATE:
			onReceiveCoordinate((CoordinateReceivePayload) payload);
			break;
		case DATA:
			onReceiveData((DataReceivePayload) payload);
			break;
		case DELIVERY_NOTIFICATION:
			onReceiveDeliveryNotification((DeliveryNotificationReceivePayload) payload);
			break;
		case FORM:
			onReceiveForm((FormReceivePayload) payload);
			break;
		case POSTBACK:
			onReceivePostback((PostbackReceivePayload) payload);
			break;
		case QUICK_REPLY:
			onReceiveQuickReply((QuickReplyReceivePayload) payload);
			break;
		case READ_NOTIFICATION:
			onReceiveReadNotification((ReadNotificationReceivePayload) payload);
			break;
		case TEXT:
			onReceiveText((TextReceivePayload) payload);
			break;
		case URL_ATTACHMENT:
			onReceiveAttachment((UrlAttachmentReceivePayload) payload);
			break;
		default:
			break;
		}
	}

	protected void onReceivePostback(final PostbackReceivePayload payload) throws Exception {

	}

	protected void onReceiveQuickReply(final QuickReplyReceivePayload payload) throws Exception {

	}

	protected void onReceiveReadNotification(final ReadNotificationReceivePayload payload) throws Exception {

	}

	protected void onReceiveText(final TextReceivePayload payload) throws Exception {

	}

	protected void sendImage(final String title, final String imageUrl, final Participant recipient,
			final Participant sender) {
		final SendMessage sendMessage = new SendMessage();
		sendMessage.setRecipient(recipient);
		sendMessage.setSender(sender);

		final ImageSendPayload imageSendPayload = new ImageSendPayload(sendMessage);
		imageSendPayload.setTitle(title);
		imageSendPayload.setImageUrl(imageUrl);
		sendMessage.setPayload(imageSendPayload);

		messageSender.send(sendMessage);
	}

	protected void sendText(final String text, final Participant recipient, final Participant sender) {
		final SendMessage sendMessage = new SendMessage();
		sendMessage.setRecipient(recipient);
		sendMessage.setSender(sender);

		final TextSendPayload textSendPayload = new TextSendPayload(sendMessage);
		textSendPayload.setText(text);
		sendMessage.setPayload(textSendPayload);

		messageSender.send(sendMessage);
	}

	protected void sendTypingOff(final Participant recipient, final Participant sender) {
		final SendMessage sendMessage = new SendMessage();
		sendMessage.setRecipient(recipient);
		sendMessage.setSender(sender);

		final TypingSendPayload typingSendPayload = new TypingSendPayload(sendMessage);
		typingSendPayload.setTyping(Typing.OFF);
		sendMessage.setPayload(typingSendPayload);

		messageSender.send(sendMessage);
	}

	protected void sendTypingOn(final Participant recipient, final Participant sender) {
		final SendMessage sendMessage = new SendMessage();
		sendMessage.setRecipient(recipient);
		sendMessage.setSender(sender);

		final TypingSendPayload typingSendPayload = new TypingSendPayload(sendMessage);
		typingSendPayload.setTyping(Typing.ON);
		sendMessage.setPayload(typingSendPayload);

		messageSender.send(sendMessage);
	}
}
