/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.alexa.receive.impl;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletRequestHandlerException;
import com.amazon.speech.speechlet.servlet.ServletSpeechletRequestHandler;

import ai.nitro.bot4j.integration.alexa.Bot4jSpeechlet;
import ai.nitro.bot4j.integration.alexa.impl.Bot4jSpeechletImpl;
import ai.nitro.bot4j.integration.alexa.receive.AlexaReceiveHandler;
import ai.nitro.bot4j.integration.alexa.receive.AlexaReceiveMessageFactory;
import ai.nitro.bot4j.integration.alexa.send.AlexaMessageSender;
import ai.nitro.bot4j.middle.receive.MessageReceiver;

@Singleton
public class AlexaReceiveHandlerImpl implements AlexaReceiveHandler {

	static Logger LOG = LogManager.getLogger(AlexaReceiveHandlerImpl.class);

	@Inject
	protected AlexaMessageSender alexaMessageSender;

	@Inject
	protected AlexaReceiveMessageFactory alexaReceiveMessageFactory;

	@Inject
	protected MessageReceiver messageReceiver;

	protected Bot4jSpeechletImpl createBot4jSpeechlet(final Map<String, String[]> params) {
		return new Bot4jSpeechletImpl(alexaReceiveMessageFactory, alexaMessageSender, messageReceiver, params);
	}

	@Override
	public byte[] handleSpeechletRequest(final byte[] speechletRequest, final Map<String, String[]> params) {
		byte[] result = null;

		try {
			final Bot4jSpeechlet bot4jSpeechlet = createBot4jSpeechlet(params);
			final ServletSpeechletRequestHandler speechletRequestHandler = new ServletSpeechletRequestHandler();
			result = speechletRequestHandler.handleSpeechletCall(bot4jSpeechlet, speechletRequest);
		} catch (IOException | SpeechletRequestHandlerException | SpeechletException e) {
			LOG.warn(e.getMessage(), e);
		}

		return result;
	}
}
