/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.slack.send.impl;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.github.seratch.jslack.api.model.Action;

import ai.nitro.bot4j.integration.slack.send.SlackSendActionFactory;
import ai.nitro.bot4j.middle.domain.send.button.PostbackSendButton;
import ai.nitro.bot4j.middle.payload.PostbackPayload;
import ai.nitro.bot4j.middle.payload.PostbackPayloadService;

@Singleton
public class SlackSendActionFactoryImpl implements SlackSendActionFactory {

	@Inject
	protected PostbackPayloadService postbackPayloadService;

	@Override
	public Action createPostbackAction(final PostbackSendButton postbackSendButton) {
		final PostbackPayload postbackPayload = new PostbackPayload();
		postbackPayload.name = postbackSendButton.getName();
		postbackPayload.payload = postbackSendButton.getPayload();
		final String serializedPayload = postbackPayloadService.serialize(postbackPayload);

		final Action action = new Action();
		action.setText(postbackSendButton.getTitle());
		action.setName(postbackSendButton.getName());
		action.setValue(serializedPayload);
		return action;
	}

}
