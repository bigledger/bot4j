/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.slack.config.impl;

import javax.inject.Inject;
import javax.inject.Singleton;

import ai.nitro.bot4j.integration.slack.config.SlackConfig;
import ai.nitro.bot4j.integration.slack.config.SlackConfigService;
import ai.nitro.bot4j.middle.domain.receive.ReceiveMessage;
import ai.nitro.bot4j.middle.domain.send.SendMessage;

@Singleton
public class SlackConfigServiceImpl implements SlackConfigService {

	@Inject
	protected SlackConfig slackConfig;

	@Override
	public String getAccessToken(final ReceiveMessage receiveMessage) {
		return slackConfig.getAccessToken();
	}

	@Override
	public String getAccessToken(final SendMessage sendMessage) {
		return slackConfig.getAccessToken();
	}

	@Override
	public String getClientId(final ReceiveMessage receiveMessage) {
		return slackConfig.getClientId();
	}

	@Override
	public String getClientId(final SendMessage sendMessage) {
		return slackConfig.getClientId();
	}

	@Override
	public String getClientSecret(final ReceiveMessage receiveMessage) {
		return slackConfig.getClientSecret();
	}

	@Override
	public String getClientSecret(final SendMessage sendMessage) {
		return slackConfig.getClientSecret();
	}

	@Override
	public String getUsername(final ReceiveMessage receiveMessage) {
		return slackConfig.getUsername();
	}

	@Override
	public String getUsername(final SendMessage sendMessage) {
		return slackConfig.getUsername();
	}
}
