package ai.nitro.bot4j.integration.api.ai.receive.impl;

import javax.inject.Inject;

import ai.api.model.AIResponse;
import ai.api.model.Fulfillment;
import ai.nitro.bot4j.integration.api.ai.receive.ApiAiReceiveHandler;
import ai.nitro.bot4j.integration.api.ai.receive.ApiAiReceiveMessageFactory;
import ai.nitro.bot4j.integration.api.ai.send.ApiAiMessageSender;
import ai.nitro.bot4j.middle.domain.receive.ReceiveMessage;
import ai.nitro.bot4j.middle.receive.MessageReceiver;

public class ApiAiReceiveHandlerImpl implements ApiAiReceiveHandler {

	@Inject
	protected ApiAiMessageSender apiAiMessageSender;

	@Inject
	protected ApiAiReceiveMessageFactory apiAiReceiveMessageFactory;

	@Inject
	protected MessageReceiver messageReceiver;

	@Override
	public Fulfillment handleAIResponse(final AIResponse aiResponse) {
		final ReceiveMessage receiveMessage = apiAiReceiveMessageFactory.createReceiveMessage(aiResponse);

		if (receiveMessage != null) {
			messageReceiver.receive(receiveMessage);
		}

		final Fulfillment fulfillment = apiAiMessageSender.getFulfillment();

		return fulfillment;
	}
}
