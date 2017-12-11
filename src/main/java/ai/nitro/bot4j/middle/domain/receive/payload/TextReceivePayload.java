/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.receive.payload;

import ai.nitro.bot4j.middle.domain.receive.nlp.NlpContext;

public class TextReceivePayload extends AbstractReceivePayload {

	protected NlpContext nlpContext;

	protected String text;

	public TextReceivePayload() {
		super(Type.TEXT);
	}

	public NlpContext getNlpContext() {
		return nlpContext;
	}

	public String getText() {
		return text;
	}

	public void setNlpContext(final NlpContext nlpContext) {
		this.nlpContext = nlpContext;
	}

	public void setText(final String text) {
		this.text = text;
	}
}
