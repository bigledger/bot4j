/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.slack.receive.impl;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonObject;

import ai.nitro.bot4j.integration.slack.receive.SlackReceiveActionMessageFactory;
import ai.nitro.bot4j.integration.slack.receive.SlackReceiveEventMessageFactory;
import ai.nitro.bot4j.integration.slack.receive.SlackReceiveHandler;
import ai.nitro.bot4j.middle.domain.receive.ReceiveMessage;
import ai.nitro.bot4j.middle.receive.MessageReceiver;

@Singleton
public class SlackReceiveHandlerImpl implements SlackReceiveHandler {

	final static Logger LOG = LogManager.getLogger(SlackReceiveHandlerImpl.class);

	@Inject
	protected MessageReceiver messageReceiver;

	@Inject
	protected SlackReceiveActionMessageFactory slackReceiveActionMessageFactory;

	@Inject
	protected SlackReceiveEventMessageFactory slackReceiveEventMessageFactory;

	@Override
	public void handleAction(final JsonObject actionJsonObject, final Map<String, String[]> params) {
		final ReceiveMessage receiveMessage = slackReceiveActionMessageFactory.createReceiveMessage(actionJsonObject,
				params);

		if (receiveMessage != null) {
			messageReceiver.receive(receiveMessage);
		}
	}

	@Override
	public void handleEvent(final JsonObject eventJsonObject) {
		final ReceiveMessage receiveMessage = slackReceiveEventMessageFactory.createReceiveMessage(eventJsonObject);

		if (receiveMessage != null) {
			messageReceiver.receive(receiveMessage);
		}
	}
}
