/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.alexa.domain;

import ai.nitro.bot4j.middle.domain.Platform;

public enum AlexaPlatformEnum implements Platform {
	ALEXA;

	@Override
	public boolean isAsync() {
		return false;
	}

	@Override
	public boolean isVoice() {
		return true;
	}
}
