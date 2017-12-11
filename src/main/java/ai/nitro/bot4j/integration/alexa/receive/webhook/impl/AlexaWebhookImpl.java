/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.alexa.receive.webhook.impl;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazon.speech.Sdk;
import com.amazon.speech.speechlet.authentication.SpeechletRequestSignatureVerifier;

import ai.nitro.bot4j.integration.alexa.receive.AlexaReceiveHandler;
import ai.nitro.bot4j.integration.alexa.receive.webhook.AlexaWebhook;

@Singleton
public class AlexaWebhookImpl implements AlexaWebhook {

	static Logger LOG = LogManager.getLogger(AlexaWebhookImpl.class);

	@Inject
	protected AlexaReceiveHandler alexaReceiveHandler;

	@Override
	public String post(final HttpServletRequest req, final HttpServletResponse res) throws IOException {
		String result = "";

		try {
			final byte[] serializedSpeechletRequest = IOUtils.toByteArray(req.getInputStream());

			SpeechletRequestSignatureVerifier.checkRequestSignature(serializedSpeechletRequest,
					req.getHeader(Sdk.SIGNATURE_REQUEST_HEADER),
					req.getHeader(Sdk.SIGNATURE_CERTIFICATE_CHAIN_URL_REQUEST_HEADER));

			final Map<String, String[]> params = req.getParameterMap();

			final byte[] outputBytes = alexaReceiveHandler.handleSpeechletRequest(serializedSpeechletRequest, params);
			result = new String(outputBytes);
		} catch (final IOException e) {
			LOG.warn(e.getMessage(), e);
		} catch (final SecurityException e) {
			final int statusCode = HttpServletResponse.SC_BAD_REQUEST;
			LOG.error("Exception occurred in doPost, returning status code {}", statusCode, e);
			res.sendError(statusCode, e.getMessage());
		}

		return result;
	}

}