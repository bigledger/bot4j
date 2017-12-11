/*
 * Copyright (C) 2017, nitro ventures GmbH
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.payload;

import com.google.gson.annotations.SerializedName;

public class PostbackPayload {

	@SerializedName("Name")
	public String name;

	@SerializedName("Payload")
	public String[] payload;

	@Override
	public String toString() {
		return "name=[" + name + "], payload=[" + payload + "]";
	}
}
