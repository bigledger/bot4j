package ai.nitro.bot4j.integration.api.ai;

import com.google.inject.AbstractModule;

import ai.nitro.bot4j.integration.api.ai.receive.ApiAiReceiveHandler;
import ai.nitro.bot4j.integration.api.ai.receive.ApiAiReceiveMessageFactory;
import ai.nitro.bot4j.integration.api.ai.receive.impl.ApiAiReceiveHandlerImpl;
import ai.nitro.bot4j.integration.api.ai.receive.impl.ApiAiReceiveMessageFactoryImpl;
import ai.nitro.bot4j.integration.api.ai.receive.webhook.ApiAiWebhook;
import ai.nitro.bot4j.integration.api.ai.receive.webhook.impl.ApiAiWebhookImpl;
import ai.nitro.bot4j.integration.api.ai.send.ApiAiMessageSender;
import ai.nitro.bot4j.integration.api.ai.send.impl.ApiAiMessageSenderImpl;

public class ApiAiModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ApiAiWebhook.class).to(ApiAiWebhookImpl.class);
		bind(ApiAiReceiveHandler.class).to(ApiAiReceiveHandlerImpl.class);
		bind(ApiAiMessageSender.class).to(ApiAiMessageSenderImpl.class);
		bind(ApiAiReceiveMessageFactory.class).to(ApiAiReceiveMessageFactoryImpl.class);
	}
}
