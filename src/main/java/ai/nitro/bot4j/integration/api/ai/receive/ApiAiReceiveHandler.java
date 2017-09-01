package ai.nitro.bot4j.integration.api.ai.receive;

import ai.api.model.AIResponse;
import ai.api.model.Fulfillment;

public interface ApiAiReceiveHandler {

	Fulfillment handleAIResponse(AIResponse aiResponse);
}
