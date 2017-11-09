/*
 * Copyright (C) 2016, nitro.ai
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.send.button;

public class FormSendButton extends AbstractSendButton {

	protected String url;

	public FormSendButton() {
		super(Type.FORM_BUTTON);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}
}
