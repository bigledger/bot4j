/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.send.impl;

import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.restfb.exception.FacebookOAuthException;

import ai.nitro.bot4j.middle.domain.Participant;
import ai.nitro.bot4j.middle.domain.Platform;
import ai.nitro.bot4j.middle.domain.send.SendMessage;
import ai.nitro.bot4j.middle.send.MessageSender;
import ai.nitro.bot4j.middle.send.PlatformMessageSender;

@Singleton
public class MessageSenderImpl implements MessageSender {

	protected final static Logger LOG = LogManager.getLogger(MessageSenderImpl.class);

	@Inject
	protected Map<Platform, PlatformMessageSender> platformMessageSenders;

	protected boolean doSend(final SendMessage sendMessage, final Platform platform) {
		boolean result = true;

		try {
			final PlatformMessageSender platformMessageSender = platformMessageSenders.get(platform);

			if (platformMessageSender == null) {
				LOG.error("no platform message sender configured for {} and {}", platform, sendMessage);
				result = false;
			} else {
				result = onSend(sendMessage, platformMessageSender);
			}
		} catch (final FacebookOAuthException e) {
			LOG.warn("Could not send fb message: {}", e.getMessage());
			result = false;
		} catch (final Exception e) {
			LOG.error(e.getMessage(), e);
			result = false;
		}

		return result;
	}

	protected void enrichSendMessageId(final SendMessage sendMessage) {
		final String messageId = UUID.randomUUID().toString();
		sendMessage.setMessageId(messageId);
	}

	protected boolean onSend(final SendMessage sendMessage, final PlatformMessageSender platformMessageSender) {
		return platformMessageSender.send(sendMessage);
	}

	@Override
	public boolean send(final SendMessage sendMessage) {
		final Participant recipient = sendMessage.getRecipient();
		final boolean result;

		enrichSendMessageId(sendMessage);

		if (recipient == null) {
			LOG.error("no recipient set in {}", sendMessage);
			result = false;
		} else {
			final Platform platform = recipient.getPlatform();

			if (platform == null) {
				LOG.error("no target platform set in {}", sendMessage);
				result = false;
			} else {
				result = doSend(sendMessage, platform);
			}
		}

		return result;
	}
}
