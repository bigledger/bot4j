/*
 * Copyright (C) 2016, nitro.ai
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.receive.payload;

import java.util.ArrayList;
import java.util.List;

import ai.nitro.bot4j.middle.domain.receive.form.FormParam;

public class FormReceivePayload extends AbstractReceivePayload {

	protected List<FormParam> form = new ArrayList<FormParam>();

	public FormReceivePayload() {
		super(Type.FORM);
	}

	public List<FormParam> getForm() {
		return form;
	}

	public void setForm(final List<FormParam> form) {
		this.form = form;
	}
}
