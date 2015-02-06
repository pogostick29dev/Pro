package me.pogostick29dev.pro;

import java.util.ArrayList;

import me.pogostick29dev.pro.block.Block;
import me.pogostick29dev.pro.block.Class;
import me.pogostick29dev.pro.block.Method;
import me.pogostick29dev.pro.block.VariableBlock;
import me.pogostick29dev.pro.parser.ClassParser;
import me.pogostick29dev.pro.parser.MethodParser;
import me.pogostick29dev.pro.parser.Parser;
import me.pogostick29dev.pro.parser.VariableParser;
import me.pogostick29dev.pro.tokenizer.Tokenizer;

public class Runtime {
	
	private ArrayList<Class> classes;
	
	public Runtime() {
		this.classes = new ArrayList<Class>();
		
		String code = "class Variables" + "\n" +
						"method main requires () returns void" + "\n" +
							"var string str = \"Hello\"" + "\n" +
						"method printString requires (string str) returns void";

		Parser<?>[] parsers = new Parser<?>[] { new ClassParser(), new MethodParser(), new VariableParser() };

		Class main = null;
		
		Block block = null;
		
		boolean success = false;
		
		for (String line : code.split("\n")) {
			success = false;
			line = line.trim();
			Tokenizer tokenizer = new Tokenizer(line);
			
			for (Parser<?> parser : parsers) {
				if (parser.shouldParse(line)) {
					Block newBlock = parser.parse(block, tokenizer);
					
					if (newBlock instanceof Class) {
						classes.add((Class) newBlock);
					}
					
					else if (newBlock instanceof Method) {
						block.getBlockTree().get(0).addBlock(newBlock);
					}
					
					else {
						block.addBlock(newBlock);
					}
					
					block = newBlock;
					success = true;
					break;
				}
			}
			
			if (!success) {
				throw new IllegalArgumentException("Invalid line " + line);
			}
		}
		
		for (Class c : classes) {
			for (Block b : c.getSubBlocks()) {
				if (b instanceof Method) {
					Method method = (Method) b;
					if (method.getName().equals("main") && method.getType().equals("void") && method.getParameters().length == 0) {
						main = c;
					}
				}
			}
		}
		
		if (main == null) {
			throw new IllegalStateException("No main method.");
		}
		
		main.run();
	}

	public static void main(String[] args) {
		new Runtime();
	}
}