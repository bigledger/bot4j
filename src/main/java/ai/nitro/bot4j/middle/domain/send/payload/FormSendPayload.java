package ai.nitro.bot4j.middle.domain.send.payload;

import java.util.ArrayList;
import java.util.List;

import ai.nitro.bot4j.middle.domain.send.SendMessage;
import ai.nitro.bot4j.middle.domain.send.form.FormElement;

public class FormSendPayload extends AbstractSendPayload {

	protected final List<FormElement> formElements = new ArrayList<FormElement>();

	protected String title;

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

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}

	public void setTitle(final String text) {
		this.title = text;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

}
