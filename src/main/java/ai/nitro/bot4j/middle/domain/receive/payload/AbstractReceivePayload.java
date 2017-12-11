/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.receive.payload;

import ai.nitro.bot4j.middle.domain.receive.ReceiveMessage;

public abstract class AbstractReceivePayload {

	public enum Type {
		COORDINATE, DATA, DELIVERY_NOTIFICATION, FORM, POSTBACK, QUICK_REPLY, READ_NOTIFICATION, TEXT, URL_ATTACHMENT
	}

	protected transient ReceiveMessage receiveMessage;

	protected final Type type;

	public AbstractReceivePayload(final Type type) {
		this.type = type;
	}

	public ReceiveMessage getReceiveMessage() {
		return receiveMessage;
	}

	public Type getType() {
		return type;
	}

	public void setReceiveMessage(final ReceiveMessage receiveMessage) {
		this.receiveMessage = receiveMessage;
	}
}
