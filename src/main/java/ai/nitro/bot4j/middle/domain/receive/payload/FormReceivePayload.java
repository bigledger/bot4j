/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.receive.payload;

import java.util.ArrayList;
import java.util.List;

import ai.nitro.bot4j.middle.domain.receive.form.FormParam;

public class FormReceivePayload extends AbstractReceivePayload {

	protected List<FormParam> form = new ArrayList<FormParam>();

	protected String name;

	public FormReceivePayload() {
		super(Type.FORM);
	}

	public List<FormParam> getForm() {
		return form;
	}

	public String getFormParam(final String key) {
		String result = null;

		for (final FormParam formParam : form) {
			if (key.equals(formParam.getKey())) {
				result = formParam.getValue();
				break;
			}
		}

		return result;
	}

	public String getName() {
		return name;
	}

	public void setForm(final List<FormParam> form) {
		this.form = form;
	}

	public void setName(final String name) {
		this.name = name;
	}
}
