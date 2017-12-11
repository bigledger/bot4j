/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.facebook.config;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.junit.Test;

import ai.nitro.bot4j.TestBase;
import ai.nitro.bot4j.integration.facebook.config.FacebookConfig;

public class FacebookConfigTest extends TestBase {

	@Inject
	protected FacebookConfig facebookConfig;

	@Test
	public void testAccessToken() throws Exception {
		final String accessToken = facebookConfig.getAccessToken();
		assertNotNull(accessToken);
	}
}
