package ai.nitro.bot4j.integration.api.ai.domain;

import ai.nitro.bot4j.middle.domain.Platform;

public enum ApiAiPlatformEnum implements Platform {
	APIAI;

	@Override
	public boolean isVoice() {
		return true;
	}

}
