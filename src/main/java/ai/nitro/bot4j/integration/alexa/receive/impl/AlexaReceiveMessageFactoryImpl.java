package ai.nitro.bot4j.integration.alexa.receive.impl;

import java.util.Map;
import java.util.Map.Entry;

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
import ai.nitro.bot4j.middle.domain.receive.payload.TextReceivePayload;

public class AlexaReceiveMessageFactoryImpl implements AlexaReceiveMessageFactory {

	protected NlpContext createNlpContext(final Intent intent) {
		final NlpContext nlpContext = new NlpContext();
		nlpContext.setConfidence(1.0);

		final String intentName = intent.getName();
		nlpContext.setIntent(intentName);

		for (final Entry<String, Slot> entry : intent.getSlots().entrySet()) {
			final String key = entry.getKey();
			final Slot value = entry.getValue();

			final String slotValue = value.getValue();
			nlpContext.addNamedEntity(key, slotValue);
		}

		return nlpContext;
	}

	@Override
	public ReceiveMessage createReceiveMessage(final IntentRequest intentRequest, final User user,
			final SpeechletRequestEnvelope<IntentRequest> requestEnvelope, final Map<String, String[]> params) {
		final Participant sender = createSender(requestEnvelope, user);

		final ReceiveMessage result = new ReceiveMessage();
		result.setMessageId(intentRequest.getRequestId());
		result.setSender(sender);

		if (params != null) {
			result.setParams(params);
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
