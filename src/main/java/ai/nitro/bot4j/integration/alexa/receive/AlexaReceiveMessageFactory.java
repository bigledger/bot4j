package ai.nitro.bot4j.integration.alexa.receive;

import java.util.Map;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.User;

import ai.nitro.bot4j.middle.domain.receive.ReceiveMessage;

public interface AlexaReceiveMessageFactory {

	ReceiveMessage createReceiveMessage(final IntentRequest intentRequest, final User user,
			final SpeechletRequestEnvelope<IntentRequest> requestEnvelope, Map<String, String[]> params);

}
