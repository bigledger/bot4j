/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.integration.telegram.send;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;

import ai.nitro.bot4j.middle.domain.send.button.AbstractSendButton;

public interface TelegramSendInlineKeyboardFactory {

	InlineKeyboardButton createInlineKeyboard(AbstractSendButton abstractSendButton);

}
