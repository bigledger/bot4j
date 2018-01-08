/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.send.payload;

import ai.nitro.bot4j.middle.domain.send.SendMessage;
import ai.nitro.bot4j.middle.domain.send.payload.type.SendPayloadTypeEnum;

public class TypingSendPayload extends AbstractSendPayload {

	public enum Typing {
		OFF, ON
	}

	protected Typing typing;

	public TypingSendPayload(final SendMessage sendMessage) {
		super(SendPayloadTypeEnum.TYPING, sendMessage);
	}

	public Typing getTyping() {
		return typing;
	}

	public void setTyping(final Typing typing) {
		this.typing = typing;
	}

	@Override
	public String toString() {
		return "type=[" + sendPayloadType + "], typing=[" + typing + "]";
	}
}
