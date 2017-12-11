/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j;

import com.google.inject.AbstractModule;

import ai.nitro.bot4j.bot.BotModule;

public class Bot4jTestModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new Bot4jModule());
		install(new BotModule());
	}
}
