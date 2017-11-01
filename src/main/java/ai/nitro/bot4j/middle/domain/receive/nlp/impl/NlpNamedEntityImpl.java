/*
 * Copyright (C) 2017, nitro.ai
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.receive.nlp.impl;

import ai.nitro.bot4j.middle.domain.receive.nlp.NlpNamedEntity;

public class NlpNamedEntityImpl implements NlpNamedEntity {

	protected Double confidence;

	protected String entity;

	protected String type;

	@Override
	public Double getConfidence() {
		return confidence;
	}

	@Override
	public String getEntity() {
		return entity;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setConfidence(final Double confidence) {
		this.confidence = confidence;
	}

	@Override
	public void setEntity(final String entity) {
		this.entity = entity;
	}

	@Override
	public void setType(final String type) {
		this.type = type;
	}
}
