package ai.nitro.bot4j.integration.api.ai.receive.impl;

import java.util.Map.Entry;

import com.google.gson.JsonElement;

import ai.api.model.AIResponse;
import ai.api.model.Result;
import ai.nitro.bot4j.integration.api.ai.domain.ApiAiPlatformEnum;
import ai.nitro.bot4j.integration.api.ai.receive.ApiAiReceiveMessageFactory;
import ai.nitro.bot4j.middle.domain.Participant;
import ai.nitro.bot4j.middle.domain.receive.ReceiveMessage;
import ai.nitro.bot4j.middle.domain.receive.nlp.NlpContext;
import ai.nitro.bot4j.middle.domain.receive.payload.TextReceivePayload;

public class ApiAiReceiveMessageFactoryImpl implements ApiAiReceiveMessageFactory {

	protected NlpContext createNlpContext(final Result aiResponseResult) {
		final NlpContext result = new NlpContext();

		if (aiResponseResult.getMetadata() != null) {
			result.setIntent(aiResponseResult.getMetadata().getIntentName());
		}
		if (aiResponseResult.getParameters() != null) {
			for (final Entry<String, JsonElement> entry : aiResponseResult.getParameters().entrySet()) {
				final String key = entry.getKey();
				final String value = entry.getValue().toString();

				result.addNamedEntity(key, value);
			}
		}

		return result;
	}

	@Override
	public ReceiveMessage createReceiveMessage(final AIResponse aiResponse) {
		final ReceiveMessage result = new ReceiveMessage();
		result.setMessageId(aiResponse.getId());
		result.setNativePayload(ApiAiPlatformEnum.APIAI, aiResponse);

		final Participant sender = createSender(aiResponse);
		result.setSender(sender);

		final Result aiResponseResult = aiResponse.getResult();
		if (aiResponseResult != null) {
			final TextReceivePayload payload = new TextReceivePayload();
			payload.setText(aiResponseResult.getResolvedQuery());

			final NlpContext nlpContext = createNlpContext(aiResponseResult);
			payload.setNlpContext(nlpContext);

			result.addPayload(payload);
		}

		return result;
	}

	protected Participant createSender(final AIResponse aiResponse) {
		final Participant sender = new Participant();
		sender.setPlatform(ApiAiPlatformEnum.APIAI);
		sender.setId(aiResponse.getSessionId());

		return sender;
	}
}
