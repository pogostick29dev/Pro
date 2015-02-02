package me.pogostick29dev.pro.parser;

import java.util.ArrayList;

import me.pogostick29dev.pro.Parameter;
import me.pogostick29dev.pro.BuiltInType;
import me.pogostick29dev.pro.block.Block;
import me.pogostick29dev.pro.block.Method;
import me.pogostick29dev.pro.tokenizer.Token;
import me.pogostick29dev.pro.tokenizer.Tokenizer;

public class MethodParser extends Parser<Method> {

	@Override
	public boolean shouldParse(String line) {
		return line.matches("method [a-zA-Z][a-zA-Z0-9]* requires \\(([a-zA-Z][a-zA-Z0-9]* [a-zA-Z][a-zA-Z0-9]*)*\\) returns [a-zA-Z][a-zA-Z0-9]*");
	}

	@Override
	public Method parse(Block superBlock, Tokenizer tokenizer) {
		tokenizer.nextToken(); // Skip the method token.
		
		String name = tokenizer.nextToken().getToken();
		
		tokenizer.nextToken(); // Skip the requires token.
		tokenizer.nextToken(); // Skip the ( token.
		
		Token first = tokenizer.nextToken();
		
		ArrayList<Parameter> params = new ArrayList<>();
		
		if (!first.getToken().equals(")")) {
			String[] paramData = new String[] { first.getToken(), null }; // 0 = type, 1 = value
			
			while (tokenizer.hasNextToken()) {
				Token token = tokenizer.nextToken();
				
				if (token.getToken().equals(")")) {
					break;
				}
				
				if (paramData[0] == null) {
					paramData[0] = token.getToken();
				}
				
				else {
					paramData[1] = token.getToken();
					
					params.add(new Parameter(BuiltInType.valueOf(paramData[0].toUpperCase()), paramData[1]));
					
					paramData = new String[2];
				}
			}
		}
		
		tokenizer.nextToken(); // Skip the returns token.
		
		String returnType = tokenizer.nextToken().getToken();
		
		return new Method(superBlock, name, returnType, params.toArray(new Parameter[params.size()]));
	}
}