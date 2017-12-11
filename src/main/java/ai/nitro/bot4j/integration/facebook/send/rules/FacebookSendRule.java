/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.facebook.send.rules;

import ai.nitro.bot4j.middle.domain.send.SendMessage;

public interface FacebookSendRule {

	boolean applies(SendMessage sendMessage);

	void apply(SendMessage sendMessage);
}
