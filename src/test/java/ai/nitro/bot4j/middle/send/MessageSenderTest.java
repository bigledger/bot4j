/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.send;

import static org.junit.Assert.assertFalse;

import javax.inject.Inject;

import org.junit.Test;

import ai.nitro.bot4j.TestBase;
import ai.nitro.bot4j.middle.domain.send.SendMessage;
import ai.nitro.bot4j.middle.send.MessageSender;

public class MessageSenderTest extends TestBase {

	@Inject
	protected MessageSender messageSender;

	@Test
	public void testSendWithoutPlatform() throws Exception {
		final SendMessage sendMessage = new SendMessage();
		final boolean result = messageSender.send(sendMessage);
		assertFalse(result);
	}
}
