/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.send.button;

public class PostbackSendButton extends AbstractSendButton {

	protected String name;

	protected String[] payload;

	public PostbackSendButton() {
		super(Type.POSTBACK_BUTTON);
	}

	public String getName() {
		return name;
	}

	public String[] getPayload() {
		return payload;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setPayload(final String... payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "type=[" + type + "], title=[" + title + "], name=[" + name + "], payload=[" + payload + "]";
	}
}
