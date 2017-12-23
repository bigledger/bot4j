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

	protected final Map<String, String> entries = new HashMap<String, String>();

	public void clear() {
		entries.clear();
	}

	public Map<String, String> getEntries() {
		return entries;
	}

	public String getValue(final String name) {
		return entries.get(name);
	}

	public void putValue(final String name, final String value) {
		entries.put(name, value);
	}

	@Override
	public String toString() {
		return entries.toString();
	}
}
