/*
 * Copyright (C) 2016, nitro.ai
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.send.payload;

import ai.nitro.bot4j.middle.domain.send.SendMessage;
import ai.nitro.bot4j.middle.domain.send.payload.type.SendPayloadTypeEnum;

public class VideoSendPayload extends AbstractSendPayload {

	protected String title;

	protected String videoUrl;

	public VideoSendPayload(final SendMessage sendMessage) {
		super(SendPayloadTypeEnum.VIDEO, sendMessage);
	}

	public String getTitle() {
		return title;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public void setVideoUrl(final String videoUrl) {
		this.videoUrl = videoUrl;
	}

}
