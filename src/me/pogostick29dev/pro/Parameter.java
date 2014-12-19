package me.pogostick29dev.pro;

public class Parameter {

	private String name;
	private Type type;
	
	public Parameter(Type type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Type getType() {
		return type;
	}
}