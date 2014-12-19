package me.pogostick29dev.pro;

import me.pogostick29dev.pro.block.Block;

public class Variable extends Value {

	private Block block;
	private String name;
	
	public Variable(Block block, Type type, String name, Object value) {
		super(type, value);
		
		this.block = block;
		this.name = name;
	}
	
	public Block getBlock() {
		return block;
	}
	
	public String getName() {
		return name;
	}
}