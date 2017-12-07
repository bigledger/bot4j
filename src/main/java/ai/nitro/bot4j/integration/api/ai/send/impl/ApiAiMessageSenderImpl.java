package ai.nitro.bot4j.integration.api.ai.send.impl;

import javax.inject.Singleton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ai.api.model.Fulfillment;
import ai.nitro.bot4j.integration.api.ai.domain.ApiAiPlatformEnum;
import ai.nitro.bot4j.integration.api.ai.send.ApiAiMessageSender;
import ai.nitro.bot4j.middle.domain.Platform;
import ai.nitro.bot4j.middle.domain.send.SendMessage;
import ai.nitro.bot4j.middle.domain.send.payload.AbstractSendPayload;
import ai.nitro.bot4j.middle.domain.send.payload.TextSendPayload;
import ai.nitro.bot4j.middle.domain.send.payload.type.SendPayloadType;
import ai.nitro.bot4j.middle.domain.send.payload.type.SendPayloadTypeEnum;

@Singleton
public class ApiAiMessageSenderImpl implements ApiAiMessageSender {

	static Logger LOG = LogManager.getLogger(ApiAiMessageSenderImpl.class);

	protected ThreadLocal<Fulfillment> threadlocalFulfillment = new ThreadLocal<>();

	@Override
	public Fulfillment getFulfillment() {
		final Fulfillment result = threadlocalFulfillment.get();
		threadlocalFulfillment.remove();
		return result;
	}

	@Override
	public Platform getPlatform() {
		return ApiAiPlatformEnum.APIAI;
	}

	@Override
	public boolean send(final SendMessage sendMessage) {
		boolean result = false;

		final AbstractSendPayload sendPayload = sendMessage.getPayload();
		final SendPayloadType sendPayloadType = sendPayload.getSendPayloadType();

		if (SendPayloadTypeEnum.TEXT.equals(sendPayloadType)) {
			final TextSendPayload textSendPayload = (TextSendPayload) sendPayload;
			final String text = textSendPayload.getText();

			final Fulfillment fulfillment = new Fulfillment();
			fulfillment.setSpeech(text);

			threadlocalFulfillment.set(fulfillment);

			result = true;
		} else {
			LOG.warn("ignoring send payload with type {}", sendPayloadType);
		}

		return result;
	}
}
