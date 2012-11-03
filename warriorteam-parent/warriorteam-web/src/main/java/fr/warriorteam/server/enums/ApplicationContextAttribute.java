package fr.warriorteam.server.enums;

public enum ApplicationContextAttribute {

	PROPERTIES_OK("properties"), PROPERTIES_RETIREE("propertiesRetiree"), PROPERTIES_ERROR(
			"propertiesError");

	private String value;

	private ApplicationContextAttribute(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value;
	}
}
