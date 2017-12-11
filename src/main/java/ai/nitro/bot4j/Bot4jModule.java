/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j;

import com.google.inject.AbstractModule;

import ai.nitro.bot4j.integration.alexa.AlexaModule;
import ai.nitro.bot4j.integration.api.ai.ApiAiModule;
import ai.nitro.bot4j.integration.facebook.FacebookModule;
import ai.nitro.bot4j.integration.slack.SlackModule;
import ai.nitro.bot4j.integration.telegram.TelegramModule;
import ai.nitro.bot4j.middle.MiddlewareModule;

public class Bot4jModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FacebookModule());
		install(new SlackModule());
		install(new TelegramModule());
		install(new AlexaModule());
		install(new ApiAiModule());

		install(new MiddlewareModule());
	}
}
