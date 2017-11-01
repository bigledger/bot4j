/*
 * Copyright (C) 2017, nitro.ai
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.receive.nlp.impl;

import ai.nitro.bot4j.middle.domain.receive.nlp.NlpIntent;

public class NlpIntentImpl implements NlpIntent {

	protected Double confidence;

	protected String name;

	@Override
	public Double getConfidence() {
		return confidence;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setConfidence(final Double confidence) {
		this.confidence = confidence;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}
}
