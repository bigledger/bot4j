/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.slack.config;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.junit.Test;

import ai.nitro.bot4j.TestBase;
import ai.nitro.bot4j.integration.slack.config.SlackConfig;

public class SlackConfigTest extends TestBase {

	@Inject
	protected SlackConfig slackConfig;

	@Test
	public void testAccessToken() throws Exception {
		final String accessToken = slackConfig.getAccessToken();
		assertNotNull(accessToken);
	}
}
