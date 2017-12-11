/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.payload.impl;

import javax.inject.Singleton;

import com.google.gson.Gson;

import ai.nitro.bot4j.middle.payload.PostbackPayload;
import ai.nitro.bot4j.middle.payload.PostbackPayloadService;

@Singleton
public class PostbackPayloadServiceImpl implements PostbackPayloadService {

	@Override
	public PostbackPayload deserialize(final String serializedPayload) {
		final Gson gson = new Gson();
		final PostbackPayload result = gson.fromJson(serializedPayload, PostbackPayload.class);
		return result;
	}

	@Override
	public String serialize(final PostbackPayload postbackPayload) {
		final Gson gson = new Gson();
		final String result = gson.toJson(postbackPayload);
		return result;
	}
}
