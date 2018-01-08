package ai.nitro.bot4j.middle.domain.send.form;

public class FormElement {

	protected String label;

	protected String name;

	protected String type;

	protected String value;

	public String getLabel() {
		return label;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	public void setLabel(final String label) {
		this.label = label;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public void setValue(final String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "name=[" + name + "], type=[" + type + "], label=[" + label + "], value=[" + value + "]";
	}
}
