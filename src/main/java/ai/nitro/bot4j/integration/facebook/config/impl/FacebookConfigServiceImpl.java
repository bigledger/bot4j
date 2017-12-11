/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.facebook.config.impl;

import javax.inject.Inject;
import javax.inject.Singleton;

import ai.nitro.bot4j.integration.facebook.config.FacebookConfig;
import ai.nitro.bot4j.integration.facebook.config.FacebookConfigService;
import ai.nitro.bot4j.middle.domain.receive.ReceiveMessage;
import ai.nitro.bot4j.middle.domain.send.SendMessage;

@Singleton
public class FacebookConfigServiceImpl implements FacebookConfigService {

	@Inject
	protected FacebookConfig facebookConfig;

	@Override
	public String getAccessToken(final ReceiveMessage receiveMessage) {
		return facebookConfig.getAccessToken();
	}

	@Override
	public String getAccessToken(final SendMessage sendMessage) {
		return facebookConfig.getAccessToken();
	}

	@Override
	public String getPageId(final ReceiveMessage receiveMessage) {
		return facebookConfig.getPageId();
	}

	@Override
	public String getPageId(final SendMessage sendMessage) {
		return facebookConfig.getPageId();
	}
}
