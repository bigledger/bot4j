/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.send;

import java.util.HashMap;
import java.util.Map;

import ai.nitro.bot4j.middle.domain.AbstractMessage;
import ai.nitro.bot4j.middle.domain.Session;
import ai.nitro.bot4j.middle.domain.send.payload.AbstractSendPayload;

public class SendMessage extends AbstractMessage {

	protected final Map<String, String> params = new HashMap<String, String>();

	protected AbstractSendPayload payload;

	protected Session session;

	public String getParam(final String key) {
		return params.get(key);
	}

	public Map<String, String> getParams() {
		return params;
	}

	public AbstractSendPayload getPayload() {
		return payload;
	}

	@SuppressWarnings("unchecked")
	public <T extends AbstractSendPayload> T getPayloadWithType(final Class<T> clazz) {
		final T result;

		if (payload.getClass().isAssignableFrom(clazz)) {
			result = (T) payload;
		} else {
			result = null;
		}

		return result;
	}

	public Session getSession() {
		return session;
	}

	public void putParams(final String key, final String value) {
		params.put(key, value);
	}

	public void setPayload(final AbstractSendPayload payload) {
		this.payload = payload;
	}

	public void setSession(final Session session) {
		this.session = session;
	}

	@Override
	public String toString() {
		return super.toString() + ", payload=[" + payload + "]";
	}
}
