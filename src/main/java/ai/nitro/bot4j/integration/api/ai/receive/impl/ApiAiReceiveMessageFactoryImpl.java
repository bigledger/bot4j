package ai.nitro.bot4j.integration.api.ai.receive.impl;

import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Singleton;

import com.google.gson.JsonElement;

import ai.api.model.AIResponse;
import ai.api.model.Result;
import ai.nitro.bot4j.integration.api.ai.domain.ApiAiPlatformEnum;
import ai.nitro.bot4j.integration.api.ai.receive.ApiAiReceiveMessageFactory;
import ai.nitro.bot4j.middle.domain.Participant;
import ai.nitro.bot4j.middle.domain.receive.ReceiveMessage;
import ai.nitro.bot4j.middle.domain.receive.nlp.NlpContext;
import ai.nitro.bot4j.middle.domain.receive.nlp.NlpIntent;
import ai.nitro.bot4j.middle.domain.receive.nlp.NlpNamedEntity;
import ai.nitro.bot4j.middle.domain.receive.nlp.impl.NlpContextImpl;
import ai.nitro.bot4j.middle.domain.receive.nlp.impl.NlpIntentImpl;
import ai.nitro.bot4j.middle.domain.receive.nlp.impl.NlpNamedEntityImpl;
import ai.nitro.bot4j.middle.domain.receive.payload.TextReceivePayload;

@Singleton
public class ApiAiReceiveMessageFactoryImpl implements ApiAiReceiveMessageFactory {

	protected NlpContext createNlpContext(final Result aiResponseResult) {
		final NlpContext result = new NlpContextImpl();

		if (aiResponseResult.getMetadata() != null) {
			final NlpIntent nlpIntent = new NlpIntentImpl();
			nlpIntent.setConfidence(1.0);
			nlpIntent.setName(aiResponseResult.getMetadata().getIntentName());

			result.setMaxIntent(nlpIntent);
			result.addIntent(nlpIntent);
		}

		if (aiResponseResult.getParameters() != null) {
			for (final Entry<String, JsonElement> entry : aiResponseResult.getParameters().entrySet()) {
				final NlpNamedEntity nlpNamedEntity = new NlpNamedEntityImpl();
				nlpNamedEntity.setType(entry.getKey());
				nlpNamedEntity.setConfidence(1.0);
				nlpNamedEntity.setEntity(entry.getValue().toString());

				result.addNamedEntity(nlpNamedEntity);
			}
		}

		return result;
	}

	@Override
	public ReceiveMessage createReceiveMessage(final AIResponse aiResponse, final Map<String, String[]> params) {
		final ReceiveMessage result = new ReceiveMessage();
		result.setMessageId(aiResponse.getId());
		result.setNativePayload(ApiAiPlatformEnum.APIAI, aiResponse);

		if (params != null) {
			result.getParams().putAll(params);
		}

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
