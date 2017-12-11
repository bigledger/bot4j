/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.send.payload;

import java.util.ArrayList;
import java.util.List;

import ai.nitro.bot4j.middle.domain.send.SendMessage;
import ai.nitro.bot4j.middle.domain.send.payload.type.SendPayloadTypeEnum;
import ai.nitro.bot4j.middle.domain.send.quickreply.QuickReply;

public class QuickRepliesSendPayload extends AbstractSendPayload {

	protected List<QuickReply> quickReplies = new ArrayList<QuickReply>();

	protected String text;

	public QuickRepliesSendPayload(final SendMessage sendMessage) {
		super(SendPayloadTypeEnum.QUICK_REPLIES, sendMessage);
	}

	public void addQuickReply(final QuickReply quickReply) {
		quickReplies.add(quickReply);
	}

	public List<QuickReply> getQuickReplies() {
		return quickReplies;
	}

	public String getText() {
		return text;
	}

	public void setText(final String text) {
		this.text = text;
	}
}
