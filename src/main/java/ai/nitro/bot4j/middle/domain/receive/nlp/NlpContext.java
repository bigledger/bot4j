/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.receive.nlp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class NlpContext {

	public static final double SCALED_INTENT_CONFIDENCE_THRESHOLD = 0.5;

	protected final SortedSet<NlpIntent> intents = new TreeSet<NlpIntent>();

	protected final List<NlpNamedEntity> namedEntities = new ArrayList<NlpNamedEntity>();

	public void addIntent(final NlpIntent intent) {
		intents.add(intent);
	}

	public void addNamedEntity(final NlpNamedEntity namedEntity) {
		namedEntities.add(namedEntity);
	}

	public NlpIntent getIntent(final String name) {
		for (final NlpIntent intent : intents) {
			if (intent.getName().equalsIgnoreCase(name)) {
				return intent;
			}
		}

		return null;
	}

	public SortedSet<NlpIntent> getIntents() {
		return intents;
	}

	public NlpIntent getMaxIntent() {
		return intents.isEmpty() ? null : intents.first();
	}

	public List<NlpNamedEntity> getNamedEntities() {
		return namedEntities;
	}

	public List<NlpNamedEntity> getNamedEntities(final String type) {
		final List<NlpNamedEntity> result = new ArrayList<NlpNamedEntity>();

		for (final NlpNamedEntity nlpNamedEntity : namedEntities) {
			if (type.equalsIgnoreCase(nlpNamedEntity.getType())) {
				result.add(nlpNamedEntity);
			}
		}

		return result;
	}

	public double getScaledIntentConfidence() {
		final Iterator<NlpIntent> intentsIterator = intents.iterator();
		final NlpIntent firstIntent = intentsIterator.hasNext() ? intentsIterator.next() : null;
		final NlpIntent secondIntent = intentsIterator.hasNext() ? intentsIterator.next() : null;

		final Double firstConfidence = firstIntent != null ? firstIntent.getConfidence() : null;
		final Double secondConfidence = secondIntent != null ? secondIntent.getConfidence() : null;
		final double result;

		if (firstConfidence == null) {
			result = 0;
		} else if (firstConfidence == 0.0) {
			result = 0;
		} else if (secondConfidence == null) {
			result = 1.0;
		} else {
			result = 1.0 - secondConfidence / firstConfidence;
		}

		return result;
	}

	public boolean hasIntent(final String name) {
		return getIntent(name) != null;
	}

	public void removeIntent(final NlpIntent intent) {
		intents.remove(intent);
	}

	@Override
	public String toString() {
		return "intents=[" + intents + "]";
	}
}
