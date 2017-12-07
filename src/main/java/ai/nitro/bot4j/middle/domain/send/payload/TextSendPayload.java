/*
 * Copyright (C) 2016, nitro.ai
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.send.payload;

import com.amazon.speech.ui.Card;

import ai.nitro.bot4j.middle.domain.send.SendMessage;
import ai.nitro.bot4j.middle.domain.send.payload.type.SendPayloadTypeEnum;

public class TextSendPayload extends AbstractSendPayload {

	protected Card alexaCard;

	public boolean shouldEndSession = true;

	protected String text;

	public TextSendPayload(final SendMessage sendMessage) {
		super(SendPayloadTypeEnum.TEXT, sendMessage);
	}

	public Card getAlexaCard() {
		return alexaCard;
	}

	public String getText() {
		return text;
	}

	public void setAlexaCard(final Card card) {
		alexaCard = card;
	}

	public void setText(final String text) {
		this.text = text;
	}

}
