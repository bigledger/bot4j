package ai.nitro.bot4j.integration.api.ai.receive;

import ai.api.model.AIResponse;

public interface ApiAiReceiveHandler {

	void handleAIResponse(AIResponse aiResponse);

}
