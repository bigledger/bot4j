/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j;

import org.junit.Before;

import com.google.inject.Guice;
import com.google.inject.Injector;

public abstract class TestBase {

	protected final double epsilon = 0.000001;

	@Before
	public void setUp() throws Exception {
		final Injector injector = Guice.createInjector(new Bot4jTestModule());
		injector.injectMembers(this);
	}
}
