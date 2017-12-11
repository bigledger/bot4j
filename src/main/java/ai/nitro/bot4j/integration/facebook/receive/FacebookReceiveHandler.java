/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.facebook.receive;

import java.util.Map;

import com.restfb.types.webhook.messaging.MessagingItem;

public interface FacebookReceiveHandler {

	void handleMessagingItem(MessagingItem messagingItem, Map<String, String[]> params);

}
