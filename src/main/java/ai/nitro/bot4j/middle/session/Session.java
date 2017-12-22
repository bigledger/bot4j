/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.session;

import java.util.HashMap;
import java.util.Map;

public class Session {

	protected final Map<String, Object> values = new HashMap<String, Object>();

	public void clear() {
		values.clear();
	}

	public Object getValue(final String name) {
		return values.get(name);
	}

	public void putValue(final String name, final Object value) {
		values.put(name, value);
	}

	@Override
	public String toString() {
		return values.toString();
	}
}
