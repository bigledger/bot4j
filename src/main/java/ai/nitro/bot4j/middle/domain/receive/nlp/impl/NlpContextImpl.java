/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.receive.nlp.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import ai.nitro.bot4j.middle.domain.receive.nlp.NlpContext;
import ai.nitro.bot4j.middle.domain.receive.nlp.NlpIntent;
import ai.nitro.bot4j.middle.domain.receive.nlp.NlpNamedEntity;

public class NlpContextImpl implements NlpContext {

	protected final SortedSet<NlpIntent> intents = new TreeSet<NlpIntent>();

	protected final Map<String, List<NlpNamedEntity>> namedEntities = new HashMap<String, List<NlpNamedEntity>>();

	@Override
	public void addIntent(final NlpIntent intent) {
		intents.add(intent);
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

	@Override
	public NlpIntent getIntent(final String name) {
		for (final NlpIntent intent : intents) {
			if (intent.getName().equalsIgnoreCase(name)) {
				return intent;
			}
		}

		return null;
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
	public Map<String, List<NlpNamedEntity>> getNamedEntities() {
		return namedEntities;
	}

	@Override
	public double getScaledIntentConfidence() {
		final Iterator<NlpIntent> intentsIterator = intents.iterator();
		final NlpIntent firstIntent = intentsIterator.hasNext() ? intentsIterator.next() : null;
		final NlpIntent secondIntent = intentsIterator.hasNext() ? intentsIterator.next() : null;

		final Double firstConfidence = firstIntent != null ? firstIntent.getConfidence() : null;
		final Double secondConfidence = secondIntent != null ? secondIntent.getConfidence() : null;
		final double result;

		if (firstConfidence == null) {
			result = 0;
		} else if (secondConfidence == null) {
			result = 0;
		} else if (firstConfidence == 0.0) {
			result = 0;
		} else {
			result = 1.0 - secondConfidence / firstConfidence;
		}

		return result;
	}

	@Override
	public boolean hasIntent(final String name) {
		return getIntent(name) != null;
	}

	@Override
	public void removeIntent(final NlpIntent intent) {
		intents.remove(intent);
	}

	@Override
	public String toString() {
		return "intents=[" + intents + "]";
	}
}
