package ai.nitro.bot4j.integration.api.ai.receive;

import ai.api.model.AIResponse;
import ai.nitro.bot4j.middle.domain.receive.ReceiveMessage;

public interface ApiAiReceiveMessageFactory {

	ReceiveMessage createReceiveMessage(AIResponse aiResponse);

}
