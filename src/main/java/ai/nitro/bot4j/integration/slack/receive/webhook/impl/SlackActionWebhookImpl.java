/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.slack.receive.webhook.impl;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ai.nitro.bot4j.integration.slack.receive.SlackReceiveHandler;
import ai.nitro.bot4j.integration.slack.receive.webhook.SlackActionWebhook;

@Singleton
public class SlackActionWebhookImpl implements SlackActionWebhook {

	private final static Logger LOG = LogManager.getLogger(SlackActionWebhookImpl.class);

	@Inject
	protected SlackReceiveHandler slackReceiveHandler;

	protected void handleException(final Exception e) {
		LOG.error(e.getMessage(), e);
	}

	@Override
	public String post(final HttpServletRequest req, final HttpServletResponse res) {
		try {
			final String payload = req.getParameter("payload");
			final JsonParser jsonParser = new JsonParser();
			final JsonObject json = jsonParser.parse(payload).getAsJsonObject();

			final Map<String, String[]> params = req.getParameterMap();

			slackReceiveHandler.handleAction(json, params);
		} catch (final Exception e) {
			handleException(e);
		}

		return "";
	}
}
