package ai.nitro.bot4j.integration.alexa.receive.impl;

import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Singleton;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.Context;
import com.amazon.speech.speechlet.Device;
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
import ai.nitro.bot4j.middle.domain.receive.payload.TextReceivePayload;

@Singleton
public class AlexaReceiveMessageFactoryImpl implements AlexaReceiveMessageFactory {

	protected NlpContext createNlpContext(final Intent intent) {
		final NlpContext result = new NlpContext();
		final NlpIntent nlpIntent = createNlpIntent(intent);
		result.addIntent(nlpIntent);

		if (intent.getSlots() != null) {
			for (final Entry<String, Slot> entry : intent.getSlots().entrySet()) {
				final NlpNamedEntity nlpNamedEntity = createNlpNamedEntity(entry);
				result.addNamedEntity(nlpNamedEntity);
			}
		}

		return result;
	}

	protected NlpIntent createNlpIntent(final Intent intent) {
		final String intentName = intent.getName();

		final NlpIntent result = new NlpIntent();
		result.setName(intentName.toLowerCase());
		result.setConfidence(1.0);
		return result;
	}

	protected NlpNamedEntity createNlpNamedEntity(final Entry<String, Slot> entry) {
		final Slot value = entry.getValue();
		final String type = entry.getKey();
		final String entity = value.getValue();

		final NlpNamedEntity result = new NlpNamedEntity();
		result.setType(type.toLowerCase());
		result.setEntity(entity);
		result.setConfidence(1.0);
		return result;
	}

	protected Participant createParticipant(final SpeechletRequestEnvelope<IntentRequest> requestEnvelope,
			final User user) {
		final Participant result = new Participant();
		result.setPlatform(AlexaPlatformEnum.ALEXA);
		result.setId(user.getUserId());

		if (requestEnvelope == null) {
		} else if (requestEnvelope.getContext() == null) {
		} else {
			final Context context = requestEnvelope.getContext();
			final SystemState state = context.getState(SystemInterface.class, SystemInterface.STATE_TYPE);

			if (state == null) {
			} else if (state.getDevice() == null) {
			} else {
				final Device device = state.getDevice();
				final String deviceId = device.getDeviceId();
				result.setDeviceId(deviceId);
			}
		}

		return result;
	}

	@Override
	public ReceiveMessage createReceiveMessage(final IntentRequest intentRequest, final User user,
			final SpeechletRequestEnvelope<IntentRequest> requestEnvelope, final Map<String, String[]> params) {
		final ReceiveMessage result = new ReceiveMessage();
		result.setMessageId(intentRequest.getRequestId());

		final Participant sender = createParticipant(requestEnvelope, user);
		result.setSender(sender);

		final Participant recipient = createParticipant(requestEnvelope, user);
		result.setRecipient(recipient);

		if (params != null) {
			result.getParams().putAll(params);
		}

		final TextReceivePayload payload = createTextReceivePayload(intentRequest);
		result.addPayload(payload);
		return result;
	}

	protected TextReceivePayload createTextReceivePayload(final IntentRequest intentRequest) {
		final Intent intent = intentRequest.getIntent();
		final NlpContext nlpContext = createNlpContext(intent);

		final TextReceivePayload result = new TextReceivePayload();
		result.setNlpContext(nlpContext);
		return result;
	}
}
