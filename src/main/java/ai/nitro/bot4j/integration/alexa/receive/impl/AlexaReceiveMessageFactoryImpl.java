package ai.nitro.bot4j.integration.alexa.receive.impl;

import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Singleton;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.User;
import com.amazon.speech.speechlet.interfaces.system.SystemInterface;
import com.amazon.speech.speechlet.interfaces.system.SystemState;

import ai.nitro.bot4j.integration.alexa.domain.AlexaPlatformEnum;
import ai.nitro.bot4j.integration.alexa.receive.AlexaReceiveMessageFactory;
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
public class AlexaReceiveMessageFactoryImpl implements AlexaReceiveMessageFactory {

	protected NlpContext createNlpContext(final Intent intent) {
		final NlpContext result = new NlpContextImpl();

		final NlpIntent nlpIntent = new NlpIntentImpl();
		nlpIntent.setConfidence(1.0);
		nlpIntent.setName(intent.getName());

		result.setMaxIntent(nlpIntent);

		for (final Entry<String, Slot> entry : intent.getSlots().entrySet()) {
			final Slot value = entry.getValue();

			final NlpNamedEntity nlpNamedEntity = new NlpNamedEntityImpl();
			nlpNamedEntity.setType(entry.getKey());
			nlpNamedEntity.setConfidence(1.0);
			nlpNamedEntity.setEntity(value.getValue());

			result.addNamedEntity(nlpNamedEntity);
		}

		return result;
	}

	@Override
	public ReceiveMessage createReceiveMessage(final IntentRequest intentRequest, final User user,
			final SpeechletRequestEnvelope<IntentRequest> requestEnvelope, final Map<String, String[]> params) {
		final Participant sender = createSender(requestEnvelope, user);

		final ReceiveMessage result = new ReceiveMessage();
		result.setMessageId(intentRequest.getRequestId());
		result.setSender(sender);

		if (params != null) {
			result.getParams().putAll(params);
		}

		final TextReceivePayload textReceivePayload = createTextReceivePayload(intentRequest);
		result.addPayload(textReceivePayload);

		return result;
	}

	protected Participant createSender(final SpeechletRequestEnvelope<IntentRequest> requestEnvelope, final User user) {
		final Participant sender = new Participant();
		sender.setPlatform(AlexaPlatformEnum.ALEXA);
		sender.setId(user.getUserId());

		if (requestEnvelope != null && requestEnvelope.getContext() != null
				&& requestEnvelope.getContext().getState(SystemInterface.class, SystemInterface.STATE_TYPE) != null) {
			final SystemState state = requestEnvelope.getContext().getState(SystemInterface.class,
					SystemInterface.STATE_TYPE);

			if (state.getDevice() != null) {
				final String deviceId = state.getDevice().getDeviceId();
				sender.setDeviceId(deviceId);
			}
		}

		return sender;
	}

	protected TextReceivePayload createTextReceivePayload(final IntentRequest intentRequest) {
		final NlpContext nlpContext = createNlpContext(intentRequest.getIntent());
		final TextReceivePayload result = new TextReceivePayload();
		result.setNlpContext(nlpContext);

		return result;
	}
}
