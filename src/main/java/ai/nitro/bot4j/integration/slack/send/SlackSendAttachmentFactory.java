/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.slack.send;

import com.github.seratch.jslack.api.model.Attachment;

import ai.nitro.bot4j.middle.domain.send.button.AbstractSendButton;

public interface SlackSendAttachmentFactory {

	Attachment createAttachment(AbstractSendButton button);
}
