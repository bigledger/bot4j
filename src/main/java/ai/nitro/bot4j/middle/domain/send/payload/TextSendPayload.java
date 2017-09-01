/*
 * Copyright (C) 2016, nitro.ai
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.send.payload;

import com.amazon.speech.ui.Card;

public class TextSendPayload extends AbstractSendPayload {

	protected Card alexaCard;

	public boolean shouldEndSession = true;

	protected String text;

	public TextSendPayload() {
		super(Type.TEXT);
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
