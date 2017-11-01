package ai.nitro.bot4j.middle.receive.api.ai;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.Test;

import ai.api.model.AIResponse;
import ai.api.model.Metadata;
import ai.api.model.Result;
import ai.nitro.bot4j.TestBase;
import ai.nitro.bot4j.integration.api.ai.receive.ApiAiReceiveMessageFactory;
import ai.nitro.bot4j.middle.domain.receive.ReceiveMessage;
import ai.nitro.bot4j.middle.domain.receive.payload.TextReceivePayload;

public class ApiAiMessageTest extends TestBase {

	private static final String intentName = "intentName";

	private static final String messageId = "123";

	private static final String senderId = "456";

	@Inject
	protected ApiAiReceiveMessageFactory apiAiReceiveMessageFactory;

	@Test
	public void testReceiveMessage() {
		final Metadata metadata = new Metadata();
		metadata.setIntentName(intentName);

		final Result result = new Result();
		result.setMetadata(metadata);

		final AIResponse aiResponse = new AIResponse();
		aiResponse.setId(messageId);
		aiResponse.setResult(result);
		aiResponse.setSessionId(senderId);

		final ReceiveMessage receiveMessage = apiAiReceiveMessageFactory.createReceiveMessage(aiResponse, null);
		assertEquals(receiveMessage.getSender().getId(), senderId);
		assertEquals(receiveMessage.getMessageId(), messageId);

		assertEquals(receiveMessage.getPayloads().get(0).getClass(), TextReceivePayload.class);
		final TextReceivePayload textReceivePayload = (TextReceivePayload) receiveMessage.getPayloads().get(0);
		assertEquals(intentName, textReceivePayload.getNlpContext().getMaxIntent().getName());
	}
}
