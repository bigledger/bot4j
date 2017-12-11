/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.slack.receive;

import com.google.gson.JsonObject;

import ai.nitro.bot4j.middle.domain.receive.payload.PostbackReceivePayload;
import ai.nitro.bot4j.middle.domain.receive.payload.TextReceivePayload;

public interface SlackReceivePayloadFactory {

	PostbackReceivePayload createPostbackReceivePayload(JsonObject actionJson);

	TextReceivePayload createTextReceivePayload(String text);

}
