package ai.nitro.bot4j.integration.api.ai.receive.webhook.impl;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import javax.inject.Inject;

import ai.api.GsonFactory;
import ai.api.model.AIResponse;
import ai.api.model.Fulfillment;
import ai.nitro.bot4j.integration.api.ai.receive.ApiAiReceiveHandler;
import ai.nitro.bot4j.integration.api.ai.receive.webhook.ApiAiWebhook;

public class ApiAiWebhookImpl implements ApiAiWebhook {

	private final static Logger LOG = LogManager.getLogger(ApiAiWebhookImpl.class);

	@Inject
	protected ApiAiReceiveHandler apiAiReceiveHandler;

	private final Gson gson = GsonFactory.getDefaultFactory().getGson();

	@Override
	public String post(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		final String result;
		final Map<String, String[]> params = request.getParameterMap();

		final String data = IOUtils.toString(request.getInputStream(), "UTF-8");
		final AIResponse aiResponse = gson.fromJson(data, AIResponse.class);
		final Fulfillment fulfillment = apiAiReceiveHandler.handleAIResponse(aiResponse, params);

		result = gson.toJson(fulfillment);

		return result;
	}
}
