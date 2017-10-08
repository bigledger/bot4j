/*
 * Copyright (C) 2016, nitro.ai
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.facebook.config.impl;

import javax.inject.Inject;

import ai.nitro.bot4j.integration.facebook.config.FacebookConfig;
import ai.nitro.bot4j.integration.facebook.config.FacebookConfigService;
import ai.nitro.bot4j.middle.domain.receive.ReceiveMessage;
import ai.nitro.bot4j.middle.domain.send.SendMessage;

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
