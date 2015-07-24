package com.likecodes.convert.impl;


public class Param {


	public String getOption() {
		return this.option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	private String  option;


	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private String value;

	public Param(String option, String value) {
		this.option = option;
		this.value = value;
	}

	public Param(String option) {
		this(option, null);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder().append(Symbol.separator).append(Symbol.param).append(option);
		if (value != null)
			sb.append(Symbol.separator).append(value);
		return sb.toString();
	}

}
