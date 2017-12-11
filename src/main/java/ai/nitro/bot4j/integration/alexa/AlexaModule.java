/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.alexa;

import com.google.inject.AbstractModule;

import ai.nitro.bot4j.integration.alexa.receive.AlexaReceiveHandler;
import ai.nitro.bot4j.integration.alexa.receive.AlexaReceiveMessageFactory;
import ai.nitro.bot4j.integration.alexa.receive.impl.AlexaReceiveHandlerImpl;
import ai.nitro.bot4j.integration.alexa.receive.impl.AlexaReceiveMessageFactoryImpl;
import ai.nitro.bot4j.integration.alexa.receive.webhook.AlexaWebhook;
import ai.nitro.bot4j.integration.alexa.receive.webhook.impl.AlexaWebhookImpl;
import ai.nitro.bot4j.integration.alexa.send.AlexaMessageSender;
import ai.nitro.bot4j.integration.alexa.send.impl.AlexaMessageSenderImpl;

public class AlexaModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(AlexaWebhook.class).to(AlexaWebhookImpl.class);
		bind(AlexaReceiveHandler.class).to(AlexaReceiveHandlerImpl.class);
		bind(AlexaReceiveMessageFactory.class).to(AlexaReceiveMessageFactoryImpl.class);
		bind(AlexaMessageSender.class).to(AlexaMessageSenderImpl.class);
	}
}
