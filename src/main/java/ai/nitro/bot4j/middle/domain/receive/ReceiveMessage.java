/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.receive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ai.nitro.bot4j.middle.domain.AbstractMessage;
import ai.nitro.bot4j.middle.domain.receive.payload.AbstractReceivePayload;

public class ReceiveMessage extends AbstractMessage {

	protected final Map<String, String> cookies = new HashMap<String, String>();

	protected final Map<String, String[]> params = new HashMap<String, String[]>();

	protected final List<AbstractReceivePayload> payloads = new ArrayList<AbstractReceivePayload>();

	public void addPayload(final AbstractReceivePayload payload) {
		payload.setReceiveMessage(this);
		payloads.add(payload);
	}

	public void addPayloads(final List<AbstractReceivePayload> payloads) {
		for (final AbstractReceivePayload payload : payloads) {
			addPayload(payload);
		}
	}

	public void clearPayloads() {
		payloads.clear();
	}

	public Map<String, String> getCookies() {
		return cookies;
	}

	public Map<String, String[]> getParams() {
		return params;
	}

	public List<AbstractReceivePayload> getPayloads() {
		return payloads;
	}

	@SuppressWarnings("unchecked")
	public <T extends AbstractReceivePayload> List<T> getPayloadsWithType(final Class<T> clazz) {
		final List<T> result = new ArrayList<T>();

		for (final AbstractReceivePayload payload : payloads) {
			if (payload.getClass().isAssignableFrom(clazz)) {
				result.add((T) payload);
			}
		}

		return result;
	}

	@Override
	public String toString() {
		return super.toString() + ", payloads=[" + payloads + "]";
	}
}
