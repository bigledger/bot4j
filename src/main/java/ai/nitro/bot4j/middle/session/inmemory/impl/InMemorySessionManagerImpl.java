/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.session.inmemory.impl;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import ai.nitro.bot4j.middle.domain.Participant;
import ai.nitro.bot4j.middle.domain.Platform;
import ai.nitro.bot4j.middle.domain.receive.ReceiveMessage;
import ai.nitro.bot4j.middle.session.Session;
import ai.nitro.bot4j.middle.session.inmemory.InMemorySessionManager;
import ai.nitro.bot4j.middle.session.inmemory.SenderKey;

@Singleton
public class InMemorySessionManagerImpl implements InMemorySessionManager {

	protected final Cache<SenderKey, Session> sessionCache = CacheBuilder.newBuilder()
			.expireAfterAccess(60, TimeUnit.MINUTES).build();

	protected Session getSession(final Participant sender) {
		final Session result;

		if (sender == null) {
			result = null;
		} else if (sender.getId() == null) {
			result = null;
		} else if (sender.getPlatform() == null) {
			result = null;
		} else {
			final Platform platform = sender.getPlatform();
			final String senderId = sender.getId();
			final SenderKey key = new SenderKey(platform, senderId);
			Session session = sessionCache.getIfPresent(key);

			if (session == null) {
				session = new Session();
				sessionCache.put(key, session);
			}

			result = session;
		}

		return result;
	}

	@Override
	public Session getSession(final ReceiveMessage receiveMessage) {
		final Session result;

		if (receiveMessage == null) {
			result = null;
		} else {
			final Participant sender = receiveMessage.getSender();
			result = getSession(sender);
		}

		return result;
	}
}
