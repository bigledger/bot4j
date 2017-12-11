/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.slack.send.impl;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ai.nitro.bot4j.integration.slack.domain.SlackPlatformEnum;
import ai.nitro.bot4j.integration.slack.send.SlackMessageSender;
import ai.nitro.bot4j.integration.slack.send.rules.SlackSendRule;
import ai.nitro.bot4j.middle.domain.Platform;
import ai.nitro.bot4j.middle.domain.send.SendMessage;

@Singleton
public class SlackMessageSenderImpl implements SlackMessageSender {

	final static Logger LOG = LogManager.getLogger(SlackMessageSenderImpl.class);

	@Inject
	protected Set<SlackSendRule> rules;

	@Override
	public Platform getPlatform() {
		return SlackPlatformEnum.SLACK;
	}

	@Override
	public boolean send(final SendMessage sendMessage) {
		boolean result = false;

		if (rules != null) {
			for (final SlackSendRule rule : rules) {
				final boolean applies = rule.applies(sendMessage);

				if (applies) {
					LOG.info("applying send rule {}", rule);

					rule.apply(sendMessage);
					result = true;
					break;
				}
			}
		}

		return result;
	}
}
