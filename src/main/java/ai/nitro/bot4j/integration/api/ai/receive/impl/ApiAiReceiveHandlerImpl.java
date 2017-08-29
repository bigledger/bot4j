package ai.nitro.bot4j.integration.api.ai.receive.impl;

import javax.inject.Inject;

import ai.api.model.AIResponse;
import ai.nitro.bot4j.integration.api.ai.receive.ApiAiReceiveHandler;
import ai.nitro.bot4j.middle.domain.receive.ReceiveMessage;
import ai.nitro.bot4j.middle.receive.MessageReceiver;

public class ApiAiReceiveHandlerImpl implements ApiAiReceiveHandler {

	@Inject
	protected ApiAiReceiveMessageFactoryImpl apiAiReceiveMessageFactory;

	@Inject
	protected MessageReceiver messageReceiver;

	@Override
	public void handleAIResponse(final AIResponse aiResponse) {
		final ReceiveMessage receiveMessage = apiAiReceiveMessageFactory.createReceiveMessage(aiResponse);

		if (receiveMessage != null) {
			messageReceiver.receive(receiveMessage);
		}
	}

}
