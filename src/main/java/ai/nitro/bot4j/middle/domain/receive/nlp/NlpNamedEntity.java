/*
 * Copyright (C) 2017, nitro.ai
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.receive.nlp;

public interface NlpNamedEntity {

	Double getConfidence();

	String getEntity();

	String getType();

	void setConfidence(Double confidence);

	void setEntity(String entity);

	void setType(String type);
}
