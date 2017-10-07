/*
 * Copyright (C) 2017, nitro.ai
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.facebook.config;

import ai.nitro.bot4j.middle.domain.send.SendMessage;

public interface FacebookConfigService {

	String getAccessToken(SendMessage sendMessage);

	String getPageId(SendMessage sendMessage);

}
