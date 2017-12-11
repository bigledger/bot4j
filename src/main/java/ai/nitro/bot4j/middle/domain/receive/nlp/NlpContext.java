/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.receive.nlp;

import java.util.List;
import java.util.Map;
import java.util.SortedSet;

public interface NlpContext {

	static final double SCALED_INTENT_CONFIDENCE_THRESHOLD = 0.5;

	void addIntent(NlpIntent intent);

	void addNamedEntity(NlpNamedEntity namedEntity);

	NlpIntent getIntent(String name);

	SortedSet<NlpIntent> getIntents();

	NlpIntent getMaxIntent();

	Map<String, List<NlpNamedEntity>> getNamedEntities();

	double getScaledIntentConfidence();

	boolean hasIntent(String name);

	void removeIntent(NlpIntent intent);
}
