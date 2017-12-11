/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.slack.receive.impl;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.gson.JsonObject;

import ai.nitro.bot4j.integration.slack.receive.SlackReceivePayloadFactory;
import ai.nitro.bot4j.middle.domain.receive.payload.PostbackReceivePayload;
import ai.nitro.bot4j.middle.domain.receive.payload.TextReceivePayload;
import ai.nitro.bot4j.middle.payload.PostbackPayload;
import ai.nitro.bot4j.middle.payload.PostbackPayloadService;

@Singleton
public class SlackReceivePayloadFactoryImpl implements SlackReceivePayloadFactory {

	private static final String NAME = "name";

	private static final String VALUE = "value";

	@Inject
	protected PostbackPayloadService postbackPayloadService;

	@Override
	public PostbackReceivePayload createPostbackReceivePayload(final JsonObject actionJson) {
		final PostbackReceivePayload result = new PostbackReceivePayload();
		result.setName(actionJson.get(NAME).getAsString());

		final String serializedPayload = actionJson.get(VALUE).getAsString();
		final PostbackPayload postbackPayload = postbackPayloadService.deserialize(serializedPayload);
		result.setPayload(postbackPayload.payload);

		return result;
	}

	@Override
	public TextReceivePayload createTextReceivePayload(final String text) {
		final TextReceivePayload result = new TextReceivePayload();
		result.setText(text);
		return result;
	}
}
