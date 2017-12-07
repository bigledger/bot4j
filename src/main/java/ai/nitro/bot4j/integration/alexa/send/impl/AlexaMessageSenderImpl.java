/*
 * Copyright (C) 2016, nitro.ai
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.alexa.send.impl;

import javax.inject.Singleton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazon.speech.ui.Card;

import ai.nitro.bot4j.integration.alexa.domain.AlexaPlatformEnum;
import ai.nitro.bot4j.integration.alexa.send.AlexaMessageSender;
import ai.nitro.bot4j.middle.domain.Platform;
import ai.nitro.bot4j.middle.domain.send.SendMessage;
import ai.nitro.bot4j.middle.domain.send.payload.AbstractSendPayload;
import ai.nitro.bot4j.middle.domain.send.payload.TextSendPayload;
import ai.nitro.bot4j.middle.domain.send.payload.type.SendPayloadType;
import ai.nitro.bot4j.middle.domain.send.payload.type.SendPayloadTypeEnum;

@Singleton
public class AlexaMessageSenderImpl implements AlexaMessageSender {

	static Logger LOG = LogManager.getLogger(AlexaMessageSenderImpl.class);

	protected ThreadLocal<Card> threadLocalAlexaCard = new ThreadLocal<>();

	protected ThreadLocal<Boolean> threadLocalShouldEndSession = new ThreadLocal<>();

	protected ThreadLocal<StringBuilder> threadLocalText = new ThreadLocal<>();

	protected void assureThreadLocalStringBuilder() {
		if (threadLocalText.get() == null) {
			threadLocalText.set(new StringBuilder());
		}
	}

	@Override
	public Card getCard() {
		final Card result = threadLocalAlexaCard.get();
		threadLocalAlexaCard.remove();
		return result;
	}

	@Override
	public Platform getPlatform() {
		return AlexaPlatformEnum.ALEXA;
	}

	@Override
	public boolean getShouldEndSession() {
		final boolean result = threadLocalShouldEndSession.get().booleanValue();
		threadLocalShouldEndSession.remove();
		return result;
	}

	@Override
	public String getText() {
		final String result;

		if (threadLocalText == null) {
			result = "";
		} else {
			result = threadLocalText.get().toString();
		}

		threadLocalText.remove();
		return result;
	}

	@Override
	public boolean send(final SendMessage sendMessage) {
		boolean result = false;

		final AbstractSendPayload sendPayload = sendMessage.getPayload();
		final SendPayloadType sendPayloadType = sendPayload.getSendPayloadType();

		if (SendPayloadTypeEnum.TEXT.equals(sendPayloadType)) {
			final TextSendPayload textSendPayload = (TextSendPayload) sendPayload;
			final String text = textSendPayload.getText();

			final Card alexaCard = textSendPayload.getAlexaCard();
			if (alexaCard != null) {
				final Card card = alexaCard;
				threadLocalAlexaCard.set(card);
			}

			final boolean shouldEndSession = textSendPayload.shouldEndSession;
			threadLocalShouldEndSession.set(shouldEndSession);

			assureThreadLocalStringBuilder();
			threadLocalText.get().append(text);

			result = true;
		} else {
			LOG.warn("ignoring send payload with type {}", sendPayloadType);
		}

		return result;
	}
}
