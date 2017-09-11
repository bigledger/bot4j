/*
 * Copyright (C) 2016, nitro.ai
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.slack.receive;

import java.util.Map;

import com.google.gson.JsonObject;

public interface SlackReceiveHandler {

	void handleAction(JsonObject actionJsonObject, Map<String, String[]> params);

	void handleEvent(JsonObject eventJsonObject);
}
