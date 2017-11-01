/*
 * Copyright (C) 2017, nitro.ai
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.receive.nlp;

public interface NlpIntent {

	Double getConfidence();

	String getName();

	void setConfidence(Double confidence);

	void setName(String name);
}
