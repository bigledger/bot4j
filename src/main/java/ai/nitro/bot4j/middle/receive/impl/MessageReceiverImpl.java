/*
 * Copyright (C) 2016, nitro.ai
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.receive.impl;

import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ai.nitro.bot4j.bot.Bot;
import ai.nitro.bot4j.middle.domain.Participant;
import ai.nitro.bot4j.middle.domain.Platform;
import ai.nitro.bot4j.middle.domain.Session;
import ai.nitro.bot4j.middle.domain.receive.ReceiveMessage;
import ai.nitro.bot4j.middle.receive.DuplicateMessageFilter;
import ai.nitro.bot4j.middle.receive.MessageReceiver;
import ai.nitro.bot4j.middle.receive.SessionManager;

@Singleton
public class MessageReceiverImpl implements MessageReceiver {

	final static Logger LOG = LogManager.getLogger(MessageReceiverImpl.class);

	@Inject
	protected Bot bot;

	@Inject
	protected DuplicateMessageFilter duplicateMessageFilter;

	@Inject
	protected SessionManager sessionManager;

	protected void doReceive(final ReceiveMessage receiveMessage) {
		try {
			final Session session = sessionManager.getSession(receiveMessage);
			receiveMessage.setSession(session);

			final boolean isDuplicateMessage = duplicateMessageFilter.isDuplicate(receiveMessage);

			if (isDuplicateMessage) {
				LOG.info("ignoring duplicate message {}", receiveMessage);
			} else {
				onBotMessage(receiveMessage);
			}
		} catch (final Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	protected void onBotMessage(final ReceiveMessage receiveMessage) throws Exception {
		bot.onMessage(receiveMessage);
	}

	@Override
	public void receive(final ReceiveMessage receiveMessage) {
		final Participant sender = receiveMessage.getSender();
		final Platform platform = sender.getPlatform();

		if (platform.isVoice()) {
			doReceive(receiveMessage);
		} else {
			CompletableFuture.runAsync(() -> doReceive(receiveMessage));
		}
	}
}
