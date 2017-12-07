/*
 * Copyright (C) 2016, nitro.ai
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.facebook.send.rules.impl;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.send.IdMessageRecipient;
import com.restfb.types.send.Message;
import com.restfb.types.send.SendResponse;
import com.restfb.types.send.SenderActionEnum;

import ai.nitro.bot4j.integration.facebook.config.FacebookConfigService;
import ai.nitro.bot4j.integration.facebook.send.rules.FacebookSendRule;
import ai.nitro.bot4j.middle.domain.Participant;
import ai.nitro.bot4j.middle.domain.send.SendMessage;
import ai.nitro.bot4j.middle.domain.send.payload.AbstractSendPayload;
import ai.nitro.bot4j.middle.domain.send.payload.type.SendPayloadType;

@Singleton
public abstract class AbstractFacebookSendRuleImpl implements FacebookSendRule {

	final static Logger LOG = LogManager.getLogger(AbstractFacebookSendRuleImpl.class);

	@Inject
	protected FacebookConfigService facebookConfigService;

	protected IdMessageRecipient createIdMessageRecipient(final Participant recipient) {
		final String recipientId = recipient.getId();
		final IdMessageRecipient result;

		if (Strings.isBlank(recipientId)) {
			result = null;
		} else {
			result = new IdMessageRecipient(recipientId);
		}

		return result;
	}

	protected boolean hasPayloadType(final SendPayloadType sendPayloadType, final SendMessage sendMessage) {
		final AbstractSendPayload payload = sendMessage.getPayload();
		final boolean result = payload != null && sendPayloadType.equals(payload.getSendPayloadType());
		return result;
	}

	protected FacebookClient provideFacebookClient(final SendMessage sendMessage) {
		final String facebookAccessToken = facebookConfigService.getAccessToken(sendMessage);
		return new DefaultFacebookClient(facebookAccessToken, Version.VERSION_2_8);
	}

	protected void publish(final SendMessage sendMessage, final Message message, final IdMessageRecipient recipient) {
		LOG.info("sending message to {}", recipient);

		if (recipient == null) {
			LOG.warn("recipient is {}", recipient);
		} else {
			final Parameter recipientParam = Parameter.with("recipient", recipient);
			final Parameter messageParam = Parameter.with("message", message);

			final FacebookClient facebookClient = provideFacebookClient(sendMessage);
			facebookClient.publish("me/messages", SendResponse.class, recipientParam, messageParam);
		}
	}

	protected void publish(final SendMessage sendMessage, final SenderActionEnum senderAction,
			final IdMessageRecipient recipient) {
		LOG.info("sending to {} param {}", recipient, senderAction);

		if (recipient == null) {
			LOG.warn("recipient is {}", recipient);
		} else {
			final Parameter recipientParam = Parameter.with("recipient", recipient);
			final Parameter senderActionParam = Parameter.with("sender_action", senderAction);

			final FacebookClient facebookClient = provideFacebookClient(sendMessage);
			facebookClient.publish("me/messages", SendResponse.class, recipientParam, senderActionParam);
		}
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}
