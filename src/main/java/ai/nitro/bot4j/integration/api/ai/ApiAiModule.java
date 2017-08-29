package ai.nitro.bot4j.integration.api.ai;

import com.google.inject.AbstractModule;

import ai.nitro.bot4j.integration.api.ai.receive.ApiAiReceiveHandler;
import ai.nitro.bot4j.integration.api.ai.receive.impl.ApiAiReceiveHandlerImpl;
import ai.nitro.bot4j.integration.api.ai.receive.webhook.ApiAiWebhook;
import ai.nitro.bot4j.integration.api.ai.receive.webhook.impl.ApiAiWebhookImpl;

public class ApiAiModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ApiAiWebhook.class).to(ApiAiWebhookImpl.class);
		bind(ApiAiReceiveHandler.class).to(ApiAiReceiveHandlerImpl.class);
	}

}
