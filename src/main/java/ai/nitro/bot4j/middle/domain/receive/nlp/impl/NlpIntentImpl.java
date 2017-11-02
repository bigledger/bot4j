/*
 * Copyright (C) 2017, nitro.ai
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.receive.nlp.impl;

import ai.nitro.bot4j.middle.domain.receive.nlp.NlpIntent;

public class NlpIntentImpl implements NlpIntent, Comparable<NlpIntent> {

	protected Double confidence;

	protected String name;

	@Override
	public int compareTo(final NlpIntent otherNlpIntent) {
		return otherNlpIntent.getConfidence().compareTo(confidence);
	}

	@Override
	public boolean equals(final Object o) {
		final boolean result;

		if (o == this) {
			result = true;
		} else if (!(o instanceof NlpIntent)) {
			result = false;
		} else {
			final NlpIntent otherNlpIntent = (NlpIntent) o;

			if (!otherNlpIntent.getName().equals(name)) {
				result = false;
			} else if (!otherNlpIntent.getConfidence().equals(confidence)) {
				result = false;
			} else {
				result = true;
			}
		}

		return result;
	}

	@Override
	public Double getConfidence() {
		return confidence;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return 31 * 17 + name.hashCode();
	}

	@Override
	public void setConfidence(final Double confidence) {
		this.confidence = confidence;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
