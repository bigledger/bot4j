package ai.nitro.bot4j.integration.api.ai.receive.webhook.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.inject.Inject;

import ai.api.GsonFactory;
import ai.api.model.AIResponse;
import ai.api.web.AIServiceServlet;
import ai.nitro.bot4j.integration.api.ai.receive.ApiAiReceiveHandler;
import ai.nitro.bot4j.integration.api.ai.receive.webhook.ApiAiWebhook;

public class ApiAiWebhookImpl extends AIServiceServlet implements ApiAiWebhook {

	private final static Logger LOG = LogManager.getLogger(ApiAiWebhookImpl.class);

	@Inject
	protected ApiAiReceiveHandler apiAiReceiveHandler;

	private final Gson gson = GsonFactory.getDefaultFactory().getGson();

	@Override
	public String post(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		final String result = "";

		final String data = IOUtils.toString(request.getInputStream(), "UTF-8");
		final AIResponse aiResponse = gson.fromJson(data, AIResponse.class);

		apiAiReceiveHandler.handleAIResponse(aiResponse);

		return result;
	}
}
