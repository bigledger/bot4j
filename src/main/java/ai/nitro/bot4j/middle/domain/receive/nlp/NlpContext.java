/*
 * Copyright (C) 2017, nitro.ai
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.receive.nlp;

import java.util.List;
import java.util.Map;
import java.util.SortedSet;

public interface NlpContext {

	public static final double DEFAULT_MAX_INTENT_CONFIDENCE_THRESHOLD = 0.5;

	void addIntent(NlpIntent intent);

	void addNamedEntity(NlpNamedEntity namedEntity);

	SortedSet<NlpIntent> getIntents();

	NlpIntent getMaxIntent();

	Double getMaxIntentConfidenceThreshold();

	Map<String, List<NlpNamedEntity>> getNamedEntities();

	void setMaxIntentConfidenceThreshold(Double maxIntentConfidenceThreshold);
}
