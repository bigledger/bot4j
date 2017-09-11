package ai.nitro.bot4j.integration.api.ai.receive;

import java.util.Map;

import ai.api.model.AIResponse;
import ai.api.model.Fulfillment;

public interface ApiAiReceiveHandler {

	Fulfillment handleAIResponse(AIResponse aiResponse, Map<String, String[]> params);
}
