/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.facebook.domain;

import ai.nitro.bot4j.middle.domain.Platform;

public enum FacebookPlatformEnum implements Platform {
	FACEBOOK;

	@Override
	public boolean isAsync() {
		return true;
	}

	@Override
	public boolean isVoice() {
		return false;
	}
}
