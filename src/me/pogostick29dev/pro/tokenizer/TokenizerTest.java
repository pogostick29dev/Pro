package me.pogostick29dev.pro.tokenizer;

public class TokenizerTest {

	public static void main(String[] args) {
		String code =
				"var string str = \"Hello\""
		;
		
		Tokenizer tokenizer = new Tokenizer(code);
		
		while (tokenizer.hasNextToken()) {
			Token t = tokenizer.nextToken();
			System.out.println(t.getType() + " " + t.getToken());
		}
	}
}