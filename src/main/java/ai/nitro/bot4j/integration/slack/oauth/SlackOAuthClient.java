/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.slack.oauth;

import java.io.IOException;

import com.github.seratch.jslack.api.methods.SlackApiException;

public interface SlackOAuthClient {

	String getBotAccessToken(String code, String clientId, String clientSecret) throws IOException, SlackApiException;
}
