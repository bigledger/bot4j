/*
 * Copyright (C) 2016, nitro.ai
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.send.button;

public abstract class AbstractSendButton {

	public enum Type {
		FORM_BUTTON, POSTBACK_BUTTON, WEB_BUTTON
	}

	protected String title;

	protected final Type type;

	public AbstractSendButton(final Type type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public Type getType() {
		return type;
	}

	public void setTitle(final String title) {
		this.title = title;
	}
}
