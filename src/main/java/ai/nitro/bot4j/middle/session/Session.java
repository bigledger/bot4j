/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.session;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import com.google.common.collect.Iterators;

public class Session implements HttpSession {

	protected Deque<Runnable> concernStack = new ArrayDeque<Runnable>();

	protected final long creationTime;

	protected final Map<String, Object> values = new HashMap<String, Object>();

	public Session() {
		creationTime = System.currentTimeMillis();
	}

	public void clear() {
		values.clear();
	}

	@Override
	public Object getAttribute(final String name) {
		return values.get(name);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		return Iterators.asEnumeration(values.keySet().iterator());
	}

	public Deque<Runnable> getConcernStack() {
		return concernStack;
	}

	@Override
	public long getCreationTime() {
		return creationTime;
	}

	@Override
	public String getId() {
		return String.valueOf(creationTime);
	}

	@Override
	public long getLastAccessedTime() {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int getMaxInactiveInterval() {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public ServletContext getServletContext() {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public HttpSessionContext getSessionContext() {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Object getValue(final String name) {
		return values.get(name);
	}

	@Override
	public String[] getValueNames() {
		return values.keySet().toArray(new String[values.keySet().size()]);
	}

	@Override
	public void invalidate() {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean isNew() {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void putValue(final String name, final Object value) {
		setAttribute(name, value);
	}

	@Override
	public void removeAttribute(final String name) {
		values.remove(name);
	}

	@Override
	public void removeValue(final String name) {
		removeAttribute(name);
	}

	@Override
	public void setAttribute(final String name, final Object value) {
		values.put(name, value);
	}

	@Override
	public void setMaxInactiveInterval(final int interval) {
		throw new java.lang.UnsupportedOperationException();
	}

	public void setMethodStack(final Deque<Runnable> concernStack) {
		this.concernStack = concernStack;
	}

	@Override
	public String toString() {
		return values.toString();
	}
}
