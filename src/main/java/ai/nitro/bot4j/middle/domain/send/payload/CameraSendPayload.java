/*
 * Copyright (C) 2017, nitro.ai
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.send.payload;

import ai.nitro.bot4j.middle.domain.send.SendMessage;

public class CameraSendPayload extends AbstractSendPayload {

	public CameraSendPayload(final SendMessage sendMessage) {
		super(Type.CAMERA, sendMessage);
	}
}
