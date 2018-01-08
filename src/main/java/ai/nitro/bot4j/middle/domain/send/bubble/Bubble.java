/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.send.bubble;

import java.util.ArrayList;
import java.util.List;

import ai.nitro.bot4j.middle.domain.send.button.AbstractSendButton;

public class Bubble {

	protected List<AbstractSendButton> buttons = new ArrayList<AbstractSendButton>();

	protected String imageUrl;

	protected String text;

	protected String title;

	protected String url;

	public void addButton(final AbstractSendButton button) {
		buttons.add(button);
	}

	public List<AbstractSendButton> getButtons() {
		return buttons;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getText() {
		return text;
	}

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}

	public void setImageUrl(final String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setText(final String text) {
		this.text = text;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "title=[" + title + "], imageUrl=[" + imageUrl + "], url=[" + url + "], text=[" + text + "]";
	}
}
