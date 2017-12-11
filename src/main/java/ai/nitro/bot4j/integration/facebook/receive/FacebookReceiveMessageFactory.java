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

import ai.nitro.bot4j.middle.domain.receive.ReceiveMessage;

public interface FacebookReceiveMessageFactory {

	ReceiveMessage createReceiveMessage(MessagingItem messagingItem, Map<String, String[]> params);

}
