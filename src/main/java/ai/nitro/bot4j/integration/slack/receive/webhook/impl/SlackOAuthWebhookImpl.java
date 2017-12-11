/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.slack.receive.webhook.impl;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import ai.nitro.bot4j.integration.slack.config.SlackConfigService;
import ai.nitro.bot4j.integration.slack.oauth.SlackOAuthClient;
import ai.nitro.bot4j.integration.slack.receive.webhook.SlackOAuthWebhook;
import ai.nitro.bot4j.middle.domain.receive.ReceiveMessage;

@Singleton
public class SlackOAuthWebhookImpl implements SlackOAuthWebhook {

	private final static Logger LOG = LogManager.getLogger(SlackOAuthWebhookImpl.class);

	@Inject
	protected SlackConfigService slackConfigService;

	@Inject
	protected SlackOAuthClient slackOAuthClient;

	@Override
	public String get(final HttpServletRequest req, final HttpServletResponse res) {
		String result = "";

		try {
			final String clientId = slackConfigService.getClientId((ReceiveMessage) null);
			final String clientSecret = slackConfigService.getClientSecret((ReceiveMessage) null);
			final String code = req.getParameter("code");

			if (Strings.isBlank(clientId)) {
				LOG.warn("client_id is not set in slack.properties");
				result = "client_id is not set in slack.properties";
			} else if (Strings.isBlank(clientSecret)) {
				LOG.warn("client_secret is not set in slack.properties");
				result = "client_secret is not set in slack.properties";
			} else if (Strings.isBlank(code)) {
				LOG.warn("query parameter code is missing");
				result = "query parameter code is missing";
			} else {
				final String accessToken = slackOAuthClient.getBotAccessToken(code, clientId, clientSecret);

				LOG.info("OAuth access token is {}", accessToken);
				result = "add line to slack.properties: access_token = " + accessToken;
			}
		} catch (final Exception e) {
			handleException(e);
		}

		return result;
	}

	protected void handleException(final Exception e) {
		LOG.error(e.getMessage(), e);
	}

}
