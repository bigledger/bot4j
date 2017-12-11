/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.telegram;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

import ai.nitro.bot4j.integration.telegram.config.TelegramConfig;
import ai.nitro.bot4j.integration.telegram.config.TelegramConfigService;
import ai.nitro.bot4j.integration.telegram.config.impl.TelegramConfigImpl;
import ai.nitro.bot4j.integration.telegram.config.impl.TelegramConfigServiceImpl;
import ai.nitro.bot4j.integration.telegram.receive.TelegramReceiveHandler;
import ai.nitro.bot4j.integration.telegram.receive.TelegramReceiveMessageFactory;
import ai.nitro.bot4j.integration.telegram.receive.TelegramReceivePayloadFactory;
import ai.nitro.bot4j.integration.telegram.receive.impl.TelegramReceiveHandlerImpl;
import ai.nitro.bot4j.integration.telegram.receive.impl.TelegramReceiveMessageFactoryImpl;
import ai.nitro.bot4j.integration.telegram.receive.impl.TelegramReceivePayloadFactoryImpl;
import ai.nitro.bot4j.integration.telegram.receive.webhook.TelegramWebhook;
import ai.nitro.bot4j.integration.telegram.receive.webhook.impl.TelegramWebhookImpl;
import ai.nitro.bot4j.integration.telegram.send.TelegramMessageSender;
import ai.nitro.bot4j.integration.telegram.send.TelegramSendInlineKeyboardFactory;
import ai.nitro.bot4j.integration.telegram.send.impl.TelegramMessageSenderImpl;
import ai.nitro.bot4j.integration.telegram.send.impl.TelegramSendInlineKeyboardFactoryImpl;
import ai.nitro.bot4j.integration.telegram.send.rules.TelegramSendRule;
import ai.nitro.bot4j.integration.telegram.send.rules.impl.BubbleRuleImpl;
import ai.nitro.bot4j.integration.telegram.send.rules.impl.ButtonsRuleImpl;
import ai.nitro.bot4j.integration.telegram.send.rules.impl.ImageRuleImpl;
import ai.nitro.bot4j.integration.telegram.send.rules.impl.ListRuleImpl;
import ai.nitro.bot4j.integration.telegram.send.rules.impl.NativeRuleImpl;
import ai.nitro.bot4j.integration.telegram.send.rules.impl.TextRuleImpl;
import ai.nitro.bot4j.integration.telegram.send.rules.impl.VideoRuleImpl;

public class TelegramModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(TelegramWebhook.class).to(TelegramWebhookImpl.class);
		bind(TelegramConfig.class).to(TelegramConfigImpl.class);
		bind(TelegramConfigService.class).to(TelegramConfigServiceImpl.class);
		bind(TelegramMessageSender.class).to(TelegramMessageSenderImpl.class);
		bind(TelegramSendInlineKeyboardFactory.class).to(TelegramSendInlineKeyboardFactoryImpl.class);
		bind(TelegramReceiveHandler.class).to(TelegramReceiveHandlerImpl.class);
		bind(TelegramReceiveMessageFactory.class).to(TelegramReceiveMessageFactoryImpl.class);
		bind(TelegramReceivePayloadFactory.class).to(TelegramReceivePayloadFactoryImpl.class);
		bind(TelegramWebhook.class).to(TelegramWebhookImpl.class);

		final Multibinder<TelegramSendRule> telegramSendRuleBinder = Multibinder.newSetBinder(binder(),
				TelegramSendRule.class);

		telegramSendRuleBinder.addBinding().to(BubbleRuleImpl.class);
		telegramSendRuleBinder.addBinding().to(ButtonsRuleImpl.class);
		telegramSendRuleBinder.addBinding().to(ImageRuleImpl.class);
		telegramSendRuleBinder.addBinding().to(ListRuleImpl.class);
		telegramSendRuleBinder.addBinding().to(NativeRuleImpl.class);
		telegramSendRuleBinder.addBinding().to(TextRuleImpl.class);
		telegramSendRuleBinder.addBinding().to(VideoRuleImpl.class);
	}
}
