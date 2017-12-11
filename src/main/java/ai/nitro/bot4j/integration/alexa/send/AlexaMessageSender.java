/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.alexa.send;

import com.amazon.speech.ui.Card;

import ai.nitro.bot4j.middle.send.PlatformMessageSender;

public interface AlexaMessageSender extends PlatformMessageSender {

	Card getCard();

	boolean getShouldEndSession();

	String getText();

}
