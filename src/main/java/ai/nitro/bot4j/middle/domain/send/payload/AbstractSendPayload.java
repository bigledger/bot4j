/*
 * Copyright (C) 2016, nitro.ai
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.send.payload;

import ai.nitro.bot4j.middle.domain.send.SendMessage;
import ai.nitro.bot4j.middle.domain.send.payload.type.SendPayloadType;

public abstract class AbstractSendPayload {

	protected transient SendMessage sendMessage;

	protected final SendPayloadType sendPayloadType;

	public AbstractSendPayload(final SendPayloadType sendPayloadType, final SendMessage sendMessage) {
		assert sendPayloadType != null;
		assert sendMessage != null;

		this.sendPayloadType = sendPayloadType;
		this.sendMessage = sendMessage;
	}

	public SendMessage getSendMessage() {
		return sendMessage;
	}

	public SendPayloadType getSendPayloadType() {
		return sendPayloadType;
	}

	public void setSendMessage(final SendMessage sendMessage) {
		this.sendMessage = sendMessage;
	}
}
