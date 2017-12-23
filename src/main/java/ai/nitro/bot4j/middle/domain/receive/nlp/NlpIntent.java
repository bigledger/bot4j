/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.receive.nlp;

public class NlpIntent implements Comparable<NlpIntent> {

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

	public Double getConfidence() {
		return confidence;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return 31 * 17 + name.hashCode();
	}

	public void setConfidence(final Double confidence) {
		this.confidence = confidence;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
