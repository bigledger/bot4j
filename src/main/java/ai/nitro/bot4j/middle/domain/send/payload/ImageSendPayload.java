/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.send.payload;

import ai.nitro.bot4j.middle.domain.send.SendMessage;
import ai.nitro.bot4j.middle.domain.send.payload.type.SendPayloadTypeEnum;

public class ImageSendPayload extends AbstractSendPayload {

	protected String imageUrl;

	protected String title;

	public ImageSendPayload(final SendMessage sendMessage) {
		super(SendPayloadTypeEnum.IMAGE, sendMessage);
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setImageUrl(final String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

}
