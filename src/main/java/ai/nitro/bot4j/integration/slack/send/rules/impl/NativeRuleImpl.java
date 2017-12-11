/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.slack.send.rules.impl;

import javax.inject.Singleton;

import com.github.seratch.jslack.api.webhook.Payload;

import ai.nitro.bot4j.integration.slack.domain.SlackPlatformEnum;
import ai.nitro.bot4j.middle.domain.send.SendMessage;

@Singleton
public class NativeRuleImpl extends AbstractSlackSendRuleImpl {

	@Override
	public boolean applies(final SendMessage sendMessage) {
		final boolean result = sendMessage.getNativePayload(SlackPlatformEnum.SLACK) != null;
		return result;
	}

	@Override
	public void apply(final SendMessage sendMessage) {
		final Payload payload = (Payload) sendMessage.getNativePayload(SlackPlatformEnum.SLACK);
		chatPostMessage(sendMessage, payload);
	}
}
