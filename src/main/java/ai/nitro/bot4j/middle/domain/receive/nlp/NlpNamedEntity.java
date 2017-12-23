/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.receive.nlp;

public class NlpNamedEntity {

	protected Double confidence;

	protected String entity;

	protected String type;

	@Override
	public boolean equals(final Object o) {
		final boolean result;

		if (o == this) {
			result = true;
		} else if (!(o instanceof NlpNamedEntity)) {
			result = false;
		} else {
			final NlpNamedEntity otherNlpNamedEntity = (NlpNamedEntity) o;

			if (!otherNlpNamedEntity.getType().equals(type)) {
				result = false;
			} else if (!otherNlpNamedEntity.getEntity().equals(entity)) {
				result = false;
			} else if (!otherNlpNamedEntity.getConfidence().equals(confidence)) {
				result = false;
			} else {
				result = true;
			}
		}

		return result;
	}

	public Double getConfidence() {
		return confidence;
	}

	public String getEntity() {
		return entity;
	}

	public String getType() {
		return type;
	}

	@Override
	public int hashCode() {
		return 31 * 17 + type.hashCode();
	}

	public void setConfidence(final Double confidence) {
		this.confidence = confidence;
	}

	public void setEntity(final String entity) {
		this.entity = entity;
	}

	public void setType(final String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return entity;
	}
}
