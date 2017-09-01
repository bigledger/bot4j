package ai.nitro.bot4j.integration.api.ai.send;

import ai.api.model.Fulfillment;
import ai.nitro.bot4j.middle.domain.Platform;
import ai.nitro.bot4j.middle.domain.send.SendMessage;
import ai.nitro.bot4j.middle.send.PlatformMessageSender;

public interface ApiAiMessageSender extends PlatformMessageSender {

	Fulfillment getFulfillment();

	@Override
	Platform getPlatform();

	@Override
	boolean send(SendMessage sendMessage);

}
