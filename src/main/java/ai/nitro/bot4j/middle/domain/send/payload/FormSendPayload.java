package ai.nitro.bot4j.middle.domain.send.payload;

import java.util.ArrayList;
import java.util.List;

import ai.nitro.bot4j.middle.domain.send.SendMessage;
import ai.nitro.bot4j.middle.domain.send.form.FormElement;

public class FormSendPayload extends AbstractSendPayload {

	protected final List<FormElement> formElements = new ArrayList<FormElement>();

	protected String text;

	protected String url;

	public FormSendPayload(final SendMessage sendMessage) {
		super(Type.FORM, sendMessage);
	}

	public void addElement(final FormElement element) {
		formElements.add(element);
	}

	public List<FormElement> getElement() {
		return formElements;
	}

	public List<FormElement> getFormElements() {
		return formElements;
	}

	public String getText() {
		return text;
	}

	public String getUrl() {
		return url;
	}

	public void setText(final String text) {
		this.text = text;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

}
