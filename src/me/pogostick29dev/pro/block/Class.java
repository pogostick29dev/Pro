package me.pogostick29dev.pro.block;

import me.pogostick29dev.pro.Type;

/**
 * Represents a class.
 */
public class Class extends Block implements Type {
	
	private String name;

	public Class(String name) {
		super(null);
		
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public void run() {
		// Run main method.
	}
}