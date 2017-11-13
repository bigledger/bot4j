/*
 * Copyright (C) 2016, nitro.ai
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.send.payload;

import ai.nitro.bot4j.middle.domain.send.SendMessage;

public abstract class AbstractSendPayload {

	public enum Type {
		BUBBLE, BUTTONS, CAMERA, FORM, IMAGE, LIST, QUICK_REPLIES, TEXT, TYPING, VIDEO
	}

	protected transient SendMessage sendMessage;

	protected final Type type;

	public AbstractSendPayload(final Type type, final SendMessage sendMessage) {
		assert type != null;
		assert sendMessage != null;

		this.type = type;
		this.sendMessage = sendMessage;
	}

	public SendMessage getSendMessage() {
		return sendMessage;
	}

	public Type getType() {
		return type;
	}

	public void setSendMessage(final SendMessage sendMessage) {
		this.sendMessage = sendMessage;
	}
}
