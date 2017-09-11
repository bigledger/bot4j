/*
 * Copyright (C) 2016, nitro.ai
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.alexa.impl;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.User;
import com.amazon.speech.ui.Card;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.SsmlOutputSpeech;

import ai.nitro.bot4j.integration.alexa.Bot4jSpeechlet;
import ai.nitro.bot4j.integration.alexa.receive.AlexaReceiveMessageFactory;
import ai.nitro.bot4j.integration.alexa.send.AlexaMessageSender;
import ai.nitro.bot4j.middle.domain.receive.ReceiveMessage;
import ai.nitro.bot4j.middle.receive.MessageReceiver;

public class Bot4jSpeechletImpl implements Bot4jSpeechlet {

	private static final String AMAZON_CANCEL_INTENT = "AMAZON.CancelIntent";

	private static final String AMAZON_HELP_INTENT = "AMAZON.HelpIntent";

	private static final String AMAZON_STOP_INTENT = "AMAZON.StopIntent";

	private static final Logger LOG = LogManager.getLogger(Bot4jSpeechletImpl.class);

	protected AlexaMessageSender alexaMessageSender;

	protected AlexaReceiveMessageFactory alexaReceiveMessageFactory;

	protected MessageReceiver messageReceiver;

	private final Map<String, String[]> params;

	public Bot4jSpeechletImpl(final AlexaReceiveMessageFactory alexaReceiveMessageFactory,
			final AlexaMessageSender alexaMessageSender, final MessageReceiver messageReceiver,
			final Map<String, String[]> params) {
		this.alexaReceiveMessageFactory = alexaReceiveMessageFactory;
		this.alexaMessageSender = alexaMessageSender;
		this.messageReceiver = messageReceiver;
		this.params = params;
	}

	protected SpeechletResponse onCancelIntent() {
		final PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText("Bye");

		final SpeechletResponse result = SpeechletResponse.newTellResponse(speech);
		result.setShouldEndSession(true);

		return result;
	}

	protected SpeechletResponse onHelpIntent() {
		final PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText("Ok");

		final SpeechletResponse result = SpeechletResponse.newTellResponse(speech);
		result.setShouldEndSession(false);

		return result;
	}

	@Override
	public SpeechletResponse onIntent(final SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
		LOG.debug("onIntent requestId={}, sessionId={}", requestEnvelope.getRequest().getRequestId(),
				requestEnvelope.getSession().getSessionId());

		final SpeechletResponse result;

		if (requestEnvelope.getRequest().getIntent() == null) {
			LOG.warn("no intent set");
			result = null;
		} else if (requestEnvelope.getSession().getUser() == null) {
			LOG.warn("no user set");
			result = null;
		} else {
			final IntentRequest request = requestEnvelope.getRequest();
			final String intentName = request.getIntent().getName();

			switch (intentName) {
			case AMAZON_HELP_INTENT:
				result = onHelpIntent();
				break;
			case AMAZON_STOP_INTENT:
				result = onStopIntent();
				break;
			case AMAZON_CANCEL_INTENT:
				result = onCancelIntent();
				break;
			default:
				result = onIntent(requestEnvelope, request);
			}
		}

		return result;
	}

	protected SpeechletResponse onIntent(final SpeechletRequestEnvelope<IntentRequest> requestEnvelope,
			final IntentRequest request) {
		final SpeechletResponse result;
		final User user = requestEnvelope.getSession().getUser();

		receiveMessage(request, user, requestEnvelope);

		final String text = alexaMessageSender.getText();

		final SsmlOutputSpeech speech = new SsmlOutputSpeech();
		final String wrappedSsmlText = wrapTextForSsml(text);
		speech.setSsml(wrappedSsmlText);

		final Card card = alexaMessageSender.getCard();

		if (card == null) {
			result = SpeechletResponse.newTellResponse(speech);
		} else {
			result = SpeechletResponse.newTellResponse(speech, card);
		}

		result.setShouldEndSession(alexaMessageSender.getShouldEndSession());

		return result;
	}

	@Override
	public SpeechletResponse onLaunch(final SpeechletRequestEnvelope<LaunchRequest> requestEnvelope) {
		return null;
	}

	@Override
	public void onSessionEnded(final SpeechletRequestEnvelope<SessionEndedRequest> requestEnvelope) {
	}

	@Override
	public void onSessionStarted(final SpeechletRequestEnvelope<SessionStartedRequest> requestEnvelope) {
	}

	protected SpeechletResponse onStopIntent() {
		final PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText("Bye");

		final SpeechletResponse result = SpeechletResponse.newTellResponse(speech);
		result.setShouldEndSession(true);

		return result;
	}

	protected void receiveMessage(final IntentRequest intentRequest, final User user,
			final SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {

		final ReceiveMessage receiveMessage = alexaReceiveMessageFactory.createReceiveMessage(intentRequest, user,
				requestEnvelope, params);
		messageReceiver.receive(receiveMessage);
	}

	protected String wrapTextForSsml(final String text) {
		final String result = "<speak> " + text + " </speak>";
		return result;
	}
}
