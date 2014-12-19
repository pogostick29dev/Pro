package me.pogostick29dev.pro.parser;

import me.pogostick29dev.pro.block.Block;
import me.pogostick29dev.pro.block.Class;
import me.pogostick29dev.pro.tokenizer.Tokenizer;

public class ClassParser extends Parser<Class> {

	@Override
	public boolean shouldParse(String line) {
		return line.matches("class [a-zA-Z][a-zA-Z0-9]*");
	}

	@Override
	public Class parse(Block superBlock, Tokenizer tokenizer) {
		tokenizer.nextToken(); // Skip the class token.
		
		String name = tokenizer.nextToken().getToken(); // Get the string value of the next token.
		
		return new Class(name);
	}
}