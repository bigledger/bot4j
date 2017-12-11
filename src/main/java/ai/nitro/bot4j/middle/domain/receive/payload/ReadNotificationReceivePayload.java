/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.receive.payload;

public class ReadNotificationReceivePayload extends AbstractReceivePayload {

	protected String watermark;

	public ReadNotificationReceivePayload() {
		super(Type.READ_NOTIFICATION);
	}

	public String getWatermark() {
		return watermark;
	}

	public void setWatermark(final String watermark) {
		this.watermark = watermark;
	}

}
