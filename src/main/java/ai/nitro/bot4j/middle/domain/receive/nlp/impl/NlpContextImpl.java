/*
 * Copyright (C) 2017, nitro.ai
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.receive.nlp.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import ai.nitro.bot4j.middle.domain.receive.nlp.NlpContext;
import ai.nitro.bot4j.middle.domain.receive.nlp.NlpIntent;
import ai.nitro.bot4j.middle.domain.receive.nlp.NlpNamedEntity;

public class NlpContextImpl implements NlpContext {

	protected final SortedSet<NlpIntent> intents = new TreeSet<NlpIntent>();

	protected Double maxIntentConfidenceThreshold;

	protected final Map<String, List<NlpNamedEntity>> namedEntities = new HashMap<String, List<NlpNamedEntity>>();

	@Override
	public void addIntent(final NlpIntent intent) {
		intents.add(intent);
		resetMaxIntentConfidenceThreshold();
	}

	@Override
	public void addNamedEntity(final NlpNamedEntity namedEntity) {
		assureNamedEntityList(namedEntity.getType());

		namedEntities.get(namedEntity.getType()).add(namedEntity);
	}

	protected void assureNamedEntityList(final String name) {
		if (namedEntities.get(name) == null) {
			namedEntities.put(name, new ArrayList<NlpNamedEntity>());
		}
	}

	protected double calculateIntentConfidenceThreshold() {
		final double result;

		if (intents.isEmpty()) {
			result = 0.0;
		} else {
			final double confidenceSum = intents.stream().map(intent -> intent.getConfidence())
					.mapToDouble(Double::doubleValue).sum();
			result = confidenceSum * INTENT_CONFIDENCE_THRESHOLD_FACTOR;
		}

		return result;
	}

	@Override
	public SortedSet<NlpIntent> getIntents() {
		return intents;
	}

	@Override
	public NlpIntent getMaxIntent() {
		return intents.isEmpty() ? null : intents.first();
	}

	@Override
	public Double getMaxIntentConfidenceThreshold() {
		if (maxIntentConfidenceThreshold == null) {
			maxIntentConfidenceThreshold = calculateIntentConfidenceThreshold();
		}

		return maxIntentConfidenceThreshold;
	}

	@Override
	public Map<String, List<NlpNamedEntity>> getNamedEntities() {
		return namedEntities;
	}

	@Override
	public void removeIntent(final NlpIntent intent) {
		intents.remove(intent);
		resetMaxIntentConfidenceThreshold();
	}

	protected void resetMaxIntentConfidenceThreshold() {
		maxIntentConfidenceThreshold = null;
	}

	@Override
	public String toString() {
		return "intents=[" + intents + "]";
	}
}
