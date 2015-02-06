package me.pogostick29dev.pro.parser;

import me.pogostick29dev.pro.block.Block;
import me.pogostick29dev.pro.block.VariableBlock;
import me.pogostick29dev.pro.tokenizer.Token;
import me.pogostick29dev.pro.tokenizer.TokenType;
import me.pogostick29dev.pro.tokenizer.Tokenizer;

public class VariableParser extends Parser<Block> {

	@Override
	public boolean shouldParse(String line) {
		return line.matches("var [a-zA-Z]+ [a-zA-Z]+ = (\")?[a-zA-Z0-9]*(\")?");
	}

	@Override
	public Block parse(Block superBlock, Tokenizer tokenizer) {
		tokenizer.nextToken(); // Skip the var token.
		
		String type = tokenizer.nextToken().getToken();
		
		String name = tokenizer.nextToken().getToken();
		
		tokenizer.nextToken(); // Skip the = token.
		
		Token v = tokenizer.nextToken();
		Object value = null;
		
		if (v.getType() == TokenType.INTEGER_LITERAL) {
			value = Integer.valueOf(v.getToken());
		}
		
		else if (v.getType() == TokenType.STRING_LITERAL) {
			value = v.getToken();
		}
		
		else /* If it's an identifier */ {
			value = superBlock.getVariable(v.getToken()).getValue();
		}
		
		return new VariableBlock(superBlock, type, name, value);
	}
}