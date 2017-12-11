/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.facebook.receive.impl;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.restfb.types.webhook.messaging.MessagingItem;

import ai.nitro.bot4j.integration.facebook.receive.FacebookReceiveHandler;
import ai.nitro.bot4j.integration.facebook.receive.FacebookReceiveMessageFactory;
import ai.nitro.bot4j.middle.domain.receive.ReceiveMessage;
import ai.nitro.bot4j.middle.receive.MessageReceiver;

@Singleton
public class FacebookReceiveHandlerImpl implements FacebookReceiveHandler {

	@Inject
	protected FacebookReceiveMessageFactory facebookReceiveMessageFactory;

	@Inject
	protected MessageReceiver messageReceiver;

	@Override
	public void handleMessagingItem(final MessagingItem messagingItem, final Map<String, String[]> params) {
		final ReceiveMessage receiveMessage = facebookReceiveMessageFactory.createReceiveMessage(messagingItem, params);

		if (receiveMessage != null) {
			messageReceiver.receive(receiveMessage);
		}
	}
}
