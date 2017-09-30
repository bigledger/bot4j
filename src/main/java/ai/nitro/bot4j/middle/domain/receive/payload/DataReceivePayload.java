/*
 * Copyright (C) 2016, nitro.ai
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package ai.nitro.bot4j.middle.domain.receive.payload;

public class DataReceivePayload extends AbstractReceivePayload {

	protected String attachmentType;

	protected byte[] data;

	public DataReceivePayload() {
		super(Type.DATA);
	}

	public String getAttachmentType() {
		return attachmentType;
	}

	public byte[] getData() {
		return data;
	}

	public void setAttachmentType(final String attachmentType) {
		this.attachmentType = attachmentType;
	}

	public void setData(final byte[] data) {
		this.data = data;
	}
}
