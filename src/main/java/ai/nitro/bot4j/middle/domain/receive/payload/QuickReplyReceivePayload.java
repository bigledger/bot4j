/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.receive.payload;

public class QuickReplyReceivePayload extends AbstractReceivePayload {

	protected String payload;

	public QuickReplyReceivePayload() {
		super(Type.QUICK_REPLY);
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(final String payload) {
		this.payload = payload;
	}
}
