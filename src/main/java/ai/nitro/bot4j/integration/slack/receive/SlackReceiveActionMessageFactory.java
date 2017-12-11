/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.slack.receive;

import java.util.Map;

import com.google.gson.JsonObject;

import ai.nitro.bot4j.middle.domain.receive.ReceiveMessage;

public interface SlackReceiveActionMessageFactory {

	ReceiveMessage createReceiveMessage(JsonObject json, Map<String, String[]> params);

}
