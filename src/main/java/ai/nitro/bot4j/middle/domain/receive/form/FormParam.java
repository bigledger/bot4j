package ai.nitro.bot4j.middle.domain.receive.form;

public class FormParam {

	protected String key;

	protected String value;

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public void setKey(final String key) {
		this.key = key;
	}

	public void setValue(final String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "key=[" + key + "], value=[" + value + "]";
	}
}
