package ai.nitro.bot4j.integration.api.ai.receive.impl;

import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Singleton;

import com.google.gson.JsonElement;

import ai.api.model.AIResponse;
import ai.api.model.Metadata;
import ai.api.model.Result;
import ai.nitro.bot4j.integration.api.ai.domain.ApiAiPlatformEnum;
import ai.nitro.bot4j.integration.api.ai.receive.ApiAiReceiveMessageFactory;
import ai.nitro.bot4j.middle.domain.Participant;
import ai.nitro.bot4j.middle.domain.receive.ReceiveMessage;
import ai.nitro.bot4j.middle.domain.receive.nlp.NlpContext;
import ai.nitro.bot4j.middle.domain.receive.nlp.NlpContext;
import ai.nitro.bot4j.middle.domain.receive.nlp.NlpIntent;
import ai.nitro.bot4j.middle.domain.receive.nlp.NlpIntent;
import ai.nitro.bot4j.middle.domain.receive.nlp.NlpNamedEntity;
import ai.nitro.bot4j.middle.domain.receive.nlp.NlpNamedEntity;
import ai.nitro.bot4j.middle.domain.receive.payload.TextReceivePayload;

@Singleton
public class ApiAiReceiveMessageFactoryImpl implements ApiAiReceiveMessageFactory {

	protected NlpNamedEntity createNamedEntity(final Entry<String, JsonElement> entry) {
		final String type = entry.getKey();
		final JsonElement entity = entry.getValue();

		final NlpNamedEntity result = new NlpNamedEntity();
		result.setType(type.toLowerCase());
		result.setEntity(entity.toString());
		result.setConfidence(1.0);
		return result;
	}

	protected NlpContext createNlpContext(final Result aiResponseResult) {
		final NlpContext result = new NlpContext();

		if (aiResponseResult.getMetadata() != null) {
			final NlpIntent nlpIntent = createNlpIntent(aiResponseResult);
			result.addIntent(nlpIntent);
		}

		if (aiResponseResult.getParameters() != null) {
			for (final Entry<String, JsonElement> entry : aiResponseResult.getParameters().entrySet()) {
				final NlpNamedEntity nlpNamedEntity = createNamedEntity(entry);
				result.addNamedEntity(nlpNamedEntity);
			}
		}

		return result;
	}

	protected NlpIntent createNlpIntent(final Result aiResponseResult) {
		final Metadata metadata = aiResponseResult.getMetadata();
		final String intentName = metadata.getIntentName();

		final NlpIntent result = new NlpIntent();
		result.setName(intentName.toLowerCase());
		result.setConfidence(1.0);
		return result;
	}

	protected Participant createParticipant(final AIResponse aiResponse) {
		final Participant result = new Participant();
		result.setPlatform(ApiAiPlatformEnum.APIAI);
		result.setId(aiResponse.getSessionId());
		return result;
	}

	protected TextReceivePayload createPayload(final Result aiResponseResult) {
		final String text = aiResponseResult.getResolvedQuery();
		final TextReceivePayload result = new TextReceivePayload();
		result.setText(text);

		final NlpContext nlpContext = createNlpContext(aiResponseResult);
		result.setNlpContext(nlpContext);
		return result;
	}

	@Override
	public ReceiveMessage createReceiveMessage(final AIResponse aiResponse, final Map<String, String[]> params) {
		final ReceiveMessage result = new ReceiveMessage();
		result.setMessageId(aiResponse.getId());

		final Participant sender = createParticipant(aiResponse);
		result.setSender(sender);

		final Participant recipient = createParticipant(aiResponse);
		result.setRecipient(recipient);

		result.setNativePayload(ApiAiPlatformEnum.APIAI, aiResponse);

		if (params != null) {
			result.getParams().putAll(params);
		}

		final Result aiResponseResult = aiResponse.getResult();

		if (aiResponseResult != null) {
			final TextReceivePayload payload = createPayload(aiResponseResult);
			result.addPayload(payload);
		}

		return result;
	}
}
